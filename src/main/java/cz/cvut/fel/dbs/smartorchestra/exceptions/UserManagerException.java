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
public class UserManagerException extends Exception {

    /**
     * Creates a new instance of <code>UserManagerException</code> without
     * detail message.
     */
    public UserManagerException() {
    }

    /**
     * Constructs an instance of <code>UserManagerException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UserManagerException(String msg) {
        super(msg);
    }
}
