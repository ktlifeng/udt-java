package http.data;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author lifeng
 */
public class Buffer {
    //public static LinkedBlockingQueue<UDTPackage> sendList = new LinkedBlockingQueue(20);
    //public static LinkedBlockingQueue<UDTPackage> receiveList = new LinkedBlockingQueue(20);
    public static ArrayBlockingQueue<UDTPackage> sendList = new ArrayBlockingQueue(200);
    public static ArrayBlockingQueue<UDTPackage> receiveList = new ArrayBlockingQueue(200);
}
