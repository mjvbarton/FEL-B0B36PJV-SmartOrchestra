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
