import com.trendyol.shipment.ShipmentSize;
import com.trendyol.shipment.sizestrategy.ShipmentSizeStrategyUtils;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

public class ShipmentSizeStrategyUtilsTest {

    private Map<ShipmentSize, Integer> shipmentSizeCountMap;
    @BeforeEach
    public void init(){
        shipmentSizeCountMap = new HashMap<>();
        for(ShipmentSize shipmentSize : ShipmentSize.values()){
            shipmentSizeCountMap.put(shipmentSize, 1);
        }
    }

    @Test
    public void getLargestShipmentSizeTestShouldReturnXLarge(){
        Assertions.assertEquals(ShipmentSize.X_LARGE, ShipmentSizeStrategyUtils.getLargestShipmentSize(shipmentSizeCountMap));
    }

    @Test
    public void getLargestShipmentSizeTestShouldReturnLarge(){
        shipmentSizeCountMap.put(ShipmentSize.X_LARGE, 0);
        Assertions.assertEquals(ShipmentSize.LARGE, ShipmentSizeStrategyUtils.getLargestShipmentSize(shipmentSizeCountMap));
    }

    @Test
    public void getLargestShipmentSizeTestShouldReturnMedium(){
        shipmentSizeCountMap.put(ShipmentSize.LARGE, 0);
        shipmentSizeCountMap.put(ShipmentSize.X_LARGE, 0);
        Assertions.assertEquals(ShipmentSize.MEDIUM, ShipmentSizeStrategyUtils.getLargestShipmentSize(shipmentSizeCountMap));
    }

    @Test
    public void getLargestShipmentSizeTestShouldReturnSmall(){
        shipmentSizeCountMap.put(ShipmentSize.MEDIUM, 0);
        shipmentSizeCountMap.put(ShipmentSize.LARGE, 0);
        shipmentSizeCountMap.put(ShipmentSize.X_LARGE, 0);
        Assertions.assertEquals(ShipmentSize.SMALL, ShipmentSizeStrategyUtils.getLargestShipmentSize(shipmentSizeCountMap));
    }

}
