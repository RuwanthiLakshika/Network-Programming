import java.io.*;
import java.net.*;

//Single-threaded Server – accepts one client at a time.
public class TCPEchoServerSingleThreaded {
    public static void main(String[] args) {
        int port = 5000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Single-threaded server is running on port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    System.out.println("Client connected: " + clientSocket.getInetAddress());

                    String message;
                    while ((message = reader.readLine()) != null) {
                        System.out.println("Received: " + message);
                        writer.println("Echo: " + message); // Echoes the received message
                    }

                    System.out.println("Client disconnected.");
                } catch (IOException e) {
                    System.out.println("Connection error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}

