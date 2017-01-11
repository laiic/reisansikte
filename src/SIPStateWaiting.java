import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;


public class SIPStateWaiting extends SIPState {
    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;


    public SIPStateWaiting(PeerConnection peerConnection) {
        super(peerConnection);
    }

    @Override
    public SIPState sendINVITE() {
        peerConnection.setInSession(true);
        return new SIPStateTryCall(this.peerConnection);
    }

    @Override
    public SIPState receiveINVITE(/* LÄGG TILL PARAMETE
    RAR HÄR, HANDLER SER TILL ATT SKICKA DE DATA SOM BEHÖVS */){
           // FÖR ATT *HÄR* KUNNA SKICKA TRO
        peerConnection.sendMsg(SIPEvent.SEND_TRY);   //  TRO     OK +
        peerConnection.sendMsg(SIPEvent.SEND_RINGING);
        System.out.println("To respond type OK a, timeout in 15 sek: ");
        peerConnection.setInSession(true);
        return new SIPStateRespondeCall(this.peerConnection);
    }


    @Override
    public String printState() {
        System.out.println("WAITING state");
        return "";
    }

    @Override
    public void sendBUSY(Socket socket) {
        peerConnection.sendMsg(SIPEvent.SEND_BUSY);
    }


}