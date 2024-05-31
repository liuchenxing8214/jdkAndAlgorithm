package jvm;

import java.lang.ref.SoftReference;

public class SoftRefDemo {
    public static void main(String[] args) {
        SoftReference<String> sr = new SoftReference<>( new String("hello world "));
        // hello world
        System.out.println(sr.get());
    }
}
