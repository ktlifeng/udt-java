package http.data.type;

import http.data.HeaderType;
import http.data.UDTRequest;

/**
 * @author lifeng
 */
public class PUTRequest extends UDTRequest {
    public PUTRequest() {
        this.method = HeaderType.REQUEST_PUT;
    }
}
