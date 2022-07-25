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

public class Page5_Admin extends JFrame implements ActionListener {
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == people){
            setVisible(false);
            Main.fifthA.setVisible(true);
        }else if(e.getSource() == appointment){
            setVisible(false);
            Main.fifthB.setVisible(true);
        }else if(e.getSource() == supply){
            setVisible(false);
            Main.fifthC.setVisible(true);
        }else if(e.getSource() == report){
            setVisible(false);

            float totalCitAge = 0;
            int citizens = 0;

            int citMale = 0;
            int citFemale = 0;

            int cVac0 = 0;
            int cVac1 = 0;
            int cVac2 = 0;

            for (Citizen c : DataIO.allCitizen){
                totalCitAge += c.getAge();  //Get Age
                citizens += 1;

                if(c.getGender() == Gender.Male){    //Get Gender
                    citMale += 1;
                }else {
                    citFemale += 1;
                };

                if(c.getVaccinatedAmount() == 0){   //Vac Amount
                    cVac0 += 1;
                }else if(c.getVaccinatedAmount() == 1){
                    cVac1 += 1;
                }else {
                    cVac2 += 1;
                }
            }

            float totalNCitAge = 0;
            int nCitizens = 0;

            int nCitMale = 0;
            int nCitFemale = 0;

            int nCVac0 = 0;
            int nCVac1 = 0;
            int nCVac2 = 0;
            for (NonCitizen nc : DataIO.allNonCitizen){
                totalNCitAge += nc.getAge();    //Get Age
                nCitizens += 1;

                if(nc.getGender() == Gender.Male){   //Get Gender
                    nCitMale += 1;
                }else {
                    nCitFemale += 1;
                }

                if(nc.getVaccinatedAmount() == 0){   //Vac Amount
                    nCVac0 += 1;
                }else if(nc.getVaccinatedAmount() == 1){
                    nCVac1 += 1;
                }else {
                    nCVac2 += 1;
                }
            }

            JFrame x = new JFrame();
            x.setTitle("User Statistical Report");
            x.setSize(600, 310);

            x.setLocationRelativeTo(null);
            x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            x.setLayout(new GridLayout(2, 1));

            Panel y2 = new Panel();
            y2.setLayout(new BoxLayout(y2, BoxLayout.Y_AXIS));

            Panel y3 = new Panel();
            y3.setLayout(new FlowLayout());

            String[] columnNames = {"Stats", "Citizens", "Non-Citizens"};
            String[][] data = new String[7][3];
            data[0][0] = "Total Citizens";
            data[0][1] = Integer.toString(citizens);
            data[0][2] = Integer.toString(nCitizens);

            data[1][0] = "Gender: Male";
            data[1][1] = Integer.toString(citMale);
            data[1][2] = Integer.toString(nCitMale);

            data[2][0] = "Gender: Female";
            data[2][1] = Integer.toString(citFemale);
            data[2][2] = Integer.toString(nCitFemale);

            data[3][0] = "Average Age";
            data[3][1] = Float.toString(totalCitAge/citizens);
            data[3][2] = Float.toString(totalNCitAge/nCitizens);

            data[4][0] = "Vaccinated Amount: 0";
            data[4][1] = Integer.toString(cVac0);
            data[4][2] = Integer.toString(nCVac0);

            data[5][0] = "Vaccinated Amount: 1";
            data[5][1] = Integer.toString(cVac1);
            data[5][2] = Integer.toString(nCVac1);

            data[6][0] = "Vaccinated Amount: 2";
            data[6][1] = Integer.toString(cVac2);
            data[6][2] = Integer.toString(nCVac2);

            DefaultTableModel z = new DefaultTableModel(data, columnNames);
            JTable z1 = new JTable(z);
            z1.setEnabled(false); //Disable Editing
            z1.getTableHeader().setReorderingAllowed(false); //Disable Row Reordering

            TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(z1.getModel()); //sort
            z1.setRowSorter(sorter);
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();
            int index = 0;
            sortKeys.add(new RowSorter.SortKey(index,SortOrder.ASCENDING));
            sorter.setSortKeys(sortKeys);
            sorter.sort();

            JScrollPane sp = new JScrollPane(z1);
            y2.add(sp);

            Button ok = new Button("OK");
            Button generateChart = new Button("Generate Chart");
            ok.setPreferredSize(new Dimension(200, 70));
            generateChart.setPreferredSize(new Dimension(200, 70));
            y3.add(ok);
            y3.add(generateChart);

            x.add(y2);
            x.add(y3);

            x.setVisible(true);

            ok.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    x.dispose();
                    setVisible(true);
                }
            });

            int finalCVac = cVac0;
            int finalCVac1 = cVac1;
            int finalCVac2 = cVac2;

            int finalnCVac = nCVac0;
            int finalnCVac1 = nCVac1;
            int finalnCVac2 = nCVac2;

            generateChart.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame f = new JFrame();
                    f.setTitle("Citizen Vaccine Bar Chart");
                    f.setSize(600, 300);
                    f.setLocation(200,260);
                    double[] citValues = new double[3];
                    String[] citNames = new String[3];

                    citValues[0] = finalCVac;
                    citNames[0] = "Vaccinated Amount = 0";

                    citValues[1] = finalCVac1;
                    citNames[1] = "Vaccinated Amount = 1";

                    citValues[2] = finalCVac2;
                    citNames[2] = "Vaccinated Amount = 2";

                    f.getContentPane().add(new BarChart(citValues, citNames, "Citizen Vaccine Distribution",Color.pink));

                    f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    f.setVisible(true);

                    JFrame g = new JFrame();
                    g.setTitle("Non Citizen Vaccine Bar Chart");
                    g.setSize(600, 300);
                    g.setLocation(800,260);
                    double[] nCitValues = new double[3];
                    String[] nCitNames = new String[3];

                    nCitValues[0] = finalnCVac;
                    nCitNames[0] = "Vaccinated Amount = 0";

                    nCitValues[1] = finalnCVac1;
                    nCitNames[1] = "Vaccinated Amount = 1";

                    nCitValues[2] = finalnCVac2;
                    nCitNames[2] = "Vaccinated Amount = 2";

                    g.getContentPane().add(new BarChart(nCitValues, nCitNames, "Non-Citizen Vaccine Distribution",Color.CYAN));

                    g.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    g.setVisible(true);
                }
            });
        }else if(e.getSource() == logout){
            setVisible(false);
            Main.first.setVisible(true);
        }
    }

    private Button people, appointment, supply, report, logout;

    public Page5_Admin(){
        setTitle("Admin Menu");
        setSize(500, 210);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout()); //top-bottom, center alignment

        people = new Button("Manage People");
        appointment = new Button("Manage Appointments");
        supply = new Button("Manage Vaccination Supply");
        report = new Button("Produce Report");
        logout = new Button("Logout");

        people.setPreferredSize(new Dimension(200, 50));
        appointment.setPreferredSize(new Dimension(200, 50));
        supply.setPreferredSize(new Dimension(200, 50));
        report.setPreferredSize(new Dimension(200, 50));
        logout.setPreferredSize(new Dimension(100, 50));

        add(people);
        add(appointment);
        add(supply);
        add(report);
        add(logout);

        people.addActionListener(this);
        appointment.addActionListener(this);
        supply.addActionListener(this);
        report.addActionListener(this);
        logout.addActionListener(this);

        /*setVisible(true);*/
    }
}
