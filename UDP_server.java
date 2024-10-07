import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDP_server {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try{
            //create a DatagramSocket on port 8090
            socket = new DatagramSocket(8090);
            byte[] buffer = new byte[256];

            //Prepare a DatagramPacket to receive data from client
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            System.out.println("Server is waiting for a client packet....");

            //Receive packet from client
            socket.receive(packet);
            String received = new String(packet.getData(),0,packet.getLength());
            System.out.println("Received from client: " + received);

            //send a response back to the client
            String response = "Hello from the server";
            byte[] responseData = response.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(
                    responseData,responseData.length,packet.getAddress(),packet.getPort());
            socket.send(responsePacket);
            System.out.println("Respond sent to the client.");

        } catch(SocketException e){
            System.out.println("Socket Error: " + e.getMessage());

        } catch(IOException e){
            System.out.println("I/O Error: " + e.getMessage());
        }finally{
            //Ensure the socket is closed even if an error occurs
            if(socket !=null && !socket.isClosed()){
                socket.close();
                System.out.println("Socket closed. ");
            }
        }
    }
}
