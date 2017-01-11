import java.net.Socket;

public class SIPStateRespondeCall extends SIPState {

    public SIPStateRespondeCall(PeerConnection peerConnection) {
        super(peerConnection);


    }

    @Override
    public SIPState receiveACK() {
        return new SIPStateTalking(this.peerConnection);
    }

    @Override
    public String printState() {
        System.out.println("RespondeState");
        return "";
    }

    @Override
    public SIPState sendOK() {
        peerConnection.sendMsg(SIPEvent.SEND_OK);
        return new SIPStateRespondeCall(this.peerConnection);
    }

    @Override
    public void sendBUSY(Socket socket) {
        peerConnection.sendMsg(SIPEvent.SEND_BUSY);
    }

    // RECEIVED INVITE => FEL => TILLBAKA TILL WAITING.

}
