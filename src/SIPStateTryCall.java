import java.io.IOException;
import java.net.InetAddress;

public class SIPStateTryCall extends SIPState {

    public SIPStateTryCall(PeerConnection peerConnection) {
        super(peerConnection);
    }


    public SIPState receiveTRY() {
            System.out.println("Try received");

        //  peerConnection.sendMsg(SIPEv
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
    public void printState() {
        System.out.println("Vi är i tryCall state");
    }

    @Override
    public void sendBUSY() {

    }

    //public receiveinvitesendtro skickar en BUSY returnerar

    // ALLA metoder som svarar mot tillståndsövergångar kan deklareras här och ärvas
    // s
}
