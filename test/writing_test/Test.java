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
        HashMap<String, JSObject> map = new HashMap<>();
        map.put("name", new SimpleJSObject("Nikola"));
        map.put("lastname", new SimpleJSObject("Zelic"));
        JSObject js = new SimpleJSObject(map);
        System.out.println( js.type() );
    }
    
} 
