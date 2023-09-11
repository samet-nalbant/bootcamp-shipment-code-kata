package com.trendyol.shipment.sizestrategy;

import com.trendyol.shipment.Product;
import com.trendyol.shipment.ShipmentSize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ShipmentSizeStrategyFactory {
    private static final int SHIPMENT_SIZE_THRESHOLD = 3;

    public static ShipmentSizeStrategy create(List<Product> products){

        Map<ShipmentSize, Integer> shipmentSizeCountMap = initializeShipmentSizeCountMap(products);

        int countOfTheProducts = shipmentSizeCountMap.values().stream().reduce(0, Integer::sum);

        if(countOfTheProducts < SHIPMENT_SIZE_THRESHOLD){
            return new LessThanThresholdShipmentSizeStrategy(shipmentSizeCountMap);
        }

        Optional<ShipmentSize> shipmentSizeMoreThanThreshold = shipmentSizeCountMap.keySet().stream()
                .filter(entry -> shipmentSizeCountMap.get(entry) >= SHIPMENT_SIZE_THRESHOLD)
                .findFirst();

        if(shipmentSizeMoreThanThreshold.isPresent())
            return new SameSizeMoreThanThresholdShipmentSizeStrategy(shipmentSizeCountMap, SHIPMENT_SIZE_THRESHOLD);

        else
            return new MoreThanThresholdShipmentSizeStrategy(shipmentSizeCountMap);
    }


    private static Map<ShipmentSize, Integer> initializeShipmentSizeCountMap(List<Product> products){
        Map <ShipmentSize, Integer> shipmentSizeCountMap = new HashMap<>();

        for(ShipmentSize shipmentSize : ShipmentSize.values()){
            shipmentSizeCountMap.put(shipmentSize, 0);
        }

        for(Product product : products){
            int currentProductSizeCount = shipmentSizeCountMap.get(product.getSize());
            shipmentSizeCountMap.put(product.getSize(), currentProductSizeCount + 1);
        }

        return shipmentSizeCountMap;
    }

    private ShipmentSizeStrategyFactory(){

    }


}
