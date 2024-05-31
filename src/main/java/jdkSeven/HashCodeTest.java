/*
package jdkSeven;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

public class HashCodeTest {


    public static void main(String[] args) throws Exception {
        HashCodeTest hashCodeTest = new HashCodeTest();
        int hash = hashCodeTest.hash("hashCode测试01");
        System.out.println("hash === " + hash);

        // int j = (hash >>> segmentShift) & segmentMask;

        //long u = (k << SSHIFT) + SBASE;

        //int index = (tab.length - 1) & hash;
        int j = (hash >>> 28) & 15;
        int index = (2 - 1) & hash;

        System.out.println("j===" + j + ";index===" + index);


    }

    private transient final int hashSeed = randomHashSeed(this);

    private int hash(Object k) {
        int h = hashSeed;

        if ((0 != h) && (k instanceof String)) {
            return sun.misc.Hashing.stringHash32((String) k);
        }

        h ^= k.hashCode();

        // Spread bits to regularize both segment and index locations,
        // using variant of single-word Wang/Jenkins hash.
        h += (h << 15) ^ 0xffffcd7d;
        h ^= (h >>> 10);
        h += (h << 3);
        h ^= (h >>> 6);
        h += (h << 2) + (h << 14);
        return h ^ (h >>> 16);
    }


    private static int randomHashSeed(HashCodeTest instance) {
        if (sun.misc.VM.isBooted()) {
            return sun.misc.Hashing.randomHashSeed(instance);
        }

        return 0;
    }


}

*/
