package abstractions;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

abstract public class AbstractJSObject implements JSObject
{
    protected Object data;
    protected List<JSObject> array;
    protected Map<String, JSObject> object;
    
    @Override
    public int type()
    {
        if( data!=null )
            return JSObject.DATA;
        if( array!=null )
            return JSObject.ARRAY;
        else
            return JSObject.OBJECT;
    }

    @Override
    public Integer length()
    {
        if( array==null )
            return null;
        return array.size();
    }
    
    @Override
    public String toString()
    {
        if(data!=null)
            return data.getClass().toString();
        if(array!=null)
            return array.getClass().toString();
        return object.getClass().toString();
    }

    //---------GETERS----------
    
    public Object get()
    {
        return data;
    }
    
    public Object get(int n)
    {
        JSObject node = getNode( n );
        
        if( node == null )
            return null;
        
        return node.get();
    }
    
    public Object get(String key)
    {
        JSObject n = getNode( key );
        
        if( n == null )
            return null;
        
        return n.get();
    }

    
    public JSObject getNode(int n)
    {
        if( array == null )
            return null;
        
        return array.get(n);
    }
    
    public JSObject getNode(String key)
    {
        if( key==null || key.length()==0 )
            return null;
        
        if( object == null )
            return null;
        
        return object.get( key );
    }
    
    //----------SETERS-------------
    
    public void set(Object data)
    {
        this.data = data;
        array = null;
        object = null;
    }
    
    
}
