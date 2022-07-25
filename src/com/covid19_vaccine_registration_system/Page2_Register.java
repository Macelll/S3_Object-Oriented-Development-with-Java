package com.covid19_vaccine_registration_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Page2_Register extends JFrame implements ActionListener {

    Scanner sc = new Scanner(System.in);

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == citizen){
            JTextField username = new JTextField(16);
            JTextField password = new JPasswordField(16);
            Gender[] genders = {Gender.Male, Gender.Female};
            JComboBox gender = new JComboBox(genders);
            JTextField age = new JTextField(5);
            JTextField citID = new JTextField(16);

            Object[] message = {
                    "Username: ", username,
                    "Password: ", password,
                    "Gender : ", gender,
                    "Age : ", age,
                    "Citizen ID : ", citID
            };

            int option = JOptionPane.showConfirmDialog(citizen, message, "Citizen Register", JOptionPane.OK_CANCEL_OPTION);
            try {
                if (option == JOptionPane.OK_OPTION) {
                    String nmInput = username.getText();
                    String psInput = password.getText();
                    Gender gdInput = (Gender) gender.getSelectedItem();
                    int ageInput = Integer.parseInt(age.getText());
                    int vacInput = 0;
                    int citIDInput = Integer.parseInt(citID.getText());

                    Citizen found = DataIO.checking(nmInput);
                    if (found == null) {
                        Citizen c = new Citizen(nmInput, psInput, gdInput, ageInput, vacInput, citIDInput);
                        DataIO.allCitizen.add(c);
                        DataIO.write();
                        JOptionPane.showMessageDialog(citizen, "Record Successfully Saved");
                    } else {
                        JOptionPane.showMessageDialog(citizen, "The username has been used!");
                    }
                } else {
                    setVisible(true);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(citizen, "Incorrect Input, Please Try Again");
            }

        } else if (e.getSource() == nCitizen) {
            JTextField username = new JTextField(16);
            JTextField password = new JPasswordField(16);
            Gender[] genders = {Gender.Male, Gender.Female};
            JComboBox gender = new JComboBox(genders);
            JTextField age = new JTextField(5);
            JTextField passportNum = new JTextField(16);

            Object[] message = {
                    "Username: ", username,
                    "Password: ", password,
                    "Gender : ", gender,
                    "Age : ", age,
                    "Passport ID : ", passportNum
            };

            int option = JOptionPane.showConfirmDialog(null, message, "NonCitizen Register", JOptionPane.OK_CANCEL_OPTION);
            if(option == JOptionPane.OK_OPTION) {
                try {
                    String nmInput = username.getText();
                    String psInput = password.getText();
                    Gender gdInput = (Gender) gender.getSelectedItem();
                    int ageInput = Integer.parseInt(age.getText());
                    int passportInput = Integer.parseInt(passportNum.getText());

                    NonCitizen found = DataIO.checkingn(nmInput);
                    if (found == null) {
                        NonCitizen nc = new NonCitizen(nmInput, psInput, gdInput, ageInput, 0, passportInput);
                        DataIO.allNonCitizen.add(nc);
                        DataIO.write();
                        JOptionPane.showMessageDialog(null, "Record Successfully Saved");
                    } else {
                        JOptionPane.showMessageDialog(nCitizen, "The username has been used!");
                    }
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(citizen, "Incorrect Input, Please Try Again");
                }
            }else{

            }

        } else if (e.getSource() == admin) {
            String input = JOptionPane.showInputDialog("Enter special password:");
            try {
                if (input.equals("12345")) {
                    JTextField username = new JTextField(16);
                    JTextField password = new JPasswordField(16);
                    Gender[] genders = {Gender.Male, Gender.Female};
                    JComboBox gender = new JComboBox(genders);
                    JTextField age = new JTextField(5);

                    Object[] message = {
                            "Username: ", username,
                            "Password: ", password,
                            "Gender : ", gender,
                            "Age : ", age
                    };

                    int option = JOptionPane.showConfirmDialog(null, message, "Admin Register", JOptionPane.OK_CANCEL_OPTION);
                    String nmInput = username.getText();
                    String psInput = password.getText();
                    Gender gdInput = (Gender) gender.getSelectedItem();
                    int ageInput = Integer.parseInt(age.getText());

                    Admin found = DataIO.checkinga(nmInput);
                    if (found == null) {
                        Admin a = new Admin(nmInput, psInput, gdInput, ageInput);
                        DataIO.allAdmin.add(a);
                        DataIO.write();
                        JOptionPane.showMessageDialog(null, "Record Successfully Saved");
                    } else {
                        JOptionPane.showMessageDialog(admin, "The username has been used!");
                    }
                } else {
                    JOptionPane.showMessageDialog(admin, "Wrong password!");
                }
            }catch (Exception ex){
                setVisible(true);
            }

        } else if (e.getSource() == back) {
            setVisible(false);
            Main.first.setVisible(true);
        }
    }

    private Button citizen, nCitizen, admin, back;

    public Page2_Register(){
        setTitle("Register");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 4, 5, 5)); //top-bottom, center alignment

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
