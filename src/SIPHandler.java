public class SIPHandler {

    public enum SIPEvent {SEND_INVITE, SEND_TRO, SEND_ACK, SEND_BYE, SEND_OK, RECEIVE_INVITE, RECEIVE_TRO, RECEIVE_ACK,
            RECEIVE_BYE, RECEIVE_OK}

    private SIPState currentState;

    public SIPHandler() {
        currentState = new SIPStateWaiting();
    }

    public void processNextEvent(SIPEvent event) {

        switch(event) {
            case SEND_INVITE: currentState = currentState.sendInvite(); break;  // döp inspirereat av PDU:erna INVITE, TRO, ACK
            case SEND_TRO: currentState = currentState.sendTRO(); break;
            case SEND_ACK: currentState = currentState.sendACK(); break;
            case SEND_BYE: currentState = currentState.send_BYE(); break;
            case SEND_OK: currentState = currentState.send_OK(); break;
            case RECEIVE_INVITE: currentState = currentState.receiveInvite(); break;
            case RECEIVE_TRO: currentState = currentState.receiveTRO(); break;
            case RECEIVE_ACK: currentState = currentState.receiveACK(); break;
            case RECEIVE_BYE: currentState = currentState.receive_BYE(); break;
            case RECEIVE_OK: currentState = currentState.receive_OK(); break;
        }
    }

    public void printState() {
        currentState.printState();
    }
}

// DOOR, EXPANDEDDOOR

// IF CURRENTSTATE == RESPONDED INTE!