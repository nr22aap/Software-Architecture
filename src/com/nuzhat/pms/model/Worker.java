package com.nuzhat.pms.model;

public class Worker {
    private Log log;

    public Worker() {
        log = Log.getInstance();
    }

    public String processCustomer(Customer customer, Parcel parcel) {
        double fee = parcel.getWeight() * 2.0;
        parcel.markAsProcessed();

        String message = String.format("Processed Customer: %s | Parcel: %s | Fee: $%.2f",
                customer.getName(), parcel.getParcelId(), fee);
        log.addLog(message);
        return message;
    }
}
