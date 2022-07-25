package com.covid19_vaccine_registration_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Page5b_appointment extends JFrame implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            setVisible(false);
            Add();
        }else if (e.getSource() == remove){
            setVisible(false);
            Remove();
        } else if (e.getSource() == modify) {
            setVisible(false);
            Modify();
        } else if (e.getSource() == view) {
            setVisible(false);
            View();
        } else if (e.getSource() == search) {
            setVisible(false);
            Search();
        } else if (e.getSource() == back) {
            setVisible(false);
            Main.fifth.setVisible(true);
        }
    }

    private Button add, remove, modify, view, search, back;

    public Page5b_appointment() {
        setTitle("Manage Vaccination Appointment");
        setSize(350, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10)); //top-bottom, center alignment

        add = new Button("Register Appointment");
        remove = new Button("Remove Appointment");
        modify = new Button("Modify Appointment");
        view = new Button("View Appointment");
        search = new Button("Search Appointment");
        back = new Button("Go Back");

        add(add);
        add(remove);
        add(modify);
        add(view);
        add(search);
        add(back);

        add.addActionListener(this);
        remove.addActionListener(this);
        modify.addActionListener(this);
        view.addActionListener(this);
        search.addActionListener(this);
        back.addActionListener(this);
        /*setVisible(true);*/
    }

    private void Add(){
        try{
            JTextField id = new JTextField(5);
            Centre[] centres = {Centre.CentreA,Centre.CentreB,Centre.CentreC,Centre.CentreD,Centre.CentreE};
            JComboBox centre = new JComboBox(centres);
            Day[] days = {Day.Monday,Day.Tuesday,Day.Wednesday,Day.Thursday,Day.Friday,Day.Saturday,Day.Sunday};
            JComboBox day = new JComboBox(days);
            JTextField time = new JTextField(5);

            Object[] message = {
                    "Appointment ID: ", id,
                    "Vaccination Centre : ", centre,
                    "Day : ", day,
                    "Time (0-24) (Hour) : ", time,
            };
            int option = JOptionPane.showConfirmDialog(add, message, "Add Appointment", JOptionPane.OK_CANCEL_OPTION);

            if(option == JOptionPane.OK_OPTION) {
                int idInp = Integer.parseInt(id.getText());
                Centre cntInp = (Centre) centre.getSelectedItem();
                Day dayInp = (Day) day.getSelectedItem();
                int timeInp = Integer.parseInt(time.getText());
                if (timeInp < 0 || timeInp > 24){
                    throw new Exception();
                }
                Appointment found = DataIO.checkingapp(idInp);
                if (found == null) {
                    Appointment founddup = DataIO.checkingappdup(cntInp,dayInp,timeInp);
                    if (founddup == null) {
                        Appointment a = new Appointment(idInp, cntInp, dayInp, timeInp);
                        DataIO.allAppointment.add(a);
                        DataIO.write();
                        JOptionPane.showMessageDialog(add, "Record Successfully Added");
                        setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(add, "This Centre already had the same appointment day & time!");
                        setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(add, "The Appointment ID has been used!");
                    setVisible(true);
                }
            }else{
                setVisible(true);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(add, "Unsuitable Input Detected, Please Try Again");
            setVisible(true);
        }
    }
    private void Remove(){
        try{
            int idInp = Integer.parseInt(JOptionPane.showInputDialog(remove,
                    "Appointment ID: ",
                    "Remove Appointment", JOptionPane.INFORMATION_MESSAGE));
            Appointment found = DataIO.checkingapp(idInp);
            if(found != null){
                Main.appointment = found;
                JTextField id = new JTextField(Integer.toString(Main.appointment.getId()), 5);
                JTextField centre = new JTextField(Main.appointment.getCentre().toString());
                JTextField day = new JTextField(Main.appointment.getDay().toString());
                JTextField time = new JTextField(Integer.toString(Main.appointment.getTime()));

                Object[] message = {
                        "Appointment ID: ", id,
                        "Vaccination Centre : ", centre,
                        "Day : ", day,
                        "Time (Hour) : ", time,
                        "\nPress OK to remove appointment"
                };

                id.setEditable(false);
                centre.setEditable(false);
                day.setEditable(false);
                time.setEditable(false);

                int option = JOptionPane.showConfirmDialog(remove, message, "Remove Appointment", JOptionPane.OK_CANCEL_OPTION);
                if(option == JOptionPane.OK_OPTION) {
                    DataIO.allAppointment.remove(found); //Removes the index of Appointment ID from the ArrayList
                    DataIO.write();
                    JOptionPane.showMessageDialog(remove, "Record Successfully Saved");
                    setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(remove, "Cancelled");
                    setVisible(true);
                }
            }else{
                JOptionPane.showMessageDialog(remove, "Appointment ID not found");
                setVisible(true);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(remove, "Please Try Again");
            setVisible(true);
        }
    }
    private void Modify(){
        /*Appointment found = null;*/
        try{
            int idInp = Integer.parseInt(JOptionPane.showInputDialog(modify, "Appointment ID : ", "Modify Appointment", JOptionPane.INFORMATION_MESSAGE));
            Appointment found = DataIO.checkingapp(idInp);
            Main.appointment = found;

            JTextField id = new JTextField(Integer.toString(Main.appointment.getId()), 5);
            Centre[] centres = {Centre.CentreA, Centre.CentreB, Centre.CentreC, Centre.CentreD, Centre.CentreE};
            JComboBox centre = new JComboBox(centres);
            centre.setSelectedItem(Main.appointment.getCentre());
            Day[] days = {Day.Monday, Day.Tuesday, Day.Wednesday, Day.Thursday, Day.Friday, Day.Saturday, Day.Sunday};
            JComboBox day = new JComboBox(days);
            day.setSelectedItem(Main.appointment.getDay());
            JTextField time = new JTextField(Integer.toString(Main.appointment.getTime()));

            Object[] message = {
                    "Appointment ID: ", id,
                    "Vaccination Centre : ", centre,
                    "Day : ", day,
                    "Time (Hour) : ", time,
            };
            id.setEditable(false);

            int option = JOptionPane.showConfirmDialog(modify, message, "Modify Appointment", JOptionPane.OK_CANCEL_OPTION);

            Centre cntInp = (Centre) centre.getSelectedItem();
            Day dayInp = (Day) day.getSelectedItem();
            int timeInp = Integer.parseInt(time.getText());
            if (timeInp < 0 || timeInp > 24){
                throw new Exception();
            }

            if (option == JOptionPane.OK_OPTION) {
                Appointment founddup = DataIO.checkingappdup(cntInp,dayInp,timeInp);
                if(founddup == null) {
                    Main.appointment.setCentre(cntInp);
                    Main.appointment.setDay(dayInp);
                    Main.appointment.setTime(timeInp);
                    DataIO.write();
                    JOptionPane.showMessageDialog(modify, "Record Updated");
                    setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(modify, "This Centre already had the same appointment day & time!");
                    setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(modify, "Record Not Updated");
                setVisible(true);
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(modify, "Please Try Again");
            setVisible(true);
        }
    }
    private void View(){
        JFrame x = new JFrame();
        x.setTitle("All Appointments");
        x.setSize(500, 400);
        x.setLocationRelativeTo(null);
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x.setLayout(new GridLayout(2, 1));

        Panel y2 = new Panel();
        y2.setLayout(new BoxLayout(y2, BoxLayout.Y_AXIS));
        int size = DataIO.allAppointment.size();

        Panel y3 = new Panel();
        y3.setLayout(new FlowLayout());

        String[] columnNames = { "ID", "Centre", "Day", "Time"};
        String[][] data = new String[size][4];
        for(int i=0; i<size; i++){
            Appointment a = DataIO.allAppointment.get(i);
            data[i][0] = ""+a.getId();
            data[i][1] = ""+a.getCentre();
            data[i][2] = ""+a.getDay();
            data[i][3] = ""+a.getTime();
        }
        DefaultTableModel z = new DefaultTableModel(data, columnNames);
        JTable z1 = new JTable(z);
        z1.setEnabled(false);  //Disable Table Editing
        z1.getTableHeader().setReorderingAllowed(false); //Disable Row Reordering

        TableRowSorter sorter = new TableRowSorter(z1.getModel());//sort
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
                setVisible(true);
            }
        });
    }
    private void Search(){
        /*Appointment found = null;*/
        try{
            int idInp = Integer.parseInt(JOptionPane.showInputDialog(search, "Appointment ID : ", "Modify Appointmnet", JOptionPane.INFORMATION_MESSAGE));
            Appointment found = DataIO.checkingapp(idInp);
            Main.appointment = found;

            JTextField id = new JTextField(Integer.toString(Main.appointment.getId()), 5);
            JTextField centre = new JTextField(Main.appointment.getCentre().toString());
            JTextField day = new JTextField(Main.appointment.getDay().toString());
            JTextField time = new JTextField(Integer.toString(Main.appointment.getTime()));

            Object[] message = {
                    "Appointment ID: ", id,
                    "Vaccination Centre : ", centre,
                    "Day : ", day,
                    "Time (Hour) : ", time,
            };

            id.setEditable(false);
            centre.setEditable(false);
            day.setEditable(false);
            time.setEditable(false);

            JOptionPane.showMessageDialog(search, message, "View Appointment", JOptionPane.DEFAULT_OPTION);
            setVisible(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(search, "Please Try Again");
            setVisible(true);
        }
    }
}
