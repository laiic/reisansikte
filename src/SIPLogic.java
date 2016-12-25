import java.io.IOException;

/**
 * Created by laic on 2016-12-21.
 */
public interface SIPLogic {

     void processNextEvent(SIPEvent event) throws IOException;

     void printState();
}
