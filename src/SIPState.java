import java.io.IOException;
import java.net.InetAddress;

public abstract class SIPState {

    protected PeerConnection peerConnection;

    public SIPState(PeerConnection peerConnection) {

        this.peerConnection = peerConnection;

    }

    public SIPState sendINVITE() {
        return new SIPStateWaiting(peerConnection);
    }

    public SIPState sendACK() {
        return new SIPStateWaiting(peerConnection);
    }

    public SIPState sendTRY() {
        return new SIPStateWaiting(peerConnection);
    }

    public SIPState sendRINGING() {
        return new SIPStateWaiting(peerConnection);
    }

    public SIPState sendBYE() {
        return new SIPStateWaiting(peerConnection);
    }

    public SIPState sendOK() {
        return new SIPStateWaiting(peerConnection);
    }

    public SIPState receiveINVITE() {return new SIPStateWaiting(peerConnection);} // RETURN WAITING

    public SIPState receiveACK() {
        return new SIPStateWaiting(peerConnection);
    }

    public SIPState receiveTRY() {return new SIPStateWaiting(peerConnection);}

    public SIPState receiveRINGING() {
        return new SIPStateWaiting(peerConnection);
    }

    public SIPState receiveBYE() {
        return new SIPStateWaiting(peerConnection);
    }

    public SIPState receiveOK() {
        return new SIPStateWaiting(peerConnection);
    }

    public abstract void printState();

    public abstract SIPState sendBUSY();

    //Samtliga metoder som hör till tillståndsövergångar

    // incInviteSendingTRO - byt till respCall
    // sendInvite - byt till tryCall
    // receiveAck - byt till Talking
    // receivedTROsendACK - byt till Talking
    // receivedBYEsendOK - byt till Waiting
    // sendBYE - byt till Disconnect
    // receiveOK - byt til Waiting
    // asdasdasd eSeder

}

