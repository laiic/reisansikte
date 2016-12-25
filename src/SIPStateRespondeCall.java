import java.io.IOException;
import java.net.InetAddress;

public class SIPStateRespondeCall extends SIPState {

    public SIPStateRespondeCall(PeerConnection peerConnection) {
        super(peerConnection);
        peerConnection.sendMsg(SIPEvent.SEND_TRY);
        peerConnection.sendMsg(SIPEvent.SEND_RINGING);
        System.out.println("To respond type OK IPno and Portno");
    }

    @Override
    public SIPState receiveACK() {
        return new SIPStateTalking(this.peerConnection);
    }

    @Override
    public void printState() {
        System.out.println("We are in the RespondeState");
    }

    @Override
    public void sendBUSY() {

    }

}
