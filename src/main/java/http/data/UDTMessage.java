package http.data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author lifeng
 */
public class UDTMessage implements Serializable {
    private HashMap<String,String> headers = new HashMap();
    private byte[] body;

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public void addHeader(String key, String value){
        headers.put(key,value);
    }

    public String getHeader(String key){
        return headers.get(key);
    }
}
