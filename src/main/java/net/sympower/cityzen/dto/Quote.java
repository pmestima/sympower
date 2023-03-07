package net.sympower.cityzen.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Quote {
    private String market;

    @JsonProperty("date_applied")
    private String dateApplied;

    @JsonProperty("values")
    private List<QuoteValue> quoteValues;
}
