import java.net.Socket;

public class SIPStateDisconnect extends SIPState {


    public SIPStateDisconnect(PeerConnection peerConnection) {
        super(peerConnection);
    }

    @Override
    public SIPState receiveOK() {
        System.out.println("Ok received");
        System.out.println("returning to Wait from Disconnect and closing current port and SetinS");
        return new SIPStateWaiting(peerConnection);
    }

    @Override
    public SIPState receiveRINGING() {
        System.out.println("DON'T WANT RINGING");
        return new SIPStateDisconnect(this.peerConnection);
    }

    @Override
    public SIPState receiveTRY() {
        System.out.println("DON'T WANT TRY");
        return new SIPStateDisconnect(this.peerConnection);
    }

    @Override
    public SIPState receiveINVITE() {
        System.out.println("DON'T WANT INVITE");
        return new SIPStateDisconnect(this.peerConnection);
    }

    @Override
    public SIPState receiveACK() {
        System.out.println("DON'T WANT ACK");
        return new SIPStateDisconnect(this.peerConnection);
    }

    @Override
    public SIPState receiveBYE() {
        System.out.println("DON'T WANT BYE");
        return new SIPStateDisconnect(this.peerConnection);
    }

    @Override
    public String printState() {
        String str = new String("DISCONNECT");
        System.out.println("DISCONNECT state");
        return str;
    }

    public String getState() {
        return "DISCONNECT";
    }

    @Override
    public void sendBUSY(Socket socket) {
        peerConnection.sendMsg(SIPEvent.SEND_BUSY);
    }
}
