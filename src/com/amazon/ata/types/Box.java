package com.amazon.ata.types;

import java.math.BigDecimal;

public class Box extends Packaging{
    private BigDecimal endsArea;
    private BigDecimal shortSidesArea;
    private BigDecimal longSidesArea;
    private BigDecimal mass;
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
        endsArea = length.multiply(width.multiply(BigDecimal.valueOf(2)));
        shortSidesArea = length.multiply(height.multiply(BigDecimal.valueOf(2)));
        longSidesArea = width.multiply(height.multiply(BigDecimal.valueOf(2)));
        mass = endsArea.add(shortSidesArea.add(longSidesArea));
    }



}
