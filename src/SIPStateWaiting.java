import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class SIPStateWaiting extends SIPState {
    private  Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;


    public SIPStateWaiting() {

    }

    @Override
    public SIPState sendINVITE(String toIP, int port) {

        try {
            System.out.println("Connectar");
            InetAddress ipAddress = InetAddress.getByAddress(toIP.getBytes()); //.getAllByName(toIP);
            socket = new Socket(ipAddress, port); //INET PORT.. SAMPT PORTNO
            System.out.println("Connectar1");
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connectar2");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connectar3");


            out.println("tja Jag vill prata med dig gumman");
            out.flush();
            System.out.println("Connectad.. ip:"+toIP + " por:" + port);
            return new SIPStateTalking();
        }catch (IOException e){
            System.out.println("oups ej, Connectad");
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
