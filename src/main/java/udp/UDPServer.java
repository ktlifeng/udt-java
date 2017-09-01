package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import udt.UDPEndPoint;

/**
 * @author lifeng
 */
public class UDPServer {
    final int packetSize= UDPEndPoint.DATAGRAM_SIZE;
    final DatagramSocket serverSocket=new DatagramSocket(33001);

    public UDPServer() throws SocketException {}

    public void run() {
        try{
            byte[]buf=new byte[packetSize];
            while(true){
                DatagramPacket dp=new DatagramPacket(buf,buf.length);
                serverSocket.receive(dp);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SocketException {
        UDPServer s = new UDPServer();
        System.out.println("Server started.");
        s.run();
    }
}
