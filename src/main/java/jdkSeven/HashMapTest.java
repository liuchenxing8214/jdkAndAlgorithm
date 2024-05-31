package jdkSeven;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args) {
/*        HashMap map = new HashMap();
        map.put("ab", "ab");
        map.get("ab");


        //Integer.highestOneBit((number - 1) << 1)
        System.out.println(Integer.highestOneBit(10));
        System.out.println(Integer.highestOneBit(16));
        System.out.println(Integer.highestOneBit(6));*/

        /**
         * 1. 声明1个 HashMap的对象
         */
        Map<String, Integer> map1 = new HashMap<String, Integer>();

        /**
         * 2. 向HashMap添加数据（成对 放入 键 - 值对）
         */    map1.put("1", 1);
        map1.put("2", 2);

  /*      map1.put("iOS", 3);
        map1.put("数据挖掘", 4);
        map1.put("产品经理", 5);
        */
        for (String a : map1.keySet()) {
            if (a.equals("2")) {
                map1.remove(a);
            }
        }

    }
}
