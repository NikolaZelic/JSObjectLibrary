package classes;

import abstractions.AbstractJSObject;
import abstractions.JSObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;


/**
 * @author Nikola
 */
public class SimpleJSObject extends AbstractJSObject 
        implements Iterable<JSObject>
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

    @Override
    public Iterator<JSObject> iterator()
    {
        if( array != null )
            return array.iterator();
        
        if( object != null )
            return object.values().iterator();
        
        return null;
    }

    @Override
    public void forEach(Consumer<? super JSObject> action)
    {
        if( array != null )
            array.forEach(action);
    }
    
    
    public void forEach(BiConsumer<? super String, ? super JSObject> action)
    {
        if( object != null )
            object.forEach(action);
    }
    
    
}
