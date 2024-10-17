# Accounting Ledger Application

YearUp Capstone 1, a simple Accounting Ledger Application that can track all financial
transactions for a business or for personal use. All transactions in the application will be read from and saved to a transaction
file named transaction.csv.

## Table of Contents 
- [Capstone Requirements](#Capstone-Requirements)
- [Features](#Features)
- [File Structure](#File-Structure)
- [How to Run](#How-to-Run)
- [Usage](#Usage)
- [License](#License)

---

## Capstone Requirements

![Screenshot of requirements](https://i.imgur.com/kSY35gi.png)

## Features

- Add deposits or payments to the ledger.
- View all ledger entries, deposits only, or payments only, sorted out by the newest entries.
- Generate reports such as:
    - Month to date
    - Previous month
    - Year to date
    - Previous year
    - Search by vendor
- Transactions are saved to and read from a CSV file, [transactions.csv](https://github.com/NikeshSitaula1/AccountingLedgerApplication/blob/main/transactions.csv).

## File Structure

- **Main.java**  
  The main entry point for the application. Contains all the methods that handles the menu and logic for adding transactions, viewing the ledger, and generating reports.

- **Ledger.java**  
  This file contains the `Ledger` class, which is the core object of the application. It holds transaction details of `id`, `date`, `time`, `description`, `vendor`, and `amount`. The class includes getters and setters for these fields.

- **Console.java**  
  Provides utility methods for user input with a static Scanner to apply to the Main class, The util methods used in this application:  `PromptForString()`, `PromptForDouble()`, `PromptForInt()`, `PromptForYesNo()`, `PromptForDate` and `PromptForTime`.

- **transactions.csv**  
  The file where all transaction data is stored. Each transaction includes the date, time, description, vendor, and amount.


  
  
