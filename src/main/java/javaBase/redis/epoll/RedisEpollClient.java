package javaBase.redis.epoll;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class RedisEpollClient {
    private SocketChannel socketChannel;

    public RedisEpollClient(String host, int port) throws IOException {
        // 创建客户端通道并连接到服务器
        socketChannel = SocketChannel.open(new InetSocketAddress(host, port));
    }

    public void sendMessage(String message) throws IOException {
        // 发送消息到服务器
        socketChannel.write(ByteBuffer.wrap(message.getBytes()));
        ByteBuffer buffer = ByteBuffer.allocate(256);
        socketChannel.read(buffer); // 读取服务器的回显
        String response = new String(buffer.array()).trim();
        System.out.println("Received from server: " + response);
    }

    public void close() throws IOException {
        socketChannel.close(); // 关闭连接
    }

    public static void main(String[] args) {
        try {
            RedisEpollClient client = new RedisEpollClient("localhost", 8080);
            client.sendMessage("Hello from client!"); // 发送消息
            client.close(); // 关闭客户端
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}