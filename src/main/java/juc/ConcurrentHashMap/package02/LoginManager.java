package juc.ConcurrentHashMap.package02;

import java.util.concurrent.ConcurrentHashMap;

public class LoginManager {
    private ConcurrentHashMap<String, UserSession> sessions = new ConcurrentHashMap<>();

    // 用户登录
    public void login(String userId, String username) {
        UserSession session = new UserSession(userId, username);
        sessions.put(userId, session);
        System.out.println(username + " logged in.");
    }

    // 用户登出
    public void logout(String userId) {
        UserSession removedSession = sessions.remove(userId);
        if (removedSession != null) {
            System.out.println(removedSession.getUsername() + " logged out.");
        } else {
            System.out.println("No session found for user ID: " + userId);
        }
    }

    // 获取当前在线用户
    public void printActiveSessions() {
        System.out.println("Active Sessions:");
        sessions.forEach((key, value) -> System.out.println(value));
    }
}
