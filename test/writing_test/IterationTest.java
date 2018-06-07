package writing_test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import jsobject.FlexibleLinkedList;
import jsobject.JSObject;
import jsobject.JSObjectType;
import jsobject.SimpleJSObject;
import parsers.JSObjectIterator;
import parsers.VisitState;

/**
 *
 * @author Nikola
 */
public class IterationTest
{

    public static void main(String[] args)
    {   
        HashMap<String, JSObject> map = new HashMap<>();
        map.put("name", new SimpleJSObject("Nikola", null, null));
        map.put("lastname", new SimpleJSObject("Zelic", null, null));
        SimpleJSObject s1 = new SimpleJSObject(null, null, map);
        
        HashMap<String, JSObject> map2 = new HashMap<>();
        map2.put("name", new SimpleJSObject("Miroslav", null, null));
        map2.put("lastname", new SimpleJSObject("Ilic", null, null));
        SimpleJSObject s2 = new SimpleJSObject(null, null, map2);
        
        List<JSObject> array = new FlexibleLinkedList<>();
        array.add(s1);
        array.add( s2 );
        
        
        JSObject ob = new SimpleJSObject( null, array, null );
        
//        ob.set(0, null);
//        ob.set(1, "Mika");
//        ob.set(3,"Pera");
        
        JSObjectIterator.iterateJSObject(ob, (e, s)-> {
//            switch( s )
//            {
//                case ArrayStart: System.out.println("["); break;
//                case ArrayEnd: System.out.println("]"); break;
//                case ArrayElement: System.out.println(e+", "); break;
//                case ObjectStart: System.out.println("{"); break;
//                case ObjectEnd: System.out.println("}"); break;
//                case ObjectElement: System.out.println("OE: " + e);
//            }
            System.out.println( s );
            if( e==null )
            {
                System.out.println("\tnull");
                return;
            }
            if( e.type() == JSObjectType.Data )
                System.out.println("\t"+ e);
        });
        
    }
    
}
