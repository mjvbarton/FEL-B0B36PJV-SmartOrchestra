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
