public class SIPStateTryCall extends SIPState {

    public SIPStateTryCall() {

    }

    @Override
    public SIPState sendInvite() {
        System.out.println("Busy");
        return null;
    }

    @Override
    public SIPState sendACK() {
        return new SIPStateTalking();
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
        return new SIPStateTalking();
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
        System.out.println("Vi är i tryCall state");
    }

    //public receiveinvitesendtro skickar en BUSY returnerar

    // ALLA metoder som svarar mot tillståndsövergångar kan deklareras här och ärvas
    // och per default ge ett ERROR.

}
