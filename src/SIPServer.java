import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class SIPServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        InetAddress clientAddress = null;
        int clientPort = 0;

        try {
            serverSocket = new ServerSocket(Integer.parseInt(args[0]));
        }

        catch(IOException e) {
            System.err.println("Could not listen on port: " + args[0]);
            System.exit(1);
        }

        Socket clientSocket = null;

        System.out.println("Waiting for a connection....");

        try {
            clientSocket = serverSocket.accept();
        }

        catch(IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        System.out.println("Connection successful!");
        System.out.println("Waiting for input...");



        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;


        while((inputLine = in.readLine()) != null) {
            System.out.println("Server: " + inputLine);
            out.println(inputLine);

            System.out.println("klientens ip: " + clientAddress);
            System.out.println("klientens port: " + clientPort);

            if(inputLine.equals("BYE")) {
                System.out.println("Servern stängs ner");
                break;
            }
        }




        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
