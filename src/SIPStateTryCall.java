public class SIPStateTryCall extends SIPState {

    public SIPStateTryCall() {

    }

    @Override
    public SIPState sendINVITE(String toIP, int port) {
        System.out.println("Nu är vi inne i try call ass");
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
        System.out.println("Vi är i tryCall state");
    }

    //public receiveinvitesendtro skickar en BUSY returnerar

    // ALLA metoder som svarar mot tillståndsövergångar kan deklareras här och ärvas
    // s

}
