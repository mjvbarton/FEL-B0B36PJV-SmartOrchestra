/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.model;

import cz.cvut.fel.dbs.smartorchestra.model.entities.Events;
import java.util.Date;
import java.util.InputMismatchException;
import javax.swing.JComboBox;

/**
 * Enum for filtering events
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public enum EventDateFilter {
    PAST,
    NEXT,
    ALL;
    
    /**
     * Gets {@link EventDateFilter} enum value from combo box given. Returns {@code EventDateFilter.NEXT}
     * if selected index is 0, {@code EventDateFilter.PAST} if selected index is 1 and {@code EventDateFilter.ALL} if selected index is 2.
     * @param comboBox
     * @return a new {@link EventDateFilter} value
     * @throws InputMismatchException if there is greater selected index than 2
     */
    public static EventDateFilter getFromComboBox(JComboBox comboBox){
        switch(comboBox.getSelectedIndex()){
            case 0:
                return NEXT;
            case 1:
                return PAST;
            case 2:
                return ALL;
            default:
                throw new InputMismatchException("Invalid enum value");
        }
    }
    
    /**
     * Gets {@link EventDateFilter} enum value for event given. The value is {@code EventDateFilter.NEXT} if the {@code Event.begins} is
     * greater than date now. The {@code EventDateFilter.PAST} value is returned if not so.
     * @param event - an {@link Events} entity
     * @return a new {@link EventDateFilter} value
     */
    public static EventDateFilter getFromEvent(Events event){
        Date now = new Date();
        if(now.after(event.getBegins())){
            return PAST;
        } else {
            return NEXT;
        }
    }
}
