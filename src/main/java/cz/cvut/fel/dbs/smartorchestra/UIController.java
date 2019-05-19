/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra;

/**
 * This interface represents UI element of <code>Controller</code>. The element controls by <code>View</code>.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 * @param <UIControlled> - Class of the <b>controlled</b> element
 */
public interface UIController<UIControlled> {
    /**
     * This method establishes connection between <code>UIController</code> and <code>UIControlled</code> instances
     * @param controled - <code>UIControlled</code> instance 
     */
    public void setControlled(UIControlled controled);    
}
