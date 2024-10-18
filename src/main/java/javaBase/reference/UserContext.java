package javaBase.reference;

public class UserContext {
    // ThreadLocal 变量存储用户信息
    private static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    // 设置用户信息
    public static void setUser(User user) {
        userThreadLocal.set(user);
    }

    // 获取用户信息
    public static User getUser() {
        return userThreadLocal.get();
    }

    // 清除用户信息
    public static void clear() {
        System.out.println("执行了清除用户信息操作"+userThreadLocal.get().getUsername());
        userThreadLocal.remove();
    }
}