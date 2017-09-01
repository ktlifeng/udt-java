package http.data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author lifeng
 */
public class UDTSession implements Serializable {
    private HashMap<String, String> context = new HashMap();
    private UDTRequest request;
    private UDTResponse response;

    public UDTSession(UDTRequest request, UDTResponse response) {
        this.request = request;
        this.response = response;
    }

    public void addContext(String key, String value) {
        this.context.put(key, value);
    }

    public String getContext(String key) {
        return this.context.get(key);
    }

    public UDTRequest getRequest() {
        return request;
    }

    public void setRequest(UDTRequest request) {
        this.request = request;
    }

    public UDTResponse getResponse() {
        return response;
    }

    public void setResponse(UDTResponse response) {
        this.response = response;
    }
}
