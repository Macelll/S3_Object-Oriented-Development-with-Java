package com.covid19_vaccine_registration_system;

import java.util.ArrayList;

public class People{

    private String username, password;
    private Gender gender;
    private int age;
    private int vaccinatedAmount;
    /*private ArrayList<CitAppointmentMade> myCitAppointment = new ArrayList<CitAppointmentMade>();*/
    /*private ArrayList<NCitAppointmentMade> myNCitAppointment = new ArrayList<NCitAppointmentMade>();*/

    public People(String username, String password, Gender gender, int age, int vaccinatedAmount) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.vaccinatedAmount = vaccinatedAmount;
    }

    public void addVaccinatedAmt(int addAmt){
        if(Main.clogin != null) {
            int vacNum = Main.clogin.getVaccinatedAmount() + addAmt;
            Main.clogin.setVaccinatedAmount(vacNum);
            DataIO.write();
        }else{
            int vacNum = Main.nclogin.getVaccinatedAmount() + addAmt;
            Main.nclogin.setVaccinatedAmount(vacNum);
            DataIO.write();
        }
    }

    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setGender(Gender gender) { this.gender = gender; }
    public void setAge (int age) { this.age = age; }
    public void setVaccinatedAmount(int vaccinatedAmount) {
        this.vaccinatedAmount = vaccinatedAmount;
    }


    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }

    public Gender getGender() { return gender; }
    public int getAge() { return age; }

    public int getVaccinatedAmount() {
        return vaccinatedAmount;
    }

    /*public ArrayList<CitAppointmentMade> getMyCitAppointment() {
        return myCitAppointment;
    }
    public void setMyCitAppointment(ArrayList<CitAppointmentMade> myCitAppointment) {
        this.myCitAppointment = myCitAppointment;
    }*/

    /*public ArrayList<NCitAppointmentMade> getMyNCitAppointment() {
        return myNCitAppointment;
    }
    public void setMyNCitAppointment(ArrayList<NCitAppointmentMade> myNCitAppointment) {
        this.myNCitAppointment = myNCitAppointment;
    }*/
}
