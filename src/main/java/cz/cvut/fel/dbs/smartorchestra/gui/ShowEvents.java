/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.gui;

import cz.cvut.fel.dbs.smartorchestra.model.entities.Events;
import cz.cvut.fel.dbs.smartorchestra.model.entities.ParticipantState;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Participants;
import java.awt.Font;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Matěj Bartoň
 */
public class ShowEvents extends javax.swing.JPanel {
    
    private List<Events> events;
    private final JLabel noEvents = new JLabel();
    /**
     * Creates new form ShowEvents
     */
    public ShowEvents() {
        initComponents();
        noEvents.setText("Seznam událostí je prázdný.");
        noEvents.setHorizontalAlignment(JLabel.CENTER);
        Font f = noEvents.getFont();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolbar = new javax.swing.JToolBar();
        toolbarContent = new javax.swing.JPanel();
        labelFilterEvents = new javax.swing.JLabel();
        fieldFilterEvents = new javax.swing.JComboBox<>();
        btnAddEvent = new javax.swing.JButton();
        contentScroll = new javax.swing.JScrollPane();
        content = new javax.swing.JPanel();

        setMinimumSize(new java.awt.Dimension(640, 300));
        setPreferredSize(new java.awt.Dimension(640, 300));
        setLayout(new java.awt.BorderLayout());

        toolbar.setFloatable(false);
        toolbar.setRollover(true);
        toolbar.setMaximumSize(new java.awt.Dimension(18, 40));
        toolbar.setMinimumSize(new java.awt.Dimension(18, 40));
        toolbar.setPreferredSize(new java.awt.Dimension(40, 50));

        toolbarContent.setPreferredSize(new java.awt.Dimension(547, 50));

        labelFilterEvents.setText("Zobrazení událostí:");

        fieldFilterEvents.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nadcházející", "Uplynulé", "Všechny události" }));
        fieldFilterEvents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldFilterEventsActionPerformed(evt);
            }
        });

        btnAddEvent.setText("Přidat událost");
        btnAddEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEventActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout toolbarContentLayout = new javax.swing.GroupLayout(toolbarContent);
        toolbarContent.setLayout(toolbarContentLayout);
        toolbarContentLayout.setHorizontalGroup(
            toolbarContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(toolbarContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelFilterEvents)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldFilterEvents, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 218, Short.MAX_VALUE)
                .addComponent(btnAddEvent))
        );
        toolbarContentLayout.setVerticalGroup(
            toolbarContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(toolbarContentLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(toolbarContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFilterEvents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fieldFilterEvents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddEvent, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addContainerGap())
        );

        toolbar.add(toolbarContent);

        add(toolbar, java.awt.BorderLayout.PAGE_START);

        content.setLayout(new javax.swing.BoxLayout(content, javax.swing.BoxLayout.PAGE_AXIS));
        contentScroll.setViewportView(content);

        add(contentScroll, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void fieldFilterEventsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldFilterEventsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldFilterEventsActionPerformed

    private void btnAddEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEventActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddEventActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddEvent;
    private javax.swing.JPanel content;
    private javax.swing.JScrollPane contentScroll;
    private javax.swing.JComboBox<String> fieldFilterEvents;
    private javax.swing.JLabel labelFilterEvents;
    private javax.swing.JToolBar toolbar;
    private javax.swing.JPanel toolbarContent;
    // End of variables declaration//GEN-END:variables

    public JPanel getContent() {
        return content;
    }
    
    public void loadEvents(List<Events> events, Map<Events, ParticipantState> participations){
        this.events = events;
        content.removeAll();
        content.repaint();
        if(events.isEmpty()){
            content.add(noEvents);
            return;
        }
        for(Events event : events){
            EventInfo eventInfo = new EventInfo(event);
            JComboBox<String> eventState = eventInfo.getFieldParticipation();
            
            switch(participations.get(event)){
                case NOT_INVITED:
                    eventState.setSelectedIndex(ParticipantState.NOT_INVITED.intVal());
                    eventState.setEnabled(false);
                    break;
                case NOT_FILLED:
                    eventState.setSelectedIndex(ParticipantState.NOT_FILLED.intVal());
                    eventState.setEnabled(true);
                    break;
                case NOT_COMING:
                    eventState.setSelectedIndex(ParticipantState.NOT_COMING.intVal());
                    eventState.setEnabled(true);
                    break;
                case COMING:
                    eventState.setSelectedIndex(ParticipantState.COMING.intVal());
                    eventState.setEnabled(true);
                    break;
            }
            content.add(eventInfo);
        }
    }
    
    public List<Events> getEvents(){
        return events;
    }
}
