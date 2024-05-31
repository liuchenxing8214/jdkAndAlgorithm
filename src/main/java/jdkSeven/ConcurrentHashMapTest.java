package jdkSeven;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put("1", 1);
        map.put("2", 2);
        map.put("数据挖掘", 4);
        map.put("产品经理", 5);
        map.size();


        Map hashMap = new HashMap<>();
        hashMap.put("key01","value01");
    }
}
