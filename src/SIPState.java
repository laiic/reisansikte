import java.net.Socket;

public abstract class SIPState {

    protected PeerConnection peerConnection;

    public SIPState(PeerConnection peerConnection) {

        this.peerConnection = peerConnection;

    }

    public SIPState sendINVITE(Socket socket) {
        return new SIPStateWaiting(peerConnection);
    }

    public SIPState sendACK(Socket socket) {
        return new SIPStateWaiting(peerConnection);
    }

    public SIPState sendTRY(Socket socket) {
        return new SIPStateWaiting(peerConnection);
    }

    public SIPState sendRINGING(Socket socket) {
        return new SIPStateWaiting(peerConnection);
    }

    public SIPState sendBYE(Socket socket) {
        return new SIPStateWaiting(peerConnection);
    }

    public SIPState sendOK(Socket socket) {
        return new SIPStateWaiting(peerConnection);
    }

    public SIPState receiveINVITE(Socket socket, int port) {return new SIPStateWaiting(peerConnection);} // RETURN WAITING

    public SIPState receiveACK(Socket socket) {
        return new SIPStateWaiting(peerConnection);
    }

    public SIPState receiveTRY(Socket socket) {return new SIPStateWaiting(peerConnection);}

    public SIPState receiveRINGING(Socket socket) {
        return new SIPStateWaiting(peerConnection);
    }

    public SIPState receiveBYE(Socket socket) {
        return new SIPStateWaiting(peerConnection);
    }

    public SIPState receiveOK(Socket socket, int port) {
        return new SIPStateWaiting(peerConnection);
    }
    public SIPState socketTimeout(Socket socket) {return new SIPStateWaiting(peerConnection);}
    public String printState() {
        return null;
    }
    public SIPState receiveBUSY(){return new SIPStateWaiting(peerConnection);}

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

