//change this to handle multiple requests

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Multithreaded_TCP_Server {
    public static int COUNT = 0;
    public static void main(String[] args) {
        try{
            //connection initialization
            ServerSocket serverSocket = new ServerSocket(5006);
            System.out.println("Server is listening...");

            while(true){
                Socket socket =serverSocket.accept();
                System.out.println("Connection Accepted");

                Thread t = new Thread(new Runnable(){
                    @Override
                    public void run(){
                        handleRequest(socket);
                    }
                });
                t.start();
            }

        }
        catch (Exception e){
            System.out.println("Error: "+ e.getMessage());
        }
    }

    private static void handleRequest(Socket socket){
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);

            //read from client
            String clientMessage = reader.readLine();
            System.out.println("Client Message: "+ clientMessage);
            int number = Integer.parseInt(clientMessage);

            //processing data
            COUNT += number;
            String serverMessage = "COUNT: " + COUNT;

            //send response to client
            writer.println(serverMessage);

            //close connection
            socket.close();

        }
        catch (NumberFormatException | IOException e){
            System.out.println("Error: "+ e.getMessage());
        }
    }
}
