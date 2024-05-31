package jvm;

import java.util.WeakHashMap;

public class weakHashMapDemo {
    public static void main(String[] args) {
        WeakHashMap<String,String> weakHashMap = new WeakHashMap<>();
        String key1 = new String("key1");
        String key2 = new String("key2");
        String key3 = new String("key3");
        weakHashMap.put(key1, "value1");
        weakHashMap.put(key2, "value2");
        weakHashMap.put(key3, "value3");

        // 使没有任何强引用指向key1
        key1 = null;

        System.out.println("before gc weakHashMap = " + weakHashMap + " , size=" + weakHashMap.size());

        // 通知JVM的gc进行垃圾回收
        System.gc();
        System.out.println("after gc weakHashMap = " + weakHashMap + " , size="+ weakHashMap.size());
    }
}
