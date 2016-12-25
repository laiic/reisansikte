import java.io.IOException;

public class SIPHandler implements SIPLogic{


    private SIPState currentState;

    public SIPHandler(PeerConnection peerConnection) {
        currentState = new SIPStateWaiting(peerConnection);
    }

    @Override
    public void processNextEvent(SIPEvent event) throws IOException {

        switch(event) {

            case SEND_INVITE: currentState = currentState.sendINVITE(); break;  // döp inspirereat av PDU:erna INVITE, TRO, ACK
            case SEND_TRY: currentState = currentState.sendTRY(); break;
            case SEND_RINGING: currentState = currentState.sendRINGING(); break;
            case SEND_ACK: currentState = currentState.sendACK(); break;
            case SEND_BYE: currentState = currentState.sendBYE(); break;
            case SEND_OK: currentState = currentState.sendOK(); break;

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