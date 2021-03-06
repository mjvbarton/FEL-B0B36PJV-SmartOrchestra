/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.gui;

import cz.cvut.fel.dbs.smartorchestra.SmartOrchestra;
import cz.cvut.fel.dbs.smartorchestra.UserLogin;

/**
 * This class represents the login screen shown at the application startup.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class LoginScreen extends javax.swing.JFrame {
   

    /**
     * Creates new form LoginScreen
     */
    public LoginScreen() {
        initComponents();
    }

    // Editor generated code
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        caption = new javax.swing.JLabel();
        description = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        labelUserEmail = new javax.swing.JLabel();
        fieldUserEmail = new javax.swing.JTextField();
        fieldPassword = new javax.swing.JPasswordField();
        labelPassword = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartOrchestra - Přihlásit se");
        setIconImage(SmartOrchestra.getInstance().getIco());
        setMaximumSize(new java.awt.Dimension(400, 240));
        setMinimumSize(new java.awt.Dimension(400, 240));
        setPreferredSize(new java.awt.Dimension(400, 240));
        setResizable(false);

        jPanel2.setLayout(new java.awt.GridLayout(3, 0));

        caption.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        caption.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        caption.setText("SmartOrchestra");
        jPanel2.add(caption);

        description.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        description.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        description.setText("Pro použití aplikace se prosím přihlaste.");
        jPanel2.add(description);

        jSeparator1.setToolTipText("");
        jPanel2.add(jSeparator1);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        labelUserEmail.setLabelFor(fieldUserEmail);
        labelUserEmail.setText("Email:");

        fieldUserEmail.setToolTipText("");

        labelPassword.setLabelFor(fieldPassword);
        labelPassword.setText("Heslo:");

        btnSubmit.setText("Přihlásit se");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelUserEmail)
                    .addComponent(labelPassword))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(fieldPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(fieldUserEmail))
                    .addComponent(btnSubmit))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUserEmail)
                    .addComponent(fieldUserEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPassword))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSubmit)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    @Deprecated
    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSubmitActionPerformed
    
    // Calling controller login when user login was clicked
    private void btnSubmitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubmitMouseClicked
        // TODO add your handling code here:
        UserLogin controller = new UserLogin(this);
        controller.login(fieldUserEmail, fieldPassword);
    }//GEN-LAST:event_btnSubmitMouseClicked
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel caption;
    private javax.swing.JLabel description;
    private javax.swing.JPasswordField fieldPassword;
    private javax.swing.JTextField fieldUserEmail;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JLabel labelUserEmail;
    // End of variables declaration//GEN-END:variables
}
