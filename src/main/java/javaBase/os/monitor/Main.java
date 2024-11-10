package javaBase.os.monitor;


import java.util.concurrent.*;

// 主类
public class Main {
    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();

        // 创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // 提交生产者和消费者任务
        executor.submit(new Producer(pc));
        executor.submit(new Producer(pc));
        executor.submit(new Producer(pc));
        executor.submit(new Consumer(pc));

        // 关闭线程池
        executor.shutdown();

        ExecutorService threadPool=new ThreadPoolExecutor(2,5,
                1L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    }
}
