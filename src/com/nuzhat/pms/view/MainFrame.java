package com.nuzhat.pms.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CustomerQueuePanel customerPanel;
    private ParcelPanel parcelPanel;

    public MainFrame() {
        setTitle("Parcel Processing System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        // Initialize panels
        customerPanel = new CustomerQueuePanel();
        parcelPanel = new ParcelPanel();

        // Add panels to the frame
        add(customerPanel);
        add(parcelPanel);
    }

    public CustomerQueuePanel getCustomerPanel() {
        return customerPanel;
    }

    public ParcelPanel getParcelPanel() {
        return parcelPanel;
    }
}
