import java.io.IOException;

public abstract class SIPState {

    protected PeerConnection peerConnection;

    public SIPState ( PeerConnection peerConnection ) {

        this.peerConnection = peerConnection;

    }

    public SIPState sendINVITE() { return this; }

    public  SIPState sendACK(){
        return this;
    }

    public  SIPState sendTRY(){
        return this;
    }

    public  SIPState sendRINGING(){
        return this;
    }

    public  SIPState sendBYE()
    {
        return this;
    }
    public  SIPState sendOK(){
        return this;
    }

    public  SIPState receiveINVITE() { return this;}

    public  SIPState receiveACK(){
        return this;
    }

    public  SIPState receiveTRY(){
        return this;
    }

    public  SIPState receiveRINGING(){
        return this;
    }

    public  SIPState receiveBYE(){
        return this;
    }

    public  SIPState receiveOK(){
        return this;
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

