import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ModifiedUDPClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        int maxRetries = 3;
        int timeout = 3000;

        try {
            // Create a DatagramSocket
            socket = new DatagramSocket();
            socket.setSoTimeout(timeout);

            // Prepare the packet to send to the server
            String message = "Hello server";
            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), 8090);

            boolean responseReceived = false;

            // Retry mechanism using for loop
            for (int attempt = 1; attempt <= maxRetries; attempt++) {
                try {
                    // Send the packet
                    socket.send(packet);
                    System.out.println("Message sent to server. Attempt " + attempt);

                    // Receive response from the server
                    byte[] responseBuffer = new byte[256];
                    DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);

                    socket.receive(responsePacket);

                    // If a response is received, print it
                    String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
                    System.out.println("Server response: " + response);
                    responseReceived = true;
                    break;

                } catch (Exception e) {
                    System.out.println("No response from server. Retrying...");
                }
            }

            if (!responseReceived) {
                System.out.println("Failed to receive response after " + maxRetries + " attempts.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
