package http.Filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import http.data.UDTRequest;
import http.data.UDTResponse;

/**
 * @author lifeng
 */
public class FilterChain {
    private final List<IFilter> filters = new ArrayList();
    private Map<String,String> context = new HashMap();

    public void addContext(String key,String value){
        context.put(key,value);
    }

    public void addFilters(BaseFilter filter){
        filters.add(filter);
    }

    public void preFilter(UDTRequest request, UDTResponse response) throws Exception {
        for(int i=0;i<filters.size();i++){
            IFilter filter = filters.get(i);
            for(String key:context.keySet()){
                filter.addContext(key,context.get(key));
            }
            filters.get(i).preFilt(request,response);
        }
    }

    public void postFilter(UDTRequest request, UDTResponse response) throws Exception {
        for(int i=filters.size()-1;i>=0;i--){
            IFilter filter = filters.get(i);
            for(String key:context.keySet()){
                filter.addContext(key,context.get(key));
            }
            filters.get(i).afterFilt(request,response);
        }
    }

}
