package com.amazon.ata.types;

import java.math.BigDecimal;
import java.util.Objects;

public class Box {
    BigDecimal length;
    BigDecimal width;
    BigDecimal height;
    Material material;


    /**
     * Instantiates a new Packaging object.
     *
     * @param material - the Material of the package
     * @param length   - the length of the package
     * @param width    - the width of the package
     * @param height   - the height of the package
     */
    public Box(Material material , BigDecimal length , BigDecimal width , BigDecimal height) {
        this.material = material;
        this.length = length;
        this.width = width;
        this.height = height;
    }


}
