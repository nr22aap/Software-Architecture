package com.nuzhat.pms.model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class QueueofCustomers {
    private Queue<Customer> customerQueue;

    public QueueofCustomers() {
        customerQueue = new LinkedList<>();
    }

    public void loadCustomersFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+", 2);
                customerQueue.add(new Customer(parts[1], parts[0]));
            }
        } catch (IOException e) {
            System.err.println("Error reading customers file: " + e.getMessage());
        }
    }

    public Queue<Customer> getCustomerQueue() {
        return customerQueue;
    }
}

