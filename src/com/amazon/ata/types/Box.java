package com.amazon.ata.types;

import java.math.BigDecimal;

public class Box extends Packaging {
    BigDecimal length;
    BigDecimal width;
    BigDecimal height;
    Material material;

    /**
     * Instantiates a new Packaging object.
     * @param material - the material of the package
     * @param length1   - the length of the package
     * @param width1    - the width of the package
     * @param height1  - the height of the package
     */
    public Box(Material material, BigDecimal length1 , BigDecimal width1 , BigDecimal height1) {
        super(material, length1, width1, height1);
        this.material = material;
        this.length1 = length1;
        this.width1 = width1;
        this.height1 = height1;
    }

}
