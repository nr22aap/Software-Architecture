package com.nuzhat.pms.model;


public class Parcel {
    private String parcelId;
    private double weight;
    private boolean isProcessed;

    public Parcel(String parcelId, double weight) {
        this.parcelId = parcelId;
        this.weight = weight;
        this.isProcessed = false;
    }

    public String getParcelId() {
        return parcelId;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void markAsProcessed() {
        this.isProcessed = true;
    }

    public String getDetails() {
        return String.format("%s - %.2f kg", parcelId, weight);
    }
}

