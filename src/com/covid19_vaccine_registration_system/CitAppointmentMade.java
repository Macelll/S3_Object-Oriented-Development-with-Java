package com.covid19_vaccine_registration_system;

public class CitAppointmentMade extends Appointment{

    private Citizen owner;
    private String vaccine;
    private boolean isCompleted;

    public CitAppointmentMade(Citizen owner, int id, Centre centre, Day day, int time, String vaccine, boolean isCompleted) {
        super(id, centre, day, time);
        this.owner = owner;
        this.vaccine = vaccine;
        this.isCompleted = isCompleted;
    }

    public Citizen getOwner() {
        return owner;
    }

    public void setOwner(Citizen owner) {
        this.owner = owner;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
