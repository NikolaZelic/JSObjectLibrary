package writing_test;

import classes.SimpleJSObject;
import java.util.LinkedList;
import java.util.List;
import abstractions.JSObject;
import java.util.Deque;
import java.util.HashMap;

public class Test
{
    Object data = 5.0;
    
    public <T> T convertTo()
    {
        return (T) data;
    }
    
    public static void main(String[] args)
    {
        SimpleJSObject ob = new SimpleJSObject(new String[]{"Pera", "Mika", "Laza"});
        
        for( JSObject i : ob )
            System.out.println( i.get() );
        
        ob.set("name", "Nikola");
         for( JSObject i : ob )
            System.out.println( i.get() );
        
    }
    
} 
