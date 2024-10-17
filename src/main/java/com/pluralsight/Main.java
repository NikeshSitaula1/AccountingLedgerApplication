package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main {

    // Store the Transaction file as a Variable. So do not have to keep typing "transaction.csv" to call the file
    public final static String dataFileName = "transactions.csv";

    // Arraylist to store all Ledger Transactions
    public static ArrayList<Ledger> transactions = getTransaction();


    public static void main(String[] args) {
        homeScreen();
    }


    static void homeScreen(){

        /*
         * The home screen should give the user the following options. The application should continue to run until the user chooses to exit.
         * D) Add Deposit - prompt user for the deposit information and save it to the csv file.
         * P) Make Payment (Debit) - prompt user for the debit information and save it to the csv file
         * L) Ledger - display the ledger screen
         * X) Exit - exit the application
         * 1-4. Added numerical String values as well to choose options
         */

        //Loops the Home screen until the user exits
        do{
            // Just in case there is an error
            try{
                // Display the menu option for the user
                System.out.println("-".repeat(90));
                System.out.println("      WELCOME TO THE ACCOUNTING LEDGER APPLICATION      ");
                System.out.println("-".repeat(90));
                System.out.println("Please choose one of the following options: ");
                System.out.println("1. Add [D]eposit");
                System.out.println("2. Make [P]ayment] (Debit)");
                System.out.println("3. [L]edger");
                System.out.println("4. E[X]it the Application");
                System.out.print(">> ");

                // Get user's input to choose an option
                String option = Console.PromptForString();

                // Processes the user input based on Selected option
                if (option.equalsIgnoreCase("D") || option.equals("1")){
                    addDeposit(); //Add a deposit
                } else if (option.equalsIgnoreCase("P") || option.equals("2")) {
                    makePayment(); //Make a payment
                } else if (option.equalsIgnoreCase("L") || option.equals("3")) {
                    Ledger(); //Displays the ledger screen
                } else if (option.equalsIgnoreCase("X") || option.equals("4")) {
                    return; //Exit the application
                } else {
                    System.out.println("Invalid Entry. Please try again."); //If input is different from the options
                }

            }catch (Exception e){
                System.out.println("Invalid Entry. Please try again.");
            }
        }while (true);
    }

    // Method to handle Deposit transactions
    static void addDeposit(){

        System.out.println("Add a Deposit Screen");
        System.out.println("-".repeat(90));

        // Ask if the user wants to enter a custom date and time
        boolean useCustomDateTime = Console.PromptForYesNo("Do you want to enter a Custom date and time?: ");

        LocalDate depositDate;
        LocalTime depositTime;

        // Get the date and time from user or use current date and time
        if(useCustomDateTime){

            depositDate = Console.PromptForDate("Enter the date (YYYY-MM-DD): ");
            depositTime = Console.PromptForTime("Enter the Time (HH:MM:SS): ");

        } else {

            depositDate = LocalDate.now(); // Gives the current date
            depositTime = LocalTime.now(); // Gives the current time
        }

        // Prompts the user for deposit details
        String depositDescription = Console.PromptForString("Description of the deposit: ");
        String depositVendor = Console.PromptForString("Enter the name of the Vendor: ");
        double depositAmount = Console.PromptForDouble("Enter the amount you want to deposit:  ");

        // Create a new Ledger entry for the deposit
        Ledger ledger = new Ledger(transactions.size() + 1, depositDate, depositTime,
                 depositDescription, depositVendor, depositAmount);
        transactions.add(ledger); // Adds the deposit to the transaction list
        saveTransaction(); // Save the transaction to the file. saveTransaction is the fileWriter
        Console.PromptForString("Deposit Added Successfully. \nPress Enter to exit");

    }

    // Method to handle Payment transactions
    static void makePayment(){

        System.out.println("Make a Payment Screen");
        System.out.println("-".repeat(90));

        // Ask if the user wants to enter a custom date and time
        boolean useCustomDateTime = Console.PromptForYesNo("Do you want to enter a Custom date and time?: ");

        LocalDate paymentDate;
        LocalTime paymentTime;

        // Get the date and time from user or use current date and time
        if(useCustomDateTime){

            paymentDate = Console.PromptForDate("Enter the date (YYYY-MM-DD): ");
            paymentTime = Console.PromptForTime("Enter the Time (HH:MM:SS)");

        } else {

            paymentDate = LocalDate.now();
            paymentTime = LocalTime.now();
        }

        // Prompts the user for payment details
        String paymentDescription = Console.PromptForString("Enter the name of the Item: ");
        String paymentVendor = Console.PromptForString("Enter the name of the vendor: ");
        double paymentAmount = Console.PromptForDouble("Enter the amount you have spent: ");

        paymentAmount = paymentAmount * -1; //Payments are now recorded in negative amount

        // Creates a new Ledger entry for the payment
        Ledger ledge = new Ledger(transactions.size() +1, paymentDate, paymentTime,
                paymentDescription, paymentVendor, paymentAmount);
        transactions.add(ledge); //Add the payment to the transactions list
        saveTransaction(); //Save the transaction to the file. saveTransaction is the fileWriter
        Console.PromptForString("Payment Added Successfully. \nPress Enter to exit");

    }


    //Methods to display and manage the ledger
    static void Ledger(){

        /*
        * A) All - Display all entries
        * D) Deposits - Display only the entries that are deposits into the account
        * P) Payments - Display only the negative entries (or payments)
        * R) Reports - A new screen that allows the user to run pre-defined reports or to run a custom search
        * H) Home - go back to the home page
        * 1-5. Added numerical String values as well to choose options
         */

        //Loops the Ledger screen until the user exits, it will go back to the Home screen
        do{
            // Just in case there is an error
            try{
                System.out.println("-".repeat(90));
                System.out.println("      LEDGER SCREEN      ");
                System.out.println("-".repeat(90));
                System.out.println("Choose one of the following options");
                System.out.println("1. [A]ll Entries");
                System.out.println("2. [D]eposits");
                System.out.println("3. [P]ayments");
                System.out.println("4. [R]eports");
                System.out.println("5. [H]ome");
                System.out.print(">> ");

                // Get the user's input for Ledger options
                String ledgerOption = Console.PromptForString();

                // Processes the user input based on the selected option
                if (ledgerOption.equalsIgnoreCase("A") || ledgerOption.equals("1")){
                    allEntries(); //Displays all Ledger entries

                } else if (ledgerOption.equalsIgnoreCase("D") || ledgerOption.equals("2")) {
                    displayDeposits(); //Displays Deposit only entries

                } else if (ledgerOption.equalsIgnoreCase("P") || ledgerOption.equals("3")) {
                    displayPayments(); //Displays Payment only entries

                } else if (ledgerOption.equalsIgnoreCase("R") || ledgerOption.equals("4")) {
                    reports(); //Displays Reports screen

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

    // Method to display all Ledger entries
    static void allEntries(){

        System.out.println("These are all the Entries in the Ledger: ");
        System.out.println("-".repeat(90));
        System.out.println("   Date    |   Time   |            Description         |        Vendor     |    Amount ");
        System.out.println("-".repeat(90));

        // Provides pattern for time to not have it show nanoseconds
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Loops through all transactions and prints them
        //for (Ledger ledger : transactions) {

        // Loops through all transactions and prints them but in the reverse order, making the new entries show first
        for (int i = transactions.size() -1; i >= 0; i--) {
            Ledger ledger = transactions.get(i);
            String timeFormatted = ledger.getTime().format(timeFormat);
            System.out.printf("%s | %s | %30s | %17s | %9.2f\n",
                    ledger.getDate(), timeFormatted, ledger.getDescription(), ledger.getVendor(), ledger.getAmount());

        }
        System.out.println("-".repeat(90));
        Console.PromptForString("Press Enter to exit");
    }


    //Method to display only Deposit entries
    static void displayDeposits(){
        System.out.println("These are Deposits only: ");
        System.out.println("-".repeat(90));
        System.out.println("   Date    |   Time   |            Description         |        Vendor     |    Amount ");
        System.out.println("-".repeat(90));

        // Provides pattern for time to not have it show nanoseconds
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Loop through transactions and display deposits, so all the positive amounts
        for (int i = transactions.size() -1; i >= 0; i--) {
            Ledger ledger = transactions.get(i);
            if (ledger.getAmount() > 0){
                String timeFormatted = ledger.getTime().format(timeFormat);
                System.out.printf("%s | %s | %30s | %17s | %9.2f\n",
                        ledger.getDate(), timeFormatted, ledger.getDescription(), ledger.getVendor(), ledger.getAmount());

            }
        }
        System.out.println("-".repeat(90));
        Console.PromptForString("Press Enter to exit");
    }

    // Method to display only Payment entries
    static void displayPayments(){
        System.out.println("These are Payments only: ");
        System.out.println("-".repeat(90));
        System.out.println("   Date    |   Time   |            Description         |        Vendor     |    Amount ");
        System.out.println("-".repeat(90));

        // Provides pattern for time to not have it show nanoseconds
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Loop through transactions and display payments, so all the negative amounts
        for (int i = transactions.size() -1; i >= 0; i--) {
            Ledger ledger = transactions.get(i);
            if (ledger.getAmount() < 0){
                String timeFormatted = ledger.getTime().format(timeFormat);
                System.out.printf("%s | %s | %30s | %17s | %9.2f\n",
                        ledger.getDate(), timeFormatted, ledger.getDescription(), ledger.getVendor(), ledger.getAmount());

            }
        }
        System.out.println("-".repeat(90));
        Console.PromptForString("Press Enter to exit");

    }

    static void reports(){

        /*
        1) Month To Date
        2) Previous Month
        3) Year To Date
        4) Previous Year
        5) Search by Vendor - prompt the user for the vendor name and display all entries for that vendor
        0) Back - go back to the report page
         */

        //Loops the Reports screen until the user exits, it will go back to the Ledger screen
        do{
            // Just in case there is an error
            try{
                System.out.println("-".repeat(90));
                System.out.println("      REPORTS SCREEN      ");
                System.out.println("-".repeat(90));
                System.out.println("Choose one of the following options");
                System.out.println("1. Month To Date");
                System.out.println("2. Previous Month");
                System.out.println("3. Year To Date");
                System.out.println("4. Previous Year");
                System.out.println("5. Search by Vendor");
                System.out.println("0. Back to Main Menu");
                System.out.print(">> ");

                // Get the user's input for Report option
                int reportOption = Console.PromptForInt();

                // Processes the user input based on the selected option
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
        System.out.println("-".repeat(90));
        System.out.println("   Date    |   Time   |            Description         |        Vendor     |    Amount ");
        System.out.println("-".repeat(90));

        // Provides pattern for time to not have it show nanoseconds
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Loop through transactions to find entries from the current month
        for (int i = transactions.size() -1; i >= 0; i--) {
            Ledger ledger = transactions.get(i);
            String timeFormatted = ledger.getTime().format(timeFormat); //Formatting the created time to the DateTimeFormatter pattern
            LocalDate monthAndYearDate = ledger.getDate(); //Creating a variable for created date

            // Check if the transaction date matches the current month and year
            if (monthAndYearDate.getMonthValue() == LocalDate.now().getMonthValue() //using getMonthValue() to match with the current month
                    &&
                    monthAndYearDate.getYear() == LocalDate.now().getYear()) { //using getYear() to match with the current year
                System.out.printf("%s | %s | %30s | %17s | %9.2f\n",
                        ledger.getDate(), timeFormatted, ledger.getDescription(), ledger.getVendor(), ledger.getAmount());
            }
        }
        System.out.println("-".repeat(90));
        Console.PromptForString("Press Enter to exit");
    }

    static void previousMonth(){

        System.out.println("These are Entries from previous month: ");
        System.out.println("-".repeat(90));
        System.out.println("   Date    |   Time   |            Description         |        Vendor     |    Amount ");
        System.out.println("-".repeat(90));

        // Provides pattern for time to not have it show nanoseconds
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Loop through transactions to find entries from the previous month
        for (int i = transactions.size() -1; i >= 0; i--) {
            Ledger ledger = transactions.get(i);
            String timeFormatted = ledger.getTime().format(timeFormat); //Formatting the created time to the DateTimeFormatter pattern
            LocalDate monthDate = ledger.getDate(); //Creating a variable for created date

            // Check if the transaction date matches the previous month
            //YearMonth helps to not make an && condition that also looks for the current year
            if (YearMonth.from(monthDate).equals(YearMonth.now().minusMonths(1))){
                System.out.printf("%s | %s | %30s | %17s | %9.2f\n",
                        ledger.getDate(), timeFormatted, ledger.getDescription(), ledger.getVendor(), ledger.getAmount());
            }
        }
        System.out.println("-".repeat(90));
        Console.PromptForString("Press Enter to exit");
    }


    static void yearToDate(){

        System.out.println("These are Entries from current year: ");
        System.out.println("-".repeat(90));
        System.out.println("   Date    |   Time   |            Description         |        Vendor     |    Amount ");
        System.out.println("-".repeat(90));

        // Provides pattern for time to not have it show nanoseconds
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Loop through transactions to find entries from the current year
        for (int i = transactions.size() -1; i >= 0; i--) {
            Ledger ledger = transactions.get(i);
            String timeFormatted = ledger.getTime().format(timeFormat); //Formatting the created time to the DateTimeFormatter pattern
            LocalDate yearDate = ledger.getDate(); //Creating a variable for created date

            // Check if the transaction date matches the current year
            if (yearDate.getYear() == LocalDate.now().getYear()){ //using getYear() to match with the current year
                System.out.printf("%s | %s | %30s | %17s | %9.2f\n",
                        ledger.getDate(), timeFormatted, ledger.getDescription(), ledger.getVendor(), ledger.getAmount());
            }
        }

        System.out.println("-".repeat(90));
        Console.PromptForString("Press Enter to exit");

    }


    static void previousYear(){

        System.out.println("These are Entries from previous year: ");
        System.out.println("-".repeat(90));
        System.out.println("   Date    |   Time   |            Description         |        Vendor     |    Amount ");
        System.out.println("-".repeat(90));

        // Provides pattern for time to not have it show nanoseconds
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Loop through transactions to find entries from the previous year
        for (int i = transactions.size() -1; i >= 0; i--) {
            Ledger ledger = transactions.get(i);
            String timeFormatted = ledger.getTime().format(timeFormat); //Formatting the created time to the DateTimeFormatter pattern
            LocalDate yearDate = ledger.getDate(); //Creating a variable for created date

            // Check if the transaction date matches the previous year using getYear
            if (yearDate.getYear() == LocalDate.now().getYear()-1){
                System.out.printf("%s | %s | %30s | %17s | %9.2f\n",
                        ledger.getDate(), timeFormatted, ledger.getDescription(), ledger.getVendor(), ledger.getAmount());
            }

        }

        System.out.println("-".repeat(90));
        Console.PromptForString("Press Enter to exit");

    }

    static void searchByVendor(){

        String vendor = Console.PromptForString("Enter the vendor you want to search. \n>> ");

        System.out.println("Vendor search: ");
        System.out.println("-".repeat(90));
        System.out.println("   Date    |   Time   |            Description         |        Vendor     |    Amount ");
        System.out.println("-".repeat(90));


        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Loop through transactions to find entries that match the specified vendor
        for (int i = transactions.size() -1; i >= 0; i--) {
            Ledger ledger = transactions.get(i);
            String timeFormatted = ledger.getTime().format(timeFormat);
            if(ledger.getVendor().equalsIgnoreCase(vendor)){
                System.out.printf("%s | %s | %30s | %17s | %9.2f\n",
                        ledger.getDate(), timeFormatted, ledger.getDescription(), ledger.getVendor(), ledger.getAmount());
            }
        }
        System.out.println("-".repeat(90));
        Console.PromptForString("Press Enter to exit");
    }


    // Method to save transactions to a file in a specific format
    public static void saveTransaction() {

        try {
            //Creating a file writer and assigning the file writer to the buffered writer.
            FileWriter fw = new FileWriter(dataFileName);
            BufferedWriter bw = new BufferedWriter(fw);

            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

            bw.write("date|time|description|vendor|amount \n"); //Write header

            // Loop through transactions and write each one to the file
            for (int i = transactions.size() -1; i >= 0; i--) {
                Ledger ledger = transactions.get(i);
                String timeFormatted = ledger.getTime().format(timeFormat);
                String data = ledger.getDate() + "|"
                        + timeFormatted + "|" + ledger.getDescription() + "|" + ledger.getVendor()
                        + "|" + ledger.getAmount() + "\n";
                bw.write(data);
            }
            bw.close(); // Close the BufferedWriter

        } catch (IOException e){
            System.out.println("Error while saving Transactions: " + e.getMessage());
        }
    }


    // Method to read transactions from a file and populate the ArrayList
    public static ArrayList<Ledger> getTransaction(){
        ArrayList<Ledger> transaction = new ArrayList<>();

        try{
            FileReader fr = new FileReader(dataFileName);
            BufferedReader br = new BufferedReader(fr);

            String input;

            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

            br.readLine(); //Skips the header line

            /* Read each line from the file until there are no more lines to read.
            Splits the lines into tokens using the '|' character as the delimiter.
             */
            while ((input = br.readLine()) != null) {
                String [] tokens = input.split(Pattern.quote("|"));
                LocalDate date = LocalDate.parse(tokens[0]);
                LocalTime time = LocalTime.parse(tokens[1], timeFormat);
                String description = tokens[2];
                String vendor = tokens[3];
                double amount = Double.parseDouble(tokens[4]); //
                Ledger ledger = new Ledger (transaction.size() +1, date, time, description, vendor, amount);
                transaction.add(ledger);
            }
            br.close(); //Closes the BufferedReader

        } catch (Exception e) {
            System.out.println("Error while saving Transactions: " + e.getMessage());
        }
        return transaction; //Returns the list of transactions
    }
}
























