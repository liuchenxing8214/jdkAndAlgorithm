/*
package jdkSeven;

public class HashMapTest01 {
    public static void main(String[] args) {
*/
/*        int hash = hash(key);
        int i = indexFor(hash, table.length);*//*

        String key = "hashKey";
        String key2 = "key2";
        int hashKey =  hash(key);
        System.out.println(hashKey);
        int i = indexFor(hashKey,2);
        System.out.println(i);
        System.out.println(indexFor(hash(key2),2));


    }

    static  final int hash(Object k) {
        int h = 0;
        if (0 != h && k instanceof String) {
            return sun.misc.Hashing.stringHash32((String) k);
        }

        h ^= k.hashCode();

        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    static int indexFor(int h, int length) {
        // assert Integer.bitCount(length) == 1 : "length must be a non-zero power of 2";
        return h & (length-1);
    }
}
*/
