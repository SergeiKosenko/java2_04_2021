package ru.kosenko.chat_client;

import java.io.*;
import java.net.Socket;

public class ChatClient {
    private static final String HOST = "localhost";
    private static final int PORT = 8082;
    private DataInputStream in;
    private DataOutputStream out;
    private Thread ChatClientThread;



    public void runClient() {
        try(Socket socket = new Socket(HOST, PORT)) {
            System.out.println("Клиент запущен!");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            startConsoleThread();

            while (true) {
                String msg = in.readUTF();
                if (msg.equals("/end")) {
                    shutdownClient(socket);
                    break;
                }
                System.out.println("Received: " + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("FINISHED");
        }
    }

    private void shutdownClient(Socket socket) throws IOException {
        ChatClientThread.interrupt();
        socket.close();
        System.out.println("Клиент остановлен");
    }

    private void startConsoleThread() {
        ChatClientThread = new Thread(() -> {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите сообщение для отправки >>>>");
            try {
                while(!Thread.currentThread().isInterrupted()) {
                    if (bufferedReader.ready()) {
                        String messageFromClient = bufferedReader.readLine();
                        out.writeUTF(messageFromClient);
                        Thread.sleep(200);
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        ChatClientThread.start();
    }
}
