package Models;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<Integer, Rack> rack;

    public Inventory(Integer rackCount, Integer maxUnitPerRack) {
        if (rackCount <= 0) {
            throw new IllegalArgumentException("RackCount should be greater than 0");
        }
        rack = new HashMap<>();
        for (int i = 1; i <= rackCount; i++) {
            rack.put(i, new Rack(i, maxUnitPerRack));
        }
    }

    public int rackCount() {
        return rack.size();
    }

    public Rack getRack(int rackNum) {
        return rack.get(rackNum);
    }

}
