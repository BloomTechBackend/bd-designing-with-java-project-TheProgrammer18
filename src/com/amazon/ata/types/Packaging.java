package com.amazon.ata.types;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a packaging option.
 *
 * This packaging supports standard boxes, having a length, width, and height.
 * Items can fit in the packaging so long as their dimensions are all smaller than
 * the packaging's dimensions.
 */
public class Packaging {

    BigDecimal length1;
    BigDecimal height1;
    BigDecimal width1;
    private Material material;

    /**
     * Instantiates a new Packaging object.
     * @param material - the Material of the package
     * @param length1 - the length of the package
     * @param width1 - the width of the package
     * @param height1 - the height of the package
     */
    public Packaging(Material material, BigDecimal length1, BigDecimal width1, BigDecimal height1) {
        this.material = material;
        this.length1 = length1;
        this.width1 = width1;
        this.height1 = height1;
    }

    public Material getMaterial() {
        return material;
    }


    /**
     * Returns whether the given item will fit in this packaging.
     *
     * @param item the item to test fit for
     * @return whether the item will fit in this packaging
     */
    public boolean canFitItem(Item item) {
        return this.length1.compareTo(item.getLength()) > 0 &&
                this.width1.compareTo(item.getWidth()) > 0 &&
                this.height1.compareTo(item.getHeight()) > 0;
    }

    /**
     * Returns the mass of the packaging in grams. The packaging weighs 1 gram per square centimeter.
     * @return the mass of the packaging
     */
    public BigDecimal getMass() {
        BigDecimal two = BigDecimal.valueOf(2);

        // For simplicity, we ignore overlapping flaps
        BigDecimal endsArea = length1.multiply(width1).multiply(two);
        BigDecimal shortSidesArea = length1.multiply(height1).multiply(two);
        BigDecimal longSidesArea = width1.multiply(height1).multiply(two);

        return endsArea.add(shortSidesArea).add(longSidesArea);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Packaging packaging = (Packaging) o;
        return material == packaging.material && length1.equals(packaging.length1) &&
                height1.equals(packaging.height1) && width1.equals(packaging.width1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(material , length1 , height1 , width1);
    }
}
