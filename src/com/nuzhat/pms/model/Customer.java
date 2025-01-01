package com.nuzhat.pms.model;

public class Customer {
    private String name;
    private String parcelId; // Explicitly linked Parcel ID

    public Customer(String name, String parcelId) {
        this.name = name;
        this.parcelId = parcelId;
    }

    public String getName() {
        return name;
    }

    public String getParcelId() {
        return parcelId; // Returns associated Parcel ID
    }
}

