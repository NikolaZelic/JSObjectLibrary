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
            action.visit(null, VisitState.SimpleElement);
            return;
        }
        
        int length = js.length();
        
        switch( js.type() )
        {
            case Array: 
                action.visit(js, VisitState.ArrayStart );
                
                if( length > 0 )
                {
                    Iterator<JSObject> i = js.iterator();
                    JSObject next = i.next();
                    
                    if( length == 1 )
                    {
                        if( next != null && next.type() == JSObjectType.Data )
                            action.visit(next, VisitState.ArrayFirstAndLastElement);
                        else
                            rek(next, null);
                    }
                    else
                    {   
                        if( next != null && next.type() == JSObjectType.Data )
                            action.visit(next, VisitState.ArrayFirstElement);
                        else
                            rek(next, null);
                        
                        while( i.hasNext() )
                        {
                            next = i.next();
                            
                            if( next == null )
                                action.visit(null, VisitState.ArrayElement);
                            else if( next.type() == JSObjectType.Data )
                            {
                                if( i.hasNext() )
                                    action.visit(next, VisitState.ArrayElement);
                                else
                                    action.visit(next, VisitState.ArrayLastElement);
                            }
                            else
                                rek(next, null);
                        }
                    }
                }
                
                action.visit(js, VisitState.ArrayEnd );
                return;
            case Object:
                action.visit(js, VisitState.ObjectStart);
                
                if( length > 0 )
                {
                    Iterator<Map.Entry<String, JSObject>> i = js.objectIterator();
                    Map.Entry<String, JSObject> nextObject = i.next();
                    JSObject next = nextObject.getValue();
                    
                    if( length == 1 )
                    {
                        if( next != null && next.type() == JSObjectType.Data )
                            action.visit(next, VisitState.ObjectFirstAndLastElement);
                        else
                            rek(next, null);
                    }
                    else
                    {   
                        if( next != null && next.type() == JSObjectType.Data )
                            action.visit(next, VisitState.ObjectFirstElement);
                        else
                            rek(next, null);
                        
                        while( i.hasNext() )
                        {
                            nextObject = i.next();
                            next = nextObject.getValue();
                            
                            if( next == null )
                                action.visit(null, VisitState.ArrayElement);
                            else if( next.type() == JSObjectType.Data )
                            {
                                if( i.hasNext() )
                                    action.visit(next, VisitState.ArrayElement);
                                else
                                    action.visit(next, VisitState.ArrayLastElement);
                            }
                            else
                                rek(next, null);
                        }
                    }
                }
                
                action.visit(js, VisitState.ObjectEnd);
                return;
            case Data:
                action.visit(js, VisitState.SimpleElement);
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
