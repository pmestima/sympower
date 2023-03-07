package net.sympower.cityzen.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuoteValue {
    @JsonProperty("tLabel")
    private String tLabel;
    private String value;
    private String unit;
}


