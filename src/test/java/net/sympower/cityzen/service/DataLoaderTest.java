package net.sympower.cityzen.service;

import net.sympower.cityzen.dto.Quote;
import net.sympower.cityzen.dto.QuoteResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DataLoaderTest {

    @InjectMocks
    private DataLoader dataLoader;

    @Test
    void loadDataTest() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("apx-data.json");

        QuoteResponse quoteResponse = dataLoader.loadData(resource);

        assertNotNull(quoteResponse);
        assertEquals(24, quoteResponse.getQuote().size());
        Quote quote = quoteResponse.getQuote().get(0);
        assertEquals("APX Power NL Hourly", quote.getMarket());
        assertEquals("1573599600000", quote.getDateApplied());
        assertEquals(4, quote.getQuoteValues().size());
    }
}
