package jsobject;

import java.util.Iterator;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author Nikola
 */
public interface JSObject extends Iterable<JSObject>
{
    public JSObjectType type();
    
    public Object get();
    public Object get(int n);
    public Object get(String key);
    
    public JSObject getNode(int n);
    public JSObject getNode(String key);
    
    public void set(Object data);
    public void set(int n, Object data);
    public void set(int n, JSObject data);
    public void set(String key, Object data);
    public void set(String key, JSObject data);
    
    public Integer length();
    
    public void forEach( BiConsumer<? super String, ? super JSObject> consumer );
    public Iterator<Map.Entry<String, JSObject>> objectIterator();
}
