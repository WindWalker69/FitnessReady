package GUI;

import Dominio.*;
import Eccezioni.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class LoginFrame extends javax.swing.JFrame {

    private FitnessReady FR;
    private String email, password;
    private char userType;
    /**
     * Creates new form Login
     */
    public LoginFrame() {
        FR = FitnessReady.getInstance();
        initComponents();
    }
    
    private void close(){
        WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginPanel = new javax.swing.JPanel();
        loginLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        signInButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        loginLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        loginLabel.setText("Login Utente");

        emailLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        emailLabel.setText("E-mail:");

        passwordLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        passwordLabel.setText("Password:");

        loginButton.setText("Accedi");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        signInButton.setText("Registrati");
        signInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInButtonActionPerformed(evt);
            }
        });

        exitButton.setText("Esci");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(loginLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(175, 175, 175))
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(loginPanelLayout.createSequentialGroup()
                                .addComponent(emailLabel)
                                .addGap(18, 18, 18)
                                .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(loginPanelLayout.createSequentialGroup()
                                .addComponent(passwordLabel)
                                .addGap(18, 18, 18)
                                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(signInButton)
                        .addGap(59, 59, 59)
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(loginLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(26, 26, 26)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginButton)
                    .addComponent(exitButton)
                    .addComponent(signInButton))
                .addGap(80, 80, 80))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void signInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signInButtonActionPerformed
        new SignInFrame().setVisible(true);
        close();
    }//GEN-LAST:event_signInButtonActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        try{
            email = emailTextField.getText();
            password = String.valueOf(passwordField.getPassword());
            userType = FR.effettuaLogin(email, password);
            if(userType == 'C'){
                JOptionPane.showMessageDialog(this, "Benvenuto cliente");
                new GUIClienteFrame().setVisible(true);
            }
            //else if(userType == 'A'){}
            close();
        }catch(ExistingEmailException e){
            JOptionPane.showMessageDialog(this, "L'e-mail inserita non è registrata", "Warning", JOptionPane.WARNING_MESSAGE);
        }catch(PasswordNotEqualsException e){
            JOptionPane.showMessageDialog(this, "Password non corretta", "Warning", JOptionPane.WARNING_MESSAGE);
        }catch(EmptyFieldException e){
            JOptionPane.showMessageDialog(this, e, "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        close();
    }//GEN-LAST:event_exitButtonActionPerformed

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
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton signInButton;
    // End of variables declaration//GEN-END:variables
}
