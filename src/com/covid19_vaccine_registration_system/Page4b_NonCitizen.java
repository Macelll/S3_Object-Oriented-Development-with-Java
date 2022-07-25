package com.covid19_vaccine_registration_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Page4b_NonCitizen extends JFrame implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logout) {
            Main.nclogin = null;
            setVisible(false);
            Main.first.setVisible(true);

        } else if (e.getSource() == profile) {
            JTextField username = new JTextField(Main.nclogin.getUsername(), 16);
            JTextField password = new JTextField(Main.nclogin.getPassword(), 16);
            Gender[] genders = {Gender.Male, Gender.Female};
            JComboBox gender = new JComboBox(genders);
            gender.setSelectedItem(Main.nclogin.getGender());
            JTextField age = new JTextField(Integer.toString(Main.nclogin.getAge()), 5);
            JTextField passNum = new JTextField(Integer.toString(Main.nclogin.getPassportNum()), 16);

            username.setEditable(false);

            Object[] message = {
                    "Username: ", username,
                    "Password: ", password,
                    "Gender:", gender,
                    "Age : ", age,
                    "Passport Number : ", passNum
            };

            int option = JOptionPane.showConfirmDialog(profile, message, "Profile Update", JOptionPane.OK_CANCEL_OPTION);
            String nmInput = username.getText();
            String psInput = password.getText();
            Gender gdInput = (Gender) gender.getSelectedItem();
            int ageInput = Integer.parseInt(age.getText());
            int vacInput  = Main.nclogin.getVaccinatedAmount();
            int passNumInput = Integer.parseInt(passNum.getText());

            NonCitizen found = DataIO.checkingn(nmInput);
            if (option == JOptionPane.OK_OPTION && found != null) {
                Main.nclogin.updateNCitProfile(nmInput,psInput,gdInput,ageInput,vacInput,passNumInput); //Modularity
                JOptionPane.showMessageDialog(profile, "Record Updated");
            } else {
                setVisible(true);
            }

        } else if (e.getSource() == appointment) {
            setVisible(false);
            Main.fourthD.setVisible(true);

        } else if (e.getSource() == status) {
            int vacNum = Main.nclogin.getVaccinatedAmount();
            int s = Main.nclogin.getMyNCitAppointment().size();
            if (vacNum == 0) {
                JOptionPane.showMessageDialog(status, "You haven't been vaccinated!\nPlease complete an appointment first!");
            } else {
                for(int i = s; i >= 1 ; i--) {
                    if(Main.nclogin.getMyNCitAppointment().get(s-i).isCompleted()) {
                        JTextField id = new JTextField(Integer.toString(Main.nclogin.getMyNCitAppointment().get(s - i).getId()), 5);
                        JTextField centre = new JTextField(Main.nclogin.getMyNCitAppointment().get(s - i).getCentre().toString());
                        JTextField day = new JTextField(Main.nclogin.getMyNCitAppointment().get(s - i).getDay().toString());
                        JTextField time = new JTextField(Integer.toString(Main.nclogin.getMyNCitAppointment().get(s - i).getTime()));
                        JTextField vaccine = new JTextField(Main.nclogin.getMyNCitAppointment().get(s - i).getVaccine());

                        Object[] message = {
                                "Vaccination " + (s - i + 1) + " Details:\n",
                                "Appointment ID: ", id,
                                "Vaccination Centre : ", centre,
                                "Day : ", day,
                                "Time (Hour) : ", time,
                                "Vaccine : ", vaccine,
                        };
                        id.setEditable(false);
                        centre.setEditable(false);
                        day.setEditable(false);
                        time.setEditable(false);
                        vaccine.setEditable(false);

                        JOptionPane.showConfirmDialog(status, message, "Vaccination Status", JOptionPane.DEFAULT_OPTION);
                    }else{
                        break;
                    }
                }
                setVisible(true);
            }
        }
    }

    private Button profile, appointment, status, logout;

    public Page4b_NonCitizen() {
        setTitle("NonCitizen Menu");
        setSize(300,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout()); //top-bottom, center alignment

        profile = new Button("Profile");
        appointment = new Button("Vaccination Appointment");
        status = new Button("Vaccination Status");
        logout = new Button("Logout");

        profile.setPreferredSize(new Dimension(75, 50));
        appointment.setPreferredSize(new Dimension(140, 50));
        status.setPreferredSize(new Dimension(130,50));
        logout.setPreferredSize(new Dimension(75,50));

        add(profile);
        add(status);
        add(appointment);
        add(logout);

        profile.addActionListener(this);
        status.addActionListener(this);
        appointment.addActionListener(this);
        logout.addActionListener(this);

        /*setVisible(true);*/
    }
}
