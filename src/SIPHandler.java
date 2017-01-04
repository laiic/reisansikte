import java.io.IOException;
import java.net.InetAddress;

public class SIPHandler implements SIPLogic{

    private SIPState currentState; // DATA BEHÖVS FÖR ATT KUNNA SKICKA I PARAMETRAR

    public SIPHandler(PeerConnection peerConnection) {
        currentState = new SIPStateWaiting(peerConnection);
    }

    @Override
    public void processNextEvent(SIPEvent event, InetAddress address) throws IOException {

        switch(event) {

            case SEND_INVITE: currentState = currentState.sendINVITE(address); break;  // döp inspirereat av PDU:erna INVITE, TRO, ACK
            case SEND_TRY: currentState = currentState.sendTRY(address); break;
            case SEND_RINGING: currentState = currentState.sendRINGING(address); break;
            case SEND_ACK: currentState = currentState.sendACK(address); break;
            case SEND_BYE: currentState = currentState.sendBYE(address); break;
            case SEND_OK: currentState = currentState.sendOK(address); break;

            case RECEIVE_INVITE: currentState = currentState.receiveINVITE(address); break;
            case RECEIVE_TRY: currentState = currentState.receiveTRY(address); break;
            case RECEIVE_RINGING: currentState = currentState.receiveRINGING(address); break;
            case RECEIVE_ACK: currentState = currentState.receiveACK(address); break;
            case RECEIVE_BYE: currentState = currentState.receiveBYE(address); break;
            case RECEIVE_OK: currentState = currentState.receiveOK(address); break;
        }
    }

    public void printState() {
        currentState.printState();
    }
}

// DOOR, EXPANDEDDOOR

// IF CURRENTSTATE == RESPONDED INTE!