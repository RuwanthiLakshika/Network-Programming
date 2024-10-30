import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class TCPEchoServerThreadPool10 {
    public static void main(String[] args) {
        int port = 5000;
        ExecutorService executor = Executors.newFixedThreadPool(10); // Thread pool of size 10

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Multi-threaded server (pool size 10) is running on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                executor.submit(() -> handleClient(clientSocket));
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {

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
}
