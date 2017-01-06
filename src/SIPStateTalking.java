import java.io.IOException;
import java.net.InetAddress;

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
    public SIPState sendBYE() {
        RemoteInfo.audioStreamUDP.stopStreaming();
        RemoteInfo.audioStreamUDP.close();
        running = false;
        peerConnection.sendMsg(SIPEvent.SEND_BYE);
        return new SIPStateDisconnect(peerConnection);
    }

    @Override
    public SIPState receiveBYE() {
        RemoteInfo.audioStreamUDP.stopStreaming();
        RemoteInfo.audioStreamUDP.close();
        running = false;
        try {
            Thread.currentThread().sleep(200); // väntar lite på att skicka OK
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        peerConnection.sendMsg(SIPEvent.SEND_OK);
        return new SIPStateWaiting(peerConnection);
    }

    @Override
    public void printState() {
        System.out.println("vi är i talking state");
    }

    @Override
    public SIPState sendBUSY() {
        peerConnection.sendMsg(SIPEvent.SEND_BUSY);
        return new SIPStateTalking(peerConnection);
    }
}
