import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Scanner;



public class SIPStateWaiting extends SIPState {
    private  Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;


    public SIPStateWaiting(PeerConnection peerConnection) {
        super(peerConnection);
    }

    @Override
    public SIPState sendINVITE() {
        return new SIPStateTryCall(this.peerConnection);
    }

    @Override
    public SIPState receiveINVITE() throws IOException {

        peerConnection.sendMsg(SIPEvent.SEND_TRY);
        peerConnection.sendMsg(SIPEvent.SEND_RINGING);
        //peerConnection.sendMsg(SIPEvent.SEND_OK);

        in = new BufferedReader(new InputStreamReader(System.in));
        String msg = "";


        System.out.println("hej");
        while((msg = in.readLine()) != null) {

            System.out.println("e vi inne i while?");
            if(msg.equals("OK")) {
                System.out.println("är vi här?");
                return new SIPStateRespondeCall(this.peerConnection);
            }
        }

        System.out.println("utanför while");

        return new SIPStateWaiting(this.peerConnection);
    }

    public void printState() {
        System.out.println("We are in waiting!");
    }

    @Override
    public void sendBUSY() {
        peerConnection.sendMsg(SIPEvent.SEND_BUSY);
    }
}