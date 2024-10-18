package javaBase.redis.poll;

import java.io.IOException;
import java.net.Socket;
import java.io.OutputStream;
import java.io.InputStream;

public class RedisClient {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for(int i=0;i<20000;i++){
            try (Socket socket = new Socket("localhost", 6379)) {
                OutputStream output = socket.getOutputStream();
                InputStream input = socket.getInputStream();

                String message ="客户端【"+i+ "】  国庆节快乐 第"+i+"次数";
                output.write(message.getBytes());
                output.flush();

                byte[] response = new byte[256];
                int bytesRead = input.read(response);
                System.out.println("Server response: " + new String(response, 0, bytesRead));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis()-startTime);
    }
}