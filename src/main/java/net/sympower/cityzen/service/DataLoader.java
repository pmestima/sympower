package net.sympower.cityzen.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sympower.cityzen.dto.QuoteResponse;

import java.io.IOException;
import java.net.URL;

public class DataLoader {

    public QuoteResponse loadData(URL url) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(url, QuoteResponse.class);
    }
}
