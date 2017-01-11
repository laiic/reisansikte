import java.net.Socket;

public class SIPStateTryCall extends SIPState {

    public SIPStateTryCall(PeerConnection peerConnection) {
        super(peerConnection);
    }


    public SIPState receiveTRY(Socket socket) {
            System.out.println("Try received");

        return new SIPStateTryCall(this.peerConnection);
    }

    @Override
    public SIPState sendINVITE(Socket socket) {
        System.out.println("WHY WOULD YOU WANNA SEND INVITE? ");
        return new SIPStateTryCall(this.peerConnection);
    }

    public SIPState receiveRINGING(Socket socket) {
        System.out.println("RINGING received");
        return new SIPStateTryCall(this.peerConnection);
    }

    public SIPState receiveOK(Socket socket, int port) {
        System.out.println("OK received Srnding ACK");
        peerConnection.sendMsg(SIPEvent.SEND_ACK);
        RemoteInfo.addr = socket.getInetAddress().getHostAddress();
        RemoteInfo.port = port;
        return new SIPStateTalking(this.peerConnection);
    }

    @Override
    public SIPState receiveINVITE(Socket socket, int port) {
        System.out.println("CAN'T RECEIVE INVITE");
        return new SIPStateTryCall(this.peerConnection);
    }

    @Override
    public SIPState receiveACK(Socket socket) {
        System.out.println("DON'T WANT ACK!");
        return new SIPStateTryCall(this.peerConnection);
    }

    @Override
    public SIPState receiveBYE(Socket socket) {
        System.out.println("DON'T WANT BYE");
        return new SIPStateTryCall(this.peerConnection);
    }

    @Override
    public String printState() {
        System.out.println("tryCall state");
        return "";
    }


    //public receiveinvitesendtro skickar en BUSY returnerar

    // ALLA metoder som svarar mot tillståndsövergångar kan deklareras här och ärvas
    // s
}
