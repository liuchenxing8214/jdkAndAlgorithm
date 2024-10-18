package javaBase.os.monitor;

import java.util.concurrent.atomic.AtomicInteger;

public  class Counter {
    private static AtomicInteger count = new AtomicInteger(0);

    public static int incrementAndGet() {
       return count.incrementAndGet();
    }

}

