public class SIPStateDisconnect extends SIPState {

    public SIPStateDisconnect(PeerConnection peerConnection) {
        super(peerConnection);

    }

    @Override
    public SIPState receiveOK() {
        System.out.println("Ok received");
        System.out.println("returning to Wait from Disconnect");
        return new SIPStateWaiting(peerConnection);
    }


    @Override
    public String printState() {
        String str = new String("DISCONNECT");
        System.out.println("DISCONNECT state");
        return str;
    }

    public String getState(){
        return "DISCONNECT";
    }

    @Override
    public SIPState sendBUSY() {
        peerConnection.sendMsg(SIPEvent.SEND_BUSY);
        return new SIPStateDisconnect(peerConnection);
    }
}
