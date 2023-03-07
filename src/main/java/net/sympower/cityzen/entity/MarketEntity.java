package net.sympower.cityzen.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class MarketEntity {
    private final String market;
    private final LocalDateTime date;
    private final String orderValue;
    private final int hour;
    private final ValueEntity netVolume;
    private final ValueEntity price;
}
