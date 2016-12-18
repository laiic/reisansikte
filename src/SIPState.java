public abstract class SIPState {

    public abstract SIPState sendINVITE(String toIP, int port);

    public abstract SIPState sendACK(String toIP, int port);

    public abstract SIPState sendTRY(String toIP, int port);

    public abstract SIPState sendRINGING(String toIP, int port);

    public abstract SIPState sendBYE(String toIP, int port);

    public abstract SIPState sendOK(String toIP, int port);

    public abstract SIPState receiveINVITE();

    public abstract SIPState receiveACK();

    public abstract SIPState receiveTRY();

    public abstract SIPState receiveRINGING();

    public abstract SIPState receiveBYE();

    public abstract SIPState receiveOK();

    public abstract void printState();

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

