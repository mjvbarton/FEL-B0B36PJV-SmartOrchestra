/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.gui;

import cz.cvut.fel.dbs.smartorchestra.SmartOrchestra;
import cz.cvut.fel.dbs.smartorchestra.UIControlled;
import cz.cvut.fel.dbs.smartorchestra.UserSettings;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Sections;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * This class represents dialog for editing information about user.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class UserDetails extends javax.swing.JDialog implements UIControlled<UserSettings>{
    public static final int SAVE_DETAILS = 1;
    public static final int CANCEL = 0;
    public static final int EXIT = -1;
        
    protected UserSettings controller;
        
    private int exitCode = UserDetails.CANCEL;   
        
    /**
     * Creates new UserDetails dialog
     * @param parent - parent {@code Frame} 
     * @param modal - a {@code boolean} flag if the dialog is modal or not
     */
    public UserDetails(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setUIController(new UserSettings(UserDetails.this));
        
    }

    // Editor generated code
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupConcertMaster = new javax.swing.ButtonGroup();
        btnGroupPermissions = new javax.swing.ButtonGroup();
        content = new javax.swing.JTabbedPane();
        settingsCustom = new javax.swing.JPanel();
        labelFirstName = new javax.swing.JLabel();
        fieldFirstName = new javax.swing.JTextField();
        labelFamilyName = new javax.swing.JLabel();
        fieldFamilyName = new javax.swing.JTextField();
        labelBirthDate = new javax.swing.JLabel();
        fieldBirthDate = new javax.swing.JTextField();
        labelPhone = new javax.swing.JLabel();
        fieldPhone = new javax.swing.JTextField();
        fieldEmail = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        labelAddrStreet = new javax.swing.JLabel();
        fieldAddrStreet = new javax.swing.JTextField();
        labelAddrHouseNumber = new javax.swing.JLabel();
        fieldAddrHouseNumber = new javax.swing.JTextField();
        fieldAddrTown = new javax.swing.JTextField();
        labelAddrTown = new javax.swing.JLabel();
        fieldAddrZipCode = new javax.swing.JTextField();
        labelAddrZipCode = new javax.swing.JLabel();
        infoFirstName = new javax.swing.JLabel();
        infoFamilyName = new javax.swing.JLabel();
        infoBirthDate = new javax.swing.JLabel();
        infoEmail = new javax.swing.JLabel();
        infoPhone = new javax.swing.JLabel();
        infoAddrStreet = new javax.swing.JLabel();
        infoAddrTown = new javax.swing.JLabel();
        infoAddrHouseNumber = new javax.swing.JLabel();
        infoAddrZipCode = new javax.swing.JLabel();
        settingsSection = new javax.swing.JPanel();
        labelSection = new javax.swing.JLabel();
        fieldSection = new javax.swing.JComboBox<>();
        fieldNoFunction = new javax.swing.JRadioButton();
        fieldConcertMaster = new javax.swing.JRadioButton();
        fieldCompConcertMaster = new javax.swing.JRadioButton();
        labelFunction = new javax.swing.JLabel();
        settingsSpecial = new javax.swing.JPanel();
        labelPasswordChange = new javax.swing.JLabel();
        labelCurrentPasswd = new javax.swing.JLabel();
        labelNewPasswd = new javax.swing.JLabel();
        labelConfirmPasswd = new javax.swing.JLabel();
        fieldCurrentPasswd = new javax.swing.JPasswordField();
        fieldNewPasswd = new javax.swing.JPasswordField();
        fieldConfirmPasswd = new javax.swing.JPasswordField();
        btnPasswdChange = new javax.swing.JButton();
        labelPermissions = new javax.swing.JLabel();
        fieldPermCommonAccount = new javax.swing.JRadioButton();
        fieldPermSpecialAccount = new javax.swing.JRadioButton();
        btnDeleteAccount = new javax.swing.JButton();
        infoCurrentPasswd = new javax.swing.JLabel();
        infoNewPasswd = new javax.swing.JLabel();
        infoConfirmPasswd = new javax.swing.JLabel();
        caption = new javax.swing.JLabel();
        controls = new javax.swing.JPanel();
        btnSubmit = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nastavení uživatele");
        setAlwaysOnTop(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(null);
        setMaximumSize(new java.awt.Dimension(530, 430));
        setMinimumSize(new java.awt.Dimension(530, 430));
        setModal(true);
        setResizable(false);
        setSize(new java.awt.Dimension(530, 430));

        labelFirstName.setText("Jméno:");

        labelFamilyName.setText("Příjmení:");

        labelBirthDate.setText("Datum narození:");
        labelBirthDate.setToolTipText("");

        labelPhone.setText("Telefon:");

        fieldEmail.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                UserDetails.this.getUIController().checkEmail();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                UserDetails.this.getUIController().checkEmail();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                UserDetails.this.getUIController().checkEmail();
            }
        });
        fieldEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldEmailActionPerformed(evt);
            }
        });

        labelEmail.setText("Email:");

        labelAddrStreet.setText("Ulice:");

        labelAddrHouseNumber.setText("Číslo popisné:");

        labelAddrTown.setText("Město:");

        labelAddrZipCode.setText("PSČ:");

        infoFirstName.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        infoFirstName.setForeground(new java.awt.Color(255, 0, 0));

        infoFamilyName.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        infoFamilyName.setForeground(new java.awt.Color(255, 0, 0));

        infoBirthDate.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        infoBirthDate.setForeground(new java.awt.Color(255, 0, 0));

        infoEmail.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        infoEmail.setForeground(new java.awt.Color(255, 0, 0));

        infoPhone.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        infoPhone.setForeground(new java.awt.Color(255, 0, 0));

        infoAddrStreet.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        infoAddrStreet.setForeground(new java.awt.Color(255, 0, 0));

        infoAddrTown.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        infoAddrTown.setForeground(new java.awt.Color(255, 0, 0));

        infoAddrHouseNumber.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        infoAddrHouseNumber.setForeground(new java.awt.Color(255, 0, 0));

        infoAddrZipCode.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        infoAddrZipCode.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout settingsCustomLayout = new javax.swing.GroupLayout(settingsCustom);
        settingsCustom.setLayout(settingsCustomLayout);
        settingsCustomLayout.setHorizontalGroup(
            settingsCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsCustomLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(settingsCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelAddrHouseNumber)
                    .addComponent(labelAddrStreet)
                    .addComponent(labelPhone)
                    .addComponent(labelBirthDate)
                    .addComponent(labelFamilyName)
                    .addComponent(labelFirstName)
                    .addComponent(labelEmail)
                    .addComponent(labelAddrTown)
                    .addComponent(labelAddrZipCode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settingsCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(settingsCustomLayout.createSequentialGroup()
                        .addComponent(fieldAddrZipCode, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(infoAddrZipCode))
                    .addGroup(settingsCustomLayout.createSequentialGroup()
                        .addGroup(settingsCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fieldFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(fieldFamilyName)
                            .addGroup(settingsCustomLayout.createSequentialGroup()
                                .addComponent(fieldBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(infoBirthDate))
                            .addComponent(fieldEmail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(settingsCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(infoFirstName)
                            .addComponent(infoFamilyName)
                            .addComponent(infoEmail)))
                    .addGroup(settingsCustomLayout.createSequentialGroup()
                        .addComponent(fieldAddrHouseNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(infoAddrHouseNumber))
                    .addGroup(settingsCustomLayout.createSequentialGroup()
                        .addGroup(settingsCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(fieldAddrTown, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(fieldAddrStreet, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(settingsCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(infoAddrStreet)
                            .addComponent(infoAddrTown)))
                    .addGroup(settingsCustomLayout.createSequentialGroup()
                        .addComponent(fieldPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(infoPhone)))
                .addContainerGap(191, Short.MAX_VALUE))
        );
        settingsCustomLayout.setVerticalGroup(
            settingsCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsCustomLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(settingsCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(settingsCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fieldFirstName)
                        .addComponent(infoFirstName))
                    .addComponent(labelFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settingsCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(settingsCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fieldFamilyName)
                        .addComponent(infoFamilyName))
                    .addComponent(labelFamilyName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settingsCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(settingsCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fieldBirthDate)
                        .addComponent(infoBirthDate))
                    .addComponent(labelBirthDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(settingsCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldPhone)
                    .addComponent(infoPhone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settingsCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(infoEmail))
                .addGap(18, 18, 18)
                .addGroup(settingsCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(settingsCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fieldAddrStreet)
                        .addComponent(infoAddrStreet))
                    .addComponent(labelAddrStreet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settingsCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(settingsCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fieldAddrHouseNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(infoAddrHouseNumber))
                    .addComponent(labelAddrHouseNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settingsCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldAddrTown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAddrTown)
                    .addComponent(infoAddrTown))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settingsCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldAddrZipCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAddrZipCode)
                    .addComponent(infoAddrZipCode))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        content.addTab("Obecné", settingsCustom);

        labelSection.setText("Sekce:");

        fieldSection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "bez sekce", "I. housle", "II. housle", "Viola", "Violoncello", "Kontrabas", " " }));
        fieldSection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldSectionActionPerformed(evt);
            }
        });

        btnGroupConcertMaster.add(fieldNoFunction);
        fieldNoFunction.setSelected(true);
        fieldNoFunction.setText("bez funkce");

        btnGroupConcertMaster.add(fieldConcertMaster);
        fieldConcertMaster.setText("Koncertní mistr");
        fieldConcertMaster.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fieldConcertMasterMouseClicked(evt);
            }
        });

        btnGroupConcertMaster.add(fieldCompConcertMaster);
        fieldCompConcertMaster.setText("Zástupce koncertního");
        fieldCompConcertMaster.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fieldCompConcertMasterMouseClicked(evt);
            }
        });

        labelFunction.setText("Speciální funkce:");

        javax.swing.GroupLayout settingsSectionLayout = new javax.swing.GroupLayout(settingsSection);
        settingsSection.setLayout(settingsSectionLayout);
        settingsSectionLayout.setHorizontalGroup(
            settingsSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsSectionLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(settingsSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelFunction)
                    .addGroup(settingsSectionLayout.createSequentialGroup()
                        .addComponent(labelSection)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(settingsSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(settingsSectionLayout.createSequentialGroup()
                                .addComponent(fieldNoFunction)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fieldConcertMaster)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fieldCompConcertMaster))
                            .addComponent(fieldSection, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        settingsSectionLayout.setVerticalGroup(
            settingsSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsSectionLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(settingsSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSection)
                    .addComponent(fieldSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(labelFunction)
                .addGap(4, 4, 4)
                .addGroup(settingsSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldNoFunction)
                    .addComponent(fieldConcertMaster)
                    .addComponent(fieldCompConcertMaster))
                .addContainerGap(200, Short.MAX_VALUE))
        );

        content.addTab("Zařazení", settingsSection);

        labelPasswordChange.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        labelPasswordChange.setText("Nastavení hesla");

        labelCurrentPasswd.setText("Současné heslo:");
        labelCurrentPasswd.setEnabled(false);

        labelNewPasswd.setText("Nové heslo:");

        labelConfirmPasswd.setText("Nové heslo znovu:");

        fieldCurrentPasswd.setEnabled(false);

        btnPasswdChange.setText("Změnit heslo");
        btnPasswdChange.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPasswdChangeMouseClicked(evt);
            }
        });

        labelPermissions.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        labelPermissions.setText("Přístupová práva");

        btnGroupPermissions.add(fieldPermCommonAccount);
        fieldPermCommonAccount.setSelected(true);
        fieldPermCommonAccount.setText("běžný účet");

        btnGroupPermissions.add(fieldPermSpecialAccount);
        fieldPermSpecialAccount.setText("správce");

        btnDeleteAccount.setText("Smazat účet");
        btnDeleteAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteAccountMouseClicked(evt);
            }
        });

        infoCurrentPasswd.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        infoCurrentPasswd.setForeground(new java.awt.Color(255, 0, 0));

        infoNewPasswd.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        infoNewPasswd.setForeground(new java.awt.Color(255, 0, 0));

        infoConfirmPasswd.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        infoConfirmPasswd.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout settingsSpecialLayout = new javax.swing.GroupLayout(settingsSpecial);
        settingsSpecial.setLayout(settingsSpecialLayout);
        settingsSpecialLayout.setHorizontalGroup(
            settingsSpecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsSpecialLayout.createSequentialGroup()
                .addGroup(settingsSpecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCurrentPasswd, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(settingsSpecialLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(settingsSpecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelPermissions)
                            .addGroup(settingsSpecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(labelConfirmPasswd)
                                .addComponent(labelNewPasswd)
                                .addGroup(settingsSpecialLayout.createSequentialGroup()
                                    .addComponent(labelPasswordChange)
                                    .addGap(60, 60, 60))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settingsSpecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(settingsSpecialLayout.createSequentialGroup()
                        .addGroup(settingsSpecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fieldCurrentPasswd, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(fieldNewPasswd)
                            .addComponent(fieldConfirmPasswd))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(settingsSpecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(infoCurrentPasswd)
                            .addComponent(infoNewPasswd)
                            .addComponent(infoConfirmPasswd)))
                    .addComponent(btnPasswdChange))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(settingsSpecialLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(fieldPermCommonAccount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldPermSpecialAccount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
                .addComponent(btnDeleteAccount)
                .addGap(37, 37, 37))
        );
        settingsSpecialLayout.setVerticalGroup(
            settingsSpecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsSpecialLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(labelPasswordChange)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(settingsSpecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldCurrentPasswd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCurrentPasswd, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(infoCurrentPasswd))
                .addGap(9, 9, 9)
                .addGroup(settingsSpecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(settingsSpecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fieldNewPasswd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(infoNewPasswd))
                    .addGroup(settingsSpecialLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(labelNewPasswd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settingsSpecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelConfirmPasswd, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldConfirmPasswd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(infoConfirmPasswd))
                .addGap(18, 18, 18)
                .addComponent(btnPasswdChange)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(labelPermissions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settingsSpecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldPermCommonAccount)
                    .addComponent(fieldPermSpecialAccount)
                    .addComponent(btnDeleteAccount))
                .addGap(45, 45, 45))
        );

        content.addTab("Speciální", settingsSpecial);

        getContentPane().add(content, java.awt.BorderLayout.CENTER);

        caption.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        caption.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        caption.setText("Nastavení uživatele");
        caption.setAlignmentX(0.5F);
        caption.setMaximumSize(new java.awt.Dimension(104, 50));
        caption.setMinimumSize(new java.awt.Dimension(104, 50));
        caption.setPreferredSize(new java.awt.Dimension(104, 50));
        getContentPane().add(caption, java.awt.BorderLayout.PAGE_START);

        btnSubmit.setText("Uložit");
        btnSubmit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSubmitMouseClicked(evt);
            }
        });
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        btnReset.setText("Zrušit");
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout controlsLayout = new javax.swing.GroupLayout(controls);
        controls.setLayout(controlsLayout);
        controlsLayout.setHorizontalGroup(
            controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsLayout.createSequentialGroup()
                .addContainerGap(312, Short.MAX_VALUE)
                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        controlsLayout.setVerticalGroup(
            controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReset)
                    .addComponent(btnSubmit))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        getContentPane().add(controls, java.awt.BorderLayout.PAGE_END);

        setBounds(0, 0, 546, 468);
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseClicked
        dispose();
    }//GEN-LAST:event_btnResetMouseClicked
    @Deprecated
    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed

    }//GEN-LAST:event_btnSubmitActionPerformed

    protected void btnSubmitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubmitMouseClicked
        controller.saveUser();
    }//GEN-LAST:event_btnSubmitMouseClicked

    private void btnPasswdChangeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPasswdChangeMouseClicked
        controller.changePasswd();
    }//GEN-LAST:event_btnPasswdChangeMouseClicked
    // Handling concertMaster checkboxes when toggling fieldSection
    private void fieldSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldSectionActionPerformed
         if(fieldSection.getSelectedIndex() == 0){
            fieldConcertMaster.setEnabled(false);
            fieldCompConcertMaster.setEnabled(false);
            fieldNoFunction.setEnabled(false);
        } else {
            fieldConcertMaster.setEnabled(true);
            fieldCompConcertMaster.setEnabled(true);
            fieldNoFunction.setEnabled(true);           
        }
        fieldNoFunction.setSelected(true);
    }//GEN-LAST:event_fieldSectionActionPerformed

    // Question dialog to warn an application user about changes that will be made in the database
    private void fieldConcertMasterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fieldConcertMasterMouseClicked
        int dialogResult = JOptionPane.showConfirmDialog(UserDetails.this, 
                "Změna tohoto nastavení přepíše hodnotu současného nastavení. Chcete pokračovat?", "Varování!", JOptionPane.YES_NO_OPTION);
        if(dialogResult == 0){
            return;
        }
        fieldNoFunction.setSelected(true);
    }//GEN-LAST:event_fieldConcertMasterMouseClicked
    
    // Question dialog to warn an application user about changes that will be made in the database
    private void fieldCompConcertMasterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fieldCompConcertMasterMouseClicked
        fieldConcertMasterMouseClicked(evt);
    }//GEN-LAST:event_fieldCompConcertMasterMouseClicked
    
    private void fieldEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldEmailActionPerformed
        controller.checkEmail();
    }//GEN-LAST:event_fieldEmailActionPerformed

    private void btnDeleteAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteAccountMouseClicked
        controller.deleteUser();
    }//GEN-LAST:event_btnDeleteAccountMouseClicked
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton btnDeleteAccount;
    private javax.swing.ButtonGroup btnGroupConcertMaster;
    private javax.swing.ButtonGroup btnGroupPermissions;
    protected javax.swing.JButton btnPasswdChange;
    private javax.swing.JButton btnReset;
    protected javax.swing.JButton btnSubmit;
    protected javax.swing.JLabel caption;
    private javax.swing.JTabbedPane content;
    private javax.swing.JPanel controls;
    protected javax.swing.JTextField fieldAddrHouseNumber;
    protected javax.swing.JTextField fieldAddrStreet;
    protected javax.swing.JTextField fieldAddrTown;
    protected javax.swing.JTextField fieldAddrZipCode;
    protected javax.swing.JTextField fieldBirthDate;
    protected javax.swing.JRadioButton fieldCompConcertMaster;
    protected javax.swing.JRadioButton fieldConcertMaster;
    protected javax.swing.JPasswordField fieldConfirmPasswd;
    protected javax.swing.JPasswordField fieldCurrentPasswd;
    protected javax.swing.JTextField fieldEmail;
    protected javax.swing.JTextField fieldFamilyName;
    protected javax.swing.JTextField fieldFirstName;
    protected javax.swing.JPasswordField fieldNewPasswd;
    protected javax.swing.JRadioButton fieldNoFunction;
    protected javax.swing.JRadioButton fieldPermCommonAccount;
    protected javax.swing.JRadioButton fieldPermSpecialAccount;
    protected javax.swing.JTextField fieldPhone;
    protected javax.swing.JComboBox<String> fieldSection;
    protected javax.swing.JLabel infoAddrHouseNumber;
    protected javax.swing.JLabel infoAddrStreet;
    protected javax.swing.JLabel infoAddrTown;
    protected javax.swing.JLabel infoAddrZipCode;
    protected javax.swing.JLabel infoBirthDate;
    protected javax.swing.JLabel infoConfirmPasswd;
    protected javax.swing.JLabel infoCurrentPasswd;
    protected javax.swing.JLabel infoEmail;
    protected javax.swing.JLabel infoFamilyName;
    protected javax.swing.JLabel infoFirstName;
    protected javax.swing.JLabel infoNewPasswd;
    protected javax.swing.JLabel infoPhone;
    protected javax.swing.JLabel labelAddrHouseNumber;
    protected javax.swing.JLabel labelAddrStreet;
    protected javax.swing.JLabel labelAddrTown;
    protected javax.swing.JLabel labelAddrZipCode;
    protected javax.swing.JLabel labelBirthDate;
    protected javax.swing.JLabel labelConfirmPasswd;
    protected javax.swing.JLabel labelCurrentPasswd;
    protected javax.swing.JLabel labelEmail;
    protected javax.swing.JLabel labelFamilyName;
    protected javax.swing.JLabel labelFirstName;
    protected javax.swing.JLabel labelFunction;
    protected javax.swing.JLabel labelNewPasswd;
    protected javax.swing.JLabel labelPasswordChange;
    protected javax.swing.JLabel labelPermissions;
    protected javax.swing.JLabel labelPhone;
    protected javax.swing.JLabel labelSection;
    private javax.swing.JPanel settingsCustom;
    private javax.swing.JPanel settingsSection;
    private javax.swing.JPanel settingsSpecial;
    // End of variables declaration//GEN-END:variables

    /**
     * Sets {@link UserSettings} as controller for this dialog.
     * See {@link UIControlled} interface for more information.
     * @param controller - a {@link UserSettings} object
     */
    @Override
    public void setUIController(UserSettings controller) {
        this.controller = controller;
    }
    
    /**
     * Loads user detail from given {@link Users entity}
     * @param user - a {@link Users} entity
     * @param dateFormatter - a {@code DateFormat} object
     */
    public void loadUserDetail(Users user, DateFormat dateFormatter){       
        fieldFirstName.setText(user.getFirstName());
        fieldFamilyName.setText(user.getFamilyName());
        fieldBirthDate.setText(dateFormatter.format(user.getBirthDate()));
        fieldPhone.setText(user.getPhone());
        fieldEmail.setText(user.getEmail());
        fieldAddrStreet.setText(user.getAddrStreet());
        fieldAddrHouseNumber.setText(user.getAddrHouseNumber());
        fieldAddrTown.setText(user.getAddrTown());
        if(user.getAddrZipCode() != null){
            fieldAddrZipCode.setText(user.getAddrZipCode().toString());
        }
    }
    
    /**
     * Set proper check box if the selected user is administrator or not.
     * If the given value is {@code true} the {@code UserDetails.fieldSpecialAccount} is selected.
     * Else the {@code UserDetails.fieldCommon} account is selected.
     * @param isAdministrator 
     */
    public void loadPermissions(boolean isAdministrator){
        fieldPermSpecialAccount.setSelected(isAdministrator);
        fieldPermCommonAccount.setSelected(!isAdministrator);
    }

    /**
     * Gets pointer to the {@link UserSettings} controller of this dialog.
     * See {@link UIControlled} interface for more information.
     * @return a {@link UserSettings} object
     */
    @Override
    public UserSettings getUIController() {
        return this.controller;
    }
    
    /**
     * Modal function of the dialog
     * @return {@code int} value as the exit code
     */
    public int doModal() {
        setModal(true);
        setVisible(true);
        return exitCode;
    }
    
    /**
     * Clears error messages at all info {@code JLabel}
     */
    public void clearErrorMessages(){
        infoFirstName.setText("");
        infoFamilyName.setText("");
        infoBirthDate.setText("");
        infoPhone.setText("");
        infoEmail.setText("");
        infoAddrStreet.setText("");
        infoAddrHouseNumber.setText("");
        infoAddrTown.setText("");
        infoAddrZipCode.setText("");
    }
    
    /**
     * Gets the pointer to user address - house number field.
     * @return a {@code JTextField} object
     */
    public JTextField getFieldAddrHouseNumber() {
        return fieldAddrHouseNumber;
    }

    /**
     * Gets the pointer to user address - street field.
     * @return a {@code JTextField} object
     */
    public JTextField getFieldAddrStreet() {
        return fieldAddrStreet;
    }

    /**
     * Gets the pointer to user address - town field.
     * @return a {@code JTextField} object
     */
    public JTextField getFieldAddrTown() {
        return fieldAddrTown;
    }

    /**
     * Gets the pointer to user address - zip code field.
     * @return a {@code JTextField} object
     */
    public JTextField getFieldAddrZipCode() {
        return fieldAddrZipCode;
    }

    /**
     * Gets the pointer to user birth date field.
     * @return a {@code JTextField} object
     */
    public JTextField getFieldBirthDate() {
        return fieldBirthDate;
    }

    /**
     * Gets the pointer to second concert master radio button.
     * @return a {@code JRadioButton} object
     */
    public JRadioButton getFieldCompConcertMaster() {
        return fieldCompConcertMaster;
    }

    /**
     * Gets the pointer to concert master radio button.
     * @return a {@code JRadioButton} object
     */
    public JRadioButton getFieldConcertMaster() {
        return fieldConcertMaster;
    }

    /**
     * Gets the pointer to confirm password field.
     * @return a {@code JTextField} object
     */
    public JPasswordField getFieldConfirmPasswd() {
        return fieldConfirmPasswd;
    }

    /**
     * Gets the pointer to user current password field.
     * @return a {@code JPasswordField} object
     */
    public JPasswordField getFieldCurrentPasswd() {
        return fieldCurrentPasswd;
    }

    /**
     * Gets the pointer to user email field.
     * @return a {@code JTextField} object
     */
    public JTextField getFieldEmail() {
        return fieldEmail;
    }

    /**
     * Gets the pointer to user family name field.
     * @return a {@code JTextField} object
     */
    public JTextField getFieldFamilyName() {
        return fieldFamilyName;
    }

    /**
     * Gets the pointer to user first name field.
     * @return a {@code JTextField} object
     */
    public JTextField getFieldFirstName() {
        return fieldFirstName;
    }

    /**
     * Gets the pointer to user new password field.
     * @return a {@code JPasswordField} object
     */
    public JPasswordField getFieldNewPasswd() {
        return fieldNewPasswd;
    }

    /**
     * Gets the pointer to player no function in its section radio button.
     * @return a {@code JRadioButton} object
     */
    public JRadioButton getFieldNoFunction() {
        return fieldNoFunction;
    }

    /**
     * Gets the pointer to permission common account radio button.
     * @return a {@code JRadioButton} object
     */
    public JRadioButton getFieldPermCommonAccount() {
        return fieldPermCommonAccount;
    }

    /**
     * Gets the pointer to permission special account radio button.
     * @return a {@code JRadioButton} object
     */
    public JRadioButton getFieldPermSpecialAccount() {
        return fieldPermSpecialAccount;
    }

    /**
     * Gets the pointer to user phone number field.
     * @return a {@code JTextField} object
     */
    public JTextField getFieldPhone() {
        return fieldPhone;
    }

    /**
     * Gets the pointer to section field.
     * @return a {@code JComboBox} object
     */
    public JComboBox<String> getFieldSection() {
        return fieldSection;
    }

    /**
     * Gets the info label of the field user address - house number.
     * @return a {@code JLabel} object
     */
    public JLabel getInfoAddrHouseNumber() {
        return infoAddrHouseNumber;
    }
    
    /**
     * Gets the info label of the field user address - street.
     * @return a {@code JLabel} object
     */
    public JLabel getInfoAddrStreet() {
        return infoAddrStreet;
    }

    /**
     * Gets the info label of the field user address - town.
     * @return a {@code JLabel} object
     */
    public JLabel getInfoAddrTown() {
        return infoAddrTown;
    }

    /**
     * Gets the info label of the field user address - zip code.
     * @return a {@code JLabel} object
     */
    public JLabel getInfoAddrZipCode() {
        return infoAddrZipCode;
    }

    /**
     * Gets the info label of the field field user birth date.
     * @return a {@code JLabel} object
     */
    public JLabel getInfoBirthDate() {
        return infoBirthDate;
    }

    /**
     * Gets the info label of the field user confirm password.
     * @return a {@code JLabel} object
     */
    public JLabel getInfoConfirmPasswd() {
        return infoConfirmPasswd;
    }

    /**
     * Gets the info label of the field user current password.
     * @return a {@code JLabel} object
     */
    public JLabel getInfoCurrentPasswd() {
        return infoCurrentPasswd;
    }

    /**
     * Gets the info label of the field user email.
     * @return a {@code JLabel} object
     */
    public JLabel getInfoEmail() {
        return infoEmail;
    }

    /**
     * Gets the info label of the field user family name.
     * @return a {@code JLabel} object
     */
    public JLabel getInfoFamilyName() {
        return infoFamilyName;
    }

    /**
     * Gets the info label of the field user first name.
     * @return a {@code JLabel} object
     */
    public JLabel getInfoFirstName() {
        return infoFirstName;
    }

    /**
     * Gets the info label of the field new password.
     * @return a {@code JLabel} object
     */
    public JLabel getInfoNewPasswd() {
        return infoNewPasswd;
    }

    /**
     * Gets the info label of the field user phone number.
     * @return a {@code JLabel} object
     */
    public JLabel getInfoPhone() {
        return infoPhone;
    }

    /**
     * Gets the button group for concert master setting.
     * @return a {@code ButtonGroup} object
     */
    public ButtonGroup getBtnGroupConcertMaster() {
        return btnGroupConcertMaster;
    }

    /**
     * Gets the button group for permissions setting.
     * @return a {@code ButtonGroup} object
     */
    public ButtonGroup getBtnGroupPermissions() {
        return btnGroupPermissions;
    }

    /**
     * Gets the pointer to the {@code UserDetails.btnDelete}.
     * @return a {@code JLabel} object
     */
    public JButton getBtnDeleteAccount() {
        return btnDeleteAccount;
    }
       
    /**
     * Sets the exitCode of the dialog.
     * @param exitCode 
     */
    public void setExitCode(int exitCode) {
        this.exitCode = exitCode;
    }   

    /**
     * Fetches available section into the {@code UserDetails.fieldSection}.
     * @param activeSections - a {@code List} of {@link Sections} entities
     */
    public void fetchSections(List<Sections> activeSections) {
        DefaultComboBoxModel sectionsFiller = new DefaultComboBoxModel();
        sectionsFiller.addElement("bez sekce");
        for(Sections section : activeSections){
            sectionsFiller.addElement(section.getSectionname());
        }
        fieldSection.setModel(sectionsFiller);
    }
    
    /**
     * Sets the concert master flag based on the {@code Boolean} value given. If the value is {@code true}
     * the user is proclaimed a concert master. If the value is {@code false} the user is proclaimed a second conert msater.
     * If the value equals {@code null} the player does not have any special function in the section.
     * @param concertMasterFlag - a {@code Boolean} object
     */
    public void setConcertMasterFlag(Boolean concertMasterFlag){
        if(concertMasterFlag == UserSettings.FUNC_CONCERTMASTER){
            fieldConcertMaster.setSelected(true);
        } else if(concertMasterFlag == UserSettings.FUNC_COMP_CONCERTMASTER){
            fieldCompConcertMaster.setSelected(true);
        } else {
            fieldNoFunction.setSelected(true);
        }
        
    }
    
    /**
     * Works as a inverse method to {@link #setConcertMasterFlag(java.lang.Boolean) }. Returns {@code true} if the player
     * is concert master, {@code false} if the player is second concert master and {@code null} if the player does not have any function
     * in his section.
     * @return a {@code Boolean} object
     */
    public Boolean getConcertMasterFlag(){
        if(fieldConcertMaster.isSelected()){
            return UserSettings.FUNC_CONCERTMASTER;
        } else if(fieldCompConcertMaster.isSelected()){
            return UserSettings.FUNC_COMP_CONCERTMASTER;
        } else {
            return UserSettings.FUNC_NONE;
        }
    }
}
