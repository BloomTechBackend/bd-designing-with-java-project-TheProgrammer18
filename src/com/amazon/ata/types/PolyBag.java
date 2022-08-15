package com.amazon.ata.types;

import java.math.BigDecimal;

public class PolyBag extends Packaging {
    Material material;
    BigDecimal length;
    BigDecimal width;
    BigDecimal height;

    /**
     * Instantiates a new Packaging object.
     *
     * @param material - the Material of the package
     * @param length1  - the length of the package
     * @param width1    - the width of the package
     * @param height1   - the height of the package
     */
    public PolyBag(Material material , BigDecimal length1 , BigDecimal width1 , BigDecimal height1) {
        super(material, length1, width1, height1);
        this.material = material;
        this.length = length1;
        this.width = width1;
        this.height = height1;
    }
}
