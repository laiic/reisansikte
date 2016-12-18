/**
 * Created by Reimondo and Daniel on 2016-12-17.
 */
public class SIPPeer {
    public static void main(String args[]) {

        Thread callee = new Thread(new Callee());
        Thread caller = new Thread(new Caller());

        caller.run(); //
        callee.run();  //

    }
}
