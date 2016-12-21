import java.io.IOException;

/**
 * Created by laic on 2016-12-21.
 */
public class RemoteInfo {

    public static AudioStreamUDP audioStreamUDP;
    public static int mySipPort;

    static {
        try {
            audioStreamUDP = new AudioStreamUDP();
            mySipPort = audioStreamUDP.getLocalPort();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static String addr;
    public static int port;
}
