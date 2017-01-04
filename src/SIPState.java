import java.io.IOException;
import java.net.InetAddress;

public abstract class SIPState {

    protected PeerConnection peerConnection;

    public SIPState ( PeerConnection peerConnection ) {

        this.peerConnection = peerConnection;

    }

    public SIPState sendINVITE(InetAddress address) { return new SIPStateWaiting(peerConnection); }

    public  SIPState sendACK(InetAddress address){ return new SIPStateWaiting(peerConnection); }

    public  SIPState sendTRY(InetAddress address){
        return new SIPStateWaiting(peerConnection);
    }

    public  SIPState sendRINGING(InetAddress address){
        return new SIPStateWaiting(peerConnection);
    }

    public  SIPState sendBYE(InetAddress address)
    {
        return new SIPStateWaiting(peerConnection);
    }

    public  SIPState sendOK(InetAddress address){
        return new SIPStateWaiting(peerConnection);
    }

    public  SIPState receiveINVITE(InetAddress address) { return new SIPStateWaiting(peerConnection);} // RETURN WAITING

    public  SIPState receiveACK(InetAddress address){
        return new SIPStateWaiting(peerConnection);
    }

    public  SIPState receiveTRY(InetAddress address){
        return new SIPStateWaiting(peerConnection);
    }

    public  SIPState receiveRINGING(InetAddress address){
        return new SIPStateWaiting(peerConnection);
    }

    public  SIPState receiveBYE(InetAddress address){
        return new SIPStateWaiting(peerConnection);
    }

    public  SIPState receiveOK(InetAddress address){
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

