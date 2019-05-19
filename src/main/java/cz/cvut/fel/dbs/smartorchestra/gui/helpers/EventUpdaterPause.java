/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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