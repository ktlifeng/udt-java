package http.Filter;

import http.data.UDTRequest;
import http.data.UDTResponse;

/**
 * @author lifeng
 */
public interface IFilter {
    void preFilt(UDTRequest request, UDTResponse response) throws Exception;
    void afterFilt(UDTRequest request, UDTResponse response) throws Exception ;
    void addContext(String key, String value);
    Object getContext(String key);
    void clearContext();
}
