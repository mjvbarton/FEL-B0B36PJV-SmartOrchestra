/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.gui;

import cz.cvut.fel.dbs.smartorchestra.AdminAccessible;
import cz.cvut.fel.dbs.smartorchestra.Participation;
import cz.cvut.fel.dbs.smartorchestra.UIControlled;
import cz.cvut.fel.dbs.smartorchestra.gui.helpers.EventUpdaterPause;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Events;
import cz.cvut.fel.dbs.smartorchestra.model.entities.ParticipantState;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;

/**
 * This class show a brief details of event (date, time of the beginning, time of the end, name of the event, a place identifier).
 * Through this class an application user can submit his participation details (if he is invited) or display participants (if he has rights for) or
 * edit/view event details (depending on the rights).
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class EventInfo extends javax.swing.JPanel implements UIControlled<Participation>, AdminAccessible{
    private Participation controller;
    private final ActionListener listenerParticipation;
        
    /**
     * Initializes the class with the {@link Events} event.
     * @param event - an instance of {@link Events}
     */
    public EventInfo(Events event) {
        setUIController(new Participation(event));
        controller.setControlled(this);
        initComponents();
        updateEventInfo(event);
        
        // Adds listener to the participation state
        listenerParticipation = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setParticipationState(ParticipantState.getParticipantState(fieldParticipation));
            }
        };
        fieldParticipation.addActionListener(listenerParticipation);        
        btnShowDetails.addActionListener(new EventUpdaterPause());
        btnShowParticipants.addActionListener(new EventUpdaterPause());
    }   
        
    /**
     * Updates event information from the {@link Event} given. This method is used for communicating with the controller.
     * @param event -  an instance of {@link Event}
     */     
    public void updateEventInfo(Events event){
        DateFormat f = new SimpleDateFormat("dd.MM.yyyy");
        eventDate.setText(f.format(event.getBegins()));
        
        f = new SimpleDateFormat("HH:mm");
        String timeBegins = f.format(event.getBegins());
        String timeEnds = f.format(event.getEnds());
        
        eventTime.setText(timeBegins + " - " + timeEnds);
        eventName.setText(event.getEventname());
        eventPlace.setText(event.getAddrinstitution());        
    }
    
    // Editor generated method
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        eventDate = new javax.swing.JLabel();
        eventTime = new javax.swing.JLabel();
        eventName = new javax.swing.JLabel();
        eventPlace = new javax.swing.JLabel();
        fieldParticipation = new javax.swing.JComboBox<>();
        btnShowParticipants = new javax.swing.JButton();
        btnShowDetails = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setMaximumSize(new java.awt.Dimension(32767, 100));
        setMinimumSize(new java.awt.Dimension(620, 100));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(620, 100));

        eventDate.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        eventDate.setText("21.12.2019");

        eventTime.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        eventTime.setText("17:00 - 18:30");

        eventName.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        eventName.setForeground(new java.awt.Color(0, 153, 0));
        eventName.setText("Zkouška");

        eventPlace.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        eventPlace.setText("Gymnázium V. B. T. Slaný");

        fieldParticipation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "zadat docházku", "Přijdu", "Nepřijdu" }));

        btnShowParticipants.setText("Docházka");
        btnShowParticipants.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowParticipantsActionPerformed(evt);
            }
        });

        btnShowDetails.setText("Detaily");
        btnShowDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnShowDetailsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eventDate)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(eventTime)))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(eventPlace))
                    .addComponent(eventName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(btnShowDetails)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnShowParticipants)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldParticipation, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventDate)
                    .addComponent(eventName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventTime)
                    .addComponent(eventPlace))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldParticipation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnShowParticipants)
                    .addComponent(btnShowDetails))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowParticipantsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowParticipantsActionPerformed
        // TODO add your handling code here:
        controller.showParticipants();
    }//GEN-LAST:event_btnShowParticipantsActionPerformed

    private void btnShowDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnShowDetailsMouseClicked
        // TODO add your handling code here:
        controller.showDetails();
    }//GEN-LAST:event_btnShowDetailsMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnShowDetails;
    private javax.swing.JButton btnShowParticipants;
    private javax.swing.JLabel eventDate;
    private javax.swing.JLabel eventName;
    private javax.swing.JLabel eventPlace;
    private javax.swing.JLabel eventTime;
    private javax.swing.JComboBox<String> fieldParticipation;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
    
    /**
     * See {@link UIControlled} interface for more information.
     * @return an instance of {@link Participation}
     */
    @Override
    public Participation getUIController() {
        return controller;
    }

    /**
     * See {@link UIControlled} interface for more information.
     * @param controller - an instance of {@link Participation}
     */
    @Override
    public void setUIController(Participation controller) {
        this.controller = controller;
    }

    /**
     * Returns a participant state field for the controller.
     * @return a {@code JComboBox) object
     */
    public JComboBox<String> getFieldParticipation() {
        return fieldParticipation;
    }

    /**
     * Returns a participant state field listener for the controller.
     * @return a {@code ActionListener) object
     */
    public ActionListener getListenerParticipation() {
        return listenerParticipation;
    }    

    /**
     * Enables/disables the administrator to display event participants.
     * For more information see {@link AdminAccessible} interface.
     * @param isEnabled - {@code boolean} value
     */
    @Override
    public void enableAdminAccess(boolean isEnabled) {
        btnShowParticipants.setEnabled(isEnabled);
    }
}
