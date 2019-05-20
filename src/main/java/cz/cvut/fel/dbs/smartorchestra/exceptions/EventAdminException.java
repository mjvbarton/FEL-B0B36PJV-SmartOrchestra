/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.exceptions;

/**
 * Exception for communicating between {@link cz.cvut.fel.dbs.smartorchestra.model.EventAdmin} and its controllers.
 * @author Matěj Bartoň
 */
public class EventAdminException extends Exception {

    /**
     * Creates a new instance of <code>EventAdminException</code> without detail
     * message.
     */
    public EventAdminException() {
    }

    /**
     * Constructs an instance of <code>EventAdminException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EventAdminException(String msg) {
        super(msg);
    }
}
