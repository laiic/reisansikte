import java.io.IOException;
import java.net.Socket;

/**
 * Created by laic on 2016-12-21.
 */
public interface SIPLogic {

     void processNextEvent(SIPEvent event, Socket socket, int port) throws IOException;

     boolean isFirst();
     void setFirst(boolean first);
     String printState();


}
