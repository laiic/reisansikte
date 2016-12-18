public class SIPHandler {

    public enum SIPEvent {SEND_INVITE, SEND_TRY,SEND_RINGING, SEND_ACK, SEND_BYE, SEND_OK, RECEIVE_INVITE, RECEIVE_TRY, RECEIVE_RINGING, RECEIVE_ACK,
            RECEIVE_BYE, RECEIVE_OK}

    private SIPState currentState;

    public SIPHandler() {
        currentState = new SIPStateWaiting();
    }

    public void processNextEvent(SIPEvent event, String ipTo, int port) {

        switch(event) {
            case SEND_INVITE: currentState = currentState.sendINVITE(ipTo,port); break;  // döp inspirereat av PDU:erna INVITE, TRO, ACK
            case SEND_TRY: currentState = currentState.sendTRY(ipTo,port); break;
            case SEND_RINGING: currentState = currentState.sendRINGING(ipTo,port); break;
            case SEND_ACK: currentState = currentState.sendACK(ipTo,port); break;
            case SEND_BYE: currentState = currentState.sendBYE(ipTo,port); break;
            case SEND_OK: currentState = currentState.sendOK(ipTo,port); break;

            case RECEIVE_INVITE: currentState = currentState.receiveINVITE(); break;
            case RECEIVE_TRY: currentState = currentState.receiveTRY(); break;
            case RECEIVE_RINGING: currentState = currentState.receiveRINGING(); break;
            case RECEIVE_ACK: currentState = currentState.receiveACK(); break;
            case RECEIVE_BYE: currentState = currentState.receiveBYE(); break;
            case RECEIVE_OK: currentState = currentState.receiveOK(); break;
        }
    }

    public void printState() {
        currentState.printState();
    }
}

// DOOR, EXPANDEDDOOR

// IF CURRENTSTATE == RESPONDED INTE!