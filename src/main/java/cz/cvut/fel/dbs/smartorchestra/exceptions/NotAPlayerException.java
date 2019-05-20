/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.exceptions;

/**
 * This exception is thrown when the user is not a member of {@link cz.cvut.fel.dbs.smartorchestra.model.entities.Player
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class NotAPlayerException extends Exception {

    /**
     * Creates a new instance of <code>NotAPlayerException</code> without detail
     * message.
     */
    public NotAPlayerException() {
    }

    /**
     * Constructs an instance of <code>NotAPlayerException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NotAPlayerException(String msg) {
        super(msg);
    }
}
