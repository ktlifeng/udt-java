package http.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import http.data.UDTRequest;
import http.exception.UDTEndStreamException;
import udt.UDTInputStream;

/**
 * @author lifeng
 */
public class ByteReader {

    public static void read(byte[] target, UDTInputStream in) throws IOException, UDTEndStreamException {
        int total = 0;
        while (total < target.length) {
            //int num = in.read(target);
            int num = in.read(target,total, target.length-total);
            total += num;
            if (num == 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
            }
            if (num < 0) {
                throw new UDTEndStreamException();
            }
        }
    }

    /**
     * 左高右低 byte[0]是高位 byte[4]是低位
     * 所以 byte[0]<<24位   byte[4]<<0
     */
    public static int byteToInt(byte[] target) {
        int value = 0;
        for (int i = 0; i < target.length; i++) {
            int move = 8 * (target.length - i - 1);
            value = (value | ((target[i] & 0XFF) << move));
        }
        return value;
    }

    /**
     * 左高右低 byte[0]是高位 byte[4]是低位
     * 所以 byte[0]>>24位   byte[4]>>0
     */
    public static byte[] intToByte(int target, int length) {
        byte[] value = new byte[length];
        for (int i = 0; i < length; i++) {
            int move = 8 * (length - i - 1);
            value[i] = (byte)((target >> move) & 0xFF);
        }
        return value;
    }

    /**
     * 对象转数组
     *
     * @param obj
     * @return
     */
    public static byte[] objectToByte(Object obj) throws Exception {
        byte[] bytes = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
        } catch (Exception e) {
            //TODO 修改自定义异常
            throw e;
        } finally {
            try {
                bos.close();
                oos.close();
            } catch (IOException e) {
            }
        }
        return bytes;
    }

    /**
     * 数组转对象
     *
     * @param bytes
     * @return
     */
    public static <T> T byteToObject(byte[] bytes) throws Exception {
        Object obj = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            obj = ois.readObject();
        } catch (Exception e) {
            //TODO 修改自定义异常
            throw e;
        } finally {
            try {
                ois.close();
                bis.close();
            } catch (IOException e) {
            }
        }
        return (T)obj;
    }

    public static void main(String[] args) throws Exception {
        UDTRequest request = new UDTRequest();
        request.setBody("trsatgasdgasdg");
        byte[] a = ByteReader.objectToByte(request);
        request = ByteReader.byteToObject(a);
        System.out.println(request.getBody());
    }
}
