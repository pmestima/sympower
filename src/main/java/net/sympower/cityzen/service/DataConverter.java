package net.sympower.cityzen.service;

import net.sympower.cityzen.dto.Quote;
import net.sympower.cityzen.dto.QuoteResponse;
import net.sympower.cityzen.entity.MarketEntity;
import net.sympower.cityzen.entity.ValueEntity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class DataConverter {

    public List<MarketEntity> convertData(QuoteResponse quoteDto) {
        return quoteDto.getQuote()
            .stream()
            .map(this::createMarket)
            .collect(Collectors.toList());
    }

    private MarketEntity createMarket(Quote quote) {
        Instant instant = Instant.ofEpochMilli(Long.parseLong(quote.getDateApplied()));
        LocalDateTime triggerTime = LocalDateTime.ofInstant(instant, TimeZone.getDefault().toZoneId());

        MarketEntity.MarketEntityBuilder marketBuilder = MarketEntity.builder()
            .date(triggerTime)
            .market(quote.getMarket());

        // Not sure about the complexity of the received data, but another solution could be a Factory that would return a specific
        // bean for each tLabel with the business logic required to parse this objects
        quote.getQuoteValues()
            .forEach(quoteValue -> {
                switch (quoteValue.getTLabel()) {
                    case "Price" -> marketBuilder.price(new ValueEntity(quoteValue.getUnit(), Double.parseDouble(quoteValue.getValue())));
                    case "Net Volume" -> marketBuilder.netVolume(new ValueEntity(quoteValue.getUnit(), Double.parseDouble(quoteValue.getValue())));
                    case "Hour" -> marketBuilder.hour(Integer.parseInt(quoteValue.getValue()));
                    case "Order" -> marketBuilder.orderValue(quoteValue.getValue());
                }
            });

        return marketBuilder.build();
    }
}
