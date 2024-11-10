package juc.ConcurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SessionManager {
    private final ConcurrentHashMap<String, UserSession> sessionMap = new ConcurrentHashMap<>();

    public void createSession(String username, String password) {
        UserSession session = new UserSession(username, password, System.currentTimeMillis());
        sessionMap.put(username, session);
    }

    public UserSession getSession(String username) {
        return sessionMap.get(username);
    }

    public void removeSession(String username) {
        sessionMap.remove(username);
    }

    public static void main(String[] args) throws InterruptedException {
        SessionManager manager = new SessionManager();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // Runnable task to simulate session creation
        Runnable createTask = () -> {
            for (int i = 0; i < 1000; i++) {
                String username = "user" + i;
                String password = "pass" + i;
                manager.createSession(username, password);
            }
        };

        // Runnable task to simulate session retrieval
        Runnable retrieveTask = () -> {
            for (int i = 0; i < 1000; i++) {
                String username = "user" + i;
                manager.getSession(username);
            }
        };

        // Runnable task to simulate session removal
        Runnable removeTask = () -> {
            for (int i = 0; i < 1000; i++) {
                String username = "user" + i;
                manager.removeSession(username);
            }
        };

        // Run create, retrieve, and remove tasks with 10 threads
        for (int i = 0; i < 3; i++) {
            executorService.submit(createTask);
            executorService.submit(retrieveTask);
            executorService.submit(removeTask);
        }

        executorService.shutdown();
        while (!executorService.isTerminated()) {
            // Wait for all threads to finish
        }

        System.out.println("Session data for user500 after all operations: " + manager.getSession("user500"));
    }
}