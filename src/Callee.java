import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Reimondo And Daniel on 2016-12-17.
 */
public class Callee implements Runnable {

    private static Socket socket = null;
    private static PrintWriter out = null;
    private static BufferedReader in = null;
    private static int PORT_NO = 5060;


    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT_NO);
            socket = serverSocket.accept();
            System.out.println("Connected");

            InetAddress clientAddress = socket.getInetAddress();
            int clientPort = socket.getPort();

            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));



        } catch (IOException e) {
            System.err.println("Server crash");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
