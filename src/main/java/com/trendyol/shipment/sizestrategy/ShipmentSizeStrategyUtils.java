package com.trendyol.shipment.sizestrategy;

import com.trendyol.shipment.ShipmentSize;

import java.util.Comparator;
import java.util.Map;

public class ShipmentSizeStrategyUtils {

    public static ShipmentSize getLargestShipmentSize(Map<ShipmentSize, Integer> shipmentSizeCountMap) {
        return shipmentSizeCountMap.keySet().stream()
                .filter(shipmentSize -> shipmentSizeCountMap.get(shipmentSize) > 0)
                .max(Comparator.comparingInt(ShipmentSizeStrategyUtils::getShipmentSizeOrder))
                .orElse(ShipmentSize.SMALL);
    }

    private static int getShipmentSizeOrder(ShipmentSize shipmentSize) {
        switch (shipmentSize) {
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

    private ShipmentSizeStrategyUtils() {

    }
}