/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.gui;

import cz.cvut.fel.dbs.smartorchestra.model.entities.Events;
import cz.cvut.fel.dbs.smartorchestra.model.entities.ParticipantState;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Participants;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Dialog with participants for certain event.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class ParticipantView extends javax.swing.JDialog {

    /**
     * Creates new dialog ParticipantView
     * @param parent - parent {@code Frame}
     * @param modal - a {@code boolean} value telling us if the dialog is modal or not
     */
    public ParticipantView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    // Editor generated code
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
        setTitle("Docházka");
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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
     * Inner class representing single participant table.
     */
    private final class ParticipantTable extends JTable{
        private final String[] COLUMNS = new String[]{"Příjmení a jméno", "Sekce", "Důvod absence"};
        private final int[] COLUMN_MIN_WIDTH = new int[]{150,80,120};
        private final List<Participants> participants;
        private final ParticipantState state;
        
        /**
         * Creates new {@code ParticipantTable}
         * @param tableView - parent {@code JScrollPane}
         * @param parentTab - parent {@code JPanel} of {@code tableView}
         * @param participants - a {@code List} of participants
         * @param state - a enum {@link ParticipantState} value, which all extracted elements from {@code participants} must undergo
         */
        public ParticipantTable(JScrollPane tableView, JPanel parentTab, List<Participants> participants, ParticipantState state){
            super();
            this.participants = participants;
            this.state = state;
            parentTab.removeAll();
            parentTab.setLayout(new javax.swing.BoxLayout(parentTab, javax.swing.BoxLayout.PAGE_AXIS));
            this.setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));
            setAutoCreateRowSorter(true);
            setModel();
            
            setFillsViewportHeight(true);
            setMinimumSize(new java.awt.Dimension(345, 240));
            setUpdateSelectionOnSort(false);
            tableView.setViewportView(ParticipantTable.this);
            for (int i = 0; i < getColumnModel().getColumnCount(); i++) {
                getColumnModel().getColumn(i).setMinWidth(COLUMN_MIN_WIDTH[i]);
                
            }
            parentTab.add(tableView);
        }
        
        // Fills the table with participants
        private void setModel(){
            DefaultTableModel model = new DefaultTableModel(
                new Object [][] {},
                COLUMNS
            ){
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            };
            // The filling process. Each participant must have the same state as this class
            for(Participants participant : participants){
                if(ParticipantState.fromBoolean(participant.getActive()) == state){
                    model.addRow(new Object[]{
                        String.format("%s %s", 
                                participant.getUsers().getFamilyName(),
                                participant.getUsers().getFirstName()),
                        participant.getSeid().getSectionname(),
                        getMsg(participant)
                    });
                }
            }
            setModel(model);
        }
        
        // Counts the participants
        private int countParticipants(){
            int i = 0;
            for(Participants part : participants){
                if(ParticipantState.fromBoolean(part.getActive()) == state){ i++; }
            }
            return i;
        }
        
        // Formatting of the message extracted from each participant
        private String getMsg(Participants participant){
            switch(ParticipantState.fromBoolean(participant.getActive())){
                case NOT_FILLED:
                    return "";
                case NOT_COMING:
                    return participant.getMessage();
                case COMING:
                    return "---";
                default:
                    throw new InputMismatchException("Invalid enum value.");
            }
        }
        // A string value especially for parent tab caption
        @Override
        public String toString() {
            switch(state){
                case NOT_INVITED:
                case NOT_FILLED:
                return String.format("Neodpověděli (%s)", countParticipants());
                
                case NOT_COMING:
                return String.format("Nezůčastní se (%s)", countParticipants());
                
                case COMING:
                return String.format("Zůčastní se (%s)", countParticipants());
                
                default:
                    throw new InputMismatchException("Invalid enum value.");                        
            }
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

    /**
     * Loads participants into tables of {@link ParticipantState#COMING}, {@link ParticipantState#NOT_COMING} and {@link ParticipantState#NOT_FILLED}
     * @param participants - a {@code List} of {@link Participants} entity
     */
    public void loadParticipants(List<Participants> participants){
        contentPane.removeAll();
        tableComing = new ParticipantTable(viewComing, tabComing, participants, ParticipantState.COMING);
        tabComing.setLayout(new BoxLayout(tabComing, BoxLayout.PAGE_AXIS));
        tableNotComing = new ParticipantTable(viewNotComing, tabNotComing, participants, ParticipantState.NOT_COMING);
        tableNotFilled = new ParticipantTable(viewNotFilled, tabNotFilled, participants, ParticipantState.NOT_FILLED);
        
        contentPane.add(tableComing.toString(), viewComing);
        contentPane.add(tableNotComing.toString(), viewNotComing);
        contentPane.add(tableNotFilled.toString(), viewNotFilled);
    }
    
    /**
     * Loads event information to the dialog. The information is similar to {@link EventInfo}
     * @param event - an {@link Event} entity
     */
    public void loadEvent(Events event){
        eventName.setText(event.getEventname());
        eventPlace.setText(event.getAddrinstitution());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH.mm");
        eventDate.setText(dateFormat.format(event.getBegins()));
        eventTime.setText(String.format("%s - %s",
                timeFormat.format(event.getBegins()),
                timeFormat.format(event.getEnds())));                
    }
    
    /**
     * Dialog run method
     * @return {@code int} 0 in all cases
     */
    public int doModal(){
        return 0;
    }
}