package ru.kosenko.chat_server;

//        Разобраться с кодом с занятия, он является фундаментом проекта-чата
//        Переписать (адаптировать под себя) проект чата: модуль чат, модуль сервера.
//        **** Попробовать внедрить код из SingleClientConsole в чат клиент.

public class ServerApp {
    public static void main(String[] args) {
        new ChatServer().start();
    }
}
