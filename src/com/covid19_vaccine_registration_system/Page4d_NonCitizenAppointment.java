package com.covid19_vaccine_registration_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Page4d_NonCitizenAppointment extends JFrame implements ActionListener{
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == makeApp){
            setVisible(false);
            MakeApp();
        }else if(e.getSource() == viewApp){
            setVisible(false);
            ViewApp();
        }else if(e.getSource() == compApp){
            setVisible(false);
            CompApp();
        }else if(e.getSource() == cancelApp){
            setVisible(false);
            CancelApp();
        }else if(e.getSource() == back){
            setVisible(false);
            Main.fourthB.setVisible(true);
        }
    }

    private JButton makeApp,viewApp,compApp,cancelApp,back;

    public Page4d_NonCitizenAppointment(){
        setTitle("Appointment Menu");
        setSize(350,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,3));

        makeApp = new JButton("Make Appointment");
        viewApp = new JButton("View Appointment");
        compApp = new JButton("Complete Appointment");
        cancelApp = new JButton("Cancel Appointment");
        back = new JButton("Go Back");

        add(makeApp);
        add(viewApp);
        add(compApp);
        add(cancelApp);
        add(back);

        makeApp.addActionListener(this);
        viewApp.addActionListener(this);
        compApp.addActionListener(this);
        cancelApp.addActionListener(this);
        back.addActionListener(this);

        /* setVisible(true);*/
    }

    private void MakeApp(){
        int s = Main.nclogin.getMyNCitAppointment().size();

        if(s == 0 || Main.nclogin.getMyNCitAppointment().get(s-1).isCompleted()) {
            Centre[] centres = {Centre.CentreA, Centre.CentreB, Centre.CentreC,Centre.CentreD,Centre.CentreE};
            Centre cen = (Centre) JOptionPane.showInputDialog(makeApp, "Select Centre: ", "Centre Selection",
                    JOptionPane.INFORMATION_MESSAGE, null, centres, centres[0]);

            try {
                ArrayList<String> vaclist = new ArrayList<String>();

                JComboBox<String> vacname = new JComboBox<String>();

                for (int i = 0; i < DataIO.allVaccine.size(); i++) {
                    Vaccine vac = DataIO.allVaccine.get(i);
                    if (cen.equals(vac.getCentre())) {
                        vaclist.add(vac.getName());
                    }
                }

                ArrayList<Integer> idlist = new ArrayList<Integer>();

                for (int o = 0; o < DataIO.allAppointment.size(); o++) {
                    Appointment app = DataIO.allAppointment.get(o);
                    if (cen.equals(app.getCentre())) {
                        idlist.add(Integer.parseInt(String.valueOf(app.getId())));
                    }
                }

                if (vaclist.size() != 0 && idlist.size() != 0) {

                    for (int ii = 0; ii < vaclist.size(); ii++) {
                        vacname.addItem(vaclist.get(ii));
                    }

                    JFrame x = new JFrame();
                    x.setTitle(String.valueOf(cen));
                    x.setSize(500, 400);
                    x.setLocationRelativeTo(null);
                    x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    x.setLayout(new GridLayout(4, 1));

                    Panel y1 = new Panel();
                    JLabel y1lab = new JLabel("Vaccine Available: ");
                    y1.setLayout(new FlowLayout());
                    y1.add(y1lab);
                    y1.add(vacname);

                    Panel y2 = new Panel();
                    y2.setLayout(new BoxLayout(y2, BoxLayout.Y_AXIS));
                    int size = DataIO.allAppointment.size();

                    Panel y3 = new Panel();
                    y3.setLayout(new FlowLayout());

                    Panel y4 = new Panel();
                    y4.setLayout(new FlowLayout());

                    JComboBox<Integer> idBox = new JComboBox<Integer>();

                    String[] columnNames = {"ID", "Centre", "Day", "Time"};
                    String[][] data = new String[size][4];
                    for (int j = 0; j < size; j++) {
                        Appointment a = DataIO.allAppointment.get(j);
                        if(a.getCentre().equals(cen)) {
                            data[j][0] = Integer.toString(a.getId());
                            data[j][1] = "" + a.getCentre();
                            data[j][2] = "" + a.getDay();
                            data[j][3] = "" + a.getTime();
                            idBox.addItem(Integer.parseInt(String.valueOf(a.getId())));
                        }
                    }

                    DefaultTableModel z = new DefaultTableModel(data, columnNames);

                    JTable z1 = new JTable(z);
                    z1.setEnabled(false);  //Disable Table Editing
                    z1.getTableHeader().setReorderingAllowed(false); //Disable Row Reordering

                    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(z1.getModel()); //sort
                    z1.setRowSorter(sorter);
                    List<RowSorter.SortKey> sortKeys = new ArrayList<>();
                    int index = 1;
                    sortKeys.add(new RowSorter.SortKey(index, SortOrder.DESCENDING));
                    sorter.setSortKeys(sortKeys);
                    sorter.sort();

                    JScrollPane sp = new JScrollPane(z1);
                    y2.add(sp);

                    JLabel idlab = new JLabel("Select Appointment ID: ");
                    y3.add(idlab);
                    y3.add(idBox);

                    Button ok = new Button("Ok");
                    Button cancel = new Button("Cancel");
                    ok.setPreferredSize(new Dimension(25, 25));
                    cancel.setPreferredSize(new Dimension(45, 30));
                    y4.add(ok);
                    y4.add(cancel);

                    ok.addActionListener(new ActionListener() {
                        /*@Override*/
                        public void actionPerformed(ActionEvent e) {
                            String vaccine = (String) vacname.getSelectedItem();
                            Integer idSelect = (Integer) idBox.getSelectedItem();
                            boolean isVac = false;
                            for (int k = 0; k < size; k++) {
                                Appointment apps = DataIO.allAppointment.get(k);
                                if (idSelect == apps.getId()) {
                                    NCitAppointmentMade g = new NCitAppointmentMade(Main.nclogin,apps.getId(), apps.getCentre(), apps.getDay(), apps.getTime(),vaccine,isVac);
                                    Main.nclogin.getMyNCitAppointment().add(g);
                                    DataIO.allNCitAppointmentMade.add(g);
                                    DataIO.write();
                                    break;
                                }
                            }

                            JOptionPane.showMessageDialog(makeApp, "Appointment has been made!");
                            x.setVisible(false);
                            setVisible(true);
                        }
                    });

                    cancel.addActionListener(new ActionListener() {
                        /*@Override*/
                        public void actionPerformed(ActionEvent e) {
                            x.setVisible(false);
                            setVisible(true);
                        }
                    });

                    x.add(y1);
                    x.add(y2);
                    x.add(y3);
                    x.add(y4);
                    x.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(makeApp, "This centre has no appointment / vaccine available!");
                    setVisible(true);
                }
            }catch (Exception e){
                setVisible(true);
            }
        }else{
            JOptionPane.showMessageDialog(makeApp, "You have an unattended appointment");
            setVisible(true);

        }
    }

    private void ViewApp(){
        int s = Main.nclogin.getMyNCitAppointment().size();
        if(s == 0 || Main.nclogin.getMyNCitAppointment().get(s-1).isCompleted()) {
            JOptionPane.showMessageDialog(viewApp,"You do not have any appointment");
            setVisible(false);
            setVisible(true);
        }else{
            JTextField id = new JTextField(Integer.toString(Main.nclogin.getMyNCitAppointment().get(s - 1).getId()), 5);
            JTextField centre = new JTextField(Main.nclogin.getMyNCitAppointment().get(s - 1).getCentre().toString());
            JTextField day = new JTextField(Main.nclogin.getMyNCitAppointment().get(s - 1).getDay().toString());
            JTextField time = new JTextField(Integer.toString(Main.nclogin.getMyNCitAppointment().get(s - 1).getTime()));
            JTextField vaccine = new JTextField(Main.nclogin.getMyNCitAppointment().get(s - 1).getVaccine());

            Object[] message = {
                    "Appointment ID: ", id,
                    "Vaccination Centre : ", centre,
                    "Day : ", day,
                    "Time (Hour) : ", time,
                    "Vaccine : ", vaccine
            };
            id.setEditable(false);
            centre.setEditable(false);
            day.setEditable(false);
            time.setEditable(false);
            vaccine.setEditable(false);

            JOptionPane.showConfirmDialog(viewApp, message, "View Appointment", JOptionPane.DEFAULT_OPTION);
            setVisible(true);
        }
    }

    private void CompApp(){
        int s = Main.nclogin.getMyNCitAppointment().size();
        if (s == 0 || Main.nclogin.getMyNCitAppointment().get(s-1).isCompleted()) {
            JOptionPane.showMessageDialog(compApp, "You do not have any appointment");
            setVisible(true);
        }else{
            JTextField id = new JTextField(Integer.toString(Main.nclogin.getMyNCitAppointment().get(s - 1).getId()), 5);
            JTextField centre = new JTextField(Main.nclogin.getMyNCitAppointment().get(s - 1).getCentre().toString());
            JTextField day = new JTextField(Main.nclogin.getMyNCitAppointment().get(s - 1).getDay().toString());
            JTextField time = new JTextField(Integer.toString(Main.nclogin.getMyNCitAppointment().get(s - 1).getTime()));
            JTextField vaccine = new JTextField(Main.nclogin.getMyNCitAppointment().get(s - 1).getVaccine());

            Object[] message = {
                    "Appointment ID: ", id,
                    "Vaccination Centre : ", centre,
                    "Day : ", day,
                    "Time (Hour) : ", time,
                    "Vaccine : ", vaccine,
                    "\nDo you want to complete this appointment: "
            };
            id.setEditable(false);
            centre.setEditable(false);
            day.setEditable(false);
            time.setEditable(false);
            vaccine.setEditable(false);

            try {
                int option = JOptionPane.showConfirmDialog(compApp, message, "Complete Appointment", JOptionPane.OK_CANCEL_OPTION);
                if(option == JOptionPane.OK_OPTION) {
                    String confirm = JOptionPane.showInputDialog("Enter your Passport Number to complete appointment: ");
                    if (Main.nclogin.getPassportNum() == Integer.parseInt(confirm)) {
                        JOptionPane.showMessageDialog(compApp, "Your Appointment have been Completed!");
                        Main.nclogin.addVaccinatedAmt(1); //Modularity
                        Main.nclogin.getMyNCitAppointment().get(s - 1).setCompleted(true);
                        DataIO.write();
                        setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(compApp, "Wrong Input!");
                        setVisible(true);
                    }
                }else{
                    setVisible(true);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(compApp, "Please try again!");
                setVisible(true);
            }
        }
    }

    private void CancelApp(){
        int s = Main.nclogin.getMyNCitAppointment().size();
        if (s == 0 || Main.nclogin.getMyNCitAppointment().get(s-1).isCompleted()) {
            JOptionPane.showMessageDialog(compApp, "You do not have any appointment");
            setVisible(true);
        }else{
            NCitAppointmentMade found = DataIO.checkingncitappmd(Main.nclogin.getMyNCitAppointment().get(s-1).getId(),Main.nclogin.getUsername());
            if(found != null){
                Main.nclogin.getMyNCitAppointment().remove(found);
                DataIO.allNCitAppointmentMade.remove(found);
                DataIO.write();
                JOptionPane.showMessageDialog(compApp, "Your Appointment have been Canceled!");

                setVisible(true);
            }else{
                JOptionPane.showMessageDialog(compApp, "Please try again!");
            }
        }
    }
}
