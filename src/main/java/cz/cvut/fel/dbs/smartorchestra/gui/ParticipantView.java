/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.gui;

import cz.cvut.fel.dbs.smartorchestra.model.entities.ParticipantState;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Participants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Matěj Bartoň
 */
public class ParticipantView extends javax.swing.JDialog {

    /**
     * Creates new form ParticipantView
     */
    public ParticipantView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        eventDate = new javax.swing.JLabel();
        eventTime = new javax.swing.JLabel();
        eventName = new javax.swing.JLabel();
        eventPlace = new javax.swing.JLabel();
        contentPane = new javax.swing.JTabbedPane();
        tabComing = new javax.swing.JPanel();
        viewComing = new javax.swing.JScrollPane();
        tableComing = new javax.swing.JTable();
        tabNotComing = new javax.swing.JPanel();
        viewNotComing = new javax.swing.JScrollPane();
        tableNotComing = new javax.swing.JTable();
        tabNotFilled = new javax.swing.JPanel();
        viewNotFilled = new javax.swing.JScrollPane();
        tableNotFilled = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("[Docházka] - ");
        setMinimumSize(new java.awt.Dimension(345, 377));
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);

        header.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        eventDate.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        eventDate.setText("21.12.2019");

        eventTime.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        eventTime.setText("17:00 - 18:30");

        eventName.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        eventName.setForeground(new java.awt.Color(0, 153, 0));
        eventName.setText("Zkouška");

        eventPlace.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        eventPlace.setText("Gymnázium V. B. T. Slaný");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eventDate)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(eventTime)))
                .addGap(30, 30, 30)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(eventPlace))
                    .addComponent(eventName))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventDate)
                    .addComponent(eventName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventTime)
                    .addComponent(eventPlace))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(header, java.awt.BorderLayout.NORTH);

        tabComing.setLayout(new javax.swing.BoxLayout(tabComing, javax.swing.BoxLayout.PAGE_AXIS));

        tableComing.setAutoCreateRowSorter(true);
        tableComing.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Františka Františka", "příčná flétna", "hraju na violoncello"},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Příjmení a jméno", "Sekce", "Důvod absence"
            }
        ));
        tableComing.setFillsViewportHeight(true);
        tableComing.setMinimumSize(new java.awt.Dimension(345, 240));
        tableComing.setUpdateSelectionOnSort(false);
        viewComing.setViewportView(tableComing);
        if (tableComing.getColumnModel().getColumnCount() > 0) {
            tableComing.getColumnModel().getColumn(0).setMinWidth(150);
            tableComing.getColumnModel().getColumn(1).setMinWidth(80);
            tableComing.getColumnModel().getColumn(2).setMinWidth(120);
        }

        tabComing.add(viewComing);

        contentPane.addTab("Zůčastní se (%s)", tabComing);

        tabNotComing.setLayout(new javax.swing.BoxLayout(tabNotComing, javax.swing.BoxLayout.PAGE_AXIS));

        tableNotComing.setAutoCreateRowSorter(true);
        tableNotComing.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Františka Františka", "příčná flétna", "hraju na violoncello"},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Příjmení a jméno", "Sekce", "Důvod absence"
            }
        ));
        tableNotComing.setFillsViewportHeight(true);
        tableNotComing.setMinimumSize(new java.awt.Dimension(345, 240));
        tableNotComing.setUpdateSelectionOnSort(false);
        viewNotComing.setViewportView(tableNotComing);
        if (tableNotComing.getColumnModel().getColumnCount() > 0) {
            tableNotComing.getColumnModel().getColumn(0).setMinWidth(150);
            tableNotComing.getColumnModel().getColumn(1).setMinWidth(80);
            tableNotComing.getColumnModel().getColumn(2).setMinWidth(120);
        }

        tabNotComing.add(viewNotComing);

        contentPane.addTab("Nezůčastní se (%s)", tabNotComing);

        tabNotFilled.setLayout(new javax.swing.BoxLayout(tabNotFilled, javax.swing.BoxLayout.PAGE_AXIS));

        tableNotFilled.setAutoCreateRowSorter(true);
        tableNotFilled.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Františka Františka", "příčná flétna", "hraju na violoncello"},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Příjmení a jméno", "Sekce", "Důvod absence"
            }
        ));
        tableNotFilled.setFillsViewportHeight(true);
        tableNotFilled.setMinimumSize(new java.awt.Dimension(345, 240));
        tableNotFilled.setUpdateSelectionOnSort(false);
        viewNotFilled.setViewportView(tableNotFilled);
        if (tableNotFilled.getColumnModel().getColumnCount() > 0) {
            tableNotFilled.getColumnModel().getColumn(0).setMinWidth(150);
            tableNotFilled.getColumnModel().getColumn(1).setMinWidth(80);
            tableNotFilled.getColumnModel().getColumn(2).setMinWidth(120);
        }

        tabNotFilled.add(viewNotFilled);

        contentPane.addTab("Neodpověděli (%s)", tabNotFilled);

        getContentPane().add(contentPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ParticipantView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParticipantView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParticipantView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParticipantView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ParticipantView dialog = new ParticipantView(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
    private class ParticipantTable extends DefaultTableModel{

        public ParticipantTable(Object[][] data, Object[] columnNames) {
            super(new Object[][]{{}}, new String[]{
                "Příjmení a jméno",
                "Sekce",
                "Důvod nepřítomnosti"
            });            
        }
        
        public void addRow(Participants participant, ParticipantState state){
            String msg;
            switch(state){
                case COMING:
                    msg = "---";
                    break;
                case NOT_FILLED:
                    msg = "???";
                    break;
                default:
                    msg = participant.getMessage();
            }
            super.addRow(new Object[] {
                String.format("%s %s", 
                        participant.getUsers().getFamilyName(), 
                        participant.getUsers().getFirstName()),
                        participant.getSeid().getSectionname(),
                        msg
                
            });
        }

        @Override
        public void addRow(Object[] rowData) {
            super.addRow(rowData); //To change body of generated methods, choose Tools | Templates.
        }

        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane contentPane;
    private javax.swing.JLabel eventDate;
    private javax.swing.JLabel eventName;
    private javax.swing.JLabel eventPlace;
    private javax.swing.JLabel eventTime;
    private javax.swing.JPanel header;
    private javax.swing.JPanel tabComing;
    private javax.swing.JPanel tabNotComing;
    private javax.swing.JPanel tabNotFilled;
    private javax.swing.JTable tableComing;
    private javax.swing.JTable tableNotComing;
    private javax.swing.JTable tableNotFilled;
    private javax.swing.JScrollPane viewComing;
    private javax.swing.JScrollPane viewNotComing;
    private javax.swing.JScrollPane viewNotFilled;
    // End of variables declaration//GEN-END:variables
}

//public void loadParticipants(){}

//private void fetchTable(JTable )

