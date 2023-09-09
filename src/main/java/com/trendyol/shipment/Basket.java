package com.trendyol.shipment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {

    private List<Product> products;
    private final int SHIPMENT_SIZE_THRESHOLD = 3;

    public ShipmentSize getShipmentSize() throws IllegalAccessException {
        //TO DO: Strategy design pattern can be used ?

        if(products.size() == 0){
            throw new IllegalAccessException("There is nothing in basket to get shipment size!");
        }

        Map<ShipmentSize, Integer> shipmentSizeCountMap = new HashMap<>();

        initializeShipmentSizeCountMap(shipmentSizeCountMap);

        for (ShipmentSize shipmentSize : shipmentSizeCountMap.keySet()){
            if(shipmentSizeCountMap.get(shipmentSize) >= SHIPMENT_SIZE_THRESHOLD){
                return getUpperShipmentSize(shipmentSize);
            }
        }
        return getLargestShipmentSize(shipmentSizeCountMap);
    }

    private ShipmentSize getLargestShipmentSize(Map<ShipmentSize, Integer> shipmentSizeCountMap) {
        ShipmentSize largestSize = ShipmentSize.SMALL;

        for(ShipmentSize shipmentSize : shipmentSizeCountMap.keySet()){
            if(shipmentSizeCountMap.get(shipmentSize) > 0 && getShipmentSizeOrder(shipmentSize) > getShipmentSizeOrder(largestSize)){
                largestSize = shipmentSize;
            }
        }
        return largestSize;
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
                throw new IllegalArgumentException("Unrecognized shipment size!");
        }
    }

    private void initializeShipmentSizeCountMap(Map<ShipmentSize, Integer> shipmentSizeCountMap){
        //TO DO: It could be initialized once and update ?
        for(Product product : products){
            ShipmentSize currentProductSize = product.getSize();
            if(shipmentSizeCountMap.containsKey(currentProductSize)){
                int currentProductSizeCount = shipmentSizeCountMap.get(currentProductSize);
                shipmentSizeCountMap.put(currentProductSize , currentProductSizeCount +1);
            }
            else{
                shipmentSizeCountMap.put(currentProductSize, 1);
            }
        }
    }

    private ShipmentSize getUpperShipmentSize(ShipmentSize size){
        switch (size){
            case SMALL:
                return ShipmentSize.MEDIUM;
            case MEDIUM:
                return ShipmentSize.LARGE;
            case LARGE:
            case X_LARGE:
                return  ShipmentSize.X_LARGE;
            default:
                throw new IllegalArgumentException("Unrecognized shipment size!");
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
