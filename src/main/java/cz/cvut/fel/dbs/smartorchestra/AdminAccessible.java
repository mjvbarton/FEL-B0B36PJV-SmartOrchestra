/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra;

/**
 * Interface which enables UI components to set admin access.
 * @author Matěj Bartoň
 */
public interface AdminAccessible {
    /**
     * List of actions to do when administrator access is enabled (<code>true</code>) or disabled (<code>false</code>)
     * @param isEnabled - if active user is administrator
     */
    public void enableAdminAccess(boolean isEnabled);
}
