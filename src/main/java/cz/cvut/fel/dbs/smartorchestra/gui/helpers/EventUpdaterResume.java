/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.gui.helpers;

import cz.cvut.fel.dbs.smartorchestra.SmartOrchestra;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * This is an Window listener which repauses {@link cz.cvut.fel.dbs.smartorchestra.EventUpdater} thread on Window Closed.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class EventUpdaterResume implements WindowListener{

        @Override
        public void windowOpened(WindowEvent e) {}

        @Override
        public void windowClosing(WindowEvent e) {}
        
        @Override
        public void windowIconified(WindowEvent e) {}
        
        @Override
        public void windowDeiconified(WindowEvent e) {}
        
        @Override
        public void windowActivated(WindowEvent e) {}
        
        @Override
        public void windowDeactivated(WindowEvent e) {}
        
        @Override
        public synchronized void windowClosed(WindowEvent e) {
            SmartOrchestra.getInstance().getEventUpdater().interrupt();
            SmartOrchestra.getInstance().getEventUpdater().setBlockUpdate(false);
        }
    }