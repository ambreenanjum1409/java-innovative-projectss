import java.io.*;
import java.util.*;

class Expense {

    double amount;
    String category;
    String date;

    Expense(double amount, String category, String date) {

        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public String toString() {
        return amount + "," + category + "," + date;
    }
}

public class PersonalExpenseTracker {

    static ArrayList<Expense> expenses = new ArrayList<>();

    // Add Expense
    public static void addExpense(double amount, String category, String date) {

        Expense e = new Expense(amount, category, date);

        expenses.add(e);

        System.out.println("Expense Added Successfully!");
    }

    // Display Expenses
    public static void displayExpenses() {

        if (expenses.isEmpty()) {
            System.out.println("No Expenses Found!");
            return;
        }

        System.out.println("\n===== ALL EXPENSES =====");

        for (Expense e : expenses) {

            System.out.println("Amount: " + e.amount +
                    " | Category: " + e.category +
                    " | Date: " + e.date);
        }
    }

    // Monthly Report
    public static void monthlyReport() {

        double total = 0;

        for (Expense e : expenses) {
            total += e.amount;
        }

        System.out.println("\nTotal Monthly Expense: " + total);
    }

    // Highest Expense Category
    public static void highestCategory() {

        HashMap<String, Double> map = new HashMap<>();

        for (Expense e : expenses) {

            map.put(e.category,
                    map.getOrDefault(e.category, 0.0) + e.amount);
        }

        String maxCategory = "";
        double maxAmount = 0;

        for (String key : map.keySet()) {

            if (map.get(key) > maxAmount) {

                maxAmount = map.get(key);
                maxCategory = key;
            }
        }

        System.out.println("Highest Expense Category: "
                + maxCategory + " = " + maxAmount);
    }

    // Save File
    public static void saveToFile() {

        try {

            FileWriter fw = new FileWriter("expenses.txt");

            for (Expense e : expenses) {
                fw.write(e.toString() + "\n");
            }

            fw.close();

            System.out.println("Data Saved Successfully!");

        } catch (Exception e) {
            System.out.println("Error Saving File!");
        }
    }

    // Load File
    public static void loadFromFile() {

        try {
        	expenses.clear();
            BufferedReader br =
                    new BufferedReader(new FileReader("expenses.txt"));

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                expenses.add(new Expense(
                        Double.parseDouble(data[0]),
                        data[1],
                        data[2]));
            }

            br.close();

            System.out.println("Data Loaded Successfully!");

        } catch (Exception e) {
            System.out.println("Error Loading File!");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n===== PERSONAL EXPENSE TRACKER =====");
            System.out.println("1. Add Expense");
            System.out.println("2. Display Expenses");
            System.out.println("3. Monthly Report");
            System.out.println("4. Highest Expense Category");
            System.out.println("5. Save Data");
            System.out.println("6. Load Data");
            System.out.println("7. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter Amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Enter Category: ");
                    String category = sc.nextLine();

                    System.out.print("Enter Date: ");
                    String date = sc.nextLine();

                    addExpense(amount, category, date);
                    break;

                case 2:
                    displayExpenses();
                    break;

                case 3:
                    monthlyReport();
                    break;

                case 4:
                    highestCategory();
                    break;

                case 5:
                    saveToFile();
                    break;

                case 6:
                    loadFromFile();
                    break;

                case 7:
                    System.out.println("Exiting Application...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 7);

        sc.close();
    }
}
