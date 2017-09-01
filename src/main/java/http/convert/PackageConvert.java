package http.convert;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

import http.data.UDTMessage;
import http.data.UDTPackage;
import http.data.UDTRequest;
import http.data.UDTResponse;
import http.utils.ByteReader;
import udt.UDTInputStream;

/**
 * @author lifeng
 */
public class PackageConvert {
    /**
     * 从流中读取package
     *
     * @param in
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static UDTPackage getPackageFromStream(UDTInputStream in) throws Exception {
        UDTPackage packet = new UDTPackage();

        long t1 = System.currentTimeMillis();
        ByteReader.read(packet.getVersion(),in);
        ByteReader.read(packet.getLength(),in);
        int length = ByteReader.byteToInt(packet.getLength());
        byte[] data = new byte[length];
        packet.setData(data);

        t1 = System.currentTimeMillis();
        ByteReader.read(packet.getData(), in);
        return packet;
    }

    /**
     * 根据字节生成package用于发送
     *
     * @param version
     * @param length
     * @param data
     * @return
     */
    private static UDTPackage genPackageFromByte(byte[] version, byte[] length, byte[] data) throws Exception {
        return UDTPackage.toPackage(version, length, data);
    }

    /**
     * 从package中读取response
     *
     * @param packet
     * @param type
     * @param
     * @return
     */
    public static UDTResponse getResponseFromPackage(UDTPackage packet, Type type, String key) throws Exception {
        UDTMessage message = ByteReader.byteToObject(packet.getData());
        return MessageConvert.convertToUDTResponse(message, type, key);
    }

    /**
     * 从package中读取request
     *
     * @param packet
     * @param type
     * @param
     * @return
     */
    public static UDTRequest getRequestFromPackage(UDTPackage packet, Type type, String key) throws Exception {
        UDTMessage message = ByteReader.byteToObject(packet.getData());
        return MessageConvert.convertToUDTRequest(message, type, key);
    }

    /**
     * 根据request生成package
     *
     * @param request
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static UDTPackage genPackageFromRequest(UDTRequest request, HashMap<String, String> headers, String key)
        throws Exception {
        UDTMessage message = MessageConvert.convertToUDTMessage(request, headers, key);
        byte[] requestBytes = ByteReader.objectToByte(message);
        byte[] version = ByteReader.intToByte(request.getMethod(), UDTPackage.getVersionSize());
        byte[] length = ByteReader.intToByte(requestBytes.length, UDTPackage.getLengthSize());
        return genPackageFromByte(version, length, requestBytes);
    }

    /**
     * 根据response生成package
     *
     * @param response
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static UDTPackage genPackageFromResponse(UDTResponse response, HashMap<String, String> headers, String key)
        throws Exception {
        UDTMessage message = MessageConvert.convertToUDTMessage(response, headers, key);
        byte[] requestBytes = ByteReader.objectToByte(message);
        byte[] version = ByteReader.intToByte(1, UDTPackage.getVersionSize());
        byte[] length = ByteReader.intToByte(requestBytes.length, UDTPackage.getLengthSize());
        return genPackageFromByte(version, length, requestBytes);
    }

    public static UDTRequest getRequestFromStream(UDTInputStream in, Type type, String key)
        throws Exception {
        return getRequestFromPackage(getPackageFromStream(in), type, key);
    }

    public static UDTResponse getResponseFromStream(UDTInputStream in, Type type, String key)
        throws Exception {
        return getResponseFromPackage(getPackageFromStream(in), type, key);
    }

    public static UDTMessage getMessageFromPackage(UDTPackage packet)
        throws Exception {
        UDTMessage message = ByteReader.byteToObject(packet.getData());
        return message;
    }

    public static UDTPackage genPackageFromMessage(UDTMessage message) throws Exception {
        byte[] requestBytes = ByteReader.objectToByte(message);
        byte[] version = ByteReader.intToByte(1, UDTPackage.getVersionSize());
        byte[] length = ByteReader.intToByte(requestBytes.length, UDTPackage.getLengthSize());
        return genPackageFromByte(version, length, requestBytes);
    }

}
