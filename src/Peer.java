import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by laic on 2016-12-21.
 */
public class Peer implements PeerConnection{

    private BlockingQueue<String> queue;
    private String myIpAddr;
    protected  SIPLogic sipLogic;
    private boolean inSession = false;

    public InetAddress getCallee() {
        return callee;
    }

    public void setCallee(InetAddress callee) {
        this.callee = callee;
    }

    protected InetAddress callee;

    public boolean isInSession() {
        return inSession;
    }

    public void setInSession(boolean inSession) {
        this.inSession = inSession;
    }

    public Peer() throws IOException {

        myIpAddr= InetAddress.getLocalHost().getHostAddress();

        sipLogic = new SIPHandler(this);
        this.queue = new LinkedBlockingQueue<>();
        new Thread(new Server(queue, sipLogic)).start();
        new Thread(new Client(queue, sipLogic, myIpAddr)).start();
//        new Thread(new Server(queue)).start();
//        new Thread(new Client(queue, myIpAddr)).start();
    }

    @Override
    public void sendMsg(SIPEvent msg) {
        try {
            switch (msg){

                case SEND_ACK:
                    queue.put("ACK");
                    break;
                case SEND_BYE:
                    queue.put("BYE");
                    break;
                case SEND_TRY:
                    queue.put("TRYING"); // FELO
                    break;
                case SEND_RINGING:
                    queue.put("RINGING");
                    break;
                case SEND_INVITE:
                    queue.put("INVITE");
                    break;
                case SEND_OK:
                    queue.put("OK " + RemoteInfo.mySipPort);
                    System.out.println("Skickar Ok samt: " + RemoteInfo.mySipPort);
                    break;
                case SEND_KEEPALIVE:
                    queue.put("KEEPALIVE");
                    break;
                case SEND_BUSY:
                    queue.put("BUSY");
                    break;
                default:
                    System.out.println("You ccant send that crap");
        }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
