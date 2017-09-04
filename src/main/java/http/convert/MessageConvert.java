package http.convert;

import java.util.HashMap;

import http.data.UDTMessage;
import http.data.UDTRequest;
import http.data.UDTResponse;
import http.utils.ByteReader;
import http.utils.UDTSecretUtil;

/**
 * @author lifeng
 */
public class MessageConvert {

    public static UDTMessage convertToUDTMessage(UDTRequest request, HashMap<String, String> headers, String key)
        throws Exception {
        UDTMessage message = new UDTMessage();
        message.setHeaders(headers);
        message.setBody(UDTSecretUtil.AESEncode(key, ByteReader.objectToByte(request)));
        return message;
    }

    public static UDTMessage convertToUDTMessage(UDTResponse response, HashMap<String, String> headers, String key)
        throws Exception {
        UDTMessage message = new UDTMessage();
        message.setHeaders(headers);
        message.setBody(UDTSecretUtil.AESEncode(key, ByteReader.objectToByte(response)));
        return message;
    }

    public static UDTRequest convertToUDTRequest(UDTMessage message, String key) throws Exception {
        byte[] requestBytes = UDTSecretUtil.AESDecode(key, message.getBody());
        return ByteReader.byteToObject(requestBytes);
    }

    public static UDTResponse convertToUDTResponse(UDTMessage message, String key) throws Exception {
        byte[] requestBytes = UDTSecretUtil.AESDecode(key, message.getBody());
        return ByteReader.byteToObject(requestBytes);
    }
}
