package parsers;

import jsobject.JSObject;

/**
 *
 * @author Nikola
 */
public interface JSObjectVisitor<T extends JSObject>
{
    public void visit( T object, VisitState state );
}
