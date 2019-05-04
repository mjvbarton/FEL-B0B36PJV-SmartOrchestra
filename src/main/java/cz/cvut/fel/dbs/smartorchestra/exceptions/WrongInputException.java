/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.exceptions;

/**
 *
 * @author Matěj Bartoň
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
