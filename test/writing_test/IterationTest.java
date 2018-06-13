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
        // Test niza
        //        ob.set(0, "Pera");
        //        ob.set(1, "Mika");
        //        ob.set(2, "Zika");
        
        // Test objekta
        //        ob.set("name", "Pera");
        //        ob.set("lastname", "Peric");
        //        ob.set("godiste", 1992);
        
        // Test tabele
        HashMap<String, JSObject> map = new HashMap<>();
        map.put("name", new SimpleJSObject("Nikola", null, null));
        map.put("lastname", new SimpleJSObject("Zelic", null, null));
        SimpleJSObject s1 = new SimpleJSObject(null, null, map);
        
        HashMap<String, JSObject> map2 = new HashMap<>();
        map2.put("name", new SimpleJSObject("Miroslav", null, null));
        map2.put("lastname", new SimpleJSObject("Ilic", null, null));
        SimpleJSObject s2 = new SimpleJSObject(null, null, map2);
//        
        List<JSObject> array = new FlexibleLinkedList<>();
        array.add(s1);
        array.add( s2 );
        
        JSObject ob = new SimpleJSObject( null, null, null );
        
        // Tets duple dubine
        ob.set("data", array);
        
        StringBuilder bld = new StringBuilder();
        JSObjectIterator.iterateJSObject(ob, (e, s, k)-> {
            switch( s )
            {
                case ArrayStart: bld.append("["); break;
                case ArrayEnd: bld.append("]"); break;
                case ArrayElement: 
                case ArrayFirstElement: 
                    bld.append(e); bld.append(","); break;
                case ArrayLastElement:
                case ArrayFirstAndLastElement:
                    bld.append(e); break;
                case ObjectStart: bld.append("{"); break;
                case ObjectEnd: bld.append("}"); break;
                case ObjectElement:
                case ObjectFirstElement:
                    bld.append(k).append(":").append(e).append(","); break;
                case ObjectLastElement:
                case ObjectFirstAndLastElement:
                         bld.append(k).append(":").append(e);
            }
            System.out.println( s + " " + k );
            if( e==null )
            {
                System.out.println("\tnull");
                return;
            }
            if( e.type() == JSObjectType.Data )
                System.out.println("\t"+ e);
        });
        System.out.println(bld);
    }
    
}
