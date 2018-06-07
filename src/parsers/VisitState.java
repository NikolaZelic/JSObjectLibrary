package parsers;

/**
 *
 * @author Nikola
 */
public enum VisitState
{
    ArrayStart, ArrayFirstElement, ArrayElement, ArrayLastElement, ArrayFirstAndLastElement, ArrayEnd, 
    ObjectStart, ObjectFirstElement, ObjectElement, ObjectLastElement, ObjectFirstAndLastElement, 
    ObjectEnd, SimpleElement;
}
