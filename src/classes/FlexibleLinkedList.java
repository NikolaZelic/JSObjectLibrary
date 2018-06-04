package classes;

import java.util.LinkedList;
import java.util.function.Consumer;

/**
 *
 * @author Nikola
 */
public class FlexibleLinkedList<E> extends LinkedList<E>
{   
    public FlexibleLinkedList(){
        super();
    }
    
    @Override
    public E set(int index, E element)
    {   
        int last = super.size();
        
        if( index < last )
        {
            super.set(index, element);
        }
        else
        {
            for(int i=last; i<index; i++)
                super.add(null);
            
            super.add(element);
        }
        
        return element;
    }
    
    @Override
    public E get(int n)
    {   
        if( n < 0 )
            return null;
        
        if( n >= size() )
            return null;
        return super.get(n);
    }

//    @Override
//    public void forEach(Consumer<? super E> action)
//    {
//        super.forEach(action); //To change body of generated methods, choose Tools | Templates.
//    }

   
}
