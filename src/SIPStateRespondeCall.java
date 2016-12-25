import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;

public class SIPStateRespondeCall extends SIPState {

    public SIPStateRespondeCall(PeerConnection peerConnection) {
        super(peerConnection);
        peerConnection.sendMsg(SIPEvent.SEND_TRY);
        peerConnection.sendMsg(SIPEvent.SEND_RINGING);
        System.out.println("To respond type OK, timeout in 15 sek: ");

        Scanner scanner = new Scanner(System.in);
        String fromUser = null;
        while(( fromUser = scanner.nextLine())!=null) {
            System.out.println("To respond type OK, timeout in 15 sek: ");
            if(fromUser.equals("OK")){
                this.sendOK();
            }
        }

    }

    @Override
    public SIPState receiveACK() {
        return new SIPStateTalking(this.peerConnection);
    }

    @Override
    public void printState() {
        System.out.println("We are in the RespondeState");
    }

    @Override
    public SIPState sendOK() {
        peerConnection.sendMsg(SIPEvent.SEND_OK);
        return new SIPStateRespondeCall(this.peerConnection);
    }

    @Override
    public void sendBUSY() {

    }

}
