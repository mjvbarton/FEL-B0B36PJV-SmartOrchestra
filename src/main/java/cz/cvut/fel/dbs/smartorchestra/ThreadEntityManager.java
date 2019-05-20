/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 *
 */
package cz.cvut.fel.dbs.smartorchestra;

import javax.persistence.EntityManager;

/**
 * This interface operates with a thread which access the database.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public interface ThreadEntityManager {
    /**
     * Returns stored <code>EntityManager</code> instance of the thread
     * @return <code>EntityManager</code> instance of the thread
     */
    public EntityManager getEntityManager();   
}
