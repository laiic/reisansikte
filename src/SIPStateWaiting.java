import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SIPStateWaiting extends SIPState {
    private static Socket socket = null;
    private static PrintWriter out = null;
    private static BufferedReader in = null;


    public SIPStateWaiting() {

    }

    @Override
    public SIPState sendINVITE(String toIP, int port) {

        try {
            socket = new Socket(toIP, port); //INET PORT.. SAMPT PORTNO
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch (IOException e){
            out.println("Connectad");
        }
        return new SIPStateTalking();
    }

    @Override
    public SIPState sendACK(String toIP, int port) {
        return null;
    }

    @Override
    public SIPState sendTRY(String toIP, int port) {
        return null;
    }

    @Override
    public SIPState sendRINGING(String toIP, int port) {
        return null;
    }

    @Override
    public SIPState sendBYE(String toIP, int port) {
        return null;
    }

    @Override
    public SIPState sendOK(String toIP, int port) {
        return null;
    }

    @Override
    public SIPState receiveINVITE() {
        return null;
    }

    @Override
    public SIPState receiveACK() {
        return null;
    }

    @Override
    public SIPState receiveTRY() {
        return null;
    }

    @Override
    public SIPState receiveRINGING() {
        return null;
    }

    @Override
    public SIPState receiveBYE() {
        return null;
    }

    @Override
    public SIPState receiveOK() {
        return null;
    }

    @Override
    public void printState() {
        System.out.println("vi är i waiting");
    }


    // public SIPState receivedInvitesendindTRO()  ... return respondtocall

}
