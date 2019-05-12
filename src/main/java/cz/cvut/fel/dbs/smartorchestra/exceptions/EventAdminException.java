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
