package udp;

import java.net.InetAddress;
import java.util.Random;

/**
 * @author lifeng
 */
public class UDTClient {
    private static udt.UDTClient client;
    private String server;
    public void run() throws Exception {
        if(client == null){
            client = new udt.UDTClient(InetAddress.getByName("localhost"));
            client.connect(server, 33001);
        }
        new Thread(new Runnable() {
            public void run() {
                while (true){
                    System.out.println("STATIS ===>{}"+client.getStatistics().toString());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();
        byte[] b = new byte[1376*1376*20];
        new Random().nextBytes(b);
        while (true) {
            client.getOutputStream().write(b);
        }
    }

    public static void main(String[] args) throws Exception {
        UDTClient s = new UDTClient();
        if(args.length==0){
            s.server="localhost";
        }else{
            s.server = args[0];
        }
        s.run();
    }
}
