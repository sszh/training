package demo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket socket;
     
    public Client()
    {
        try {
            socket = new Socket(Server.IP, Server.PORT);
            new Thread(new SendMessage(),"client--"+System.nanoTime()).start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    private class SendMessage implements Runnable
    {
 
        @Override
        public void run() {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                String message = "";
                while (true) {	
                	 System.out.println("请输入:");
                     message = bufferedReader.readLine();
                     System.out.println("client--"+message);
                     System.out.println("SendMessage线程名："+Thread.currentThread().getName());
                     dataOutputStream.writeUTF(message);
				}
               
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
             
        }
         
    }
     
    public static void main(String[] args)
    {
        Client client = new Client();
    }
}
