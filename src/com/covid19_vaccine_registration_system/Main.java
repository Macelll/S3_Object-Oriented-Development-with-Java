package com.covid19_vaccine_registration_system;
//Commenting = CTRL + SHIFT + /
//Indenting/De-indent Block = TAB / SHIFT + TAB

import java.awt.*;
import java.io.*;
import java.util.Scanner;


public class Main {

    public static Page1_Main first = new Page1_Main();
    public static Page2_Register second = new Page2_Register();
    public static Page3_Login third = new Page3_Login();
    public static Page4a_Citizen fourthA = new Page4a_Citizen();
    public static Page4b_NonCitizen fourthB = new Page4b_NonCitizen();
    public static Page4c_CitizenAppointment fourthC = new Page4c_CitizenAppointment();
    public static Page4d_NonCitizenAppointment fourthD = new Page4d_NonCitizenAppointment();
    public static Page5_Admin fifth = new Page5_Admin();
    public static Page5a_people fifthA = new Page5a_people();
    public static Page5b_appointment fifthB = new Page5b_appointment();
    public static Page5c_supply fifthC = new Page5c_supply();
    /*public static Page5d_report fifthD = new Page5d_report();*/
    public static Citizen clogin;
    public static NonCitizen nclogin;
    public static Admin alogin;
    public static Appointment appointment;
    public static Vaccine vaccine;
    public static CitAppointmentMade citAppointmentMade;
    public static NCitAppointmentMade ncitAppointmentMade;

    public static void main(String[] args) throws IOException {
        DataIO.read();
    }
}
