package juc.ConcurrentHashMap;


import lombok.Data;

@Data
public class UserSession {
    private final String username;
    private final String password;
    private final long loginTime;

    public UserSession(String username, String password, long loginTime) {
        this.username = username;
        this.password = password;
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", loginTime=" + loginTime +
                '}';
    }
}