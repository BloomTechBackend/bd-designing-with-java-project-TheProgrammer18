package com.amazon.ata.types;

import java.math.BigDecimal;

public class Box extends Packaging {
    BigDecimal mass;
    private BigDecimal endsArea;
    private BigDecimal shortSidesArea;
    private BigDecimal longSidesArea;

    /**
     * Instantiates a new Packaging object.
     *
     * @param material - the Material of the package
     * @param length   - the length of the package
     * @param width    - the width of the package
     * @param height   - the height of the package
     */
    public Box(Material material , BigDecimal length , BigDecimal width , BigDecimal height) {
        super(material , length , width , height);
        BigDecimal two = BigDecimal.valueOf(2);
        endsArea = length.multiply(width.multiply(two));
        shortSidesArea = length.multiply(height.multiply(two));
        longSidesArea = width.multiply(height.multiply(two));
        mass = endsArea.add(shortSidesArea.add(longSidesArea));
    }



}
