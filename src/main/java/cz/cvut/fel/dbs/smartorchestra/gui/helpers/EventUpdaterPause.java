/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.gui.helpers;

import cz.cvut.fel.dbs.smartorchestra.SmartOrchestra;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Matěj Bartoň
 */
public class EventUpdaterPause implements ActionListener{        
    @Override
    public void actionPerformed(ActionEvent e) {
        //SmartOrchestra.getInstance().getEventUpdater().setWaiting(true);
        SmartOrchestra.getInstance().getEventUpdater().setBlockUpdate(true);
    }
}