package juc.ConcurrentHashMap.package02;

public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        LoginManager loginManager = new LoginManager();

        // 模拟用户登录
        loginManager.login("1", "Alice");
        loginManager.login("2", "Bob");

        // 打印当前在线用户
        loginManager.printActiveSessions();

        // 模拟用户登出
        loginManager.logout("1");

        // 打印当前在线用户
        loginManager.printActiveSessions();

        // 尝试登出不存在的用户
        loginManager.logout("3");

        // 再次打印当前在线用户
        loginManager.printActiveSessions();
    }
}