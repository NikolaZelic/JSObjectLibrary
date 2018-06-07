package jsobject;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import jsobject.JSObjectType.*;

abstract public class AbstractJSObject implements JSObject
{
    protected Object data;
    protected List<JSObject> array;
    public Map<String, JSObject> object;
    
    @Override
    public JSObjectType type()
    {
        if( data!=null )
            return JSObjectType.Data;
        if( array!=null )
            return JSObjectType.Array;
        if( object!=null )
            return JSObjectType.Object;
        return JSObjectType.Undefined;
    }

    @Override
    public Integer length()
    {
        if( array != null )
            return array.size();
        
        if( object != null )
            return object.size();
        
        return null;
    }
    
    @Override
    public String toString()
    {
        if(data!=null)
            return data.toString();
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
    
    //---------ITERATIONS----------------
    @Override
    public Iterator<JSObject> iterator()
    {
        switch ( type() )
        {
            case Array: return array.iterator();
            case Object: return object.values().iterator();
            default: return null;
        }
    }

    @Override
    public void forEach(Consumer<? super JSObject> action)
    {
        if( type()!=JSObjectType.Array )
            return;
        array.forEach(action);
    }
    
    @Override
    public void forEach(BiConsumer<? super String, ? super JSObject> action)
    {
        if( type()!=JSObjectType.Object )
            return;
        object.forEach(action);
    }

    @Override
    public Iterator<Map.Entry<String, JSObject>> objectIterator()
    {
        if( type() != JSObjectType.Object )
            return null;
        return object.entrySet().iterator();
    }

}
