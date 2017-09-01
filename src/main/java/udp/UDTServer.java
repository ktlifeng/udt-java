package udp;

import java.net.InetAddress;
import java.net.SocketException;

import udt.UDTReceiver;
import udt.UDTServerSocket;
import udt.UDTSocket;

/**
 * @author lifeng
 */
public class UDTServer {
    private static UDTServerSocket server;

    public void run() {
        try{
            UDTReceiver.connectionExpiryDisabled=true;
            InetAddress myHost = InetAddress.getLocalHost();
            server = new UDTServerSocket(myHost, 33001);
            while (true) {
                UDTSocket socket = server.accept();
                System.out.println("got new connection address={}");
                new ServerRunner(socket).start();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SocketException {
        UDTServer s = new UDTServer();
        System.out.println("Server started.");
        s.run();
    }
}
