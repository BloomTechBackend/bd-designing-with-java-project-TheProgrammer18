package com.amazon.ata.cost;

import com.amazon.ata.types.*;
import com.amazonaws.services.dynamodbv2.xspec.B;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MonetaryCostStrategyTest {

    private static final Packaging BOX_10x10x20 =
            new Box(Material.CORRUGATE,BigDecimal.TEN,BigDecimal.TEN, BigDecimal.valueOf(20));

    private MonetaryCostStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new MonetaryCostStrategy();
    }

    @Test
    void getCost_corrugateMaterial_returnsCorrectCost() {
        // GIVEN
        ShipmentOption option = ShipmentOption.builder()
                .withPackaging(BOX_10x10x20)
                .build();

        // WHEN
        ShipmentCost shipmentCost = strategy.getCost(option);
        BigDecimal name = BigDecimal.valueOf(5.43);
        double came = name.doubleValue();
        // THEN
        assertTrue(BigDecimal.valueOf(5.43).compareTo(shipmentCost.getCost()) == 0,
                shipmentCost.getCost() + "Incorrect monetary cost calculation for a box with dimensions 10x10x20." + came);
    }
}
