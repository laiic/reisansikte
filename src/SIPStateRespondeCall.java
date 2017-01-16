import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.net.Socket;

public class SIPStateRespondeCall extends SIPState {

    public SIPStateRespondeCall(PeerConnection peerConnection) {
        super(peerConnection);
    }


    @Override
    public SIPState receiveOK(Socket socket, int port) {
        System.out.println("DON'T WANT OK");
        return new SIPStateRespondeCall(this.peerConnection);
    }

    @Override
    public SIPState receiveBYE(Socket socket) {
        System.out.println("DON'T WANT BYE");
        return new SIPStateRespondeCall(this.peerConnection);
    }

    @Override
    public SIPState receiveRINGING(Socket socket) {
        System.out.println("DON'T WANT RINGING");
        return new SIPStateRespondeCall(this.peerConnection);
    }

    @Override
    public SIPState receiveTRY(Socket socket) {
        System.out.println("DON'T WANT TRY");
        return new SIPStateRespondeCall(this.peerConnection);
    }
    @Override
    public SIPState sendINVITE(Socket socket) {
        System.out.println("WHY WOULD YOU WANNA SEND INVITE? ");
        return new SIPStateRespondeCall(this.peerConnection);
    }

    @Override
    public SIPState receiveINVITE(Socket socket, int port) {
        System.out.println("DON'T WANT INVITE");
        return new SIPStateRespondeCall(this.peerConnection);
    }

    @Override
    public SIPState receiveACK(Socket socket) {
        return new SIPStateTalking(this.peerConnection);
    }

    @Override
    public String printState() {
        System.out.println("RespondeState");
        return "";
    }

    @Override
    public SIPState sendOK(Socket socket) {
        peerConnection.sendMsg(SIPEvent.SEND_OK);
        return new SIPStateRespondeCall(this.peerConnection);
    }

    // RECEIVED INVITE => FEL => TILLBAKA TILL WAITING.

}
