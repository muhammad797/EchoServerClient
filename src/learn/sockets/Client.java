package learn.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by MuhammadAli on 11/8/2017.
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("Client started");
        try {
            Socket socket = new Socket("localhost", 8000);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            String inp;
            while(!socket.isClosed()){
                inp = new Scanner(System.in).nextLine();
                dataOutputStream.writeUTF(inp);
                System.out.println(dataInputStream.readUTF());
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
