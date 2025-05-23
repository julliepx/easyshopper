package com.extia.easyshopper.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductCategory {
    SMARTPHONES("Smartphones"),
    HARDWARE("Hardware"),
    TV("Televisions"),
    GAMES("Games"),
    CAMERAS("Cameras"),
    COMPUTERS("Computers"),
    DESK("Desk"),
    PERIPHERALS("Peripherals");

    private final String description;
}
