import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_client {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try{
            //create a DatagramSocket
            socket =  new DatagramSocket();

            //send packet to the server
            String message = "Hello server";
            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"),8090);
            socket.send(packet);

            //Receive response from the server
            byte[] responseBuffer =  new byte[256];
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);

            socket.receive(responsePacket);

            String response = new String(responsePacket.getData(),0,responsePacket.getLength());
            System.out.println("Server response: " + response);

        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }finally{
            if(socket != null && !socket.isClosed()){
                socket.close();
            }
        }
    }
}
