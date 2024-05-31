package jdkSeven;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

public class JDK7ConcurrentHashMap {
    public static void main(String[] args) {
        Unsafe unsafe = Person.getUnsafe();
        int si = unsafe.arrayIndexScale(String[].class);
        int offset = unsafe.arrayBaseOffset(String[].class);
        System.out.println(unsafe.getObjectVolatile(Person.names, offset + si * 1));


        //
        ConcurrentHashMap<String,String> cHashMap = new ConcurrentHashMap<String, String> ();
        cHashMap.put("biozzo", "nice~");
    }

    static class Person{
        private static int i = 0;
        static Field f;
        static String[] names = new String[]{"biozzo","kevin","can"};

        private static  Unsafe unsafe;

        static {
            try {
                f = Unsafe.class.getDeclaredField("theUnsafe");
                f.setAccessible(true);
                unsafe = (Unsafe) f.get(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        static Unsafe getUnsafe(){
            return unsafe;
        }
    }
}