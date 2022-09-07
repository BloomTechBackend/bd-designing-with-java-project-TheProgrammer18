package com.amazon.ata.types;

import java.math.BigDecimal;

public class PolyBag extends Packaging{
    private BigDecimal volume;

    /**
     * Instantiates a new Packaging object.
     *
     * @param material - the Material of the package
     */
    public PolyBag(Material material, BigDecimal volume) {
        super(material);
        this.volume = volume;
    }

    @Override
    public boolean canFitItem(Item item) {
        return super.canFitItem(item);
    }

    public BigDecimal getMass() {
        return BigDecimal.valueOf(Math.ceil(Math.sqrt(volume.doubleValue())));
    }

    public BigDecimal getVolume() {
        return volume;
    }
}
