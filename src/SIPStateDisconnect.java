public class SIPStateDisconnect extends SIPState {

    public SIPStateDisconnect() {

    }

    @Override
    public SIPState sendInvite() {
        System.out.println("BUSY");
        return null;
    }

    @Override
    public SIPState sendACK() {
        return null;
    }

    @Override
    public SIPState sendTRO() {
        return null;
    }

    @Override
    public SIPState send_BYE() {
        return null;
    }

    @Override
    public SIPState send_OK() {
        return null;
    }

    @Override
    public SIPState receiveInvite() {
        return null;
    }

    @Override
    public SIPState receiveACK() {
        return null;
    }

    @Override
    public SIPState receiveTRO() {
        return null;
    }

    @Override
    public SIPState receive_BYE() {
        return null;
    }

    @Override
    public SIPState receive_OK() {
        return null;
    }

    @Override
    public void printState() {
        System.out.println("we are in the disconnectState");
    }
}
