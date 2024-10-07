import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP_Server {
    public static void main(String[] args) throws IOException {

        //create a server socket listening on port 8080
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server is listening to the port 8080");

        //Accept a client connection
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected");

        //communication with the client
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        //Read the client's message
        String message = in.readLine();
        System.out.println("Client says: " + message);

        //send a response to the client
        out.println("Hello, Client!");

        //close the connection
        clientSocket.close();
        serverSocket.close();

    }
}
