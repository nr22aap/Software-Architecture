package com.nuzhat.pms.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
git add src/com/nuzhat/pms/view/CustomerQueuePanel.java
public class CustomerQueuePanel extends JPanel {
    private JTextArea customerQueueArea;

    public CustomerQueuePanel() {
        setLayout(new BorderLayout());
        customerQueueArea = new JTextArea(20, 30);
        customerQueueArea.setEditable(false);
        add(new JScrollPane(customerQueueArea), BorderLayout.CENTER);
    }

    public void updateCustomerList(List<String> customers) {
        StringBuilder builder = new StringBuilder("Customers in Queue:\n");
        for (String customer : customers) {
            builder.append(customer).append("\n");
        }
        customerQueueArea.setText(builder.toString());
    }
}
