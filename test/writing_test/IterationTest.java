package writing_test;

import java.util.HashMap;
import jsobject.JSObject;
import jsobject.SimpleJSObject;
import parsers.JSObjectIterator;

/**
 *
 * @author Nikola
 */
public class IterationTest
{

    public static void main(String[] args)
    {   
        HashMap<String, JSObject> map = new HashMap<>();
        map.put("name", new SimpleJSObject("Nikola"));
        map.put("lastname", new SimpleJSObject("Zelic"));
        SimpleJSObject s = new SimpleJSObject(map);
        
        JSObject[] array = new JSObject[1];
        array[0] = s;
        
        JSObject ob = new SimpleJSObject( array );
        JSObjectIterator.iterateJSObject(ob);
    }
    
}
