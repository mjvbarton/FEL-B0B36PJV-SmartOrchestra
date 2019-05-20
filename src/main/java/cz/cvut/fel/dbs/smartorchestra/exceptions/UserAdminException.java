/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.exceptions;

/**
 * This exception is for communicating between {@link cz.cvut.fel.dbs.smartorchestra.model.UserAdmin} and its controllers.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class UserAdminException extends Exception {

    /**
     * Creates a new instance of <code>UserAdminException</code> without detail
     * message.
     */
    public UserAdminException() {
    }

    /**
     * Constructs an instance of <code>UserAdminException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UserAdminException(String msg) {
        super(msg);
    }
}
