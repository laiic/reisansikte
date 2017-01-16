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

//TEST IF BUSY

             /*   if(!sipLogic.isInSession()){
                    sipLogic.setInSession(true, newSocket);
                }/*
                else{
                    out.println("i am busy, disconnecting from you");
                    newSocket.close();
                    t.interrupt();
                    continue;

                }/***/


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            PrintWriter out = new PrintWriter(newSocket.getOutputStream(), true);
                            BufferedReader in = new BufferedReader(new InputStreamReader(newSocket.getInputStream()));
                            newSocket.setSoTimeout(15000);
                           // Testar om Busy Genom att skicka något direkt till sig själ

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
                                   // RemoteInfo.addr = args[1];
                                    command = "INVITE";

                                    //OM VI FÅR EN INVITE OCH VI ÄR INSESSION,, SKICKA EN BYE.

                                }

                                if (args[0].equals("OK") && args.length == 2) {
                                    command = "OK";
                                }

                                try {
                                    if (args[1].equals(null)) {
                                        args[1] = "" + newSocket.getPort();
                                    }
                                } catch (ArrayIndexOutOfBoundsException ae) {
                                    args = new String[2];
                                    args[1] = "" + newSocket.getPort();
                                }
                                switch (command) {

                                    case "ACK":
                                        sipLogic.processNextEvent(SIPEvent.RECEIVE_ACK, newSocket, Integer.parseInt(args[1]));
                                        break;
                                    case "TRYING":
                                        sipLogic.processNextEvent(SIPEvent.RECEIVE_TRY, newSocket, Integer.parseInt(args[1]));
                                        break;
                                    case "RINGING":
                                        sipLogic.processNextEvent(SIPEvent.RECEIVE_RINGING, newSocket, Integer.parseInt(args[1]));
                                        break;
                                    case "OK":
                                        sipLogic.processNextEvent(SIPEvent.RECEIVE_OK, newSocket, Integer.parseInt(args[1]));
                                        break;
                                    case "BYE":
                                        sipLogic.processNextEvent(SIPEvent.RECEIVE_BYE, newSocket, Integer.parseInt(args[1]));
                                        break;
                                    case "INVITE":
                                        sipLogic.processNextEvent(SIPEvent.RECEIVE_INVITE, newSocket, Integer.parseInt(args[2]));
                                        break;
                                    case "BUSY":
                                        System.out.println("The other guy is busy What to do? well, lets close the socket");
                                        newSocket.close();
                                        break;
                                    case "KEEPALIVE":
                                        System.out.println("KEEPALIVE RECEIVED TO KEEP CONNECTION");
                                        break;
                                    default:
                                        out.println("DU har pratat strunt! så nu stänger vi ner våran uppkoppling");
                                        newSocket.close();
                                        sipLogic.processNextEvent(SIPEvent.SOCK_TIMEOUT, null, 1);
                                        break;
                                }

                            }

                            t.interrupt();

                        } catch (SocketTimeoutException e) {
                            System.err.println("Socket timeout: " + e.getMessage());
                            try {
                                sipLogic.processNextEvent(SIPEvent.SOCK_TIMEOUT, null, 0);
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
