package http.data.type;

import http.data.HeaderType;
import http.data.UDTRequest;

/**
 * @author lifeng
 */
public class GETRequest extends UDTRequest {
    public GETRequest() {
        this.method = HeaderType.REQUEST_GET;
    }
}
