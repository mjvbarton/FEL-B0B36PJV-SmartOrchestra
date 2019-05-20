/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra;

/**
 * Interface which enables UI components to set admin access.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public interface AdminAccessible {
    /**
     * List of actions to do when administrator access is enabled (<code>true</code>) or disabled (<code>false</code>)
     * @param isEnabled - if active user is administrator
     */
    public void enableAdminAccess(boolean isEnabled);
}
