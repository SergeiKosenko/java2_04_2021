package ru.kosenko.auth;

public interface AuthService {
    void start();
    void stop();
    String getUsernameByLoginAndPassword(String login, String password);
    String changeUsername(String oldName, String newName);
    String changePassword(String username, String oldPassword, String newPassword);

}
