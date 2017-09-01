package http.data;

import java.io.Serializable;

/**
 * @author lifeng
 */
public class UDTPackage implements Serializable {
    private byte[] version = new byte[2];
    private byte[] length = new byte[4];
    /**
     * see@UDTBlockDTO
     */
    private byte[] data;

    public byte[] getVersion() {
        return version;
    }

    public void setVersion(byte[] version) {
        this.version = version;
    }

    public byte[] getLength() {
        return length;
    }

    public void setLength(byte[] length) {
        this.length = length;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public byte[] toByte() {
        int versionLength = this.getVersion().length;
        int lengthLength = this.getLength().length;
        int requestLength = this.getData().length;
        int totalLength = versionLength + lengthLength + requestLength;
        byte[] data = new byte[totalLength];
        System.arraycopy(this.getVersion(), 0, data, 0, versionLength);
        System.arraycopy(this.getLength(), 0, data, versionLength, lengthLength);
        System.arraycopy(this.getData(), 0, data, versionLength + lengthLength, requestLength);
        return data;
    }

    public static UDTPackage toPackage(byte[] version, byte[] length, byte[] data){
        UDTPackage udtPackage = new UDTPackage();
        udtPackage.setVersion(version);
        udtPackage.setLength(length);
        udtPackage.setData(data);
        return udtPackage;
    }

    public static int getVersionSize(){
        UDTPackage temp = new UDTPackage();
        int size = temp.getVersion().length;
        temp = null;
        return size;
    }

    public static int getLengthSize(){
        UDTPackage temp = new UDTPackage();
        int size = temp.getLength().length;
        temp = null;
        return size;
    }
}
