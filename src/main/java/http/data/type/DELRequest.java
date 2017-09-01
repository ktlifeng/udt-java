package http.data.type;

import http.data.HeaderType;
import http.data.UDTRequest;

/**
 * @author lifeng
 */
public class DELRequest extends UDTRequest {
    public DELRequest() {
        this.method = HeaderType.REQUEST_DEL;
    }
}
