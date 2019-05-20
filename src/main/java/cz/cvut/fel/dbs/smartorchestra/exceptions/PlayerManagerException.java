/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.exceptions;

/**
 * This exception is for communication between {@link cz.cvut.fel.dbs.smartorchestra.model.PlayerManager} and its controllers.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class PlayerManagerException extends Exception {

    /**
     * Creates a new instance of <code>PlayerManagerException</code> without
     * detail message.
     */
    public PlayerManagerException() {
    }

    /**
     * Constructs an instance of <code>PlayerManagerException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PlayerManagerException(String msg) {
        super(msg);
    }
}
