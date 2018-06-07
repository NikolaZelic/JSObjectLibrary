package jsobject;

import jsobject.AbstractJSObject;
import jsobject.JSObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Nikola
 */
public class SimpleJSObject extends AbstractJSObject
{
    public SimpleJSObject(Object data, List<JSObject> array, Map<String, JSObject> object)
    {
        super();
        super.data = data;
        super.array = array;
        super.object = object;
    }
    
    @Override
    public void set(int n, Object data)
    {
        if( n < 0 )
            return;
        
        super.data = null;
        object = null;
        JSObject newOb = new SimpleJSObject(data, null, null);
        
        if( array == null )
        {
            array = new FlexibleLinkedList<>();
            array.add( newOb );
            return;
        }
        
        array.set(n, newOb);
    }

    @Override
    public void set(int n, JSObject jsobject)
    {
        if( n < 0 )
            return;
        
        super.data = null;
        object = null;
        
        if( array == null )
        {
            array = new FlexibleLinkedList<>();
            array.add(jsobject );
            return;
        }
        
        array.set(n, jsobject);
    }

    @Override
    public void set(String key, Object data)
    {
        if( object == null )
        {
            object = new HashMap<>();
            super.data = null;
            super.array = null;
        }
        
        object.put(key, new SimpleJSObject(data, null, null));
    }

    @Override
    public void set(String key, JSObject data)
    {
        if( object == null )
        {
            object = new HashMap<>();
            super.data = null;
            super.array = null;
        }
        
        object.put(key, data);
    }

    
    
    
}
