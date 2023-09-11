package com.trendyol.shipment.sizestrategy;

import com.trendyol.shipment.ShipmentSize;

import java.util.Map;
import java.util.Optional;


public class SameSizeMoreThanThresholdShipmentSizeStrategy implements ShipmentSizeStrategy{
    private Map<ShipmentSize, Integer> shipmentSizeCountMap;
    private int SHIPMENT_SIZE_THRESHOLD;

    public SameSizeMoreThanThresholdShipmentSizeStrategy(Map<ShipmentSize, Integer> shipmentSizeCountMap, int SHIPMENT_SIZE_THRESHOLD){
        this.shipmentSizeCountMap = shipmentSizeCountMap;
        this.SHIPMENT_SIZE_THRESHOLD = SHIPMENT_SIZE_THRESHOLD;
    }

    @Override
    public Optional<ShipmentSize> getShipmentSize() {
        Optional<ShipmentSize> shipmentSizeMoreThanThreshold = shipmentSizeCountMap.keySet().stream()
                .filter(entry -> shipmentSizeCountMap.get(entry) >= SHIPMENT_SIZE_THRESHOLD)
                .findFirst();

        if(shipmentSizeMoreThanThreshold.isPresent())
            return Optional.of(getUpperShipmentSize(shipmentSizeMoreThanThreshold.get()));

        return Optional.empty();
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

}