package cz.cvut.fel.dbs.smartorchestra;

/**
 * This interface represents UI element of <code>View</code>. The element is controlled by <code>Controller</code>.
 * @author Matěj Bartoň
 * @param <UIController> - Class of the <b>controller</b> element
 */
public interface UIControlled<UIController> {
    /**
     * Establish connection between <code>UIControlled</code> and <code>UIController</code>.
     * @param controller - instance of <code>UIController</code>
     */
    public void setUIController(UIController controller);
    
    /**
     * Interface for <code>UIControlled</code> to get its controller
     * @return controller of <code>UIControlled</code> or <code>null</code> if controller was not set
     */
    public UIController getUIController();
    
}
