import java.io.IOException;
import java.net.InetAddress;

public class SIPStateRespondeCall extends SIPState {

    public SIPStateRespondeCall(PeerConnection peerConnection) {
        super(peerConnection);
    }

    @Override
    public SIPState receiveACK() {


        return new SIPStateTalking(this.peerConnection);
    }

    @Override
    public void printState() {
        System.out.println("We are in the RespondeState");
    }

}
