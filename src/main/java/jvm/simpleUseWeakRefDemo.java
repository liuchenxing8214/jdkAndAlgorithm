package jvm;

import java.lang.ref.WeakReference;

public class simpleUseWeakRefDemo {
    public static void main(String[] args) {
        /** * 简单使用弱引用demo */

            WeakReference<String> sr = new WeakReference<>(new String("hello world " ));
            // before gc -> hello world
            System.out.println("before gc -> " + sr.get());

            // 通知JVM的gc进行垃圾回收
            System.gc();
            // after gc -> null
            System.out.println("after gc -> " + sr.get());

    }
}
