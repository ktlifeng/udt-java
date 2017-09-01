package http.data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author lifeng
 */
public class UDTResponse implements Serializable {
    private HashMap<String,String> headers = new HashMap();
    private String body;

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void addHeader(String key, String value){
        headers.put(key,value);
    }

    public String getHeader(String key){
        return headers.get(key);
    }
}
