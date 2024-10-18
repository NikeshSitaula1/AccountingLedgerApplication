package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

// Ledger class representing a financial transaction
public class Ledger {

    // Private fields for the transaction details
    private UUID id;
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;



    // Constructor to initialize a new Ledger object with all fields
    public Ledger(UUID id, LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.id = id; //Set Transaction ID
        this.date = date; //Set Transaction date
        this.time = time; //Set Transaction time
        this.description = description; //Set Transaction description
        this.vendor = vendor; //Set Transaction vendor. Vendor for this app, would be the company that you have made transaction with
        this.amount = amount; //Set transaction amount. Amount would be the sum that you have deposited or paid
    }


    /*
    Getter and Setter methods
     */

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Override of the toString method to return a formatted string with transaction details
    public String toString() {
        return "Ledger{" +
                "date=" + date +
                ", time=" + time.format(DateTimeFormatter.ofPattern("HH:mm:ss")) +
                ", description='" + description + '\'' +
                ", vendor='" + vendor + '\'' +
                ", amount=" + amount +
                '}';
    }
}
