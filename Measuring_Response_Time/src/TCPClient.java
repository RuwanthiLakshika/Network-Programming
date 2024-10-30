import java.io.*;
import java.net.Socket;

//Develop a TCPclient program that connects to the server 1000 times in a loop and
// measures the elapsed time for each server configuration.
public class TCPClient {
    public static void main(String[] args) {
        int port = 5000;
        String host = "localhost"; //adjust is the server is running on a different machine
        int numberOfRequests = 1000;
        long startTime = System.currentTimeMillis();

        for(int i=0; i<numberOfRequests;i++){
            try (Socket socket = new Socket(host,port);
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                PrintWriter writer = new PrintWriter(output,true)){

                String message = "Hello, server! Request number: "+ (i+1);
                writer.println(message); //send message to the server
                String response = reader.readLine(); //read the response form the server
                //optional: Print response
                // System.out.println("Server response " + response);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println("Total time for "+ numberOfRequests + "requests: "+ elapsedTime + " ms");
        System.out.println("Average time per request: "+ (elapsedTime / (double) numberOfRequests) + " ms");
    }
}
