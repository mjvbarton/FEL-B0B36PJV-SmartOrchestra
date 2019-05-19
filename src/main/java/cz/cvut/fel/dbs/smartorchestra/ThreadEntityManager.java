package cz.cvut.fel.dbs.smartorchestra;

import javax.persistence.EntityManager;

/**
 * This interface operates with a thread which access the database.
 * @author Matěj Bartoň
 */
public interface ThreadEntityManager {
    /**
     * Returns stored <code>EntityManager</code> instance of the thread
     * @return <code>EntityManager</code> instance of the thread
     */
    public EntityManager getEntityManager();   
}
