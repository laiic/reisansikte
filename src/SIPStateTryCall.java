import java.io.IOException;
import java.net.InetAddress;

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

    public SIPState receiveOK() {
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
    public SIPState sendBUSY() {
        peerConnection.sendMsg(SIPEvent.SEND_BUSY);
        return new SIPStateTryCall(peerConnection);
    }

    //public receiveinvitesendtro skickar en BUSY returnerar

    // ALLA metoder som svarar mot tillståndsövergångar kan deklareras här och ärvas
    // s
}
