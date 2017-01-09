import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

/**
 * Created by laic on 2016-12-21.
 */
public class Server implements Runnable {

    private BlockingQueue<String> queue;
    private SIPLogic sipLogic;
    private Scanner scanner = new Scanner(System.in);

    public Server(BlockingQueue<String> queue, SIPLogic sipLogic) {

        this.queue = queue;
        this.sipLogic = sipLogic;
    }

    @Override
    public void run() {

        try {
            ServerSocket serverSocket = new ServerSocket(5060);


            while (true) {

                sipLogic.printState();
                Socket newSocket = serverSocket.accept();



                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            PrintWriter out = new PrintWriter(newSocket.getOutputStream(), true);
                            BufferedReader in = new BufferedReader(new InputStreamReader(newSocket.getInputStream()));
                            newSocket.setSoTimeout(15000);
                            Thread t = new Thread(new Runnable() {

                                @Override
                                public void run() {

                                    while (true) {

                                        String msg = null;
                                        try {
                                            msg = queue.take();
                                        } catch (InterruptedException e) {
                                            break;
                                        }

                                        out.println(msg);

                                    }
                                }
                            });
                            t.start();

                            sipLogic.printState();

                            String command;

                            while ((command = in.readLine()) != null) {
                                System.out.println(command);
                                String[] args = command.split(" ");

                                if (args.length == 3 && args[0].equals("INVITE")) {

                                    if(!sipLogic.isInSession()){
                                        sipLogic.setInSession(true);
                                    }
                                    else{
                                        out.println("i am busy ffs, disconnecting from you");
                                        newSocket.close();
                                        t.interrupt();
                                    }

                                    RemoteInfo.port = Integer.parseInt(args[2]);
                                    RemoteInfo.addr = args[1];

                                    command = "INVITE";



                                    //OM VI FÅR EN INVITE OCH VI ÄR INSESSION,, SKICKA EN BYE.

                                }

                                if (args[0].equals("OK") && args.length == 2) {

                                    RemoteInfo.port = Integer.parseInt(args[1]);
                                    command = "OK";

                                    if (sipLogic.printState().equals("DISCONNECT")) {
                                        newSocket.close();
                                        t.interrupt();
                                    }
                                }

                                switch (command) {

                                    case "ACK":
                                        sipLogic.processNextEvent(SIPEvent.RECEIVE_ACK);
                                        break;
                                    case "TRYING":
                                        sipLogic.processNextEvent(SIPEvent.RECEIVE_TRY);
                                        break;
                                    case "RINGING":
                                        sipLogic.processNextEvent(SIPEvent.RECEIVE_RINGING);
                                        break;
                                    case "OK":

                                        sipLogic.processNextEvent(SIPEvent.RECEIVE_OK);
                                        break;
                                    case "BYE":
                                        sipLogic.setInSession(false);
                                        sipLogic.processNextEvent(SIPEvent.RECEIVE_BYE);
                                        break;
                                    case "INVITE":
                                        sipLogic.processNextEvent(SIPEvent.RECEIVE_INVITE);
                                        break;

                                    //default : fel

                                }

                            }

                            t.interrupt();

                        } catch (SocketTimeoutException e) {
                            System.err.println("Socket timeout: " + e.getMessage());
                            try {
                                sipLogic.processNextEvent(SIPEvent.RECEIVE_BYE);
                                newSocket.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                        } catch (SocketException se) {
                            System.err.println("Socket closed: " + se.getMessage());
                        } catch (IOException e) {
                            e.printStackTrace();
                            try {
                                newSocket.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }

                        System.out.println("Thread finished");
                    }
                }).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
