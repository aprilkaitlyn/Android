package com.example.TaxiCalculator;

class Taxi { //getters & setters
    
    public double base;
    public double per;
    private int id;
    public String name;

    public Taxi(int id, String name, double base, double perMile) {
        setID(id);
        setName(name);
        setBasePrice(base);
        setPricePerMile(perMile);
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) { name = newName; }

    public double getBasePrice() {
        return base;
    }

    public void setBasePrice(double newBase) {
        base = newBase;
    }
    
    public double getPricePerMile() {
        return per;
    }

    public void setPricePerMile(double newPer) {
        per = newPer;
    }
    
    public String toString() { //displayed in DeleteActivity
        return "name: " + name + " | base price: $" + base + " | per mile: $" + per; }

    public int getId() { return id; }

    private void setID(int newId) {
        id = newId;
    }

}
