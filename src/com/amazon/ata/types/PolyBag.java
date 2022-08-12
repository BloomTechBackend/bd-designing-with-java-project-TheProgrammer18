package com.amazon.ata.types;


import java.math.BigDecimal;

public class PolyBag extends Packaging {
    private BigDecimal mass;

    /**
     * Instantiates a new Packaging object.
     *
     * @param material - the Material of the package
     * @param length   - the length of the package
     * @param width    - the width of the package
     * @param height   - the height of the package
     */
    public PolyBag(Material material , BigDecimal length , BigDecimal width , BigDecimal height) {
        super(material , length , width , height);
        double doubleValueOfLength = length.doubleValue();
        double doubleValueOfWidth = width.doubleValue();
        double doubleValueOfHeight = height.doubleValue();
        double volume = doubleValueOfLength * doubleValueOfWidth * doubleValueOfHeight;
        mass = BigDecimal.valueOf(Math.ceil(Math.sqrt(volume) * 0.6));
    }
}
