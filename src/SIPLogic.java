import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by laic on 2016-12-21.
 */
public interface SIPLogic {

     void processNextEvent(SIPEvent event   ) throws IOException;
     boolean isInSession();
     void setInSession(boolean inSession);

     String printState();
}
