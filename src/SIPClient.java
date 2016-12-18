//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.InetAddress;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.net.UnknownHostException;
//import java.util.Scanner;
//
//public class SIPClient implements Runnable {
//
//    private static Socket socket = null;
//    private static PrintWriter out = null;
//    private static BufferedReader in = null;
//
//    public static void main(String[] args) throws IOException {
//
//        (new Thread(new SIPClient())).start(); //this is to start a thread that listens for incoming INVITES
//
//        SIPHandler sipHandler = new SIPHandler();
//
//        try {
//            socket = new Socket(args[1], Integer.parseInt(args[2]));
//            out = new PrintWriter(socket.getOutputStream(), true);
//            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        } catch (IOException e) {
//            System.err.println("Client crash");
//        }
//
//        System.out.println("Connected");
//
//        String userInput;
//
//        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
//
//        System.out.print("input: ");
//        while ((userInput = stdIn.readLine()) != null) {
//
//            System.out.println("SIP Protocol");
//
//
//            switch (userInput) {
//                case "INVITE":
//                    sipHandler.processNextEvent(SIPHandler.SIPEvent.SEND_INVITE);
//                    break;
//                case "STATE":
//                    sipHandler.printState();
//                    break;
//            }
//
//
//            out.println(userInput);
//            //System.out.println("echo: " + in.readLine());
//            System.out.print("input: ");
//        }
//
//        out.close();
//        in.close();
//        stdIn.close();
//        socket.close();
//    }
//
//
//    @Override
//    public void run() {
//
//
//            try {
//
//                ServerSocket serverSocket = new ServerSocket(5060);
//                System.out.println("Waiting for a connection....");
//                socket = serverSocket.accept();
//                //här, if accept.
//
//            } catch (IOException e) {
//                System.err.println("Server crash");
//            }
//
//            System.out.println("Connected");
//
//
//            InetAddress clientAddress = socket.getInetAddress();
//            int clientPort = socket.getPort();
//
//
//            out = new PrintWriter(socket.getOutputStream(), true);
//            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            String inputLine;
//
//            while ((inputLine = in.readLine()) != null) {
//                System.out.println("Server: " + inputLine);
//                out.println(inputLine);
//
//                System.out.println("client adress: " + clientAddress);
//                System.out.println("client port: " + clientPort);
//
//                if (inputLine.equals("Bye."))
//                    break;
//            }
//
//            out.close();
//            in.close();
//            socket.close();
//
//
//    }
//}
