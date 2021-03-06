import java.io.IOException;
import java.net.Socket;

public class SIPStateDisconnect extends SIPState {


    public SIPStateDisconnect(PeerConnection peerConnection) {
        super(peerConnection);
    }

    @Override
    public SIPState receiveOK(Socket socket, int port) {
        System.out.println("Ok received");
        System.out.println("returning to Wait from Disconnect and closing current port and SetinS");
        peerConnection.setInSession(false);
        System.out.println("INSESSION FALSE ");
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new SIPStateWaiting(peerConnection);
    }

    @Override
    public SIPState receiveRINGING(Socket socket) {
        System.out.println("DON'T WANT RINGING");
        return new SIPStateDisconnect(this.peerConnection);
    }

    @Override
    public SIPState receiveTRY(Socket socket) {
        System.out.println("DON'T WANT TRY");
        return new SIPStateDisconnect(this.peerConnection);
    }

    @Override
    public SIPState receiveINVITE(Socket socket, int port) {
        System.out.println("DON'T WANT INVITE");
        return new SIPStateDisconnect(this.peerConnection);
    }

    @Override
    public SIPState receiveACK(Socket socket) {
        System.out.println("DON'T WANT ACK");
        return new SIPStateDisconnect(this.peerConnection);
    }

    @Override
    public SIPState receiveBYE(Socket socket) {
        System.out.println("DON'T WANT BYE");
        return new SIPStateDisconnect(this.peerConnection);
    }

    @Override
    public SIPState sendINVITE(Socket socket) {
        System.out.println("WHY WOULD YOU WANNA SEND INVITE? ");
        return new SIPStateDisconnect(this.peerConnection);
    }


    @Override
    public String printState() {//oplökölkINVITE 130.229.178.208 5060
        String str = new String("DISCONNECT");
        System.out.println("DISCONNECT state");
        return str;
    }

    public String getState() {
        return "DISCONNECT";
    }

}
