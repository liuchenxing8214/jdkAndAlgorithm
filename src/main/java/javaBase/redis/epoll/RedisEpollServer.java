package javaBase.redis.epoll;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.SelectionKey;
import java.util.Iterator;

public class RedisEpollServer {
    private Selector selector;

    public RedisEpollServer(int port) throws IOException {
        // 创建选择器，用于管理多个通道
        selector = Selector.open();
        // 创建服务端通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false); // 设置为非阻塞模式
        // 将服务端通道注册到选择器，监听接受连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void start() throws IOException {
        while (true) {
            // 阻塞等待事件发生
            selector.select();
            // 获取所有已准备好的事件
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove(); // 从集合中移除当前键

                if (key.isAcceptable()) {
                    // 处理新的连接
                    handleAccept(key);
                } else if (key.isReadable()) {
                    // 处理读事件
                    handleRead(key);
                }
            }
        }
    }

    private void handleAccept(SelectionKey key) throws IOException {
        // 接受新的连接
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false); // 设置为非阻塞模式
        // 注册读事件
        socketChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("Accepted connection from " + socketChannel.getRemoteAddress());
    }

    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(256);
        int bytesRead = socketChannel.read(buffer); // 读取数据

        if (bytesRead == -1) {
            socketChannel.close(); // 客户端关闭连接
            System.out.println("Connection closed by client");
            return;
        }

        String message = new String(buffer.array()).trim(); // 转换为字符串
        System.out.println("Received: " + message);
        // 回显消息
        socketChannel.write(ByteBuffer.wrap(("Echo: " + message).getBytes()));
    }

    public static void main(String[] args) {
        try {
            RedisEpollServer server = new RedisEpollServer(8080);
            System.out.println("Server started on port 8080");
            server.start(); // 启动服务器
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
