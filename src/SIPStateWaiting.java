public class SIPStateWaiting extends SIPState {

    public SIPStateWaiting() {

    }

    @Override
    public SIPState sendInvite() {
        return new SIPStateTryCall();
    }

    @Override
    public SIPState sendACK() {
        return null;
    }

    @Override
    public SIPState sendTRO() {
        System.out.println("ERROR");
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
        System.out.println("vi är i waiting");
    }


    // public SIPState receivedInvitesendindTRO()  ... return respondtocall

}
