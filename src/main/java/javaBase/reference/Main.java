package javaBase.reference;

// 示例使用
public class Main {
    public static void main(String[] args) {
        // 创建用户
        User user1 = new User("1", "Alice");
        User user2 = new User("2", "Bob");

        // 模拟线程1
        Thread thread1 = new Thread(() -> {
            UserContext.setUser(user1);
            System.out.println("Thread 1: " + UserContext.getUser().getUsername());
            UserContext.clear();
    });

        // 模拟线程2
        Thread thread2 = new Thread(() -> {
            UserContext.setUser(user2);
            System.out.println("Thread 2: " + UserContext.getUser().getUsername());
            UserContext.clear();
        });

        thread1.start();
        thread2.start();
    }
}