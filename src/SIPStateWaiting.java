import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Scanner;


public class SIPStateWaiting extends SIPState {
    private Socket socket = null;
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
    public SIPState receiveINVITE(/* LÄGG TILL PARAMETE
    RAR HÄR, HANDLER SER TILL ATT SKICKA DE DATA SOM BEHÖVS */){
           // FÖR ATT *HÄR* KUNNA SKICKA TRO
        peerConnection.sendMsg(SIPEvent.SEND_TRY);   //  TRO     OK +
        peerConnection.sendMsg(SIPEvent.SEND_RINGING);
        System.out.println("To respond type OK a, timeout in 15 sek: ");
        return new SIPStateRespondeCall(this.peerConnection);
    }

    @Override
    public String printState() {
        System.out.println("WAITING state");
        return "";
    }

    @Override
    public SIPState sendBUSY() {
        peerConnection.sendMsg(SIPEvent.SEND_BUSY);
        return new SIPStateWaiting(this.peerConnection);
    }
}