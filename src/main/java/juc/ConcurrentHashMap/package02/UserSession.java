package juc.ConcurrentHashMap.package02;

public class UserSession {
    private String userId;
    private String username;
    private long loginTime;

    public UserSession(String userId, String username) {
        this.userId = userId;
        this.username = username;
        this.loginTime = System.currentTimeMillis();
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public long getLoginTime() {
        return loginTime;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", loginTime=" + loginTime +
                '}';
    }
}