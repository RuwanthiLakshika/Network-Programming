import java.awt.print.Printable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCP_Client {
    public static void main(String[] args) throws IOException {

        //connect to the server on localhost at port 8080
        Socket socket = new Socket("localhost", 8080);

        //send a message to the server
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
        out.println("Hello, Server!");

        //Receive the server's response
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String response = in.readLine();
        System.out.println("Server says: " + response);

        // close the socket connection
        socket.close();

    }
}
