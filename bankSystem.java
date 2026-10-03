import java.io.*;
import java.util.*;

// Main class for the Bank System
public class bankSystem {

    // Static variable to hold the user's name
    static String name;
    // Constants for file names
    static final String ACCOUNTS_FILE = "accounts.txt";
    static final String TRANSACTION_LOG = "transactions.txt";
    static Scanner input = new Scanner(System.in);

    // Login method to go the Admin Menu
    public static void loginAdmin() {
        // This is the admin name that will be used for login.
        String adminName = "Admin";
        // Admin password
        String password = "Admin1234";
        // Prompt for admin credentials
        boolean valid = false;
        while (!valid) {
            System.out.print("Enter Admin Name: ");
            String enteredName = input.nextLine();
            System.out.print("Enter Admin Password: ");
            String pass = input.nextLine();
            // Check if the entered credentials match the admin credentials
            if (enteredName.equalsIgnoreCase(adminName) && pass.equalsIgnoreCase(password)) {
                System.out.println("*** Admin Login Successfully! ***");
                // Set valid to true to exit the loop
                valid = true;
                // Call the admin menu method
                adminMenu();

            }
            // exit the loop
            else {
                System.out.println(">>Invalid credentials...");
            }
        }
    }

    // Admin Menu
    public static void adminMenu() {
        // This variable is used to control the admin menu loop.
        boolean valid = false;
        // Loop until the admin chooses to logout
        while (!valid) {
            System.out.println("\n=======================================");
            System.out.println("           --- Admin Menu ---          ");
            System.out.println("=======================================");
            System.out.println("|   1. Add Account                    |");
            System.out.println("|  ---------------------------------  |");
            System.out.println("|   2. View All Accounts              |");
            System.out.println("|  ---------------------------------  |");
            System.out.println("|   3. Update Account                 |");
            System.out.println("|  ---------------------------------  |");
            System.out.println("|   4. Search Account                 |");
            System.out.println("|  ---------------------------------  |");
            System.out.println("|   5. Transfer Funds                 |");
            System.out.println("|  ---------------------------------  |");
            System.out.println("|   6. View Transactions              |");
            System.out.println("|  ---------------------------------  |");
            System.out.println("|   7. Logout                         |");
            System.out.println("|-------------------------------------|");
            System.out.println("=======================================");

            // Prompt for admin choice
            System.out.print("Enter choice: ");
            try {
                // Read admin choice:
                int adminChoice = input.nextInt();
                // consume leftover newline
                input.nextLine();
                // Check the admin choice and call the corresponding method
                switch (adminChoice) {
                    case 1: {
                        createAccount();
                        break;
                    }
                    case 2: {
                        viewAllAccounts();
                        break;
                    }
                    case 3: {
                        updateAccount();
                        break;
                    }
                    case 4: {
                        searchAccount();
                        break;
                    }
                    case 5: {
                        transferFunds();
                        break;
                    }
                    case 6: {
                        viewTransactions();
                        break;
                    }
                    case 7: {
                        System.out.println(">>Logging out from Admin Menu...");
                        valid = true;
                        break;// exit the loop
                    }
                    default: {
                        System.out.println(">>Invalid choice... Please enter a number from 1 to 7.");
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println(">>Invalid input! Please enter a numeric value...");
                // clear invalid input
                input.nextLine();
            } catch (Exception e) {
                System.out.println(">>An unexpected error occurred: " + e.getMessage());
                // clear buffer to avoid infinite loop
                input.nextLine();
            }
        }
    }

    // User Menu
    public static void userMenu() {
        // This variable is used to check if the user ID is correct.
        boolean isCorrectId = false;
        System.out.print("Enter Your Name: ");
        name = input.nextLine();
        System.out.print("Enter User ID: ");
        // It is initialized to -1 to indicate an invalid ID.
        int userID = -1;
        // Try to read the user ID from input
        try {
            userID = input.nextInt();
            // consume newline
            input.nextLine();
        } catch (InputMismatchException e) {
            System.out.println(">>Invalid ID... It is always in numeric format.");
            input.nextLine();
        }
        // Check if the file for the user exists
        File file = new File(name + ".txt");
        try {
            // If the file exists, read it to check if the user ID is correct.
            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                String line = reader.nextLine();
                String[] parts = line.split("\\,");
                int id = Integer.parseInt(parts[2]);
                if (userID == id) {
                    isCorrectId = true;
                    System.out.println("*** Login Successfully! ***");
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(">>Error while dealing with the file...");
        }
        // If the user ID is correct, show the user menu
        if (isCorrectId) {
            // User menu loop
            boolean valid = false;
            while (!valid) {
                System.out.println("\n ============================");
                System.out.println("       <<< User Menu >>> ");
                System.out.println(" ============================");
                System.out.println("|  1. View Account Details   |");
                System.out.println("| ---------------------------|");
                System.out.println("|  2. View Balance           |");
                System.out.println("| ---------------------------|");
                System.out.println("|  3. Logout                 |");
                System.out.println("| ---------------------------|");
                System.out.println(" ============================");
                // Prompt for user choice
                int userChoice = 0;
                boolean isGood = false;
                // Loop until the user enters a valid choice
                while (!isGood) {
                    try {
                        System.out.print("Select an Option: ");
                        userChoice = input.nextInt();
                        input.nextLine(); // consume newline
                        if (userChoice < 1 || userChoice > 3) {
                            System.out.println(">>Invalid Choice.... Enter a number from (1 - 3):");
                        }
                        // If the choice is valid, set isGood to true to exit the loop
                        else {
                            isGood = true;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println(">>Invalid input...");
                        input.nextLine();
                    }
                }
                // checking user selection
                switch (userChoice) {
                    case 1: {
                        viewAccount();
                        break;
                    }
                    case 2: {
                        viewBalance();
                        break;
                    }
                    case 3: {
                        System.out.println("Logging out from the User Menu...");
                        valid = true;
                        break;
                    }
                }
            }
        }
        // If the user ID is incorrect, print an error message
        else if (userID != -1) {
            System.out.println(">>Invalid User ID... Please try again.");
            System.out.println(">>If you are a new user, please create an account first.");
        } else {
            System.out.println("File Does Not Exists...");
        }
    }

    // Method to view the user's account details
    public static void viewAccount() {
        // Check if the file for the user exists
        File file = new File(name + ".txt");
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                // Read the line from the file
                String line = reader.nextLine();
                // Split the line by comma to get account details
                String[] parts = line.split("\\,");
                // Display account details
                System.out.println("\n===================================");
                System.out.println("  --- " + name + "'s Account" + " ---");
                System.out.println("===================================");
                System.out.println("Name    : " + parts[0]);
                System.out.println("-----------------------------------");
                System.out.println("Age     : " + parts[1]);
                System.out.println("-----------------------------------");
                System.out.println("ID      : " + parts[2]);
                System.out.println("-----------------------------------");
                System.out.println("Balance : Rs. " + parts[3]);
                System.out.println("-----------------------------------");
            }
            // Close the reader after reading the file
            reader.close();
            // If the file does not exist, print an error message
        } catch (IOException e) {
            System.out.println(">>Error while reading data...");
        }

    }

    // Method to view the user's account balance
    public static void viewBalance() {

        // opening the file having the user details
        File file = new File(name + ".txt");
        try {
            Scanner reader = new Scanner(file);
            String line;
            // Reading the file till the end token come.
            while (reader.hasNext()) {
                line = reader.nextLine();
                // Split the line by comma to get account details
                String[] parts = line.trim().split("\\,");
                // Reading the balance because it is on index 3.
                double balance = Double.parseDouble(parts[3]);
                // reading the user name because it is on index 0.
                String userName = parts[0];

                // Displaying the balance
                System.out.println("\n=================================");
                System.out.println("     --- Account Balance ---");
                System.out.println("=================================");
                System.out.println("Name    : " + userName);
                System.out.println("-----------------------------------");
                System.out.println("Current Balance : Rs. " + balance);
                System.out.println("-----------------------------------");

            }
            // Close the reader after reading the file
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(">>Account File Does Not Exists...");
        } catch (IOException e) {
            System.out.println(">>Error while reading account data: " + e.getMessage());
        }
    }

    // Method to create a new account.
    public static void createAccount() {
        // Prompt for user details
        boolean valid = false;
        while (!valid) {
            System.out.print("Enter Name: ");
            name = input.nextLine();
            // check if the new name is valid
            if (name.length() < 3) {
                System.out.println(">>Name must be at least 3 characters long.");
            } else {
                valid = true;
            }
        }
        // Prompt for age and validate it
        int age = 0;
        valid = false;
        while (!valid) {
            try {
                System.out.print("Enter Age (18-100): ");
                age = input.nextInt();
                input.nextLine(); // consume newline
                if (age < 18) {
                    System.out.println(">>You are a child ");
                    System.out.println(">>Your Account can not be created");
                    return;
                }
                if (age > 100) {
                    System.out.println(">>You are too old.");
                    System.out.println(">>Your account cannot be created.");
                    return;
                }
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println(">>Invalid input! Please enter a numeric value for age.");
                input.nextLine();
            }
        }
        // Prompt for unique ID and validate it
        int id = 0;
        double balance = 0;
        boolean isUniqueId = false;
        while (!isUniqueId) {
            try {
                System.out.print("Enter Unique ID: ");
                id = input.nextInt();
                // consume newline:
                input.nextLine();

                if (id <= 0) {
                    System.out.println(">>ID must be a positive integer.");
                    continue;
                }

                // Check if ID already exists
                boolean idExists = false;
                File file = new File(ACCOUNTS_FILE);
                if (file.exists()) {
                    try (Scanner reader = new Scanner(file)) {
                        while (reader.hasNext()) {
                            String line = reader.nextLine();
                            String[] parts = line.split(",");
                            if (parts.length > 2 && Integer.parseInt(parts[2]) == id) {
                                idExists = true;
                                break;
                            }
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println(">>Error: Accounts file not found while checking ID uniqueness.");
                    }
                }
                if (idExists) {
                    System.out.println(">>ID already exists. Enter some unique ID.");
                    continue;
                }
                isUniqueId = true;
            } catch (InputMismatchException e) {
                System.out.println(">>Invalid input! Please enter a numeric value for ID.");
                input.nextLine();
            }
        }
        // Prompt for balance and validate it
        boolean validBalance = false;
        while (!validBalance) {
            try {
                System.out.print("Enter Amount to Deposit: ");
                balance = input.nextDouble();
                input.nextLine(); // consume newline
                if (balance < 0) {
                    System.out.println(">>Balance cannot be negative.");
                } else {
                    validBalance = true;
                }
            } catch (InputMismatchException e) {
                System.out.println(">>Invalid input! Please enter a numeric value for balance.");
                input.nextLine();
            }
        }
        // Create the accounts file if it doesn't exist
        File file1 = new File(ACCOUNTS_FILE);
        File file2 = new File(name + ".txt");
        try (
                FileWriter writer1 = new FileWriter(file1, true);
                FileWriter writer2 = new FileWriter(file2, true)) {
            // writing to Accounts file
            writer1.write(name + "," + age + "," + id + "," + balance + "\n");
            // writing to user's file
            writer2.write(name + "," + age + "," + id + "," + balance + "\n");
            // Show the message of completing the account creation.
            System.out.println("*** Account Created Successfully! ***");
        } catch (IOException e) {
            System.out.println(">>Something went wrong: " + e.getMessage());
        }
    }

    public static void viewAllAccounts() {
        // Check if the accounts file exists and read it
        File file = new File(ACCOUNTS_FILE);
        try {
            Scanner reader = new Scanner(file);
            String line;
            // If the file does not exist, print a message
            if (!file.exists()) {
                System.out.println(">>No accounts found.");
                // Exit the method if the file does not exist
                return;
            }
            // Initialize a counter to keep track of the number of accounts displayed
            int counter = 0;
            System.out.println("\n=================================");
            System.out.println("  --- Viewing All Accounts ---");
            System.out.println("=================================");
            // Read the file line by line
            while (reader.hasNext()) {
                line = reader.nextLine();
                // Split the line by comma to get account details
                String[] parts = line.split("\\,");

                // Display account details
                System.out.println("Name    : " + parts[0]);
                System.out.println("Age     : " + parts[1]);
                System.out.println("ID      : " + parts[2]);
                System.out.println("Balance : Rs. " + parts[3]);
                System.out.println("-------------------------------");
                // Increment the counter for each account displayed
                counter++;
            }
            // Close the reader after reading the file
            reader.close();
            // If no accounts were found, print a message
            if (counter == 0) {
                System.out.println(">>No data in the file");
                // Exit the method if no accounts were found
                return;
            }
            // Handle the case where the file does not exist
        } catch (FileNotFoundException e) {
            System.out.println(">>No file exists..." + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println(">>No accounts found...");
        } catch (IOException e) {
            System.out.println(">>Error reading accounts: " + e.getMessage());
        }
    }

    // Method to update an existing account
    public static void updateAccount() {
        // Prompt for the account ID to update
        try {
            System.out.print("Enter the ID of the account to update: ");
            int updateId = input.nextInt();
            // consume the newline character
            input.nextLine();

            // declares and initialize variable account as list of string.
            List<String> accounts = new ArrayList<>();
            // This variable is used to check if the account is updated or not.
            boolean updated = false;
            // Open the accounts file to read existing accounts
            File file = new File(ACCOUNTS_FILE);
            String line;

            try (Scanner reader = new Scanner(file)) {
                while (reader.hasNext()) {
                    line = reader.nextLine();
                    String[] parts = line.split("\\,");
                    // Parse the ID from the line
                    int id = Integer.parseInt(parts[2]);
                    // If the ID matches the one to update, prompt for new details
                    if (id == updateId) {
                        boolean valid = false;
                        while (!valid) {
                            System.out.print("Enter new name (Keep blank to " + parts[0] + "): ");
                            String newName = input.nextLine();
                            if (newName.equals("")) {
                                // keep the old name if blank
                                newName = parts[0];
                                valid = true;
                            } else {
                                // check if the new name is valid
                                if (newName.length() < 3) {
                                    System.out.println(">>Name must be at least 3 characters long.");
                                }
                                // if the new name is valid, set valid to true
                                else {
                                    valid = true;
                                }
                            }

                            System.out.print("Enter new age: ");
                            // Read new age from user input
                            int newAge = input.nextInt();
                            // consume the newline character
                            input.nextLine();
                            // Check if the new age is valid
                            if (newAge < 18) {
                                System.out.println(">>You are a child. ");
                                System.out.println(">>Your Account can not be created");
                                return;
                            }
                            if (newAge > 100) {
                                System.out.println(">>You are too old...");
                                System.out.println(">>Your account cannot be created.");
                                return;
                            }
                            // Check if the new balance is valid.
                            boolean validBalance = false;
                            double newBalance = 0;
                            while (!validBalance) {
                                try {
                                    System.out.print("Enter new balance: ");
                                    newBalance = input.nextDouble();
                                    input.nextLine(); // consume the newline character
                                    if (newBalance < 0) {
                                        System.out.println(">>Balance cannot be negative.");
                                    }
                                    // if the new balance is valid, set validBalance to true
                                    else {
                                        validBalance = true;
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println(">>Invalid input! Please enter a numeric value for balance.");
                                    // clear invalid input
                                    input.nextLine();
                                }
                            }
                            // Add the updated account details to the list
                            accounts.add(newName + "," + newAge + "," + id + "," + newBalance);
                            updated = true;
                        }
                    }
                    // If the ID does not match, keep the existing account details
                    else {
                        accounts.add(line);
                    }
                }
            }
            // If the account was updated, write the updated accounts back to the file
            if (updated) {
                try (FileWriter writer = new FileWriter(ACCOUNTS_FILE)) {
                    // using for loop to write the updates in file.
                    for (String acc : accounts) {
                        writer.write(acc + "\n");
                    }
                }
                System.out.println(">>Account updated successfully...");
            }
            // If the account ID was not found, print a message
            else {
                System.out.println(">>Account ID not found...");
            }
        } catch (IOException e) {
            System.out.println(">>Error updating account: " + e.getMessage());
        }
    }

    // Method to search for an account by ID
    public static void searchAccount() {
        try {
            // Prompt for the account ID to search
            System.out.print("Enter the ID of the account to search: ");
            int searchId = input.nextInt();
            // consume the newline character
            input.nextLine();
            // Check if the accounts file exists and read it
            boolean found = false;
            File file = new File(ACCOUNTS_FILE);
            Scanner reader = new Scanner(file);

            String line;
            while (reader.hasNext()) {
                line = reader.nextLine();
                String[] parts = line.split("\\,");
                int id = Integer.parseInt(parts[2]);
                // If the ID matches the one to search, display account details
                if (id == searchId) {
                    System.out.println(" ======================");
                    System.out.println("|   >>Account Found:   |");
                    System.out.println(" ======================");
                    System.out.println("Name    : " + parts[0]);
                    System.out.println("--------------------------");
                    System.out.println("Age     : " + parts[1]);
                    System.out.println("--------------------------");
                    System.out.println("ID      : " + parts[2]);
                    System.out.println("--------------------------");
                    System.out.println("Balance : Rs. " + parts[3]);
                    System.out.println("--------------------------");
                    // Set found to true to indicate that the account was found
                    found = true;
                    // Exit the loop since the account was found
                    break;
                }
            }
            reader.close();
            // If the account was not found, print a message
            if (!found) {
                System.out.println(">>Account not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println(">>Invalid input! Please enter a numeric value for ID.");
            input.nextLine(); // clear invalid input
        } catch (FileNotFoundException e) {
            System.out.println(">>No file exists..." + e.getMessage());
            input.nextLine(); // clear buffer
        } catch (IOException e) {
            System.out.println(">>Error while searching account: " + e.getMessage());
        }
    }

    public static void viewTransactions() {
        try {
            // Display the transaction log header
            System.out.println("\n=================================");
            System.out.println("     ---- Transaction Logs ----");
            System.out.println("=================================");
            // Check if the transaction log file exists.
            File file = new File(TRANSACTION_LOG);
            Scanner reader = new Scanner(file);
            String line;
            int count = 0;
            // read the file while the till last token.
            while (reader.hasNext()) {
                line = reader.nextLine();
                System.out.println(line);
                // count will increase if the line is not empty.
                count++;
            }
            reader.close();
            // If no transactions found, print a message.
            if (count == 0) {
                System.out.println(">>No transactions found.");
            }
        } catch (FileNotFoundException e) {
            System.out.println(">> No file is found... " + e.getMessage());
        }
    }

    // Method to transfer funds between accounts
    public static void transferFunds() {
        try {
            // Prompt for sender and receiver IDs and amount to transfer
            System.out.println("\n=================================");
            System.out.println("    ---- Fund Transfer ----");
            System.out.println("=================================");
            System.out.print("Enter Sender's ID: ");
            int senderId = input.nextInt();
            System.out.print("Enter Receiver's ID: ");
            int receiverId = input.nextInt();
            System.out.print("Enter amount to transfer: ");
            double amount = input.nextDouble();
            // Check if the amount is valid
            if (amount <= 0) {
                System.out.println(">>Amount must be greater than zero.");
                return;
            }
            // consume the newline character
            input.nextLine();
            // Check if the accounts file exists and read it
            List<String> updatedAccounts = new ArrayList<>();
            File file = new File(ACCOUNTS_FILE);
            Scanner reader = new Scanner(file);
            // Variable to hold each line read from the file
            String line;
            // Initialize variables to hold sender and receiver details
            boolean senderFound = false, receiverFound = false;
            double senderBalance = 0, receiverBalance = 0;
            String senderName = "", receiverName = "";
            // Read the file line by line to find sender and receiver accounts
            while (reader.hasNext()) {
                line = reader.nextLine();
                String[] parts = line.split("\\,");
                int id = Integer.parseInt(parts[2]);
                double bal = Double.parseDouble(parts[3]);
                // Check if condition true then update the sender and receiver details.
                if (id == senderId) {
                    senderFound = true;
                    senderBalance = bal;
                    senderName = parts[0];
                }
                if (id == receiverId) {
                    receiverFound = true;
                    receiverBalance = bal;
                    receiverName = parts[0];
                }
                updatedAccounts.add(line);
            }
            reader.close();
            // Check if both sender and receiver accounts were found
            if (!senderFound) {
                System.out.println(">>Sender ID not found in file.");
                return;
            }
            if (!receiverFound) {
                System.out.println(">>Receiver ID not found in file.");
                return;
            }
            if (senderBalance < amount) {
                System.out.println(">>Insufficient balance in sender's account.");
                return;
            }

            // array list to hold updated account data
            List<String> newAccountData = new ArrayList<>();
            // Update balances for sender and receiver.
            for (String acc : updatedAccounts) {
                String[] parts = acc.split(",");
                int id = Integer.parseInt(parts[2]);

                // If the ID matches the sender or receiver, update the balance.
                if (id == senderId) {
                    double newBal = senderBalance - amount;
                    newAccountData.add(parts[0] + "," + parts[1] + "," + parts[2] + "," + newBal);
                } else if (id == receiverId) {
                    double newBal = receiverBalance + amount;
                    newAccountData.add(parts[0] + "," + parts[1] + "," + parts[2] + "," + newBal);
                }

                // If the ID does not match either sender or receiver, keep the existing account
                // data.
                else {
                    newAccountData.add(acc);
                }
            }
            // Open the accounts file to write updated account data
            FileWriter writer = new FileWriter(file);
            // using for loop to write the updated account data.
            for (String acc : newAccountData) {
                // Write updated account data back to the file.
                writer.write(acc + "\n");
            }
            writer.close();

            // Log the transaction in the transaction log file.
            PrintWriter writer1 = new PrintWriter(new FileWriter(TRANSACTION_LOG, true));
            // Write the transaction details to the log file.
            writer1.println("Transferred Rs. " + amount + " from " + senderName + " (ID: " + senderId + ") to "
                    + receiverName + " (ID: " + receiverId + ") on " + new Date());

            // Close the writer after writing the transaction.
            writer1.close();
            // Print success message
            System.out.println(">>Amount Rs. " + amount + " transferred successfully from ID " + senderId + " to ID "
                    + receiverId + ".");

        } catch (IOException e) {
            System.out.println(">>An error occurred while processing the transfer: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // use while loop to keep the program running until the user chooses to exit.
        boolean isGood = false;
        while (!isGood) {
            // Display the main menu
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║                                        ║");
            System.out.println("║         W E L C O M E  TO              ║");
            System.out.println("║                                        ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║                                        ║");
            System.out.println("║       BANK MANAGEMENT SYSTEM           ║");
            System.out.println("║                                        ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║                                        ║");
            System.out.println("║   1.  ADMIN LOGIN                      ║");
            System.out.println("║                                        ║");
            System.out.println("╠────────────────────────────────────────╣");
            System.out.println("║                                        ║");
            System.out.println("║   2.  USER LOGIN                       ║");
            System.out.println("║                                        ║");
            System.out.println("╠────────────────────────────────────────╣");
            System.out.println("║                                        ║");
            System.out.println("║   3.  EXIT                             ║");
            System.out.println("║                                        ║");
            System.out.println("╚════════════════════════════════════════╝");
            // Prompt for user choice
            System.out.print("\nEnter your choice (1-3): ");

            try {
                int choice = input.nextInt();

                // consume leftover newline:
                input.nextLine();
                // Check the user's choice and call the appropriate method
                switch (choice) {
                    case 1: {
                        loginAdmin();
                        break;
                    }
                    case 2: {
                        userMenu();
                        break;
                    }
                    case 3: {
                        System.out.println("");
                        System.out.println("\n╔════════════════════════════════════════╗");
                        System.out.println("║                                        ║");
                        System.out.println("║            * * * * * *                 ║");
                        System.out.println("║                                        ║");
                        System.out.println("║          T H A N K   Y O U             ║");
                        System.out.println("║                                        ║");
                        System.out.println("║            * * * * * *                 ║");
                        System.out.println("║                                        ║");
                        System.out.println("║   For using our Banking Services!      ║");
                        System.out.println("║                                        ║");
                        System.out.println("╚════════════════════════════════════════╝");
                        System.out.println("");

                        // Set isGood to true to exit the loop
                        isGood = true;
                        // Exit the program
                        break;
                    }
                    // If the user enters an invalid choice, print an error message
                    default: {
                        System.out.println("Invalid Choice! Please enter a number from (1-3)");
                        break;
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println(">>Invalid input! Please enter a numeric value (1-3).");
                // clear invalid input
                input.nextLine();
            } catch (Exception e) {
                System.out.println(">>An unexpected error occurred: " + e.getMessage());
                input.nextLine(); // clear buffer to avoid infinite loop
            }
        }
    }
}
