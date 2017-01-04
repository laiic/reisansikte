import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

/**
 * Created by laic on 2016-12-21.
 */
public class Clients implements Runnable {

    private BlockingQueue<String> queue;
    private SIPLogic sipLogic;

    private String myIpAddr;

    public Clients( BlockingQueue<String> queue, SIPLogic sipLogic,  String myIpAddr){

        this.queue = queue;
        this.sipLogic = sipLogic;
        this.myIpAddr = myIpAddr;
    }

    @Override
    public void run() {
        System.out.println("Client handler started");
        String msg;
        Scanner scanner = new Scanner(System.in);


        while (true) {

            System.out.println("Tillbaka här Client");
            sipLogic.printState();
            msg = scanner.nextLine();

            String [] args = msg.split(" ");

            if ( args.length == 3 && args[0].equals("INVITE") ){
                System.out.println("Started new connection");
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            RemoteInfo.addr = args[1];
                            Socket socket = new Socket( args[1], Integer.parseInt(args[2]) );
                            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            out.println("INVITE " + myIpAddr +" " + RemoteInfo.mySipPort);
                            sipLogic.processNextEvent(SIPEvent.SEND_INVITE);

                            Thread t = new Thread(new Runnable() {
                                @Override
                                public void run() {

                                    while (true){

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

                            while ((command =in.readLine()) != null){
                                System.out.println(command);
                                System.out.println("Vem skrev? "+ command);
                                sipLogic.printState();
                                String []args = command.split(" ");

                                if (args[0].equals("OK") && args.length == 2){

                                    RemoteInfo.port = Integer.parseInt(args[1]);
                                    command = "OK";

                                }

                                switch (command){

                                    case "ACK":
                                        //      sipLogic.processNextEvent(SIPEvent.RECEIVE_ACK);
                                        break;
                                    case "TRYING": // " 2345"
                                        sipLogic.processNextEvent(SIPEvent.RECEIVE_TRY);
                                        break;
                                    case "RINGING":
                                        sipLogic.processNextEvent(SIPEvent.RECEIVE_RINGING);
                                        break;
                                    case "OK":
                                        sipLogic.processNextEvent(SIPEvent.RECEIVE_OK);
                                        break;
                                    case "BYE":
                                        sipLogic.processNextEvent(SIPEvent.RECEIVE_BYE);
                                        break;
                                    case "INVITE":  // " 1234"
                                        sipLogic.processNextEvent(SIPEvent.RECEIVE_INVITE);
                                        break;
                                }

                            }

                            t.interrupt();

                        } catch (ConnectException er){
                            System.err.print("You are already in a session: " + er.getMessage());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();

            }else if (msg.equals("BYE")) {

                try {
                    queue.put("BYE");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    sipLogic.processNextEvent(SIPEvent.SEND_BYE);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            else if(msg.equals("OK")) {
//                try {
//                    queue.put("OK");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                try {
                    sipLogic.processNextEvent(SIPEvent.SEND_OK);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //System.out.println("Client handler stopped");
    }
}
