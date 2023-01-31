package com.cloud;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8189);
        System.out.println("Сервер стартовал");
        while (true) {
            Socket socket = server.accept();
            new Thread(new FileMessageHandler(socket)).start();

        }
    }

}
