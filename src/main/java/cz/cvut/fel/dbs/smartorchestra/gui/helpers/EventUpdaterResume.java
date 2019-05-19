/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.gui.helpers;

import cz.cvut.fel.dbs.smartorchestra.SmartOrchestra;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
 * @author Matěj Bartoň
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