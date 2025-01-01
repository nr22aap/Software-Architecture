package com.nuzhat.pms.model;

import java.util.HashMap;
import java.util.Map;

public class ParcelMap {
    private Map<String, Parcel> parcels; // HashMap to store parcels with ID as the key

    // Constructor initializes the map
    public ParcelMap() {
        parcels = new HashMap<>();
    }

    /**
     * Adds a parcel to the map using its ID as the key.
     * @param parcel Parcel object to be added.
     */
    public void addParcel(Parcel parcel) {
        parcels.put(parcel.getParcelId(), parcel); // Add parcel to the map
    }

    /**
     * Retrieves a parcel by its ID.
     * @param parcelId ID of the parcel to fetch.
     * @return Parcel object if found, else null.
     */
    public Parcel getParcelById(String parcelId) {
        return parcels.get(parcelId); // Fetch parcel by ID
    }

    /**
     * Returns the entire parcel map.
     * @return Map of parcels.
     */
    public Map<String, Parcel> getParcels() {
        return parcels;
    }
}
