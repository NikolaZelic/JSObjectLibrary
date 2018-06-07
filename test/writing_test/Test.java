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
        JSObject ob = new SimpleJSObject(null, null, null);
        ob.set(0, new SimpleJSObject("Pera", null, null));
        ob.set(1, new SimpleJSObject("Zika", null, null));
        
    }
    
} 
