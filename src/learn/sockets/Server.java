package learn.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by MuhammadAli on 11/8/2017.
 */
public class Server {
    public static void main(String[] args) {

        System.out.println("Server started");
        ServerSocket serverSocket = null;
        try {

            serverSocket = new ServerSocket(8000);
            while(true){
                Socket socket = serverSocket.accept();
                HandleClient client = new HandleClient(socket);
                Thread thread = new Thread(client);
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class HandleClient implements Runnable {

    private Socket socket;

    HandleClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        String clientName = socket.getInetAddress().getHostAddress();
        try {
            System.out.println("Client added " + clientName);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            String inp;
            while (!socket.isClosed()) {
                inp = dataInputStream.readUTF();
                System.out.println(clientName + ": " + inp);
                dataOutputStream.writeUTF(inp);
            }
            socket.close();
        }
        catch(SocketException e){
            System.out.println("Connection Terminated from: " + clientName);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}