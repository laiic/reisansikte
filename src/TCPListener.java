import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPListener extends Thread {

    private int port = 5060;
    private static Socket socket = null;
    private static PrintWriter out = null;
    private static BufferedReader in;
    private Scanner scan;


    @Override
    public void run() {

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            System.out.println("Waiting for connection....");
            socket = serverSocket.accept();
        }

        catch (IOException e) {

            System.err.println("Server crash");
        }

        System.out.println("Connected!");

        String fromSocket;

        try {
            scan = new Scanner (socket.getInputStream());
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        while((fromSocket = scan.nextLine()) != null) {
            System.out.println(fromSocket);
        }
    }
}

