public abstract class SIPState {

    public abstract SIPState sendInvite();

    public abstract SIPState sendACK();

    public abstract SIPState sendTRO();

    public abstract SIPState send_BYE();

    public abstract SIPState send_OK();

    public abstract SIPState receiveInvite();

    public abstract SIPState receiveACK();

    public abstract SIPState receiveTRO();

    public abstract SIPState receive_BYE();

    public abstract SIPState receive_OK();

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

