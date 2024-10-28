import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Multithreaded_TCP_Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter server IP address:");
        String serverAddress = scanner.nextLine();

        while (true) {
            try {
                // Connect to the server
                Socket socket = new Socket(serverAddress, 5006);
                System.out.println("Connected to the server.");

                // Create input and output streams
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

                // Get user input
                System.out.print("Enter a number to send to the server (or 'exit' to quit): ");
                String userInput = scanner.nextLine();

                if (userInput.equalsIgnoreCase("exit")) {
                    socket.close();
                    break;
                }

                // Send the user input to the server
                writer.println(userInput);

                // Read and display the server response
                String serverResponse = reader.readLine();
                System.out.println("Server Response: " + serverResponse);

                // Close the socket connection
                socket.close();

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}

