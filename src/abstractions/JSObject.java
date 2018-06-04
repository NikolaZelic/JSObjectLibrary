package abstractions;

/**
 * @author Nikola
 */
public interface JSObject
{
    public static final int DATA = 0, ARRAY = 1, OBJECT = 2;
    
    public int type();
    
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
}
