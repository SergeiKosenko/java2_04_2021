package ru.kosenko.chat_server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private ChatServer chatServer;
    private Socket socket;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;

    static int clientCounter = 0;
    private int clientNumber;

    public ClientHandler(Socket socket, ChatServer chatServer) {
        try {
            this.chatServer = chatServer;
            this.socket = socket;
            this.outputStream = new DataOutputStream(socket.getOutputStream());
            this.inputStream = new DataInputStream(socket.getInputStream());
            this.clientNumber = ++clientNumber;
            System.out.println("Создан обработчик клиента!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handle () {
        new Thread(() ->  {
            try {
                while (!Thread.currentThread().isInterrupted() || socket.isConnected()) {
                    String message = inputStream.readUTF();
                    System.out.printf("Клиент №%d : %s\n", this.clientNumber, message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
