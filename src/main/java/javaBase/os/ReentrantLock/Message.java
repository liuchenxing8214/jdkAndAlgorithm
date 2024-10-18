package javaBase.os.ReentrantLock;

/**
 * 消息类
 */
public class Message {
    private static int count = 0;  // 静态计数器用于生成唯一ID
    private final int id;  // 消息ID
    private final String content;  // 消息内容

    public Message(String content) {
        this.id = ++count;  // 每创建一个消息，ID自动加1
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
