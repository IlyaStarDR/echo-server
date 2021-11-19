package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(3001);
             Socket server = serverSocket.accept();
             InputStream inputStream = server.getInputStream();
             OutputStream outputStream = server.getOutputStream()) {

            byte[] buffer = new byte[1024];
            int count = inputStream.read(buffer);
            String messageFromClient = new String(buffer, 0, count);
            System.out.println("Client: " + messageFromClient);

            String messageToClient = "Echo " + messageFromClient;
            outputStream.write(messageToClient.getBytes());
        }
    }
}