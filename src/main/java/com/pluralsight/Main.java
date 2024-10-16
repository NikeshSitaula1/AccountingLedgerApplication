package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main {

    public final static String dataFileName = "transactions.csv";
    public static ArrayList<Ledger> transactions = getTransaction();


    public static void main(String[] args) {
        homeScreen();
    }


    static void homeScreen(){

        do{
            try{
                System.out.println("-".repeat(70));
                System.out.println("Welcome to the Accounting Ledger App");
                System.out.println("-".repeat(70));
                System.out.println("Please choose one of the following options: ");
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

         String depositDescription = Console.PromptForString("Description of the deposit: ");
         String depositVendor = Console.PromptForString("Enter the name of the Vendor: ");
         double depositAmount = Console.PromptForDouble("Enter the amount you want to deposit:  ");

         LocalDate depositDate = LocalDate.now();
         LocalTime depositTime = LocalTime.now();


         Ledger ledge = new Ledger(transactions.size() +1, depositDate, depositTime,
                 depositDescription, depositVendor, depositAmount);
         transactions.add(ledge);
         saveTransaction();
         Console.PromptForString("Deposit Added Successfully. \nPress Enter to exit");


    }

    static void makePayment(){

        String paymentDescription = Console.PromptForString("Enter the name of the Item: ");
        String paymentVendor = Console.PromptForString("Enter the name of the vendor: ");
        double paymentAmount = Console.PromptForDouble("Enter the amount you have spent: ");

        paymentAmount = paymentAmount * -1;

        LocalDate paymentDate = LocalDate.now();
        LocalTime paymentTime = LocalTime.now();


        Ledger ledge = new Ledger(transactions.size() +1, paymentDate, paymentTime,
                paymentDescription, paymentVendor, paymentAmount);
        transactions.add(ledge);
        saveTransaction();
        Console.PromptForString("Payment Added Successfully. \nPress Enter to exit");

    }


    static void Ledger(){

        do{
            try{
                System.out.println("-".repeat(80));
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
        System.out.println("These are all the Entries in the Ledger: ");
        System.out.println("   Date   |   Time    |       Description         |   Vendor   |   Amount ");
        System.out.println("-".repeat(80));

        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        for (Ledger ledger : transactions) {
        //for (int i = transactions.size() -1; i >= 0; i--) {
            //Ledger ledger = transactions.get(i);
            String timeFormatted = ledger.getTime().format(timeFormat);
            System.out.printf("%s | %s | %25s | %10s | %10.2f\n",
                    ledger.getDate(), timeFormatted, ledger.getDescription(), ledger.getVendor(), ledger.getAmount());

        }
        System.out.println("-".repeat(80));
        Console.PromptForString("Press Enter to exit");
    }


    static void displayDeposits(){
        System.out.println("These are Deposits only: ");
        System.out.println("   Date   |   Time    |       Description         |   Vendor   |   Amount ");
        System.out.println("-".repeat(80));

        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        for (Ledger ledger : transactions){
            if (ledger.getAmount() > 0){
                String timeFormatted = ledger.getTime().format(timeFormat);
                System.out.printf("%s | %s | %25s | %10s | %10.2f\n",
                        ledger.getDate(), timeFormatted, ledger.getDescription(), ledger.getVendor(), ledger.getAmount());



            }
        }
        System.out.println("-".repeat(80));
        Console.PromptForString("Press Enter to exit");
    }


    static void displayPayments(){
        System.out.println("These are Payments only: ");
        System.out.println("   Date   |   Time    |       Description         |   Vendor   |   Amount ");
        System.out.println("-".repeat(80));

        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        for (Ledger ledger : transactions){
            if (ledger.getAmount() < 0){
                String timeFormatted = ledger.getTime().format(timeFormat);
                System.out.printf("%s | %s | %10s | %5s | %5.2f\n",
                        ledger.getDate(), timeFormatted, ledger.getDescription(), ledger.getVendor(), ledger.getAmount());

            }
        }
        System.out.println("-".repeat(80));
        Console.PromptForString("Press Enter to exit");

    }

    static void reports(){

        do{
            try{
                System.out.println("-".repeat(80));
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
                    monthToDate();
                } else if (reportOption == 2) {
                    previousMonth();
                } else if (reportOption == 3) {
                    yearToDate();
                } else if (reportOption == 4) {
                    previousYear();
                } else if (reportOption == 5) {
                    searchByVendor();
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

        System.out.println("These are Entries from current month: ");
        System.out.println("   Date   |   Time    |       Description         |   Vendor   |   Amount ");
        System.out.println("-".repeat(80));

        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        for(Ledger ledger : transactions){
            String timeFormatted = ledger.getTime().format(timeFormat);
            LocalDate monthAndYearDate = ledger.getDate();
            if (monthAndYearDate.getMonthValue() == LocalDate.now().getMonthValue()
                    &&
                    monthAndYearDate.getYear() == LocalDate.now().getYear()) {
                System.out.printf("%s | %s | %10s | %5s | %5.2f\n",
                        ledger.getDate(), timeFormatted, ledger.getDescription(), ledger.getVendor(), ledger.getAmount());
            }
        }
        System.out.println("-".repeat(80));
        Console.PromptForString("Press Enter to exit");

    }

    static void previousMonth(){

        System.out.println("These are Entries from previous month: ");
        System.out.println("   Date   |   Time    |       Description         |   Vendor   |   Amount ");
        System.out.println("-".repeat(80));

        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        for(Ledger ledger : transactions){
            String timeFormatted = ledger.getTime().format(timeFormat);
            LocalDate monthDate = ledger.getDate();

            if (YearMonth.from(monthDate).equals(YearMonth.now().minusMonths(1))){
                System.out.printf("%s | %s | %10s | %5s | %5.2f\n",
                        ledger.getDate(), timeFormatted, ledger.getDescription(), ledger.getVendor(), ledger.getAmount());
            }
        }
        System.out.println("-".repeat(80));
        Console.PromptForString("Press Enter to exit");

    }

    static void yearToDate(){

        System.out.println("These are Entries from current year: ");
        System.out.println("   Date   |   Time    |       Description         |   Vendor   |   Amount ");
        System.out.println("-".repeat(80));

        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        for(Ledger ledger : transactions){
            String timeFormatted = ledger.getTime().format(timeFormat);
            LocalDate yearDate = ledger.getDate();

            if (yearDate.getYear() == LocalDate.now().getYear()){
                System.out.printf("%s | %s | %10s | %5s | %5.2f\n",
                        ledger.getDate(), timeFormatted, ledger.getDescription(), ledger.getVendor(), ledger.getAmount());
            }
        }

        System.out.println("-".repeat(80));
        Console.PromptForString("Press Enter to exit");

    }

    static void previousYear(){

        System.out.println("These are Entries from previous year: ");
        System.out.println("   Date   |   Time    |       Description         |   Vendor   |   Amount ");
        System.out.println("-".repeat(80));

        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        for(Ledger ledger : transactions){
            String timeFormatted = ledger.getTime().format(timeFormat);
            LocalDate yearDate = ledger.getDate();

            if (yearDate.getYear() == LocalDate.now().getYear()-1){
                System.out.printf("%s | %s | %10s | %5s | %5.2f\n",
                        ledger.getDate(), timeFormatted, ledger.getDescription(), ledger.getVendor(), ledger.getAmount());
            }

        }

        System.out.println("-".repeat(80));
        Console.PromptForString("Press Enter to exit");

    }

    static void searchByVendor(){

        String vendor = Console.PromptForString("Enter the vendor you want to search. \n>> ");

        System.out.println("   Date   |   Time    |       Description         |   Vendor   |   Amount ");
        System.out.println("-".repeat(80));


        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        for(Ledger ledger : transactions){
            String timeFormatted = ledger.getTime().format(timeFormat);
            if(ledger.getVendor().equalsIgnoreCase(vendor)){
                System.out.printf("%s | %s | %10s | %5s | %5.2f\n",
                        ledger.getDate(), timeFormatted, ledger.getDescription(), ledger.getVendor(), ledger.getAmount());

            }
        }
    }


    //todo FILE WRITER
    public static void saveTransaction() {

        try {
            FileWriter fw = new FileWriter(dataFileName);
            BufferedWriter bw = new BufferedWriter(fw);

            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

            bw.write("date|time|description|vendor|amount \n");

            for(Ledger ledger : transactions) {
                String timeFormatted = ledger.getTime().format(timeFormat);
                String data = ledger.getDate() + "|"
                        + timeFormatted + "|" + ledger.getDescription() + "|" + ledger.getVendor()
                        + "|" + ledger.getAmount() + "\n";
                bw.write(data);
            }
            bw.close();

        } catch (IOException e){
            System.out.println("Error");
        }
    }


    //todo FOR FILE READER
    public static ArrayList<Ledger> getTransaction(){
        ArrayList<Ledger> transaction = new ArrayList<Ledger>();

        try{
            FileReader fr = new FileReader(dataFileName);
            BufferedReader br = new BufferedReader(fr);

            String input;

            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

            br.readLine();

            while ((input = br.readLine()) != null) {
                String [] tokens = input.split(Pattern.quote("|"));

                LocalDate date = LocalDate.parse(tokens[0]);
                LocalTime time = LocalTime.parse(tokens[1], timeFormat);
                String description = tokens[2];
                String vendor = tokens[3];
                double amount = Double.parseDouble(tokens[4]);
                Ledger ledger = new Ledger (transaction.size() +1, date, time, description, vendor, amount);
                transaction.add(ledger);
            }
            br.close();

        } catch (Exception e) {
            System.out.println("Error!!");
        }
        return transaction;
    }

}
























