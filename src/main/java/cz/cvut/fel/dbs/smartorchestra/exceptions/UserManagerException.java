/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.exceptions;

/**
 * This exception is for communicating between {@link cz.cvut.fel.dbs.smartorchestra.model.UserManager} and its controllers.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class UserManagerException extends Exception {

    /**
     * Creates a new instance of <code>UserManagerException</code> without
     * detail message.
     */
    public UserManagerException() {
    }

    /**
     * Constructs an instance of <code>UserManagerException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UserManagerException(String msg) {
        super(msg);
    }
}
