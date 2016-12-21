public class SIPStateDisconnect extends SIPState {

    public SIPStateDisconnect(PeerConnection peerConnection) {
        super(peerConnection);

    }

    @Override
    public SIPState receiveOK() {
        return new SIPStateWaiting(peerConnection);
    }

    @Override
    public void printState() {
        System.out.println("we are in the disconnectState");
    }
}
