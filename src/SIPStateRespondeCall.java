import java.net.Socket;

public class SIPStateRespondeCall extends SIPState {

    public SIPStateRespondeCall(PeerConnection peerConnection) {
        super(peerConnection);
    }


    @Override
    public SIPState receiveOK() {
        System.out.println("DON'T WANT OK");
        return new SIPStateRespondeCall(this.peerConnection);
    }

    @Override
    public SIPState receiveBYE() {
        System.out.println("DON'T WANT BYE");
        return new SIPStateRespondeCall(this.peerConnection);
    }

    @Override
    public SIPState receiveRINGING() {
        System.out.println("DON'T WANT RINGING");
        return new SIPStateRespondeCall(this.peerConnection);
    }

    @Override
    public SIPState receiveTRY() {
        System.out.println("DON'T WANT TRY");
        return new SIPStateRespondeCall(this.peerConnection);
    }

    @Override
    public SIPState receiveINVITE() {
        System.out.println("DON'T WANT INVITE");
        return new SIPStateRespondeCall(this.peerConnection);
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

    // RECEIVED INVITE => FEL => TILLBAKA TILL WAITING.

}
