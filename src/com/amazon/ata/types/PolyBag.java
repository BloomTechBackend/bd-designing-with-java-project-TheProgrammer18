package com.amazon.ata.types;

import java.math.BigDecimal;

public class PolyBag {
    BigDecimal mass1;

    /**
     * Instantiates a new Packaging object.
     *
     * @param material - the Material of the package
     * @param length   - the length of the package
     * @param width    - the width of the package
     * @param height   - the height of the package
     */
    public PolyBag(Material material , BigDecimal length , BigDecimal width , BigDecimal height) {
        double doubleValueOfLength = length.doubleValue();
        double doubleValueOfWidth = width.doubleValue();
        double doubleValueOfHeight = height.doubleValue();
        double volume = doubleValueOfLength * doubleValueOfWidth * doubleValueOfHeight;
        mass1 = BigDecimal.valueOf(Math.ceil(Math.sqrt(volume) * 0.6));
    }

    public BigDecimal getMassOfPolyBag() {
        return mass1;
    }
}
