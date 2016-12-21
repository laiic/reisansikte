import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

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
    public SIPState receiveINVITE() {

        peerConnection.sendMsg(SIPEvent.SEND_TRY);
        peerConnection.sendMsg(SIPEvent.SEND_RINGING);
        peerConnection.sendMsg(SIPEvent.SEND_OK);
        return new SIPStateRespondeCall(this.peerConnection);
    }

    public void printState() {
        System.out.println("We are in waiting!");
    }
}