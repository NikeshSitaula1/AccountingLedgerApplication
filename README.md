# Accounting Ledger Application

YearUp Capstone 1, a simple Accounting Ledger Application that can track all financial
transactions for a business or for personal use. All transactions in the application will be read from and saved to a transaction
file named transaction.csv.

# Table of Contents 
- [Application Features](#Application-Features)
- [File Structure](#File-Structure)
- [Getting Started](#Getting-Started)
- [Screenshots](#Screenshots)
- [Interesting code](#Interesting-Code)


---

## Application Features

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

Transactions are saved to and read from [transactions.csv](https://github.com/NikeshSitaula1/AccountingLedgerApplication/blob/main/transactions.csv)

---

## File Structure

- **Main.java**  
  The main entry point for the application. Contains all the methods that handles the menu and logic for adding transactions, viewing the ledger, and generating reports.

- **Ledger.java**  
  This file contains the `Ledger` class, which is the core object of the application. It holds transaction details of `id`, `date`, `time`, `description`, `vendor`, and `amount`. The class includes getters and setters for these fields.

- **Console.java**  
  Provides utility methods for user input with a static Scanner to apply to the Main class, The util methods used in this application:  `PromptForString()`, `PromptForDouble()`, `PromptForInt()`, `PromptForYesNo()`, `PromptForDate` and `PromptForTime`.

- **transactions.csv**  
  The file where all transaction data is stored. Each transaction includes the date, time, description, vendor, and amount.

---

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 11 or higher
- Text Editor or IDE (IntelliJ IDEA)
- Build System - Maven

### Installation
1. **Clone the repository** to your local machine:
    ```bash
   git clone https://github.com/NikeshSitaula1/AccountingLedgerApplication.git
2. Navigate to the project directory:
    ```bash
   cd AcoountingLedgerApplication
3. Run the Application using an IDE

---

## Screenshots

### Home Screen
Screenshot of Home Screen. 
Can use either numbers or the Letters on the [ ] to choose an option. 
![HomeScreen](https://i.imgur.com/vvxyLvJ.png)

### Ledger Screen
Screenshot of Ledger Screen and the number 3
that has been entered on the home screen to open the ledger screen 
![LedgerScreen](https://i.imgur.com/DdKtMj0.png)

### Report Screen
Screenshot of Report Screen and the number 4 that has been entered
on the ledger screen to open the report screen
![ReportScreen](https://i.imgur.com/zkOztXU.png)


---
## Interesting Code


![Arraylist.size()+1](https://i.imgur.com/bYS9L1Z.png)
![Constructed ID](https://i.imgur.com/8FPaKlz.png)



I have created an instance variable, id for my Ledger class, thinking it would be
necessary for this project. However, I later realized that the application did not
require the id field. So, I did not want to create a function to call the id, but
leaving it alone would mean my object not working as it is tied to it. As such, instead of removing the id,
I decided to auto generate ids based on current size of the transactions list with ```transactions.size() + 1```.This
allowed me to keep track of entries, allowing each new entry to get a unique. incremental id automatically.
The problem of doing this way tho, is that, if an item is deleted from the transactions list, it will no
longer remain sequential and there will be unused ids.

- Latest update replaces the arraylist.size() + 1 to get an automatic ID with UUID, a much better way to get a random ID.
- Thank you to all the feedback on my presentation for this update.  















  
  
