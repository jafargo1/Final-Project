package student.information.system;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Tamer_Aburass
 */
public class Login extends JFrame {

    Connection conn = null;//query
    ResultSet rs = null;
    PreparedStatement pst = null;
    File file;//file
    FileOutputStream fos;
    PrintWriter pw;

    /**
     * Creates new form Login
     */
    public Login() {
        try {
            initComponents();
            conn = db.java_db();
            file = new File("C:\\Users\\Tamer_AbuRass\\Downloads\\Compressed\\Student Information System\\src\\student\\information\\system\\log_file.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            setLocationRelativeTo(null);

        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }

    public String currentDate() {
        Date currentDate = GregorianCalendar.getInstance().getTime();//بتجيب التاريخ 
        DateFormat df = DateFormat.getDateInstance();
        String dateString = df.format(currentDate);

        Date d = new Date();//بتجيب  الوقت 
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String timeString = sdf.format(d);
        return timeString + " / " + dateString;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        login = new javax.swing.JButton();
        txt_username = new javax.swing.JTextField();
        txt_password = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(null);

        jLabel2.setText("Username :");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 280, 70, 14);

        jLabel3.setText("Password :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 320, 70, 14);

        login.setText("Login");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_Action(evt);
            }
        });
        jPanel1.add(login);
        login.setBounds(180, 360, 70, 30);
        jPanel1.add(txt_username);
        txt_username.setBounds(90, 270, 160, 30);
        jPanel1.add(txt_password);
        txt_password.setBounds(90, 310, 160, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/student/information/system/images/bk4.jpg"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 670, 420);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void login_Action(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login_Action
        // TODO add your handling code here:

        try {
            String sql = "select id,username,password from Users Where (username ='" + txt_username.getText() + "' and password ='" + MD5.md5(txt_password.getText()) + "')";

            int count = 0;
            pst = conn.prepareStatement(sql);//بتجهز الquery
            rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                count = count + 1;
            }

            if (count == 1) {
                JOptionPane.showMessageDialog(null, "Sucess");
                MainMenu j = new MainMenu();
                j.setVisible(true);
                this.dispose();
            } else if (count > 1) {
                JOptionPane.showMessageDialog(null, "Duplicate Username or Password Access denied");
            } else {
                JOptionPane.showMessageDialog(null, "Username and Password is not correct");
            }

            fos = new FileOutputStream(file, true);//يضيف على الكود مش يحذف ويضيف من جديد
            pw = new PrintWriter(fos);
            pw.println(sql + " - " + currentDate());
            pw.flush();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {

            try {
                rs.close();
                pst.close();
                fos.close();
                pw.close();
            } catch (Exception e) {
            }

        }


    }//GEN-LAST:event_login_Action

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton login;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
