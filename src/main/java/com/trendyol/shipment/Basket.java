package com.trendyol.shipment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Basket {

    private List<Product> products;
    private final int SHIPMENT_SIZE_THRESHOLD = 3;

    public Optional<ShipmentSize> getShipmentSize() {
        //TO DO: Strategy design pattern can be used ?
        if(products.size() == 0)
            return Optional.empty();

        Map<ShipmentSize, Integer> shipmentSizeCountMap = initializeShipmentSizeCountMap();

        Optional<ShipmentSize> shipmentSizeMoreThanThreshold = shipmentSizeCountMap.keySet().stream()
            .filter(entry -> shipmentSizeCountMap.get(entry) >= SHIPMENT_SIZE_THRESHOLD)
            .findFirst();

        if(shipmentSizeMoreThanThreshold.isPresent())
            return Optional.of(getUpperShipmentSize(shipmentSizeMoreThanThreshold.get()));

        return Optional.of(getLargestShipmentSize(shipmentSizeCountMap));
    }

    private ShipmentSize getLargestShipmentSize(Map<ShipmentSize, Integer> shipmentSizeCountMap) {
        return shipmentSizeCountMap.keySet().stream()
            .filter(shipmentSize -> shipmentSizeCountMap.get(shipmentSize) > 0)
            .max((shipmentSize1, shipmentSize2) -> 
                Integer.compare(getShipmentSizeOrder(shipmentSize1), getShipmentSizeOrder(shipmentSize2)))
            .orElse(ShipmentSize.SMALL);
    }

    private int getShipmentSizeOrder(ShipmentSize shipmentSize){
        switch (shipmentSize){
            case SMALL:
                return 0;
            case MEDIUM:
                return 1;
            case LARGE:
                return 2;
            case X_LARGE:
                return 3;
            default:
                throw new IllegalArgumentException("Unrecognized shipment size: " + shipmentSize);
        }
    }

    private Map<ShipmentSize, Integer> initializeShipmentSizeCountMap(){
        Map <ShipmentSize, Integer> shipmentSizeCountMap = new HashMap<>();

        for(ShipmentSize shipmentSize : ShipmentSize.values()){
            shipmentSizeCountMap.put(shipmentSize, 0);
        }

        for(Product product : products){
            int currentProductSizeCount = shipmentSizeCountMap.get(product.getSize());
            shipmentSizeCountMap.put(product.getSize() , currentProductSizeCount + 1);
        }

        return shipmentSizeCountMap;
    }

    private ShipmentSize getUpperShipmentSize(ShipmentSize shipmentSize){
        switch (shipmentSize){
            case SMALL:
                return ShipmentSize.MEDIUM;
            case MEDIUM:
                return ShipmentSize.LARGE;
            case LARGE:
            case X_LARGE:
                return  ShipmentSize.X_LARGE;
            default:
                throw new IllegalArgumentException("Unrecognized shipment size: " + shipmentSize);
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
