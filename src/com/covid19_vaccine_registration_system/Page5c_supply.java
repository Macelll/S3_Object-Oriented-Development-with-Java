package com.covid19_vaccine_registration_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Page5c_supply extends JFrame implements ActionListener{
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == add){
            setVisible(false);
            Add();
        }else if(e.getSource() == delete){
            setVisible(false);
            Remove();
        }else if(e.getSource() == edit){
            setVisible(false);
            Edit();
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
    private Button add, delete, edit, view, search, back;

    public Page5c_supply(){
        setTitle("Manage Vaccine");
        setSize(350, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 5, 5)); //top-bottom, center alignment

        add = new Button("Add Vaccine");
        delete = new Button("Delete Vaccine");
        edit = new Button("Edit Vaccine Details");
        view = new Button("View Vaccines");
        search = new Button("Search Vaccines");
        back = new Button("Go Back");

        add(add);
        add(delete);
        add(edit);
        add(view);
        add(search);
        add(back);

        add.addActionListener(this);
        delete.addActionListener(this);
        edit.addActionListener(this);
        view.addActionListener(this);
        search.addActionListener(this);
        back.addActionListener(this);
        /*setVisible(true);*/
    }

    private void Add(){
        try{
            JTextField id = new JTextField(5);
            JTextField name = new JTextField(16);
            JTextField qty = new JTextField(5);
            JTextField dose = new JTextField(5);
            Centre[] centres = {Centre.CentreA,Centre.CentreB,Centre.CentreC,Centre.CentreD,Centre.CentreE};
            JComboBox centre = new JComboBox(centres);

            Object[] message = {
                    "Vaccine ID: ", id,
                    "Vaccine Name: ", name,
                    "Vaccine Quantity (in Mil): ", qty,
                    "Vaccine Dose: ", dose,
                    "Vaccination Centre: ", centre,
            };
            int option = JOptionPane.showConfirmDialog(add, message, "Add Appointment", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                int idInp = Integer.parseInt(id.getText());
                String nmInp = name.getText();
                int qntInp = Integer.parseInt(qty.getText());
                int doseInp = Integer.parseInt(dose.getText());
                Centre cntInp = (Centre) centre.getSelectedItem();

                Vaccine found = DataIO.checkingvac(idInp);
                if (found == null) {
                    Vaccine foundcen = DataIO.checkingvaccen(nmInp,cntInp);
                    if(foundcen == null) {
                        Vaccine a = new Vaccine(idInp, nmInp, qntInp, doseInp, cntInp);
                        DataIO.allVaccine.add(a);
                        DataIO.write();
                        JOptionPane.showMessageDialog(add, "Record Successfully Saved");
                        setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(add, "This Centre already has the same vaccine!");
                        setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(add, "The Vaccine ID has been used!");
                    setVisible(true);
                }
            }else {
                setVisible(true);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(add, "Please Try Again");
            setVisible(true);
        }
    }
    private void Remove(){
        try{
            int idInp = Integer.parseInt(JOptionPane.showInputDialog(delete,
                    "Vaccine ID: ",
                    "Remove Appointment", JOptionPane.INFORMATION_MESSAGE));
            Vaccine found = DataIO.checkingvac(idInp);
            if(found != null){
                JTextField id = new JTextField(Integer.toString(Main.vaccine.getId()), 5);
                JTextField name = new JTextField(Main.vaccine.getName());
                JTextField qty = new JTextField(Integer.toString(Main.vaccine.getQuantity()), 5);
                JTextField dose = new JTextField(Integer.toString(Main.vaccine.getDose()), 5);
                JTextField centre = new JTextField(Main.vaccine.getCentre().toString(), 5);

                Object[] message = {
                        "Vaccine ID: ", id,
                        "Vaccine Name: ", name,
                        "Vaccine Quantity (in Mil): ", qty,
                        "Vaccine Dose: ", dose,
                        "Vaccination Centre: ", centre,
                        "\nPress OK to Remove Vaccine"
                };

                id.setEditable(false);
                name.setEditable(false);
                qty.setEditable(false);
                dose.setEditable(false);
                centre.setEditable(false);

                int option = JOptionPane.showConfirmDialog(delete, message, "Remove Vaccine", JOptionPane.OK_CANCEL_OPTION);
                if(option == JOptionPane.OK_OPTION) {
                    DataIO.allVaccine.remove(found); //Removes the index of Appointment ID from the ArrayList
                    DataIO.write();
                    JOptionPane.showMessageDialog(delete, "Record Successfully Deleted");
                    setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(delete, "Cancelled");
                    setVisible(true);
                }
            }else{
                JOptionPane.showMessageDialog(delete, "Vaccine ID not found");
                setVisible(true);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(delete, "Unsuitable Input Detected, Please Try Again");
            setVisible(true);
        }
    }
    private void Edit(){
        try{
            int idInp = Integer.parseInt(JOptionPane.showInputDialog(edit, "Vaccine ID : ", "Edit Vaccine", JOptionPane.INFORMATION_MESSAGE));
            if(idInp > 0) {
                Vaccine found = DataIO.checkingvac(idInp);
                Main.vaccine = found;
            }
            JTextField id = new JTextField(Integer.toString(Main.vaccine.getId()), 5);
            JTextField name = new JTextField(Main.vaccine.getName());
            JTextField qty = new JTextField(Integer.toString(Main.vaccine.getQuantity()), 5);
            JTextField dose = new JTextField(Integer.toString(Main.vaccine.getDose()), 5);
            Centre[] centres = {Centre.CentreA,Centre.CentreB,Centre.CentreC,Centre.CentreD,Centre.CentreE};
            JComboBox centre = new JComboBox(centres);
            centre.setSelectedItem(Main.vaccine.getCentre());

            Object[] message = {
                    "Vaccine ID: ", id,
                    "Vaccine Name: ", name,
                    "Vaccine Quantity (in Mil): ", qty,
                    "Vaccine Dose: ", dose,
                    "Vaccination Centre: ", centre,
            };
            id.setEditable(false);

            int option = JOptionPane.showConfirmDialog(edit, message, "Modify Appointment", JOptionPane.OK_CANCEL_OPTION);

            String nmInp = name.getText();
            int qtyInp = Integer.parseInt(qty.getText());
            int doseInp = Integer.parseInt(dose.getText());
            Centre cntInp = (Centre) centre.getSelectedItem();

            if(option == JOptionPane.OK_OPTION){
                Vaccine foundcen = DataIO.checkingvaccen(nmInp,cntInp);
                if(foundcen == null) {
                    Main.vaccine.setName(nmInp);
                    Main.vaccine.setQuantity(qtyInp);
                    Main.vaccine.setDose(doseInp);
                    Main.vaccine.setCentre(cntInp);
                    DataIO.write();
                    JOptionPane.showMessageDialog(edit, "Record Updated");
                    setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(add, "This Centre already has the same vaccine!");
                    setVisible(true);
                }
            }else{
                JOptionPane.showMessageDialog(edit, "Record Not Updated");
                setVisible(true);
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(edit, "Please Try Again");
            setVisible(true);
        }
    }
    private void View(){
        JFrame x = new JFrame();
        x.setTitle("All Vaccines");
        x.setSize(500, 400);
        x.setLocationRelativeTo(null);
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x.setLayout(new GridLayout(2, 1));

        Panel y2 = new Panel();
        y2.setLayout(new BoxLayout(y2, BoxLayout.Y_AXIS));
        int size = DataIO.allVaccine.size();

        Panel y3 = new Panel();
        y3.setLayout(new FlowLayout());

        String[] columnNames = { "ID", "Name", "Quantity (in Mil)", "Dose", "Centre"};
        String[][] data = new String[size][6];
        for(int i=0; i<size; i++){
            Vaccine a = DataIO.allVaccine.get(i);
            data[i][0] = ""+a.getId();
            data[i][1] = ""+a.getName();
            data[i][2] = ""+a.getQuantity();
            data[i][3] = ""+a.getDose();
            data[i][4] = ""+a.getCentre();
        }
        DefaultTableModel z = new DefaultTableModel(data, columnNames);
        JTable z1 = new JTable(z);

        TableRowSorter sorter = new TableRowSorter(z1.getModel()); //sort
        z1.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        int index = 0;
        sortKeys.add(new RowSorter.SortKey(index,SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        sorter.sort();

        z1.setEnabled(false);  //Disable Table Editing
        z1.getTableHeader().setReorderingAllowed(false); //Disable Row Reordering

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
        try{
            int idInp = Integer.parseInt(JOptionPane.showInputDialog(search, "Vaccine ID : ", "Modify Appointmnet", JOptionPane.INFORMATION_MESSAGE));
            if(idInp > 0) {
                Vaccine found = DataIO.checkingvac(idInp);
                Main.vaccine = found;

                JTextField id = new JTextField(Integer.toString(Main.vaccine.getId()), 5);
                JTextField name = new JTextField(Main.vaccine.getName());
                JTextField qty = new JTextField(Integer.toString(Main.vaccine.getQuantity()), 5);
                JTextField dose = new JTextField(Integer.toString(Main.vaccine.getDose()), 5);
                JTextField centre = new JTextField(Main.vaccine.getCentre().toString(), 5);

                Object[] message = {
                        "Vaccine ID: ", id,
                        "Vaccine Name: ", name,
                        "Vaccine Quantity (in Mil): ", qty,
                        "Vaccine Dose: ", dose,
                        "Vaccination Centre: ", centre,
                };

                id.setEditable(false);
                name.setEditable(false);
                qty.setEditable(false);
                dose.setEditable(false);
                centre.setEditable(false);

                JOptionPane.showMessageDialog(search, message, "View Appointment", JOptionPane.DEFAULT_OPTION);
                setVisible(true);
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(search, "Please Try Again");
            setVisible(true);
        }
    }
}
