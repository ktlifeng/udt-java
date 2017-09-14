package udt.packets;

import udt.util.Util;

/**
 * @author lifeng
 */
public class SendBufferPackets {
    private long time;
    private byte[] data;
    public SendBufferPackets(byte[] data){
        this.data = data;
        time = Util.getCurrentTime();
    }

    public long getTime() {
        return time;
    }

    public byte[] getData() {
        return data;
    }
}
