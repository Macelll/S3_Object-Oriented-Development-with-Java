package com.covid19_vaccine_registration_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Page5a_people extends JFrame implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == register){
            setVisible(false);
            Register();
        }else if(e.getSource() == modify){
            setVisible(false);
            Modify();
        }else if(e.getSource() == view){
            setVisible(false);
            View();
        }else if(e.getSource() == search){
            setVisible(false);
            Search();
        }else if(e.getSource() == back){
            setVisible(false);
            Main.fifth.setVisible(true);
        }
    }
    private Button register, modify, view, search, back;

    public Page5a_people(){
        setTitle("Manage Citizens/Non-Citizens");
        setSize(350, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 5, 5)); //top-bottom, center alignment

        register = new Button("Register People");
        modify = new Button("Modify People");
        view = new Button("View People");
        search = new Button("Search People");
        back = new Button("Go Back");

        add(register);
        add(modify);
        add(view);
        add(search);
        add(back);

        register.addActionListener(this);
        modify.addActionListener(this);
        view.addActionListener(this);
        search.addActionListener(this);
        back.addActionListener(this);
        /*setVisible(true);*/
    }

    private void Register(){
        JFrame f = new JFrame("Select an account: ");
        JPanel panel=new JPanel();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton citizenReg = new JButton("Citizen");
        JButton nCitizenReg = new JButton("Non-Citizen");
        JButton back = new JButton("Go Back");
        panel.setLayout(new FlowLayout());
        panel.add(citizenReg);
        panel.add(nCitizenReg);
        panel.add(back);
        f.add(panel);
        f.setSize(400,100);
        f.setLocationRelativeTo(null);;
        f.setVisible(true);

        citizenReg.addActionListener(new ActionListener() {
            /*@Override*/
            public void actionPerformed(ActionEvent e) {
                try {
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

                    int option = JOptionPane.showConfirmDialog(citizenReg, message, "Citizen Register", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        String nmInput = username.getText();
                        String psInput = password.getText();
                        Gender gdInput = (Gender) gender.getSelectedItem();
                        int ageInput = Integer.parseInt(age.getText());
                        int vacInput = 0;
                        int citIdInp = Integer.parseInt(citID.getText());

                        Citizen found = DataIO.checking(nmInput);
                        if (found == null) {
                            Citizen c = new Citizen(nmInput, psInput, gdInput, ageInput, 0, citIdInp);
                            DataIO.allCitizen.add(c);
                            DataIO.write();
                            JOptionPane.showMessageDialog(citizenReg, "Record Successfully Saved");
                        } else {
                            JOptionPane.showMessageDialog(citizenReg, "The username has been used!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(citizenReg, "Canceled");
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(citizenReg, "Please Try Again");
                }
            }
        });
        nCitizenReg.addActionListener(new ActionListener() {
            /*@Override*/
            public void actionPerformed(ActionEvent e) {
                try {
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
                    if (option == JOptionPane.OK_OPTION) {
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
                            JOptionPane.showMessageDialog(nCitizenReg, "Record Successfully Saved");
                        } else {
                            JOptionPane.showMessageDialog(nCitizenReg, "The username has been used!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(nCitizenReg, "Canceled");
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(citizenReg, "Please Try Again");
                }
            }
        });
        back.addActionListener(new ActionListener() {
            /*@Override*/
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                setVisible(true);
            }
        });
    }

    private void Modify() {
        JFrame f = new JFrame("Select an account: ");
        JPanel panel = new JPanel();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton citizenMod = new JButton("Citizen");
        JButton nCitizenMod = new JButton("Non-Citizen");
        JButton back = new JButton(("Go Back"));
        panel.setLayout(new FlowLayout());
        panel.add(citizenMod);
        panel.add(nCitizenMod);
        panel.add(back);
        f.add(panel);
        f.setSize(400, 100);
        f.setLocationRelativeTo(null);;
        f.setVisible(true);

        citizenMod.addActionListener(new ActionListener() {
            /*@Override*/
            public void actionPerformed(ActionEvent e) {
                try {
                    String usrInput = JOptionPane.showInputDialog(citizenMod, "Enter Citizen Username: ");
                    if (usrInput != null) {
                        Citizen found = DataIO.checking(usrInput);
                        Main.clogin = found;

                        JTextField username = new JTextField(Main.clogin.getUsername(), 16);
                        JTextField password = new JTextField(Main.clogin.getPassword(), 16);
                        Gender[] genders = {Gender.Male, Gender.Female};
                        JComboBox gender = new JComboBox(genders);
                        gender.setSelectedItem(Main.clogin.getGender());
                        JTextField age = new JTextField(Integer.toString(Main.clogin.getAge()), 5);
                        JTextField vaccine = new JTextField(Integer.toString(Main.clogin.getVaccinatedAmount()), 5);
                        JTextField citID = new JTextField(Integer.toString(Main.clogin.getCitizenID()), 16);

                        username.setEditable(false);

                        Object[] message = {
                                "Username: ", username,
                                "Password: ", password,
                                "Gender : ", gender,
                                "Age : ", age,
                                "Vaccines Received : ", vaccine,
                                "Citizen ID : ", citID
                        };

                        int option = JOptionPane.showConfirmDialog(citizenMod, message, "Profile Update", JOptionPane.OK_CANCEL_OPTION);
                        String psInput = password.getText();
                        Gender gdInput = (Gender) gender.getSelectedItem();
                        int ageInput = Integer.parseInt(age.getText());
                        int vacInput = Integer.parseInt(vaccine.getText());
                        int citIDInput = Integer.parseInt(citID.getText());

                        if (option == JOptionPane.OK_OPTION) {
                            Main.clogin.updateCitProfile(usrInput, psInput, gdInput, ageInput, vacInput, citIDInput); //Modularity
                            JOptionPane.showMessageDialog(citizenMod, "Record Updated");
                        } else {
                            setVisible(false);
                        }
                    }
                }catch (Exception a){
                    JOptionPane.showMessageDialog(citizenMod, "Please Try Again");
                }
            }
        });
        nCitizenMod.addActionListener(new ActionListener() {
            /*@Override*/
            public void actionPerformed(ActionEvent e) {
                try {
                    String usrInput = JOptionPane.showInputDialog(nCitizenMod, "Enter Non-Citizen Username: ");
                    if (usrInput != null) {
                        NonCitizen found = DataIO.checkingn(usrInput);
                        Main.nclogin = found;

                        JTextField username = new JTextField(Main.nclogin.getUsername(), 16);
                        JTextField password = new JTextField(Main.nclogin.getPassword(), 16);
                        Gender[] genders = {Gender.Male, Gender.Female};
                        JComboBox gender = new JComboBox(genders);
                        gender.setSelectedItem(Main.nclogin.getGender());
                        JTextField age = new JTextField(Integer.toString(Main.nclogin.getAge()), 5);
                        JTextField vaccine = new JTextField(Integer.toString(Main.nclogin.getVaccinatedAmount()), 5);
                        JTextField passportNum = new JTextField(Integer.toString(Main.nclogin.getPassportNum()), 16);

                        username.setEditable(false);

                        Object[] message = {
                                "Username: ", username,
                                "Password: ", password,
                                "Gender : ", gender,
                                "Age : ", age,
                                "Vaccines Received : ", vaccine,
                                "Passport ID : ", passportNum,
                        };

                        int option = JOptionPane.showConfirmDialog(nCitizenMod, message, "Profile Update", JOptionPane.OK_CANCEL_OPTION);
                        String nmInput = username.getText();
                        String psInput = password.getText();
                        Gender gdInput = (Gender) gender.getSelectedItem();
                        int ageInput = Integer.parseInt(age.getText());
                        int vacInput = Integer.parseInt(vaccine.getText());
                        int passportInput = Integer.parseInt(passportNum.getText());

                        if (option == JOptionPane.OK_OPTION) {
                            Main.nclogin.updateNCitProfile(usrInput, psInput, gdInput, ageInput, vacInput, passportInput); //Modularity
                            JOptionPane.showMessageDialog(nCitizenMod, "Record Updated");
                        } else {
                            setVisible(false);
                        }
                    }
                }catch (Exception a){
                    JOptionPane.showMessageDialog(nCitizenMod, "Please Try Again");
                }
            }
        });
        back.addActionListener(new ActionListener() {
            /*@Override*/
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                setVisible(true);
            }
        });
    }

    private void View() {
        JFrame f = new JFrame("Select an account: ");
        JPanel panel = new JPanel();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton citizenView = new JButton("Citizen");
        JButton nCitizenView = new JButton("Non-Citizen");
        JButton back = new JButton(("Go Back"));
        panel.setLayout(new FlowLayout());
        panel.add(citizenView);
        panel.add(nCitizenView);
        panel.add(back);
        f.add(panel);
        f.setSize(400, 100);
        f.setLocationRelativeTo(null);;
        f.setVisible(true);

        citizenView.addActionListener(new ActionListener() {
            /*@Override*/
            public void actionPerformed(ActionEvent e) {
                JFrame x = new JFrame();
                x.setTitle("All Citizens");
                x.setSize(800, 400);
                x.setLocationRelativeTo(null);
                x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                x.setLayout(new GridLayout(2, 1));

                Panel y2 = new Panel();
                y2.setLayout(new BoxLayout(y2, BoxLayout.Y_AXIS));
                int size = DataIO.allCitizen.size();

                Panel y3 = new Panel();
                y3.setLayout(new FlowLayout());

                String[] columnNames = {"Name", "Password", "Gender", "Age", "Vaccinated Amount", "Citizen ID"};
                String[][] data = new String[size][6];
                for (int i = 0; i < size; i++) {
                    Citizen a = DataIO.allCitizen.get(i);
                    data[i][0] = "" + a.getUsername();
                    data[i][1] = "" + a.getPassword();
                    data[i][2] = "" + a.getGender();
                    data[i][3] = "" + a.getAge();
                    data[i][4] = "" + a.getVaccinatedAmount();
                    data[i][5] = "" + a.getCitizenID();
                }
                DefaultTableModel z = new DefaultTableModel(data, columnNames);
                JTable z1 = new JTable(z);
                z1.setEnabled(false); //Disable Editing
                z1.getTableHeader().setReorderingAllowed(false); //Disable Row Reordering

                TableRowSorter sorter = new TableRowSorter(z1.getModel()); //sort
                z1.setRowSorter(sorter);
                List<RowSorter.SortKey> sortKeys = new ArrayList<>();
                int index = 0;
                sortKeys.add(new RowSorter.SortKey(index,SortOrder.ASCENDING));
                sorter.setSortKeys(sortKeys);
                sorter.sort();

                JScrollPane sp = new JScrollPane(z1);
                y2.add(sp);

                Button ok = new Button("OK");
                ok.setPreferredSize(new Dimension(200, 70));
                y3.add(ok);

                x.add(y2);
                x.add(y3);
                x.setVisible(true);

                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        x.setVisible(false);
                    }
                });
            }
        });
        nCitizenView.addActionListener(new ActionListener() {
            /*@Override*/
            public void actionPerformed(ActionEvent e) {
                JFrame x = new JFrame();
                x.setTitle("All Non-Citizens");
                x.setSize(800, 400);
                x.setLocationRelativeTo(null);
                x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                x.setLayout(new GridLayout(2, 1));

                Panel y2 = new Panel();
                y2.setLayout(new BoxLayout(y2, BoxLayout.Y_AXIS));
                int size = DataIO.allNonCitizen.size();

                Panel y3 = new Panel();
                y3.setLayout(new FlowLayout());

                String[] columnNames = {"Name", "Password", "Gender", "Age", "Vaccinated Amount", "Passport Number"};
                String[][] data = new String[size][6];
                for (int i = 0; i < size; i++) {
                    NonCitizen a = DataIO.allNonCitizen.get(i);
                    data[i][0] = "" + a.getUsername();
                    data[i][1] = "" + a.getPassword();
                    data[i][2] = "" + a.getGender();
                    data[i][3] = "" + a.getAge();
                    data[i][4] = "" + a.getVaccinatedAmount();
                    data[i][5] = "" + a.getPassportNum();
                }
                DefaultTableModel z = new DefaultTableModel(data, columnNames);
                JTable z1 = new JTable(z);
                z1.setEnabled(false); //Disable Editing
                z1.getTableHeader().setReorderingAllowed(false); //Disable Row Reordering

                TableRowSorter sorter = new TableRowSorter(z1.getModel()); //sort
                z1.setRowSorter(sorter);
                List<RowSorter.SortKey> sortKeys = new ArrayList<>();
                int index = 0;
                sortKeys.add(new RowSorter.SortKey(index,SortOrder.ASCENDING));
                sorter.setSortKeys(sortKeys);
                sorter.sort();

                JScrollPane sp = new JScrollPane(z1);
                y2.add(sp);

                Button ok = new Button("OK");
                ok.setPreferredSize(new Dimension(200, 70));
                y3.add(ok);

                x.add(y2);
                x.add(y3);
                x.setVisible(true);

                ok.addActionListener(new ActionListener() {
                    /*@Override*/
                    public void actionPerformed(ActionEvent e) {
                        x.setVisible(false);
                    }
                });
            }
        });
        back.addActionListener(new ActionListener() {
            /*@Override*/
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                setVisible(true);
            }
        });
    }

    private void Search() {
        JFrame f = new JFrame("Select an account: ");
        JPanel panel = new JPanel();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton citizenSearch = new JButton("Citizen");
        JButton nCitizenSearch = new JButton("Non-Citizen");
        JButton back = new JButton(("Go Back"));
        panel.setLayout(new FlowLayout());
        panel.add(citizenSearch);
        panel.add(nCitizenSearch);
        panel.add(back);
        f.add(panel);
        f.setSize(400, 100);
        f.setLocationRelativeTo(null);;
        f.setVisible(true);

        citizenSearch.addActionListener(new ActionListener() {
            /*@Override*/
            public void actionPerformed(ActionEvent e) {
                /*Citizen found = null;*/
                try {
                    String usrInput = JOptionPane.showInputDialog(citizenSearch, "Enter Citizen Username: ");
                    if (usrInput != null) {
                        Citizen found = DataIO.checking(usrInput);
                        Main.clogin = found;

                        JTextField username = new JTextField(Main.clogin.getUsername(),16);
                        JTextField password = new JTextField(Main.clogin.getPassword(),16);
                        JTextField gender = new JTextField(String.valueOf(Main.clogin.getGender()),5);
                        JTextField age = new JTextField(Integer.toString(Main.clogin.getAge()),5);
                        JTextField vaccine = new JTextField(Integer.toString(Main.clogin.getVaccinatedAmount()), 5);
                        JTextField citID = new JTextField(Integer.toString(Main.clogin.getCitizenID()),16);

                        Object[] message = {
                                "Username: ", username,
                                "Password: ", password,
                                "Gender : ", gender,
                                "Age : ", age,
                                "Vaccines Received : ", vaccine,
                                "Citizen ID : ", citID
                        };
                        username.setEditable(false);
                        password.setEditable(false);
                        gender.setEditable(false);
                        age.setEditable(false);
                        vaccine.setEditable(false);
                        citID.setEditable(false);
                        JOptionPane.showMessageDialog(citizenSearch, message);
                    }
                }catch (Exception a){
                    JOptionPane.showMessageDialog(citizenSearch, "Please Try Again");
                }
            }
        });
        nCitizenSearch.addActionListener(new ActionListener() {
            /*@Override*/
            public void actionPerformed(ActionEvent e) {
                /*NonCitizen found = null;*/
                try {
                    String usrInput = JOptionPane.showInputDialog(nCitizenSearch, "Enter Non-Citizen Username: ");
                    if (usrInput != null) {
                        NonCitizen found = DataIO.checkingn(usrInput);
                        Main.nclogin = found;

                        JTextField username = new JTextField(Main.nclogin.getUsername(),16);
                        JTextField password = new JTextField(Main.nclogin.getPassword(),16);
                        JTextField gender = new JTextField(String.valueOf(Main.nclogin.getGender()),5);
                        JTextField age = new JTextField(Integer.toString(Main.nclogin.getAge()),5);
                        JTextField passportNum = new JTextField(Integer.toString(Main.nclogin.getPassportNum()),16);

                        Object[] message = {
                                "Username: ", username,
                                "Password: ", password,
                                "Gender : ", gender,
                                "Age : ", age,
                                "Passport ID : ", passportNum,
                        };
                        username.setEditable(false);
                        password.setEditable(false);
                        gender.setEditable(false);
                        age.setEditable(false);
                        passportNum.setEditable(false);
                        JOptionPane.showMessageDialog(citizenSearch, message);
                    }
                }catch (Exception a){
                    JOptionPane.showMessageDialog(nCitizenSearch, "Please Try Again");
                }
            }
        });
        back.addActionListener(new ActionListener() {
            /*@Override*/
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                setVisible(true);
            }
        });
    }
}
