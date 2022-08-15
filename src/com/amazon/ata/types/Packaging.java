package com.amazon.ata.types;

import java.math.BigDecimal;

/**
 * Represents a packaging option.
 *
 * This packaging supports standard boxes, having a length, width, and height.
 * Items can fit in the packaging so long as their dimensions are all smaller than
 * the packaging's dimensions.
 */
public class Packaging extends Box {

    private Material material;

    /**
     * Instantiates a new Packaging object.
     * @param material - the Material of the package
     * @param length - the length of the package
     * @param width - the width of the package
     * @param height - the height of the package
     */
    public Packaging(Material material, BigDecimal length, BigDecimal width, BigDecimal height) {
        super(length, width, height);
        this.material = material;
        this.length = length;
        this.width = width;
        this.height = height;
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
        return this.length.compareTo(item.getLength()) > 0 &&
                this.width.compareTo(item.getWidth()) > 0 &&
                this.height.compareTo(item.getHeight()) > 0;
    }

    /**
     * Returns the mass of the packaging in grams. The packaging weighs 1 gram per square centimeter.
     * @return the mass of the packaging
     */
    public BigDecimal getMass() {
        BigDecimal two = BigDecimal.valueOf(2);

        // For simplicity, we ignore overlapping flaps
        BigDecimal endsArea = length.multiply(width).multiply(two);
        BigDecimal shortSidesArea = length.multiply(height).multiply(two);
        BigDecimal longSidesArea = width.multiply(height).multiply(two);

        return endsArea.add(shortSidesArea).add(longSidesArea);
    }


}
