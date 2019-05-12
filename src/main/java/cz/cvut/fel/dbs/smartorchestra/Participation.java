/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra;

import cz.cvut.fel.dbs.smartorchestra.gui.EventDetails;
import cz.cvut.fel.dbs.smartorchestra.gui.EventInfo;
import cz.cvut.fel.dbs.smartorchestra.gui.UserDetails;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Events;
import javax.swing.SwingUtilities;

/**
 *
 * @author Matěj Bartoň
 */
public class Participation implements UIController<EventInfo>{
    private EventInfo controled;
    private Events event;

    public Participation(Events event) {
        this.event = event;
    }

    public void showDetails() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                EventDetails dialog = new EventDetails(SmartOrchestra.getInstance().getMainWin());
                EventSettings es = new EventSettings(dialog);
                dialog.setUIController(es);
                es.loadEvent(event);                
                if(dialog.doModal() == UserDetails.SAVE_DETAILS) {

                }
                SmartOrchestra.getInstance().getMainWin().getUIController().loadEvents();
            }
        });
    }

    @Override
    public void setControlled(EventInfo controled) {
        this.controled = controled;
    }
    
}
