import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SIPClient {

    private static Socket socket = null;
    private static PrintWriter out = null;
    private static BufferedReader in = null;

    public static void main(String[] args) throws IOException {

        SIPHandler sipHandler = new SIPHandler();
        TCPListener lyssnare = new TCPListener();





















    }
}
