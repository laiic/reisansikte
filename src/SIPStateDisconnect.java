public class SIPStateDisconnect extends SIPState {

    public SIPStateDisconnect(PeerConnection peerConnection) {
        super(peerConnection);

    }

    @Override
    public SIPState receiveOK() {

        System.out.println("Ok received");
        return new SIPStateWaiting(peerConnection);
    }

    @Override
    public void printState() {
        System.out.println("we are in the disconnectState");
    }

    @Override
    public SIPState sendBUSY() {
        peerConnection.sendMsg(SIPEvent.SEND_BUSY);
        return new SIPStateDisconnect(peerConnection);
    }
}
