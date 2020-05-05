package com.example.wonderfulproject;

class TaxiMath {
    public double miles;
    public double basePrice;
    public double pricePerMile;
    public double totalCost;

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double base) {
        basePrice = base;
    }

    public double getMiles() {
        return miles;
    }

    public void setMiles(double milez) {
        miles = milez;
    }

    public double getPricePerMile() {
        return pricePerMile;
    }

    public void setPricePerMile(double perMile) {
        pricePerMile = perMile;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost() {
        totalCost = pricePerMile * miles + basePrice;
    }
}
