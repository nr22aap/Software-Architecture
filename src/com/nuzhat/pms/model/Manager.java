package com.nuzhat.pms.model;

import com.nuzhat.pms.controller.MainController;
import com.nuzhat.pms.view.MainFrame;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Manager {
    private QueueofCustomers customerQueue;
    private ParcelMap parcelMap;
    private Worker worker;
    private Log log;
    private MainFrame view;
    public Manager() {
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));

        // Initialize models
        customerQueue = new QueueofCustomers();
        parcelMap = new ParcelMap();
        worker = new Worker();
        log = Log.getInstance();

        // Load data
        loadCustomerData("com/nuzhat/pms/data/customers.txt");
        loadParcelData("com/nuzhat/pms/data/parcels.txt");

        // Initialize GUI
        initializeGUI();
    }

    // Load Customer Data
    private void loadCustomerData(String filePath) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (input == null) {
                throw new IOException("Customer file not found: " + filePath);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+", 3);
                String name = parts[0] + " " + parts[1]; // Combine First and Last Name
                String parcelId = parts[2];
                customerQueue.getCustomerQueue().add(new Customer(name, parcelId));
            }
            log.addLog("Customer data loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error reading customers file: " + e.getMessage());
        }
    }

    // Load Parcel Data
    private void loadParcelData(String filePath) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (input == null) {
                throw new IOException("Parcel file not found: " + filePath);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;

            while ((line = reader.readLine()) != null) {
                // Parse only the first two columns (ID and weight)
                String[] parts = line.split("\\s+");
                if (parts.length >= 2) {
                    String parcelId = parts[0];
                    double weight = Double.parseDouble(parts[1]);
                    parcelMap.addParcel(new Parcel(parcelId, weight));
                }
            }
            log.addLog("Parcel data loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error reading parcels file: " + e.getMessage());
        }
    }

    private void initializeGUI() {
        view = new MainFrame();
        new MainController(view, customerQueue, parcelMap, worker, log);
        view.setVisible(true);
    }

    public void shutdown() {
        log.writeLogToFile("logs/log.txt");
        System.out.println("Logs saved. Application shutting down.");
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Nimbus Look and Feel not available.");
        }

        Manager manager = new Manager();
        Runtime.getRuntime().addShutdownHook(new Thread(manager::shutdown));
    }
}
