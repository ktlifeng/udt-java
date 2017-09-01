package http.Filter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lifeng
 */
public abstract class BaseFilter implements IFilter {
    private Map<String,String> context = new HashMap();

    public void addContext(String key,String value){
        context.put(key,value);
    }

    public String getContext(String key){
        return context.get(key);
    }

    public void clearContext(){
        context.clear();
    }
}
