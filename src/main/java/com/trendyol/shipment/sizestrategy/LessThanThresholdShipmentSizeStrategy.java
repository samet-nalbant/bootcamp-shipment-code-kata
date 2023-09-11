package com.trendyol.shipment.sizestrategy;

import com.trendyol.shipment.ShipmentSize;

import java.util.Map;
import java.util.Optional;

public class LessThanThresholdShipmentSizeStrategy implements ShipmentSizeStrategy {
    private Map<ShipmentSize, Integer> shipmentSizeCountMap;
    public LessThanThresholdShipmentSizeStrategy(Map<ShipmentSize, Integer> shipmentSizeCountMap){
        this.shipmentSizeCountMap = shipmentSizeCountMap;
    }
    @Override
    public Optional<ShipmentSize> getShipmentSize() {
        return Optional.of(ShipmentSizeStrategyUtils.getLargestShipmentSize(shipmentSizeCountMap));
    }
}
