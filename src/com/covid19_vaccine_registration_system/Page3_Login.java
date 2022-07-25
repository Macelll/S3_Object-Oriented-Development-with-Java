package com.covid19_vaccine_registration_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Page3_Login extends JFrame implements ActionListener {
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == citizen){
            JTextField username = new JTextField(16);
            JTextField password = new JPasswordField(16);

            Object[] message = {
                    "Username: ", username,
                    "Password: ", password
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Citizen Login", JOptionPane.OK_CANCEL_OPTION);
            String usrInput = username.getText();
            String pwInput = password.getText();

            if (option == JOptionPane.OK_OPTION) {
                Citizen found = DataIO.checking(usrInput);
                if (found != null) {
                    if (Objects.equals(found.getPassword(), pwInput)) {
                        Main.clogin = found;
                        setVisible(false);
                        Main.fourthA.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(back, "Wrong Password");
                    }
                } else {
                    JOptionPane.showMessageDialog(back, "Username not found");
                }
            }else{
            }
        }
        else if (e.getSource() == nCitizen) {
            JTextField username = new JTextField(16);
            JTextField password = new JPasswordField(16);

            Object[] message = {
                    "Username: ", username,
                    "Password: ", password
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Non-Citizen Login", JOptionPane.OK_CANCEL_OPTION);
            String usrInput = username.getText();
            String pwInput = password.getText();

            if (option == JOptionPane.OK_OPTION) {
                NonCitizen found = DataIO.checkingn(usrInput);
                if (found != null) {
                    if (Objects.equals(found.getPassword(), pwInput)) {
                        Main.nclogin = found;
                        setVisible(false);
                        Main.fourthB.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(back, "Wrong Password");
                    }
                } else {
                    JOptionPane.showMessageDialog(back, "Username not found");
                }
            }else{
            }
        }
        else if (e.getSource() == admin) {
            JTextField username = new JTextField(16);
            JTextField password = new JPasswordField(16);

            Object[] message = {
                    "Username: ", username,
                    "Password: ", password
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Admin Login", JOptionPane.OK_CANCEL_OPTION);
            String usrInput = username.getText();
            String pwInput = password.getText();

            if (option == JOptionPane.OK_OPTION) {
                Admin found = DataIO.checkinga(usrInput);
                if (found != null) {
                    if (Objects.equals(found.getPassword(), pwInput)) {
                        Main.alogin = found;
                        setVisible(false);
                        Main.fifth.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(back, "Wrong Password");
                    }
                } else {
                    JOptionPane.showMessageDialog(back, "Username not found");
                }
            }else{
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            new Page1_Main().setVisible(true);
        }
    }

    private Button citizen, nCitizen, admin, back;

    public Page3_Login(){
        setTitle("Login");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 4, 5, 5));

        citizen = new Button("Citizen");
        nCitizen = new Button("Non-Citizen");
        admin = new Button("Admin");
        back = new Button("Go Back");
        add(citizen);
        add(nCitizen);
        add(admin);
        add(back);

        citizen.addActionListener(this);
        nCitizen.addActionListener(this);
        admin.addActionListener(this);
        back.addActionListener(this);

        /*setVisible(true);*/
    }
}
