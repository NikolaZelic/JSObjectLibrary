package jsobject;

import jsobject.AbstractJSObject;
import jsobject.JSObject;
import java.util.HashMap;


/**
 * @author Nikola
 */
public class SimpleJSObject extends AbstractJSObject
{
    public SimpleJSObject(Object data)
    {
        super.data = data;
    }

    public SimpleJSObject(Object[] array)
    {
        super();
        super.array = new FlexibleLinkedList<>();
        for(Object i:array)
            super.array.add( new SimpleJSObject(i) );
    }

    public SimpleJSObject(HashMap object)
    {
        super();
        super.object = object;
    }
    
    
    @Override
    public void set(int n, Object data)
    {
        if( n < 0 )
            return;
        
        super.data = null;
        object = null;
        JSObject newOb = new SimpleJSObject(data);
        
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
        
        object.put(key, new SimpleJSObject(data));
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
