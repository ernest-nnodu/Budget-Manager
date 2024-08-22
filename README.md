# Budget Manager

## Overview

Budget Manager is a Java-based application designed to help users manage their personal finances effectively. The application allows users to add income, record purchases across various categories, and analyze their spending habits. It also provides functionality to save and load transaction data to and from a file.

## Features

<ul>
  <li><strong>Add Income</strong>: Record income to keep track of your total earnings.</li>
  <li><strong>Add Purchases</strong>: Record purchases under categories such as Food, Clothes, Entertainment, and Others.</li>
  <li><strong>List Purchases</strong>: View a list of all recorded purchases.</li>
  <li><strong>Display Balance</strong>: Check your current balance by subtracting total expenses from total income.</li>
  <li><strong>Save Data</strong>: Save all transactions and total income to a file.</li>
  <li><strong>Load Data</strong>: Load transactions and income from a file.</li>
  <li><strong>Analyze Purchases</strong>: Sort and view purchases by amount, type, or specific categories.</li>
</ul>

## Project Structure

<pre>
budget/
├── Main.java                # Entry point for the application
├── BudgetManager.java       # Manages the transactions and calculations
├── BudgetManagerApp.java    # Handles user interactions and application flow
├── Console.java             # Handles console input/output operations
├── FileHandler.java         # Manages saving and loading of data from a file
├── PurchaseCategory.java    # Enum for purchase categories (Food, Clothes, etc.)
├── Transaction.java         # Represents an individual transaction (purchase/income)
└── TransactionType.java     # Enum for transaction types (Income/Expense)
</pre>

## Getting Started

### Prerequisites

<ul>
  <li>Java Development Kit (JDK) 8 or higher</li>
</ul>

### Installation

<pre>
1. Clone the repository to your local machine:

   <code>git clone https://github.com/yourusername/budget-manager.git</code>

2. Navigate to the project directory:

   <code>cd budget-manager</code>

3. Compile the project:

   <code>javac budget/*.java</code>

4. Run the application:

   <code>java budget.Main</code>
</pre>

## Usage

<ol>
  <li><strong>Add Income</strong>: Choose the "Add income" option to record your earnings.</li>
  <li><strong>Add Purchases</strong>: Record your expenses by selecting the appropriate category.</li>
  <li><strong>View Purchases</strong>: List all or filtered purchases based on category.</li>
  <li><strong>Display Balance</strong>: View your current financial balance.</li>
  <li><strong>Save/Load Data</strong>: Persist your transactions to a file or load them back later.</li>
  <li><strong>Analyze</strong>: Sort and review your purchases to better understand your spending.</li>
</ol>

## File Handling

<ul>
  <li><strong>Save Purchases</strong>: Saves all the recorded transactions and income to a file named <code>purchases.txt</code>.</li>
  <li><strong>Load Purchases</strong>: Loads the transactions and income from the <code>purchases.txt</code> file.</li>
</ul>

## Code Examples

### `Main.java`

```java
package budget;

public class Main {
    public static void main(String[] args) {
        BudgetManagerApp budgetManagerApp = new BudgetManagerApp();
        budgetManagerApp.start();
    }
}
