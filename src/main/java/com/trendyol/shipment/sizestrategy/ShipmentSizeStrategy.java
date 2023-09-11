package com.trendyol.shipment.sizestrategy;

import com.trendyol.shipment.ShipmentSize;

import java.util.Optional;

public interface ShipmentSizeStrategy {
    Optional<ShipmentSize> getShipmentSize();
}
