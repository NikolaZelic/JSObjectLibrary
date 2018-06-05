package parsers;

import java.util.Iterator;
import java.util.Map;
import jsobject.JSObject;
import jsobject.JSObjectType;
/**
 *
 * @author Nikola
 */
public class JSObjectIterator
{
    private void rek( JSObject js )
    {
        switch( js.type() )
        {
            case Undefined: return;
            case Array: 
                Iterator<JSObject> i = js.iterator();
                while( i.hasNext() )
                {
                    rek( i.next() );
                }
                return;
            case Object:
                Iterator<Map.Entry<String, JSObject>> oi = js.objectIterator();
                while( oi.hasNext() )
                {
                    rek( oi.next().getValue() );
                }
                return;
            case Data:
                System.out.println( js.get().toString() );
        }
    }
    
    public void iterate( JSObject js )
    {
        // Setups before rekurzion
        if( js == null || js.type() == JSObjectType.Undefined )
            return;
        
        rek(js);
    }
    
    public static void iterateJSObject( JSObject js )
    {
        new JSObjectIterator().iterate(js);
    }
}
