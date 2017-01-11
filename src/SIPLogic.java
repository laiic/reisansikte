import java.io.IOException;
import java.net.Socket;

/**
 * Created by laic on 2016-12-21.
 */
public interface SIPLogic {

     void processNextEvent(SIPEvent event, Socket socket) throws IOException;
     boolean isInSession();
     void setInSession(boolean inSession, Socket newSocket);

     String printState();


}
