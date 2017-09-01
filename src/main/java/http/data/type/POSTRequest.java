package http.data.type;

import http.data.HeaderType;
import http.data.UDTRequest;

/**
 * @author lifeng
 */
public class POSTRequest extends UDTRequest {
    public POSTRequest() {
        this.method = HeaderType.REQUEST_POST;
    }
}
