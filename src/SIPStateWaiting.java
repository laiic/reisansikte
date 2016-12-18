public class SIPStateWaiting extends SIPState {

    public SIPStateWaiting() {

    }

    @Override
    public SIPState sendINVITE(String toIP, int port) {
        return null;
    }

    @Override
    public SIPState sendACK(String toIP, int port) {
        return null;
    }

    @Override
    public SIPState sendTRY(String toIP, int port) {
        return null;
    }

    @Override
    public SIPState sendRINGING(String toIP, int port) {
        return null;
    }

    @Override
    public SIPState sendBYE(String toIP, int port) {
        return null;
    }

    @Override
    public SIPState sendOK(String toIP, int port) {
        return null;
    }

    @Override
    public SIPState receiveINVITE() {
        return null;
    }

    @Override
    public SIPState receiveACK() {
        return null;
    }

    @Override
    public SIPState receiveTRY() {
        return null;
    }

    @Override
    public SIPState receiveRINGING() {
        return null;
    }

    @Override
    public SIPState receiveBYE() {
        return null;
    }

    @Override
    public SIPState receiveOK() {
        return null;
    }

    @Override
    public void printState() {
        System.out.println("vi är i waiting");
    }


    // public SIPState receivedInvitesendindTRO()  ... return respondtocall

}
