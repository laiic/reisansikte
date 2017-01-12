import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;


public class SIPStateWaiting extends SIPState {
    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;


    public SIPStateWaiting(PeerConnection peerConnection) {
        super(peerConnection);
        System.out.println("VI ÄR INTE IN SESSION");
        peerConnection.setInSession(false);
    }

    @Override
    public SIPState sendINVITE(Socket socket) {
        peerConnection.setInSession(true);
        System.out.println("INSESSION TRUE ");
        return new SIPStateTryCall(this.peerConnection);
    }

    @Override
    public SIPState sendOK(Socket socket) {
        System.out.println("CAN'T send OK");
        return new SIPStateWaiting(this.peerConnection);
    }

    @Override
    public SIPState receiveINVITE(/* LÄGG TILL PARAMETE
    RAR HÄR, HANDLER SER TILL ATT SKICKA DE DATA SOM BEHÖVS */Socket socket, int port){
           // FÖR ATT *HÄR* KUNNA SKICKA TRO
        peerConnection.sendMsg(SIPEvent.SEND_TRY);   //  TRO     OK +
        peerConnection.sendMsg(SIPEvent.SEND_OK);
        System.out.println("To respond type OK a, timeout in 15 sek: ");
        peerConnection.setInSession(true);

        RemoteInfo.addr = socket.getInetAddress().getHostAddress();
        RemoteInfo.port = port;

        System.out.println("INSESSION TRUE ");
        return new SIPStateRespondeCall(this.peerConnection);
    }


    @Override
    public String printState() {
        System.out.println("WAITING state");
        return "";
    }
}