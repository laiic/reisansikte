import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class SIPStateTalking extends SIPState {

    boolean running;
    public SIPStateTalking(PeerConnection peerConnection) {
        super(peerConnection);
        try {
            System.out.println("Remote addr = " + RemoteInfo.addr + " Remote port = " + RemoteInfo.port);
            RemoteInfo.audioStreamUDP.connectTo(InetAddress.getByName(RemoteInfo.addr), RemoteInfo.port);
            RemoteInfo.audioStreamUDP.startStreaming();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                running = true;
                while (running){

                    peerConnection.sendMsg(SIPEvent.SEND_KEEPALIVE);
                    try {
                        Thread.currentThread().sleep(5000);
                    } catch (InterruptedException e) {

                    }
                }

            }
        });
        t.start();
    }

    @Override
    public SIPState receiveINVITE() {
        System.out.println("CAN'T RECEIVE INVITE");
        return new SIPStateTalking(this.peerConnection);
    }

    @Override
    public SIPState receiveACK() {
        System.out.println("DON'T WANT ACK!");
        return new SIPStateTalking(this.peerConnection);
    }

    @Override
    public SIPState receiveOK() {
        System.out.println("DON'T WANT ok!");
        return new SIPStateTalking(this.peerConnection);
    }

    @Override
    public SIPState receiveRINGING() {
        System.out.println("DON'T WANT RINGING");
        return new SIPStateTalking(this.peerConnection);
    }

    @Override
    public SIPState receiveTRY() {
        System.out.println("DON'T WANT TRY");
        return new SIPStateTalking(this.peerConnection);
    }


    @Override
    public SIPState sendBYE() {
        System.out.println("StopStream");
        RemoteInfo.audioStreamUDP.stopStreaming();
        System.out.println("closeportar");
        RemoteInfo.audioStreamUDP.close();
        running = false;
        peerConnection.sendMsg(SIPEvent.SEND_BYE);
        return new SIPStateDisconnect(peerConnection);
    }

    @Override
    public SIPState receiveBYE() {
        System.out.println("StopStream");
        RemoteInfo.audioStreamUDP.stopStreaming();
        System.out.println("closeportar");
        RemoteInfo.audioStreamUDP.close();
        running = false;
        try {
            Thread.currentThread().sleep(200); // väntar lite på att skicka OK
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        peerConnection.sendMsg(SIPEvent.SEND_OK);
        peerConnection.setInSession(false);
        return new SIPStateWaiting(peerConnection);
    }


    @Override
    public String printState() {
        System.out.println("TALKING state");
        return "";
    }

    @Override
    public void sendBUSY(Socket socket) {
        peerConnection.sendMsg(SIPEvent.SEND_BUSY);
    }
}
