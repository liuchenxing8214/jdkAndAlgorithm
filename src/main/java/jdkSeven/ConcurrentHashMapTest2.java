package jdkSeven;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest2 {
    public static void main(String[] args) throws InterruptedException {
        final ConcurrentHashMap chm = new ConcurrentHashMap();
  /*      System.out.println("通话".hashCode());
        System.out.println("重地".hashCode());*/


        new Thread(){
            @Override
            public void run(){
                chm.put("通话","11");
                System.out.println("------------------");

            }

        }.start();
        //让第一个线程先启动，进入put方法
        Thread.sleep(1000);

        new Thread(){
            @Override
            public void run(){
                chm.put("重地","22");
                System.out.println("------------------");

            }

        }.start();

    }
}
