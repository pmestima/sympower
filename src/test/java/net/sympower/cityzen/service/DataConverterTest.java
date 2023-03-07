package net.sympower.cityzen.service;

import net.sympower.cityzen.dto.Quote;
import net.sympower.cityzen.dto.QuoteResponse;
import net.sympower.cityzen.dto.QuoteValue;
import net.sympower.cityzen.entity.MarketEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DataConverterTest {

    @InjectMocks
    private DataConverter dataConverter;

    @Test
    void loadDataTest() {
        List<QuoteValue> quoteValues = new ArrayList<>();
        quoteValues.add(new QuoteValue("Hour", "01", "none"));
        quoteValues.add(new QuoteValue("Order", "1", "none"));
        quoteValues.add(new QuoteValue("Net Volume", "4564.21", "MWh"));
        quoteValues.add(new QuoteValue("Price", "34", "Euro/MWh"));

        Quote quote = new Quote("market1", "1573599600000", quoteValues);

        QuoteResponse quoteResponse = new QuoteResponse(Collections.singletonList(quote));
        List<MarketEntity> marketEntities = dataConverter.convertData(quoteResponse);

        assertEquals(1, marketEntities.size());
        MarketEntity marketEntity = marketEntities.get(0);
        assertEquals("market1", marketEntity.getMarket());
        assertEquals("2019-11-13T00:00", marketEntity.getDate().toString());
        assertEquals("1", marketEntity.getOrderValue());
        assertEquals(1, marketEntity.getHour());
        assertEquals("Euro/MWh", marketEntity.getPrice().getUnit());
        assertEquals(34, marketEntity.getPrice().getValue());
        assertEquals("MWh", marketEntity.getNetVolume().getUnit());
        assertEquals(4564.21D, marketEntity.getNetVolume().getValue());
    }

}
