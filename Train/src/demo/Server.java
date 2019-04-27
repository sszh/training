package demo;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final String IP = "localhost";
    public static final int PORT = 8888;
     
    private ServerSocket serverSocket;
     
    private class AcceptRequest implements Runnable
    {
 
        @Override
        public void run() {
            try {
                while(true)
                {
                    Socket socket = serverSocket.accept();
                    new Thread(new ReceiveMessage(socket),"AcceptRequest--"+System.nanoTime()).start();
                    System.out.println("Connected");
                    System.out.println("AcceptRequest线程名："+Thread.currentThread().getName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
             
        }
         
    }
    private class ReceiveMessage implements Runnable
    {
        private Socket socket;
         
        public ReceiveMessage(Socket socket)
        {
            this.socket = socket;
        }
         
        @Override
        public void run() {
            try {
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                String receivedMessage = "";
                while(true)
                {
                    System.out.print("server:");
                    receivedMessage = dataInputStream.readUTF();
                    System.out.println(receivedMessage);
                    System.out.println("ReceiveMessage线程名："+Thread.currentThread().getName());
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
             
        }
         
    }
    public void startup()
    {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Waiting for connection...");
            new Thread(new AcceptRequest(),"AcceptRequest--"+System.nanoTime()).start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
     
    public static void main(String[] args)
    {
        Server server = new Server();
        server.startup();
    }
}
