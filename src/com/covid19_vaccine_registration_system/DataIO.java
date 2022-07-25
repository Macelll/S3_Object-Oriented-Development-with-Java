package com.covid19_vaccine_registration_system;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DataIO {

    public static ArrayList<Citizen> allCitizen =
            new ArrayList<Citizen>();

    public static ArrayList<NonCitizen> allNonCitizen =
            new ArrayList<NonCitizen>();

    public static ArrayList<Admin> allAdmin =
            new ArrayList<Admin>();
			
	public static ArrayList<Appointment> allAppointment =
			new ArrayList<Appointment>();

    public static ArrayList<CitAppointmentMade> allCitAppointmentMade =
            new ArrayList<CitAppointmentMade>();

    public static ArrayList<NCitAppointmentMade> allNCitAppointmentMade =
            new ArrayList<NCitAppointmentMade>();

    public static ArrayList<Centre> allCentre =
            new ArrayList<Centre>();

    public static ArrayList<Vaccine> allVaccine =
            new ArrayList<Vaccine>();

    public static void read(){
        try{
            Scanner sc1 = new Scanner(new File("Citizen.txt"));
            while(sc1.hasNext()){
                String a = sc1.nextLine();
                String b = sc1.nextLine();
                Gender c = Gender.valueOf(sc1.nextLine());
                int d = Integer.parseInt(sc1.nextLine());
                int e = Integer.parseInt(sc1.nextLine());
                int f = Integer.parseInt(sc1.nextLine());

                sc1.nextLine();
                Citizen cit = new Citizen(a, b, c, d, e, f);
                allCitizen.add(cit);
            }
            Scanner sc2 = new Scanner(new File("NonCitizen.txt"));
            while(sc2.hasNext()){
                String a = sc2.nextLine();
                String b = sc2.nextLine();
                Gender c = Gender.valueOf(sc2.nextLine());
                int d = Integer.parseInt(sc2.nextLine());
                int e = Integer.parseInt(sc2.nextLine());
                int f = Integer.parseInt(sc2.nextLine());

                sc2.nextLine();
                NonCitizen ncit = new NonCitizen(a, b, c, d, e, f);
                allNonCitizen.add(ncit);
            }
            Scanner sc3 = new Scanner(new File("Admin.txt"));
            while(sc3.hasNext()){
                String a = sc3.nextLine();
                String b = sc3.nextLine();
                Gender c = Gender.valueOf(sc3.nextLine());
                int d = Integer.parseInt(sc3.nextLine());

                sc3.nextLine();
                Admin ad = new Admin(a, b, c, d);
                allAdmin.add(ad);
            }
            Scanner sc4 = new Scanner(new File("Appointment.txt"));
            while(sc4.hasNext()){
                int a = Integer.parseInt(sc4.nextLine());
                Centre b = Centre.valueOf(sc4.nextLine());
                Day c = Day.valueOf(sc4.nextLine());
                int d = Integer.parseInt(sc4.nextLine());
                sc4.nextLine();
                Appointment app = new Appointment(a,b,c,d);
                allAppointment.add(app);
            }

            Scanner sc5 = new Scanner(new File("Vaccine.txt"));
            while(sc5.hasNext()){
                int a = Integer.parseInt(sc5.nextLine());
                String b = sc5.nextLine();
                int c = Integer.parseInt(sc5.nextLine());
                int d = Integer.parseInt(sc5.nextLine());
                Centre e = Centre.valueOf(sc5.nextLine());
                sc5.nextLine();
                Vaccine vac = new Vaccine(a,b,c,d,e);
                allVaccine.add(vac);
            }

            Scanner sc6 = new Scanner(new File("CitAppointmentMade.txt"));
            while(sc6.hasNext()){
                Citizen a = DataIO.checking(sc6.nextLine());
                int b = Integer.parseInt(sc6.nextLine());
                Centre c = Centre.valueOf(sc6.nextLine());
                Day d = Day.valueOf(sc6.nextLine());
                int e = Integer.parseInt(sc6.nextLine());
                String f = sc6.nextLine();
                boolean g = Boolean.parseBoolean(sc6.nextLine());
                sc6.nextLine();
                CitAppointmentMade appmd = new CitAppointmentMade(a,b,c,d,e,f,g);
                allCitAppointmentMade.add(appmd);
                a.getMyCitAppointment().add(appmd);
            }

            Scanner sc7 = new Scanner(new File("NCitAppointmentMade.txt"));
            while(sc7.hasNext()){
                NonCitizen a = DataIO.checkingn(sc7.nextLine());
                int b = Integer.parseInt(sc7.nextLine());
                Centre c = Centre.valueOf(sc7.nextLine());
                Day d = Day.valueOf(sc7.nextLine());
                int e = Integer.parseInt(sc7.nextLine());
                String f = sc7.nextLine();
                boolean g = Boolean.parseBoolean(sc7.nextLine());
                sc7.nextLine();
                NCitAppointmentMade appmd = new NCitAppointmentMade(a,b,c,d,e,f,g);
                allNCitAppointmentMade.add(appmd);
                a.getMyNCitAppointment().add(appmd);
            }

        } catch(Exception e){
            System.out.println("Error while reading");
        }
    }

    public static void write(){
        try{
            PrintWriter c = new PrintWriter("Citizen.txt");
            for (Citizen cit : allCitizen) {
                c.println(cit.getUsername());
                c.println(cit.getPassword());
                c.println(cit.getGender());
                c.println(cit.getAge());
                c.println(cit.getVaccinatedAmount());
                c.println(cit.getCitizenID());
                c.println();
            }
            c.close();

            PrintWriter nc = new PrintWriter("NonCitizen.txt");
            for (NonCitizen ncit : allNonCitizen) {
                nc.println(ncit.getUsername());
                nc.println(ncit.getPassword());
                nc.println(ncit.getGender());
                nc.println(ncit.getAge());
                nc.println(ncit.getVaccinatedAmount());
                nc.println(ncit.getPassportNum());
                nc.println();
            }
            nc.close();

            PrintWriter a = new PrintWriter("Admin.txt");
            for (Admin ad : allAdmin) {
                a.println(ad.getUsername());
                a.println(ad.getPassword());
                a.println(ad.getGender());
                a.println(ad.getAge());
                a.println();
            }
            a.close();

            PrintWriter ap = new PrintWriter("Appointment.txt");
            for (Appointment app : allAppointment) {
                ap.println(app.getId());
                ap.println(app.getCentre());
                ap.println(app.getDay());
                ap.println(app.getTime());
                ap.println();
            }
            ap.close();

            PrintWriter v = new PrintWriter("Vaccine.txt");
            for (Vaccine vac : allVaccine) {
                v.println(vac.getId());
                v.println(vac.getName());
                v.println(vac.getQuantity());
                v.println(vac.getDose());
                v.println(vac.getCentre());
                v.println();
            }
            v.close();

            PrintWriter capmd = new PrintWriter("CitAppointmentMade.txt");
            for (CitAppointmentMade appmd : allCitAppointmentMade) {
                capmd.println(appmd.getOwner().getUsername());
                capmd.println(appmd.getId());
                capmd.println(appmd.getCentre());
                capmd.println(appmd.getDay());
                capmd.println(appmd.getTime());
                capmd.println(appmd.getVaccine());
                capmd.println(appmd.isCompleted());
                capmd.println();
            }
            capmd.close();

            PrintWriter ncapmd = new PrintWriter("NCitAppointmentMade.txt");
            for (NCitAppointmentMade appmd : allNCitAppointmentMade) {
                ncapmd.println(appmd.getOwner().getUsername());
                ncapmd.println(appmd.getId());
                ncapmd.println(appmd.getCentre());
                ncapmd.println(appmd.getDay());
                ncapmd.println(appmd.getTime());
                ncapmd.println(appmd.getVaccine());
                ncapmd.println(appmd.isCompleted());
                ncapmd.println();
            }
            ncapmd.close();

        } catch(Exception e){
            System.out.println("Error while writing");
        }
    }


    public static Citizen checking(String x) {
        Citizen found = null;
        for(Citizen c : allCitizen){
            if(x.equals(c.getUsername())){
                return c;
            }
        }
        return null;
    }

    public static NonCitizen checkingn(String x) {
        NonCitizen found = null;
        for(NonCitizen nc : allNonCitizen){
            if(x.equals(nc.getUsername())){
                return nc;
            }
        }
        return null;
    }

    public static Admin checkinga(String x) {
        Admin found = null;
        for(Admin a : allAdmin){
            if(x.equals(a.getUsername())){
                return a;
            }
        }
        return null;
    }

    public static Appointment checkingapp(int x) {
        Appointment found = null;
        for(Appointment app : allAppointment){
            if(x == app.getId()){
                return app;
            }
        }
        return null;
    }

    public static Appointment checkingappdup(Centre cen, Day day, int time){
        Appointment found = null;
        for(Appointment app : allAppointment){
            if(cen == app.getCentre() && day == app.getDay() && time == app.getTime()){
                return app;
            }
        }
        return null;
    }

    public static Vaccine checkingvac(int x) {
        Vaccine found = null;
        for(Vaccine vac : allVaccine){
            if(x == vac.getId()){
                return vac;
            }
        }
        return null;
    }

    public static Vaccine checkingvaccen(String vacname, Centre cen){
        Vaccine found = null;
        for(Vaccine vac : allVaccine){
            if(vacname.equals(vac.getName()) && cen.equals(vac.getCentre())){
                return vac;
            }
        }
        return null;
    }

    public static CitAppointmentMade checkingcitappmd(int x, String y){
        CitAppointmentMade found = null;
        for(CitAppointmentMade appmd : allCitAppointmentMade){
            if(x == appmd.getId() && y.equals(appmd.getOwner().getUsername())){
                return appmd;
            }
        }
        return null;
    }

    public static NCitAppointmentMade checkingncitappmd(int x, String y){
        NCitAppointmentMade found = null;
        for(NCitAppointmentMade appmd : allNCitAppointmentMade){
            if(x == appmd.getId() && y.equals(appmd.getOwner().getUsername())){
                return appmd;
            }
        }
        return null;
    }
}