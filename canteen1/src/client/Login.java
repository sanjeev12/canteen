/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import canteen1.ServerInterface;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login extends JFrame implements ActionListener {

    final String serverUrl = "canteenserv";
    JLabel lblUsername, lblPassword, lblName;
    JTextField txtUsername;
    JPasswordField txtPassword;
    JButton btnLogin;

    Login() {
        lblName = new JLabel("Canteen Name");
        lblUsername = new JLabel("Username");
        lblPassword = new JLabel("Password");

        txtPassword = new JPasswordField(20);
        txtUsername = new JTextField(20);

        btnLogin = new JButton("Login");

        init();

        add(lblName);
        lblName.setBounds(20, 10, 100, 20);
        add(lblUsername);
        lblUsername.setBounds(20, 40, 100, 20);
        add(lblPassword);
        lblPassword.setBounds(20, 70, 100, 20);
        add(txtUsername);
        txtUsername.setBounds(120, 40, 100, 20);
        add(txtPassword);
        txtPassword.setBounds(120, 70, 100, 20);
        add(btnLogin);
        btnLogin.setBounds(20, 110, 100, 20);

        this.getRootPane().setDefaultButton(btnLogin);
        btnLogin.addActionListener(this);

    }

    private void init() throws SecurityException {
        setSize(300, 200);
        setVisible(true);
        setAlwaysOnTop(true);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Login Form");
        setLocation(200, 200);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnLogin) {
            String username = txtUsername.getText().trim();
            String pass = txtPassword.getText();
            if (username.isEmpty() && pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username and Password cannot be blank");
            } else {
                try {
                    ServerInterface SR = (ServerInterface) Naming.lookup("//localhost/canteenserv");
                    if (SR.login(txtUsername.getText().trim(), txtPassword.getText())) {
                        //login success
                        JOptionPane.showMessageDialog(this, "Login Success");
                    } else {
                        JOptionPane.showMessageDialog(this, "Login Failed");
                    }

                } catch (Exception ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void main(String[] args) {

        new Login();
    }

 

}
