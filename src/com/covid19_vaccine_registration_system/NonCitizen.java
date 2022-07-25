package com.covid19_vaccine_registration_system;

import java.util.ArrayList;

public class NonCitizen extends People {
    private int passportNum;
    private ArrayList<NCitAppointmentMade> myNCitAppointment = new ArrayList<NCitAppointmentMade>();

    public NonCitizen(String username, String password, Gender gender, int age, int isVaccinated, int passportNum) {
        super(username, password, gender, age, isVaccinated);
        this.passportNum = passportNum;
    }

    public void updateNCitProfile(String nmInput, String psInput, Gender gdInput, int ageInput, int vacInput, int passportInput){
        Main.nclogin.setUsername(nmInput);
        Main.nclogin.setPassword(psInput);
        Main.nclogin.setGender(gdInput);
        Main.nclogin.setAge(ageInput);
        Main.nclogin.setVaccinatedAmount(vacInput);
        Main.nclogin.setPassportNum(passportInput);
        DataIO.write();
    }

    public int getPassportNum() {
        return passportNum;
    }
    public void setPassportNum(int passportNum) {
        this.passportNum = passportNum;
    }

    public ArrayList<NCitAppointmentMade> getMyNCitAppointment() {
        return myNCitAppointment;
    }
    public void setMyNCitAppointment(ArrayList<NCitAppointmentMade> myNCitAppointment) {
        this.myNCitAppointment = myNCitAppointment;
    }
}
