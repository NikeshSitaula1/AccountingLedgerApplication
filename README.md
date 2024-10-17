# Accounting Ledger Application

YearUp Capstone 1, a simple Accounting Ledger Application that can track all financial
transactions for a business or for personal use. All transactions in the application will be read from and saved to a transaction
file named transaction.csv.

# Table of Contents 
- [Application Features](#Application-Features)
- [File Structure](#File-Structure)
- [Getting Started](#Getting-Started)
- [Interesting code](#Interesting-Code)
- [License](#License)

---

# Application Features

### Home Screen
- **D) Add Deposit**: Prompt and save deposit info to CSV.
- **P) Make Payment (Debit)**: Prompt and save debit info to CSV.
- **L) Ledger**: Display the ledger screen.
- **X) Exit**: Exit the application.

### Ledger Menu
- **A) All**: Display all entries.
- **D) Deposits**: Show only deposits.
- **P) Payments**: Show only payments.
- **R) Reports**: Access predefined reports or custom search.
- **H) Home**: Return to the home page.

### Reports Menu
- **1) Month To Date**
- **2) Previous Month**
- **3) Year To Date**
- **4) Previous Year**
- **5) Search by Vendor**
- **0) Back**

Transactions are saved to and read from a CSV file, [transactions.csv](https://github.com/NikeshSitaula1/AccountingLedgerApplication/blob/main/transactions.csv)


# File Structure

- **Main.java**  
  The main entry point for the application. Contains all the methods that handles the menu and logic for adding transactions, viewing the ledger, and generating reports.

- **Ledger.java**  
  This file contains the `Ledger` class, which is the core object of the application. It holds transaction details of `id`, `date`, `time`, `description`, `vendor`, and `amount`. The class includes getters and setters for these fields.

- **Console.java**  
  Provides utility methods for user input with a static Scanner to apply to the Main class, The util methods used in this application:  `PromptForString()`, `PromptForDouble()`, `PromptForInt()`, `PromptForYesNo()`, `PromptForDate` and `PromptForTime`.

- **transactions.csv**  
  The file where all transaction data is stored. Each transaction includes the date, time, description, vendor, and amount.

# Getting Started

## Pre-Requisites
- Java Development Kit (JDK) 11 or higher
- Build System - Maven









  
  
