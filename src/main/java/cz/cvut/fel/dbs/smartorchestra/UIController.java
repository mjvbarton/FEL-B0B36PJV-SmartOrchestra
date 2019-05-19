package cz.cvut.fel.dbs.smartorchestra;

/**
 * This interface represents UI element of <code>Controller</code>. The element controls by <code>View</code>.
 * @author Matěj Bartoň
 * @param <UIControlled> - Class of the <b>controlled</b> element
 */
public interface UIController<UIControlled> {
    /**
     * This method establishes connection between <code>UIController</code> and <code>UIControlled</code> instances
     * @param controled - <code>UIControlled</code> instance 
     */
    public void setControlled(UIControlled controled);    
}
