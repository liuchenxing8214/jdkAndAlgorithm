package javaBase.redis.epoll;



import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RedisEpollServerTest {
    public static void main(String[] args) throws IOException {
        // 启动服务器
        RedisEpollServer server = new RedisEpollServer(8080);
        new Thread(() -> {
            try {
                server.start(); // 启动服务器线程
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // 使用线程池模拟多个客户端
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            final int clientId = i;
            executor.submit(() -> {
                try {
                    RedisEpollClient client = new RedisEpollClient("localhost", 8080);
                    // 模拟阻塞等待
                    Thread.sleep(1000); // 等待1秒
                    client.sendMessage("Hello from client " + clientId); // 发送消息
                    client.close(); // 关闭客户端
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown(); // 关闭线程池
    }


}