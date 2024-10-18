package javaBase.redis.poll;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class RedisPollServer {
    private Selector selector;

    public RedisPollServer() throws IOException {
        selector = Selector.open();
    }

    public void startServer(int port) throws IOException {
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Server started on port: " + port);

        while (true) {
            selector.select(); // 阻塞，直到有事件发生
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                if (key.isAcceptable()) {
                    handleAccept(key);
                } else if (key.isReadable()) {
                    handleRead(key);
                }
                keys.remove();
            }
        }
    }

    private void handleAccept(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocket = (ServerSocketChannel) key.channel();
        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
        System.out.println("Client connected: " + client.getRemoteAddress());
    }

    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(256);
        int bytesRead = client.read(buffer);
        if (bytesRead == -1) {
            client.close();
            System.out.println("Client disconnected.");
        } else {
            String message = new String(buffer.array()).trim();
            System.out.println("Received message: " + message);
            // Echo the message back to the client
            buffer.flip();
            client.write(buffer);
        }
    }

    public static void main(String[] args) {
        try {
            RedisPollServer server = new RedisPollServer();
            server.startServer(6379);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}