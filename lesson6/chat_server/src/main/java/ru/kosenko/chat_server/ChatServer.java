package ru.kosenko.chat_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    private static final int PORT = 8082;

    public void start() {
       try (ServerSocket serverSocket = new ServerSocket(PORT)) {
           System.out.println("Сервер запущен!");
           while (true) {
               System.out.println("Ожидается подключение клиента!!!");
               Socket socket = serverSocket.accept();
               System.out.println("Клиент подключился!!!");
               new ClientHandler(socket, this).handle();
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
}
