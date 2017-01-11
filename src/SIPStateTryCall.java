import java.net.Socket;

public class SIPStateTryCall extends SIPState {

    public SIPStateTryCall(PeerConnection peerConnection) {
        super(peerConnection);
    }


    public SIPState receiveTRY() {
            System.out.println("Try received");
        return new SIPStateTryCall(this.peerConnection);
    }

    public SIPState receiveRINGING() {
        System.out.println("RINGING received");
        return new SIPStateTryCall(this.peerConnection);
    }

    public SIPState receiveOK(Socket socket) {
        System.out.println("OK received Srnding ACK");
        peerConnection.sendMsg(SIPEvent.SEND_ACK);
        return new SIPStateTalking(this.peerConnection);
    }

    @Override
    public String printState() {
        System.out.println("tryCall state");
        return "";
    }

    @Override
    public void sendBUSY(Socket socket) {
        peerConnection.sendMsg(SIPEvent.SEND_BUSY);
    }

    //public receiveinvitesendtro skickar en BUSY returnerar

    // ALLA metoder som svarar mot tillståndsövergångar kan deklareras här och ärvas
    // s
}
