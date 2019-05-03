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
public class WrongUserPassword extends UserManagerException {

    /**
     * Creates a new instance of <code>WrongUserPassword</code> without detail
     * message.
     */
    public WrongUserPassword() {
    }

    /**
     * Constructs an instance of <code>WrongUserPassword</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public WrongUserPassword(String msg) {
        super(msg);
    }
}
