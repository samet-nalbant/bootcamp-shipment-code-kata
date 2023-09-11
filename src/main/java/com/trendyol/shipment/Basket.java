package com.trendyol.shipment;

import com.trendyol.shipment.sizestrategy.ShipmentSizeStrategyFactory;
import java.util.List;
import java.util.Optional;

public class Basket {

    private List<Product> products;

    public Optional<ShipmentSize> getShipmentSize() {
        if(products.size() == 0)
            return Optional.empty();

        return ShipmentSizeStrategyFactory.create(products).getShipmentSize();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
