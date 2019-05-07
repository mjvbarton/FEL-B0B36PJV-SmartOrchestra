/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.gui;

/**
 *
 * @author Matěj Bartoň
 */
public class ShowUsers extends javax.swing.JPanel {

    /**
     * Creates new form ShowUsers
     */
    public ShowUsers() {
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

        userToolbar = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        labelSearchUser = new javax.swing.JLabel();
        fieldSearchUser = new javax.swing.JTextField();
        labelFilterSection = new javax.swing.JLabel();
        fieldFilterSection = new javax.swing.JComboBox<>();
        btnSearchUser = new javax.swing.JButton();
        btnAddUser = new javax.swing.JButton();
        userContent = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        userToolbar.setFloatable(false);
        userToolbar.setRollover(true);
        userToolbar.setMaximumSize(new java.awt.Dimension(18, 40));
        userToolbar.setMinimumSize(new java.awt.Dimension(18, 40));
        userToolbar.setPreferredSize(new java.awt.Dimension(40, 50));

        labelSearchUser.setText("Vyhledat uživatele:");

        labelFilterSection.setText("Zobraz sekci:");

        fieldFilterSection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "všechny", "1. Housle", "2. Housle", "Viola", "Violoncello", "Kontrabas" }));
        fieldFilterSection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldFilterSectionActionPerformed(evt);
            }
        });

        btnSearchUser.setText("Hledej");
        btnSearchUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchUserActionPerformed(evt);
            }
        });

        btnAddUser.setText("Přidat uživatele");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelSearchUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldSearchUser, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearchUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
                .addComponent(labelFilterSection)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldFilterSection, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddUser)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSearchUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fieldSearchUser)
                        .addComponent(labelSearchUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelFilterSection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fieldFilterSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddUser)))
                .addGap(12, 12, 12))
        );

        userToolbar.add(jPanel1);

        add(userToolbar, java.awt.BorderLayout.PAGE_START);

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Josefína", "Abuzaradová", "31.12.2019", "Klášterec nad Ohří", "+420 777 777 777", "josefina.abuzaradova@ensembleacademia.cz", "příčná flétna"},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Jméno", "Příjmení", "Datum narození:", "Bydliště:", "Telefon:", "Email:", "Sekce:"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        userTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        userTable.setAutoscrolls(false);
        userTable.setMaximumSize(new java.awt.Dimension(1185, 16));
        userTable.setMinimumSize(new java.awt.Dimension(1185, 16));
        userTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        userTable.setShowHorizontalLines(false);
        userTable.setShowVerticalLines(false);
        userTable.getTableHeader().setResizingAllowed(false);
        userTable.getTableHeader().setReorderingAllowed(false);
        userTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userTableMouseClicked(evt);
            }
        });
        userContent.setViewportView(userTable);
        if (userTable.getColumnModel().getColumnCount() > 0) {
            userTable.getColumnModel().getColumn(0).setResizable(false);
            userTable.getColumnModel().getColumn(0).setPreferredWidth(150);
            userTable.getColumnModel().getColumn(1).setResizable(false);
            userTable.getColumnModel().getColumn(1).setPreferredWidth(150);
            userTable.getColumnModel().getColumn(2).setResizable(false);
            userTable.getColumnModel().getColumn(2).setPreferredWidth(120);
            userTable.getColumnModel().getColumn(3).setResizable(false);
            userTable.getColumnModel().getColumn(3).setPreferredWidth(175);
            userTable.getColumnModel().getColumn(4).setResizable(false);
            userTable.getColumnModel().getColumn(4).setPreferredWidth(200);
            userTable.getColumnModel().getColumn(5).setResizable(false);
            userTable.getColumnModel().getColumn(5).setPreferredWidth(250);
            userTable.getColumnModel().getColumn(6).setResizable(false);
            userTable.getColumnModel().getColumn(6).setPreferredWidth(100);
        }

        add(userContent, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void fieldFilterSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldFilterSectionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldFilterSectionActionPerformed

    private void btnSearchUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchUserActionPerformed

    private void userTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userTableMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
            
        }
    }//GEN-LAST:event_userTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnSearchUser;
    private javax.swing.JComboBox<String> fieldFilterSection;
    private javax.swing.JTextField fieldSearchUser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelFilterSection;
    private javax.swing.JLabel labelSearchUser;
    private javax.swing.JScrollPane userContent;
    private javax.swing.JTable userTable;
    private javax.swing.JToolBar userToolbar;
    // End of variables declaration//GEN-END:variables
}
