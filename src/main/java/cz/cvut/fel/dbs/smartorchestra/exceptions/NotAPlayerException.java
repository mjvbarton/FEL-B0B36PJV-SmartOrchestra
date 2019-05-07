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
