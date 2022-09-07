package com.amazon.ata.dao;

import com.amazon.ata.datastore.PackagingDatastore;
import com.amazon.ata.exceptions.NoPackagingFitsItemException;
import com.amazon.ata.exceptions.UnknownFulfillmentCenterException;
import com.amazon.ata.types.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * Access data for which packaging is available at which fulfillment center.
 */
public class PackagingDAO {
    /**
     * A list of fulfillment centers with a packaging options they provide.
     */
    private Map<FulfillmentCenter, Set<FcPackagingOption>> fcPackagingOptions1;

    /**
     * Instantiates a PackagingDAO object.
     * @param datastore Where to pull the data from for fulfillment center/packaging available mappings.
     */
    public PackagingDAO(PackagingDatastore datastore) {
        Set<FcPackagingOption> tempPackaging = new HashSet<>(datastore.getFcPackagingOptions());
        this.fcPackagingOptions1 = new HashMap<>();
        for (FcPackagingOption fcPackagingOption : datastore.getFcPackagingOptions()) {
            fcPackagingOptions1.put(fcPackagingOption.getFulfillmentCenter(), tempPackaging);
        }
//        HashSet<FcPackagingOption> tempSet = new HashSet<>();
//        tempSet.addAll(datastore.getFcPackagingOptions());
//        for (int i = 0; i < datastore.getFcPackagingOptions().size(); i++) {
//            fcPackagingOptions1.put(datastore.getFcPackagingOptions().get(i).getFulfillmentCenter(),tempSet);
//        }
    }

    /**
     * Returns the packaging options available for a given item at the specified fulfillment center. The API
     * used to call this method handles null inputs, so we don't have to.
     *
     * @param item the item to pack
     * @param fulfillmentCenter fulfillment center to fulfill the order from
     * @return the shipping options available for that item; this can never be empty, because if there is no
     * acceptable option an exception will be thrown
     * @throws UnknownFulfillmentCenterException if the fulfillmentCenter is not in the fcPackagingOptions list
     * @throws NoPackagingFitsItemException if the item doesn't fit in any packaging at the FC
     */
    public List<ShipmentOption> findShipmentOptions(Item item, FulfillmentCenter fulfillmentCenter)
            throws UnknownFulfillmentCenterException, NoPackagingFitsItemException {

        // Check all FcPackagingOptions for a suitable Packaging in the given FulfillmentCenter
        List<ShipmentOption> result = new ArrayList<>();
        boolean fcFound = false;
        if (fcPackagingOptions1.get(fulfillmentCenter) == null) {
            throw new UnknownFulfillmentCenterException(
                    String.format("Unknown FC: %s!", fulfillmentCenter.getFcCode()));
        }
        for (FcPackagingOption fcPackagingOption : fcPackagingOptions1.get(fulfillmentCenter)) {
            Packaging packaging = fcPackagingOption.getPackaging();
            String fcCode = fcPackagingOption.getFulfillmentCenter().getFcCode();
            if (fcCode.equals(fulfillmentCenter.getFcCode())) {
                fcFound = true;
                if (packaging.canFitItem(item)) {
                    result.add(ShipmentOption.builder()
                            .withItem(item)
                            .withPackaging(packaging)
                            .withFulfillmentCenter(fulfillmentCenter)
                            .build());
                }
            }
        }

//        for (FcPackagingOption fcPackagingOption : fcPackagingOptions1.get(fulfillmentCenter)) {
//            Packaging packaging = fcPackagingOption.getPackaging();
//            String fcCode = fcPackagingOption.getFulfillmentCenter().getFcCode();
//
//            if (fcCode.equals(fulfillmentCenter.getFcCode())) {
//                fcFound = true;
//                if (packaging.canFitItem(item)) {
//                    result.add(ShipmentOption.builder()
//                            .withItem(item)
//                            .withPackaging(packaging)
//                            .withFulfillmentCenter(fulfillmentCenter)
//                            .build());
//                }
//            }
//        }

        // Notify caller about unexpected results
        if (!fcFound) {
            throw new UnknownFulfillmentCenterException(
                    String.format("Unknown FC: %s!", fulfillmentCenter.getFcCode()));
        }

        if (result.isEmpty()) {
            throw new NoPackagingFitsItemException(
                    String.format("No packaging at %s fits %s!", fulfillmentCenter.getFcCode(), item));
        }

        return duplicateCheck(result);
    }
    // added a duplicate checker method to find duplicate items using their FcCode and remove them
    public List<ShipmentOption> duplicateCheck(List<ShipmentOption> fcPackagingOptions) {
        if (fcPackagingOptions != null) {
            for (int i = 0; i < fcPackagingOptions.size() - 1; i++) {
                String fcCode1 = fcPackagingOptions.get(i).getFulfillmentCenter().getFcCode();
                String fcCode2 = fcPackagingOptions.get(i + 1).getFulfillmentCenter().getFcCode();


                if (fcCode1.equals(fcCode2)) {
                    fcPackagingOptions.remove(i);
                }
            }
        }
        return fcPackagingOptions;
    }



//    public PackagingDAO(PackagingDatastore datastore) {
//        List<FcPackagingOption> fcPackagingOptionsList =  new ArrayList<>(datastore.getFcPackagingOptions());
//        HashSet<FcPackagingOption> FcPackagingOptionsSet = new HashSet<>(fcPackagingOptionsList);
//        for (FcPackagingOption fcPackagingOption : FcPackagingOptionsSet) {
//            fcPackagingOptionsMap.put(fcPackagingOption.getFulfillmentCenter(), FcPackagingOptionsSet);
//        }
//        for (int i = 0; i < fcPackagingOptionsMap.size() + 1; i++) {
//            fcPackagingOptionsMap.put(fcPackagingOptionsList.get(i).getFulfillmentCenter(), FcPackagingOptionsSet);
//        }
//    }
}
