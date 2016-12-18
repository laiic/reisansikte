import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.annotation.Documented;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Reimondo and Daniel on 2016-12-17.
 */
public class Caller implements Runnable {

    private static Socket socket = null;
    private static PrintWriter out = null;
    private static BufferedReader in = null;
    private static int PORT_NO = 5060;
    private String userInput = null;

    @Override
    public void run() {

        SIPHandler sipHandler = new SIPHandler();
        String userInput = null;

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("type INVITE (to_IP) (port) to make a call!");

        try {
            userInput = stdIn.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] arr = userInput.split(" ");

        sipHandler.processNextEvent(SIPHandler.SIPEvent.SEND_INVITE,arr[1],Integer.parseInt(arr[2]));


//        try {
//            socket = new Socket (args[1], Integer.parseInt(args[2])); //INET PORT.. SAMPT PORTNO
//            out = new PrintWriter(socket.getOutputStream(), true);
//            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//        } catch (IOException e) {
//            System.err.println("Client crash");
//        }      finally {
//             out.close();
//            try {
//                in.close();
//                stdIn.close();
//                socket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        }
//        System.out.println("Connected");


    }
}
