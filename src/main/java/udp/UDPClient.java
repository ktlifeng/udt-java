package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;

import udt.UDPEndPoint;
import udt.UDTReceiver;
import udt.packets.DataPacket;

/**
 * @author lifeng
 */
public class UDPClient extends Thread {
    final int num_packets = 1000;
    final int packetSize = UDPEndPoint.DATAGRAM_SIZE;
    int N = 0;

    public UDPClient() throws SocketException {}

    @Override
    public void run() {
        UDTReceiver.connectionExpiryDisabled=true;
        DatagramSocket s= null;
        try {
            s = new DatagramSocket();
        } catch (SocketException e) {
        }
        N = num_packets * packetSize;
        byte[] data = new byte[packetSize];
        new Random().nextBytes(data);
        DatagramPacket dp = new DatagramPacket(new byte[packetSize], packetSize);
        try {
            dp.setAddress(InetAddress.getByName("localhost"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        dp.setPort(65321);
        System.out.println("Sending " + num_packets + " data blocks of <" + packetSize + "> bytes");
        while (true) {
            DataPacket p = new DataPacket();
            p.setData(data);
            dp.setData(p.getEncoded());
            try {
                s.send(dp);
            } catch (IOException e) {
                break;
            }
        }
    }

    public static void main(String[] args) throws SocketException {
        UDPClient s = new UDPClient();
        s.start();
        System.out.println("Server started.");
    }
}
