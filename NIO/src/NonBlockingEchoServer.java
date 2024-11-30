import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NonBlockingEchoServer {
    private static final int BUFFER_SIZE = 256;

    private static final int PORT = 5000;

    public static void main(String[] args) {
        //create a serverSocketChannel to listen for incoming connections
        try(ServerSocketChannel serverChannel = ServerSocketChannel.open()){
            //configure the server channel to be non-blocking
            serverChannel.configureBlocking(false);

            //Bind the server channel to the specific port
            serverChannel.bind(new InetSocketAddress(PORT));
            System.out.println("Server started on port "+ PORT);

            //Open a Selector to handle multiple channels
            Selector selector = Selector.open();

            //Register the server channel with the selector for accept events
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            //main server loop
            while(true){
                //wait for events (blocking)
                selector.select();

                //Get the set of selected keys (channels ready for I/O operations)
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

                //Iterate over the keys
                while(keyIterator.hasNext()){
                    SelectionKey key = keyIterator.next();

                    //check if the key's channel is ready to accept a new connection
                    if(key.isAcceptable()){
                        //Accept the new client connection
                        ServerSocketChannel srvChannel = (ServerSocketChannel) key.channel();
                        SocketChannel clientChannel = srvChannel.accept();

                        //Configure the client channel to be non-blocking
                        clientChannel.configureBlocking(false);

                        //Register the client channel with the selector for read events
                        clientChannel.register(selector,SelectionKey.OP_ACCEPT);
                        System.out.println("Connected to client: "+ clientChannel.getRemoteAddress());
                    }
                    else if (key.isReadable()){
                        //Read data form the client
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

                        int bytesRead = clientChannel.read(buffer);
                        if(bytesRead>0){
                            buffer.flip();
                            //Echo the data back to the client
                            clientChannel.write(buffer);
                            buffer.clear();
                        } else if (bytesRead == -1) {
                            //The client has closed the connection
                            System.out.println("Client disconnected: " +clientChannel.getRemoteAddress());
                            clientChannel.close();
                        }

                    }

                    //Remove the key from the selected set; it's been handled
                    keyIterator.remove();
                }


            }

        }catch( IOException e){
            e.printStackTrace();
        }
    }
}
