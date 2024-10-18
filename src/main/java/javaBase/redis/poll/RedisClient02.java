package javaBase.redis.poll;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class RedisClient02 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 6379)) {
            OutputStream output = socket.getOutputStream();
            InputStream input = socket.getInputStream();

            String message = "客户端02 发送消息";
            output.write(message.getBytes());
            output.flush();

            byte[] response = new byte[256];
            int bytesRead = input.read(response);
            System.out.println("Server response: " + new String(response, 0, bytesRead));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
