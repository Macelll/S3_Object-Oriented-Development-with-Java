package com.covid19_vaccine_registration_system;

public class Vaccine {
    private int id;
    private String name;
    private int quantity;
    private int dose;
    private Centre centre;

    public Vaccine(int id, String name, int quantity, int dose, Centre centre) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.dose = dose;
        this. centre = centre;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDose() {
        return dose;
    }
    public void setDose(int dose) {
        this.dose = dose;
    }

    public Centre getCentre() {
        return centre;
    }
    public void setCentre(Centre centre) {
        this.centre = centre;
    }
}
