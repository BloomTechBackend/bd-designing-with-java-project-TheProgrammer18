package com.amazon.ata.datastore;

import com.amazon.ata.types.FcPackagingOption;
import com.amazon.ata.types.FulfillmentCenter;
import com.amazon.ata.types.Material;
import com.amazon.ata.types.Packaging;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Stores all configured packaging pairs for all fulfillment centers.
 */
public class PackagingDatastore {

    /**
     * The stored pairs of fulfillment centers to the packaging options they support.
     */
    private final List<FcPackagingOption> fcPackagingOptions = Arrays.asList(
            createFcPackagingOption("IND1", "10", "10", "10"),
            createFcPackagingOption("ABE2", "20", "20", "20"),
            createFcPackagingOption("ABE2", "40", "40", "40"),
            createFcPackagingOption("YOW4", "10", "10", "10"),
            createFcPackagingOption("YOW4", "20", "20", "20"),
            createFcPackagingOption("YOW4", "60", "60", "60"),
            createFcPackagingOption("IAD2", "20", "20", "20"),
            createFcPackagingOption("IAD2", "20", "20", "20"),
            createFcPackagingOption("PDX1", "40", "40", "40"),
            createFcPackagingOption("PDX1", "60", "60", "60"),
            createFcPackagingOption("PDX1", "60", "60", "60")
    );

    /**
     * Create fulfillment center packaging option from provided parameters.
     */
    private FcPackagingOption createFcPackagingOption(String fcCode,
                                                      String length, String width, String height) {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter(fcCode);
        Packaging packaging = new Packaging(Material.CORRUGATE , new BigDecimal(length), new BigDecimal(width),
                new BigDecimal(height));

        return new FcPackagingOption(fulfillmentCenter, packaging);
    }

    public List<FcPackagingOption> getFcPackagingOptions() {
        return fcPackagingOptions;
    }
}
