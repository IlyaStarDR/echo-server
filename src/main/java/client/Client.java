package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Socket client = new Socket("localhost", 3001);
             InputStream inputStream = client.getInputStream();
             OutputStream outputStream = client.getOutputStream();
             Scanner scanner = new Scanner(System.in)) {
            String messageToServer = "";
            while (!messageToServer.equals("bye")) {
                messageToServer = scanner.nextLine();
                outputStream.write(messageToServer.getBytes());

                byte[] buffer = new byte[1024];
                int count = inputStream.read(buffer);
                inputStream.readAllBytes();
                String messageFromServer = new String(buffer, 0, count);
                System.out.println("Server: " + messageFromServer);
            }
        }
    }
}