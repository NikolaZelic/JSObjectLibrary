package writing_test;

import java.util.Collection;
import jsobject.SimpleJSObject;
import java.util.LinkedList;
import java.util.List;
import jsobject.JSObject;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import jsobject.JSObjectType;

public class Test
{
    Object data = 5.0;
    
    public <T> T convertTo()
    {
        return (T) data;
    }
    
    public static void main(String[] args)
    {
        SimpleJSObject obj = new SimpleJSObject(new String[]{"Pera","Mika"});
        obj.set("name", "Nikola");
        Iterator<Map.Entry<String, JSObject>> i = obj.objectIterator();
        while( i.hasNext() )
        {
            Map.Entry<String, JSObject> next = i.next();
            System.out.println( next.getValue().get() );
        }
    }
    
} 
