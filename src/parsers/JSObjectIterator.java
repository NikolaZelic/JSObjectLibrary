package parsers;

import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;
import jsobject.JSObject;
import jsobject.JSObjectType;
/**
 *
 * @author Nikola
 */
public class JSObjectIterator
{   
    private JSObjectVisitor<? super JSObject> action;
    
    protected void rek( JSObject js, VisitState previous )
    {   
        if( js == null )
        {
            action.visit(null, VisitState.SimpleElement, null); // Ovo proveriti zasto sam ovako stavio
            return;
        }
        
        int length = js.length();
        
        switch( js.type() )
        {
            case Array: 
                action.visit(js, VisitState.ArrayStart, null );
                
                if( length > 0 )
                {
                    Iterator<JSObject> i = js.iterator();
                    JSObject next = i.next();
                    
                    if( length == 1 )
                    {
                        if( next != null && next.type() == JSObjectType.Data )
                            action.visit(next, VisitState.ArrayFirstAndLastElement, "0");
                        else
                            rek(next, null);
                    }
                    else
                    {   
                        if( next != null && next.type() == JSObjectType.Data )
                            action.visit(next, VisitState.ArrayFirstElement, "0");
                        else
                            rek(next, null);
                        
                        int rb = 1;
                        while( i.hasNext() )
                        {
                            next = i.next();
                            String rbStr = String.valueOf(rb);
                            
                            if( next == null )
                                action.visit(null, VisitState.ArrayElement, rbStr);
                            else if( next.type() == JSObjectType.Data )
                            {
                                if( i.hasNext() )
                                    action.visit(next, VisitState.ArrayElement, rbStr);
                                else
                                    action.visit(next, VisitState.ArrayLastElement, rbStr);
                            }
                            else
                                rek(next, null);
                            
                            rb++;
                        }
                    }
                }
                
                action.visit(js, VisitState.ArrayEnd, null );
                return;
            case Object:
                action.visit(js, VisitState.ObjectStart, null);
                
                if( length > 0 )
                {
                    Iterator<Map.Entry<String, JSObject>> i = js.objectIterator();
                    Map.Entry<String, JSObject> nextObject = i.next();
                    JSObject next = nextObject.getValue();
                    
                    if( length == 1 )
                    {
                        if( next != null && next.type() == JSObjectType.Data )
                            action.visit(next, VisitState.ObjectFirstAndLastElement, nextObject.getKey());
                        else
                            rek(next, null);
                    }
                    else
                    {   
                        if( next != null && next.type() == JSObjectType.Data )
                            action.visit(next, VisitState.ObjectFirstElement, nextObject.getKey());
                        else
                            rek(next, null);
                        
                        while( i.hasNext() )
                        {
                            nextObject = i.next();
                            next = nextObject.getValue();
                            
                            if( next == null )
                                action.visit(null, VisitState.ObjectElement, nextObject.getKey());
                            else if( next.type() == JSObjectType.Data )
                            {
                                if( i.hasNext() )
                                    action.visit(next, VisitState.ObjectElement, nextObject.getKey());
                                else
                                    action.visit(next, VisitState.ObjectLastElement, nextObject.getKey());
                            }
                            else
                                rek(next, null);
                        }
                    }
                }
                
                action.visit(js, VisitState.ObjectEnd, null);
                return;
            case Data:
                action.visit(js, VisitState.SimpleElement, null);
        }
    }
    
    public void iterate( JSObject js, JSObjectVisitor<? super JSObject> action )
    {
        // Setups before rekurzion
        if( js == null || js.type() == JSObjectType.Undefined || action ==null )
            return;
        
        this.action = action;
        
        rek(js, null);
    }
    
    public static void iterateJSObject( JSObject js, JSObjectVisitor<? super JSObject> action )
    {
        new JSObjectIterator().iterate(js, action);
    }
}
