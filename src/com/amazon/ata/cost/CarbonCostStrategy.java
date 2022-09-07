package com.amazon.ata.cost;

import com.amazon.ata.types.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CarbonCostStrategy implements CostStrategy{
    private final Map<Material, BigDecimal> carbonCostPerGram;

    public CarbonCostStrategy() {
        carbonCostPerGram = new HashMap<>();
        carbonCostPerGram.put(Material.CORRUGATE, BigDecimal.valueOf(0.017));
        carbonCostPerGram.put(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(0.0073));
    }
    @Override
    public ShipmentCost getCost(ShipmentOption shipmentOption) {
        Packaging packaging = shipmentOption.getPackaging();
        BigDecimal materialCarbonCost = this.carbonCostPerGram.get(packaging.getMaterial());
        BigDecimal carbonCost = packaging.getMass().multiply(materialCarbonCost);
        return new ShipmentCost(shipmentOption, carbonCost);
    }
}
