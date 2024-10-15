package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class MainTest {

    static ArrayList<Ledger> transactions = new ArrayList<>();


    public static void main(String[] args) {
        homeScreen();

    }


    static void homeScreen(){

        do{
            try{
                System.out.println("-".repeat(40));
                System.out.println("Welcome to the Accounting Ledger App");
                System.out.println("Please choose one of the following options");
                System.out.println("1. Add [D]eposit");
                System.out.println("2. Make [P]ayment] (Debit)");
                System.out.println("3. [L]edger");
                System.out.println("4. E[X]it the Application");
                System.out.print(">> ");

                String option = Console.PromptForString();

                if (option.equalsIgnoreCase("D") || option.equals("1")){
                    addDeposit();
                } else if (option.equalsIgnoreCase("P") || option.equals("2")) {
                    makePayment();
                } else if (option.equalsIgnoreCase("L") || option.equals("3")) {
                    Ledger();
                } else if (option.equalsIgnoreCase("X") || option.equals("4")) {
                    return;
                } else {
                    System.out.println("Invalid Entry. Please try again.");
                }


            }catch (Exception e){
                System.out.println("Invalid Entry. Please try again.");
            }
        }while (true);
    }

    static void addDeposit(){

        String depositDescription = Console.PromptForString("Enter the name of the Item: ");
        String depositVendor = Console.PromptForString("Enter the name of the vendor: ");
        double depositAmount = Console.PromptForDouble("Enter the amount you have spent: ");

        //LocalDate depositDate = Console.PromptForDate("Enter the date of purchase");
        //LocalTime depositTime = Console.PromptForTime("Enter the time of purchase");

        LocalDate depositDate = LocalDate.now();
        LocalTime depositTime = LocalTime.now();

        Ledger ledger = new Ledger(transactions.size() +1, depositDate, depositTime, depositDescription, depositVendor, depositAmount);
        transactions.add(ledger);
        saveTransaction(transactions);
    }

    static void makePayment(){

    }

    static void Ledger(){

        do{
            try{
                System.out.println("-".repeat(40));
                System.out.println("Please choose one of the following Ledger options");
                System.out.println("1. [A]ll Entries");
                System.out.println("2. [D]eposits");
                System.out.println("3. [P]ayments");
                System.out.println("4. [R]eports");
                System.out.println("5. [H]ome");

                System.out.print(">> ");

                String ledgerOption = Console.PromptForString();

                if (ledgerOption.equalsIgnoreCase("A") || ledgerOption.equals("1")){
                    allEntries();
                } else if (ledgerOption.equalsIgnoreCase("D") || ledgerOption.equals("2")) {
                    displayDeposits();
                } else if (ledgerOption.equalsIgnoreCase("P") || ledgerOption.equals("3")) {
                    displayPayments();
                } else if (ledgerOption.equalsIgnoreCase("R") || ledgerOption.equals("4")) {
                    reports();
                } else if (ledgerOption.equalsIgnoreCase("H") || ledgerOption.equals("5")){
                    return;
                }
                else  {
                    System.out.println("Invalid Entry. Please try again.");
                }

            }catch (Exception e){
                System.out.println("Invalid Entry. Please try again.");
            }
        }while (true);

    }

    static void allEntries(){

    }

    static void displayDeposits(){

    }

    static void displayPayments(){

    }

    static void reports(){

        do{
            try{
                System.out.println("-".repeat(40));
                System.out.println("Please choose one of the following Reports options");
                System.out.println("1. Month To Date");
                System.out.println("2. Previous Month");
                System.out.println("3. Year To Date");
                System.out.println("4. Previous Year");
                System.out.println("5. Search by Vendor");
                System.out.println("0. Back to Main Menu");
                System.out.print(">> ");

                int reportOption = Console.PromptForInt();

                if (reportOption == 1) {
                    System.out.println("monthToDate");
                } else if (reportOption == 2) {
                    System.out.println("previousMonth");
                } else if (reportOption == 3) {
                    System.out.println("yearToDate");
                } else if (reportOption == 4) {
                    System.out.println("previousYear");
                } else if (reportOption == 5) {
                    System.out.println("searchByVendor");
                } else if (reportOption == 0) {
                    return;
                }else {
                    System.out.println("Invalid Entry. Please try again.");
                }

            }catch (Exception e){
                System.out.println("Invalid Entry. Please try again.");
            }
        }while (true);

    }

    static void monthToDate(){

    }

    static void previousMonth(){

    }

    static void yearToDate(){

    }

    static void previousYear(){

    }

    static void searchByVendor(){

    }



    //todo FILE WRITER
    public static void saveTransaction(ArrayList<Ledger> transaction) {

        try {
            FileWriter fw = new FileWriter("transactions.csv");
            BufferedWriter bw = new BufferedWriter(fw);


            for(Ledger ledger : transaction) {
                String data = ledger.getDate() + "|"
                        + ledger.getTime() + "|" + ledger.getDescription() + "|" + ledger.getVendor()
                        + "|" + ledger.getAmount() + "\n";
                bw.write(data);
            }
            bw.close();

        } catch (Exception e){
            System.out.println("Error");
        }
    }




    //todo FOR FILE READER PROBABLY NOT NEEDED
//    public static ArrayList<Ledger> getTransaction(){
//        ArrayList<Ledger> transaction = new ArrayList<>();
//
//        try{
//            FileReader fr = new FileReader("transaction.csv");
//            BufferedReader br = new BufferedReader(fr);
//
//            String input;
//
//            while ((input = br.readLine()) != null) {
//                String [] tokens = input.split(Pattern.quote("|"));
//
//                String date = tokens[0];
//                String time = tokens[1];
//                String description = tokens[2];
//                String vendor = tokens[3];
//                double amount = Double.parseDouble(tokens[4]);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return transaction;
//    }

}

























