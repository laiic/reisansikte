import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SIPHandler implements SIPLogic {

    private SIPState currentState; // DATA BEHÖVS FÖR ATT KUNNA SKICKA I PARAMETRAR

    private PeerConnection peerConnection;

    //HA EN VARIABEL SOM HÅLLER KOLL PÅ OM DET ÄR PERSON SOM VI PRATAR MED

    public SIPHandler(PeerConnection peerConnection) {
        currentState = new SIPStateWaiting(peerConnection);
        this.peerConnection = peerConnection;
    }

    @Override
    public void processNextEvent(SIPEvent event, Socket socket) throws IOException {
        /**
         *  DEn första Inviten Som skickas av en klient ska leda till att det sparas En currentTalker
         *  CURRENT
         *
         * */
//CURRENT
//        if (socket.equals(currentSocket)) {
        if (socket.getInetAddress().equals(RemoteInfo.addr)) {
            switch (event) {
                case SEND_INVITE:
                    currentState = currentState.sendINVITE();
                    break;  // döp inspirereat av PDU:erna INVITE, TRO, ACK
                case SEND_TRY:
                    currentState = currentState.sendTRY();
                    break;
                case SEND_RINGING:
                    currentState = currentState.sendRINGING();
                    break;
                case SEND_ACK:
                    currentState = currentState.sendACK();
                    break;
                case SEND_BYE:
                    currentState = currentState.sendBYE();
                    break;
                case SEND_OK:
                    currentState = currentState.sendOK();
                    break;
                case RECEIVE_INVITE:
                    currentState = currentState.receiveINVITE();
                    break;
                case RECEIVE_TRY:
                    currentState = currentState.receiveTRY();
                    break;
                case RECEIVE_RINGING:
                    currentState = currentState.receiveRINGING();
                    break;
                case RECEIVE_ACK:
                    currentState = currentState.receiveACK();
                    break;
                case RECEIVE_BYE:
                    currentState = currentState.receiveBYE();
                    break;
                case RECEIVE_OK:
                    currentState = currentState.receiveOK();
                    break;
                case SOCK_TIMEOUT:
                    currentState = currentState.socketTimeout();
                    break;
                case TEST_BUSY:
                    if(socket.getInetAddress().equals(RemoteInfo.addr)){
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        System.out.println("Got Something from another dude so i am Sending Close and closing that socket");
                        out.println("BUSY");
                        socket.close();
                    }
            }
        }
        else {;
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Got Something from another dude so i am Sending Close and closing that socket");
            out.println("BUSY");
            socket.close();
        }
    }

    public String printState() {
        return currentState.printState();
    }
}

// DOOR, EXPANDEDDOOR

// IF CURRENTSTATE == RESPONDED INTE!