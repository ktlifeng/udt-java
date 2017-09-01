package udp;

import udt.UDTSocket;

/**
 * @author lifeng
 */
public class ServerRunner extends Thread {
    private final UDTSocket socket;

    public ServerRunner(final UDTSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                byte[] b = new byte[1376];
                socket.getInputStream().read(b);
            } catch (Exception e) {
            }
        }
    }
}
