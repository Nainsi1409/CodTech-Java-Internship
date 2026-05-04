package com.codtech.chat;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    private static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server started...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");

                ClientHandler client = new ClientHandler(socket);
                clients.add(client);

                new Thread(client).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            try {
                this.socket = socket;
                in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            String message;
            try {
                while ((message = in.readLine()) != null) {
                    System.out.println("Received: " + message);

                    // Broadcast message to all clients
                    for (ClientHandler client : clients) {
                        client.out.println(message);
                    }
                }
            } catch (IOException e) {
                System.out.println("Client disconnected");
            }
        }
    }
}