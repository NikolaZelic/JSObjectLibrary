package writing_test;

import classes.SimpleJSObject;
import java.util.LinkedList;
import java.util.List;
import abstractions.JSObject;
import java.util.Deque;

public class Test
{
    Object data = 5.0;
    
    public <T> T convertTo()
    {
        return (T) data;
    }
    
    public static void main(String[] args)
    {
//        Number a = new Test().<Number>convertTo();
        Object a = new Test().convertTo();
        System.out.println(a);
    }
    
} 
