/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.exceptions;

/**
 * An exception thrown by {@link cz.cvut.fel.dbs.smartorchestra.model.UserManager} when there is no user.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class NonExistingUserException extends UserManagerException {

    /**
     * Creates a new instance of <code>NonExistingUserException</code> without
     * detail message.
     */
    public NonExistingUserException() {
    }

    /**
     * Constructs an instance of <code>NonExistingUserException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NonExistingUserException(String msg) {
        super(msg);
    }
}
