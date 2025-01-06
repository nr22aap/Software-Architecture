package com.nuzhat.pms.controller;

import com.nuzhat.pms.model.*;
import com.nuzhat.pms.view.MainFrame;

import javax.swing.*;
import java.util.Queue;


/**
 * MainController class to handle interaction between the model and view.
 */
public class MainController {
    private QueueofCustomers customerQueue;  // Model for customer queue
    private ParcelMap parcelMap;             // Model for parcel storage
    private Worker worker;                   // Model for processing parcels
    private Log log;                         // Singleton log for event tracking
    private MainFrame view;                  // View for GUI components

    /**
     * Constructor initializes the controller with required models and view.
     *
     * @param view          GUI Frame
     * @param customerQueue Customer Queue Model
     * @param parcelMap     Parcel Map Model
     * @param worker        Worker Model
     * @param log           Log Model (Singleton)
     */
    public MainController(MainFrame view, QueueofCustomers customerQueue, ParcelMap parcelMap, Worker worker, Log log) {
        this.customerQueue = customerQueue;
        this.parcelMap = parcelMap;
        this.worker = worker;
        this.log = log;
        this.view = view;

        // Setup GUI controls
        setupGUI();

        // Update the initial view
        updateView();
    }

    /**
     * Sets up buttons and listeners in the GUI.
     */
    private void setupGUI() {
        // Process Next Customer Button
        JButton processButton = new JButton("Process Next Customer");
        processButton.addActionListener(e -> processNextCustomer());

        // Add New Customer Button
        JButton addCustomerButton = new JButton("Add New Customer");
        addCustomerButton.addActionListener(e -> addNewCustomer());

        // Exit Button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));

        // Add buttons to the frame
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(processButton);
        buttonPanel.add(addCustomerButton); // Add button for new customer
        buttonPanel.add(exitButton);

        view.add(buttonPanel, java.awt.BorderLayout.SOUTH);
    }

    /**
     * Updates the GUI with the current state of customers and parcels.
     */
    private void updateView() {
        // Update Customer Queue Panel
        view.getCustomerPanel().updateCustomerList(
                customerQueue.getCustomerQueue().stream().map(Customer::getName).toList()
        );

        // Update Parcel Panel
        view.getParcelPanel().updateParcelList(
                parcelMap.getParcels().values().stream()
                        .filter(parcel -> !parcel.isProcessed()) // Show only unprocessed parcels
                        .map(Parcel::getDetails)
                        .toList()
        );
    }

    /**
     * Processes the next customer in the queue.
     */
    private void processNextCustomer() {
        // Retrieve the customer queue
        Queue<Customer> queue = customerQueue.getCustomerQueue();

        // Check if queue is empty
        if (queue.isEmpty()) {
            JOptionPane.showMessageDialog(view, "No more customers to process!");
            return;
        }

        // Get the next customer
        Customer customer = queue.poll();

        // Fetch the parcel associated with the customer
        Parcel parcel = parcelMap.getParcelById(customer.getParcelId());

        // Handle missing parcel scenario
        if (parcel == null) {
            JOptionPane.showMessageDialog(view, "No parcel found for Customer: " +
                    customer.getName() + " with Parcel ID: " + customer.getParcelId());
            log.addLog("No parcel found for Customer: " +
                    customer.getName() + " with Parcel ID: " + customer.getParcelId());
            return;
        }

        // Process the parcel using Worker
        String result = worker.processCustomer(customer, parcel);

        // Show processing result
        JOptionPane.showMessageDialog(view, result);

        // Log the processing details
        log.addLog(result);

        // Update GUI panels after processing
        updateView();
    }

    /**
     * Adds a new customer dynamically through GUI input.
     */
    private void addNewCustomer() {
        // Prompt for Customer Name
        String name = JOptionPane.showInputDialog(view, "Enter Customer Name (e.g., John Doe):");
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Invalid Name. Please try again.");
            return;
        }

        // Prompt for Parcel ID
        String parcelId = JOptionPane.showInputDialog(view, "Enter Parcel ID (e.g., X919):");
        if (parcelId == null || parcelId.trim().isEmpty() || !parcelMap.getParcels().containsKey(parcelId)) {
            JOptionPane.showMessageDialog(view, "Invalid Parcel ID. Please ensure it exists.");
            return;
        }

        // Add the new customer to the queue
        Customer newCustomer = new Customer(name, parcelId);
        customerQueue.getCustomerQueue().add(newCustomer);

        // Log the addition
        log.addLog("Added new customer: " + name + " with Parcel ID: " + parcelId);

        // Update GUI
        updateView();

        // Confirm addition
        JOptionPane.showMessageDialog(view, "Customer " + name + " added successfully!");
    }
}
