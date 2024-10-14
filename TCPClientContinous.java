import java.awt.print.Printable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class TCPClientContinous {
    public static void main(String[] args) throws IOException {

        //connect to the server on localhost at port 8080
        Socket socket = new Socket("localhost", 8080);

        //send a message to the server
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner scanner = new Scanner(System.in);

        String message;
        while(true){
            System.out.println("Enter message to send to the server (type 'exit' to quit): ");
            message = scanner.nextLine();
            if(message.equalsIgnoreCase("exit")){
                break;
            }
            out.println(message);

            String response = in.readLine();
            System.out.println("Server says: " + response);

        }


        // close the socket connection
        socket.close();

    }
}
