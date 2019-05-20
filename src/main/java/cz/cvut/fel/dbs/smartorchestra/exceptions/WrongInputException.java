/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.exceptions;

/**
 * This exception is thrown when validation of form input fails.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class WrongInputException extends Exception {

    /**
     * Creates a new instance of <code>WrongInputException</code> without detail
     * message.
     */
    public WrongInputException() {
    }

    /**
     * Constructs an instance of <code>WrongInputException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public WrongInputException(String msg) {
        super(msg);
    }
}
