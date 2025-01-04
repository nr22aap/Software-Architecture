package com.nuzhat.pms.view;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ParcelPanel extends JPanel {
    private JTextArea parcelListArea;

    public ParcelPanel() {
        setLayout(new BorderLayout());
        parcelListArea = new JTextArea(20, 30);
        parcelListArea.setEditable(false);
        add(new JScrollPane(parcelListArea), BorderLayout.CENTER);
    }


    public void updateParcelList(List<String> parcels) {
        StringBuilder builder = new StringBuilder("Available Parcels:\n");
        for (String parcel : parcels) {
            builder.append(parcel).append("\n");
        }
        parcelListArea.setText(builder.toString());
    }
}
