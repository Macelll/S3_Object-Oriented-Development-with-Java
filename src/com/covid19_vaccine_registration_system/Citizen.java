package com.covid19_vaccine_registration_system;

import java.util.ArrayList;

public class Citizen extends People{
    private int citizenID;
    private ArrayList<CitAppointmentMade> myCitAppointment = new ArrayList<CitAppointmentMade>();

    public Citizen(String username, String password, Gender gender, int age, int vaccinatedAmount, int citizenID) {
        super(username, password, gender, age, vaccinatedAmount);
        this.citizenID = citizenID;
    }

    public void updateCitProfile(String nmInput, String psInput, Gender gdInput, int ageInput, int vacInput, int citIDInput){
        Main.clogin.setUsername(nmInput);
        Main.clogin.setPassword(psInput);
        Main.clogin.setGender(gdInput);
        Main.clogin.setAge(ageInput);
        Main.clogin.setVaccinatedAmount(vacInput);
        Main.clogin.setCitizenID(citIDInput);
        DataIO.write();
    }

    public int getCitizenID() {
        return citizenID;
    }
    public void setCitizenID(int citizenID) {
        this.citizenID = citizenID;
    }

    public ArrayList<CitAppointmentMade> getMyCitAppointment() {
        return myCitAppointment;
    }

    public void setMyCitAppointment(ArrayList<CitAppointmentMade> myCitAppointment) {
        this.myCitAppointment = myCitAppointment;
    }
}