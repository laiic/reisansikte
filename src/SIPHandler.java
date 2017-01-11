import java.io.IOException;
import java.net.Socket;

public class SIPHandler implements SIPLogic {

    private SIPState currentState; // DATA BEHÖVS FÖR ATT KUNNA SKICKA I PARAMETRARxw

    private PeerConnection peerConnection;

    private boolean first = true;

    public boolean isFirst() {
        return first;
    }

    @Override
    public void setFirst(boolean first) {
        this.first = first;
    }

    //HA EN VARIABEL SOM HÅLLER KOLL PÅ OM DET ÄR PERSON SOM VI PRATAR MED

    public SIPHandler(PeerConnection peerConnection) {
        currentState = new SIPStateWaiting(peerConnection);
        this.peerConnection = peerConnection;
    }

    @Override
    public void processNextEvent(SIPEvent event, Socket socket, int port) throws IOException {
        /**
         *  DEn första Inviten Som skickas av en klient ska leda till att det sparas En currentTalker
         *  CURRENT
         *
         * */
//CURRENT
//        if (socket.equals(currentSocket)) {

            switch (event) {
                case SEND_INVITE:
                    currentState = currentState.sendINVITE(socket);
                    break;  // döp inspirereat av PDU:erna INVITE, TRO, ACK
                case SEND_TRY:
                    currentState = currentState.sendTRY(socket);
                    break;
                case SEND_RINGING:
                    currentState = currentState.sendRINGING(socket);
                    break;
                case SEND_ACK:
                    currentState = currentState.sendACK(socket);
                    break;
                case SEND_BYE:
                    currentState = currentState.sendBYE(socket);
                    break;
                case SEND_OK:
                    currentState = currentState.sendOK(socket);
                    break;
                case RECEIVE_INVITE:
                    currentState = currentState.receiveINVITE(socket,port);
                    break;
                case RECEIVE_TRY:
                    currentState = currentState.receiveTRY(socket);
                    break;
                case RECEIVE_RINGING:
                    currentState = currentState.receiveRINGING(socket);
                    break;
                case RECEIVE_ACK:
                    currentState = currentState.receiveACK(socket);
                    break;
                case RECEIVE_BYE:
                    currentState = currentState.receiveBYE(socket);
                    break;
                case RECEIVE_OK:
                    currentState = currentState.receiveOK(socket,port);
                    break;
                case SOCK_TIMEOUT:
                    currentState = currentState.socketTimeout(socket);
                    break;
            }
    }

    public String printState() {
        return currentState.printState();
    }
}

// DOOR, EXPANDEDDOOR

// IF CURRENTSTATE == RESPONDED INTE!