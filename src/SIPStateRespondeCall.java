public class SIPStateRespondeCall extends SIPState {

    public SIPStateRespondeCall() {

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
        System.out.println("We are in the RespondeState");
    }

}
