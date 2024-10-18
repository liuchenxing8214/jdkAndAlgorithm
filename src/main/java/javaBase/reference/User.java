package javaBase.reference;

// 用户类
class User {
    private String userId;
    private String username;

    public User(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    // Getter 和 Setter
    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }
}

