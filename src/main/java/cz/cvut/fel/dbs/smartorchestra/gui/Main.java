/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.gui;

import com.sun.glass.events.MouseEvent;
import java.util.logging.Logger;
import cz.cvut.fel.dbs.smartorchestra.AdminAccessible;
import cz.cvut.fel.dbs.smartorchestra.MainControl;
import cz.cvut.fel.dbs.smartorchestra.SmartOrchestra;
import cz.cvut.fel.dbs.smartorchestra.UIControlled;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Player;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * The main window of the application.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class Main extends javax.swing.JFrame implements UIControlled<MainControl>, AdminAccessible{
    public static final String[] USERS_COLUMN_NAMES = new String [] {
                "Příjmení", "Jméno", "Datum narození:", "Bydliště:", "Telefon:", "Email:", "Sekce:"
            };
    public static final int TAB_EVENTS = 0;
    public static final int TAB_USERS = 1;
    private JLabel activeUserName;
       
    private MainControl controller;
    
    /**
     * Initiates the main window and sets up the controller {@link MainControl}
     */
    public Main() {
        initComponents();        
        setUIController(SmartOrchestra.getInstance().getController(this));
    }

    // Editor generated code
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        content = new javax.swing.JTabbedPane();
        events = new cz.cvut.fel.dbs.smartorchestra.gui.ShowEvents();
        users = new javax.swing.JPanel();
        userToolbar = new javax.swing.JToolBar();
        jPanel2 = new javax.swing.JPanel();
        labelSearchUser = new javax.swing.JLabel();
        fieldSearchUser = new javax.swing.JTextField();
        labelFilterSection = new javax.swing.JLabel();
        fieldFilterSection = new javax.swing.JComboBox<>();
        btnSearchUser = new javax.swing.JButton();
        btnAddUser = new javax.swing.JButton();
        userContent = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        mainMenu = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        fileAddEvent = new javax.swing.JMenuItem();
        fileAddUser = new javax.swing.JMenuItem();
        fileSeparator1 = new javax.swing.JPopupMenu.Separator();
        fileViewProfile = new javax.swing.JMenuItem();
        fileSeparator2 = new javax.swing.JPopupMenu.Separator();
        fileQuit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartOrchestra");
        setIconImage(SmartOrchestra.getInstance().getIco());
        setMinimumSize(new java.awt.Dimension(720, 535));
        setName("SmartOrchestra"); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));

        content.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                contentStateChanged(evt);
            }
        });
        content.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contentMouseClicked(evt);
            }
        });
        content.addTab("Přehled událostí", events);

        users.setLayout(new java.awt.BorderLayout());

        userToolbar.setFloatable(false);
        userToolbar.setRollover(true);
        userToolbar.setMaximumSize(new java.awt.Dimension(18, 40));
        userToolbar.setMinimumSize(new java.awt.Dimension(18, 40));
        userToolbar.setPreferredSize(new java.awt.Dimension(40, 50));

        labelSearchUser.setText("Vyhledat uživatele:");
        labelSearchUser.setEnabled(false);

        fieldSearchUser.setEnabled(false);

        labelFilterSection.setText("Zobraz sekci:");
        labelFilterSection.setEnabled(false);

        fieldFilterSection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "všechny", "1. Housle", "2. Housle", "Viola", "Violoncello", "Kontrabas" }));
        fieldFilterSection.setEnabled(false);
        fieldFilterSection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldFilterSectionActionPerformed(evt);
            }
        });

        btnSearchUser.setText("Hledej");
        btnSearchUser.setEnabled(false);
        btnSearchUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchUserActionPerformed(evt);
            }
        });

        btnAddUser.setText("Přidat uživatele");
        btnAddUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddUserMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(labelSearchUser)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(fieldSearchUser, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 163, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnSearchUser)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 115, Short.MAX_VALUE)
                .add(labelFilterSection)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(fieldFilterSection, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnAddUser, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 123, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(btnSearchUser, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(fieldSearchUser)
                        .add(labelSearchUser, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(labelFilterSection, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(fieldFilterSection, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(btnAddUser)))
                .add(12, 12, 12))
        );

        userToolbar.add(jPanel2);

        users.add(userToolbar, java.awt.BorderLayout.PAGE_START);

        userContent.setMinimumSize(new Dimension(getWidth(), getHeight() - userToolbar.getHeight() - mainMenu.getHeight()));

        userTable.setAutoCreateRowSorter(true);
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
                false, false, false, false, false, false, false
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

        users.add(userContent, java.awt.BorderLayout.CENTER);

        content.addTab("Přehled uživatelů", users);

        getContentPane().add(content, java.awt.BorderLayout.CENTER);

        fileMenu.setText("Soubor");
        fileMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileMenuActionPerformed(evt);
            }
        });

        fileAddEvent.setText("Přidat událost");
        fileAddEvent.setEnabled(false);
        fileMenu.add(fileAddEvent);

        fileAddUser.setText("Přidat uživatele");
        fileAddUser.setEnabled(false);
        fileAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileAddUserActionPerformed(evt);
            }
        });
        fileMenu.add(fileAddUser);
        fileMenu.add(fileSeparator1);

        fileViewProfile.setText("Zobrazit profil");
        fileViewProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fileViewProfileMouseClicked(evt);
            }
        });
        fileViewProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileViewProfileActionPerformed(evt);
            }
        });
        fileMenu.add(fileViewProfile);
        fileMenu.add(fileSeparator2);

        fileQuit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        fileQuit.setText("Ukončit aplikaci");
        fileQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileQuitActionPerformed(evt);
            }
        });
        fileMenu.add(fileQuit);

        mainMenu.add(fileMenu);
        activeUserName = new JLabel("%s %s (%s)");
        activeUserName.setBorder(new EmptyBorder(0,0,0,5));
        Font f = activeUserName.getFont();
        activeUserName.setVerticalTextPosition(JLabel.CENTER);
        activeUserName.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD, 11));
        mainMenu.add(Box.createHorizontalGlue());
        mainMenu.add(activeUserName);

        setJMenuBar(mainMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void fileAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileAddUserActionPerformed
        // TODO add your handling code here:
        controller.addNewUser();
    }//GEN-LAST:event_fileAddUserActionPerformed
    
    private void fileQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileQuitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_fileQuitActionPerformed

    private void fileViewProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileViewProfileMouseClicked
        // TODO add your handling code here:
        controller.showUserProfile();
    }//GEN-LAST:event_fileViewProfileMouseClicked
    @Deprecated  
    private void fileMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileMenuActionPerformed
        
    }//GEN-LAST:event_fileMenuActionPerformed
    
    private void fileViewProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileViewProfileActionPerformed
        // TODO add your handling code here:
        controller.showUserProfile();
    }//GEN-LAST:event_fileViewProfileActionPerformed
    
    // Not in use As of SmartOrchestra 1.0. The method was left there for further development.
    @Deprecated
    private void fieldFilterSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldFilterSectionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldFilterSectionActionPerformed
    
    // Not in use As of SmartOrchestra 1.0. The method was left there for further development.
    @Deprecated
    private void btnSearchUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchUserActionPerformed
    
    // Find user at the user table and show dialog to edit his/hers profile.
    private void userTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userTableMouseClicked
        // TODO add your handling code here:
        Logger.getLogger(this.getClass().getName()).log(Level.FINE, "UserTable row toggled: {0}", userTable.rowAtPoint(evt.getPoint()));
        if(evt.getClickCount() == 2){
            controller.editUserFromTable(userTable.rowAtPoint(evt.getPoint()));
        }
    }//GEN-LAST:event_userTableMouseClicked
    @Deprecated
    private void contentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contentMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_contentMouseClicked
   
    // Loading users/events when changing tabs
    private void contentStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_contentStateChanged
        if(controller != null){
            if(content.getSelectedIndex() == 1){
                controller.loadUsersToTable();
            } else if(content.getSelectedIndex() == 0){
                SmartOrchestra.getInstance().getEventUpdater().interrupt();
            }
        }
    }//GEN-LAST:event_contentStateChanged

    private void btnAddUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddUserMouseClicked
        // TODO add your handling code here:
        controller.addNewUser();
    }//GEN-LAST:event_btnAddUserMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnSearchUser;
    private javax.swing.JTabbedPane content;
    private cz.cvut.fel.dbs.smartorchestra.gui.ShowEvents events;
    private javax.swing.JComboBox<String> fieldFilterSection;
    private javax.swing.JTextField fieldSearchUser;
    private javax.swing.JMenuItem fileAddEvent;
    private javax.swing.JMenuItem fileAddUser;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem fileQuit;
    private javax.swing.JPopupMenu.Separator fileSeparator1;
    private javax.swing.JPopupMenu.Separator fileSeparator2;
    private javax.swing.JMenuItem fileViewProfile;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelFilterSection;
    private javax.swing.JLabel labelSearchUser;
    private javax.swing.JMenuBar mainMenu;
    private javax.swing.JScrollPane userContent;
    private javax.swing.JTable userTable;
    private javax.swing.JToolBar userToolbar;
    private javax.swing.JPanel users;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Setting {@link MainControl} controller. 
     * See {@link UIControlled} interface for more information.
     * @param controller - an instance of {@link MainControl}
     */
    @Override
    public final void setUIController(MainControl controller) {
        this.controller = controller;
    }
    
    /**
     * Gets the controller.
     * See {@link UIControlled} interface for more information.
     * @return an instance of {@link MainControl}
     */
    @Override
    public MainControl getUIController() {
        return controller;
    }
    
    /**
     * Fetches users into user tables. It loads brief details about the user and information about his/hers section.
     * @param users - a {@link Users} entity
     * @param players - a {@code HashMap} with {@code Users.id} as the key and {@link Player} entity as value
     */
    public void fetchUserIntoTable(List<Users> users, HashMap<Long, Player> players){
        DefaultTableModel tableContent = new DefaultTableModel(
                new Object[][] {{}, {}},                        
                Main.USERS_COLUMN_NAMES){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
                    
                };
        tableContent.setRowCount(0);
        // Loading table rows
        for(Users user : users){
            tableContent.addRow(new String[]{
                    user.getFamilyName(),
                    user.getFirstName(),
                    new SimpleDateFormat("dd.MM.yyyy").format(user.getBirthDate()),
                    user.getAddrTown(),
                    user.getPhone(),
                    user.getEmail(),
                    getPlayerSectionName(user, players)
            });
        }
        userTable.setModel(tableContent);
        fetchColumns();
    }
    
    // Gets section name from the HashMap
    private String getPlayerSectionName(Users user, HashMap<Long, Player> players){
        if(players.isEmpty() || players.get(user.getUid()) == null){
            return "---";
        } else {
            return players.get(user.getUid()).getSeid().getSectionname();
        }
    }
    
    // Fetches columns in users table
    private void fetchColumns(){
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
    }

    /**
     * Gets a pointer to {@link ShowEvents} element
     * @return an instance of {@link ShowEvents}
     */
    public ShowEvents getEvents() {
        return events;
    }
    
    /**
     * Gets a pointer to {@code JTabbedPane} tabs.
     * @return an instance of {@code JTabbedPane}
     */
    public JTabbedPane getContent() {
        return content;
    }

    /**
     * Gets the field of active user located in the upper right corner.
     * @return a {@code JLabel} object
     */
    public JLabel getActiveUserName() {
        return activeUserName;
    }
    
    /**
     * Enables/disables tab selection change of {@code Main.content} and {@code Main.fielAddUser} in File menu.
     * It also enables/disables {@link ShowEvents} element.
     * See {@link AdminAccessible} interface for more information.
     * @param isEnabled - a {@code boolean} value
     */
    @Override
    public void enableAdminAccess(boolean isEnabled){
        fileAddUser.setEnabled(isEnabled);
        //fileAddEvent.setEnabled(isEnabled); TODO: next version
        events.enableAdminAccess(isEnabled);
        if(!isEnabled) { content.setSelectedIndex(0); }
        content.setEnabledAt(1, isEnabled);
    }
    
}
