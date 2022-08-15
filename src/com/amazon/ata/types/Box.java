package com.amazon.ata.types;

import java.math.BigDecimal;
import java.util.Objects;

public class Box {
    BigDecimal length;
    BigDecimal width;
    BigDecimal height;

    /**
     * Instantiates a new Packaging object.
     *
     * @param length   - the length of the package
     * @param width    - the width of the package
     * @param height   - the height of the package
     */
    public Box(BigDecimal length , BigDecimal width , BigDecimal height) {

        this.length = length;
        this.width = width;
        this.height = height;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Box box = (Box) o;
        return length.equals(box.length) && width.equals(box.width) && height.equals(box.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(length , width , height);
    }
}
