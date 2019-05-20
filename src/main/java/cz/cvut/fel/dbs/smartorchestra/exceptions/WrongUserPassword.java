/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.exceptions;

/**
 * This exception is thrown by {@link cz.cvut.fel.dbs.smartorchestra.model.UserManager} when the password is incorrect.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class WrongUserPassword extends UserManagerException {

    /**
     * Creates a new instance of <code>WrongUserPassword</code> without detail
     * message.
     */
    public WrongUserPassword() {
    }

    /**
     * Constructs an instance of <code>WrongUserPassword</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public WrongUserPassword(String msg) {
        super(msg);
    }
}
