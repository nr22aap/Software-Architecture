package com.nuzhat.pms.model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ParcelMap {
    private Map<String, Parcel> parcels;

    public ParcelMap() {
        parcels = new HashMap<>();
    }

    public void loadParcelsFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                parcels.put(parts[0], new Parcel(parts[0], Double.parseDouble(parts[1])));
            }
        } catch (IOException e) {
            System.err.println("Error reading parcels file: " + e.getMessage());
        }
    }

    public Parcel getParcelById(String parcelId) {
        return parcels.get(parcelId);
    }

    public Map<String, Parcel> getParcels() {
        return parcels;
    }
}

