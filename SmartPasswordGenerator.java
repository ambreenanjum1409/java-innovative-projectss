import java.util.Random;
import java.util.Scanner;

class PasswordUtility {
    public static String generatePassword(String name, String pan, String dob) {

        Random random = new Random();

        String specialChars = "@#$%&*!";

        String first = name.substring(0, 3).toUpperCase();
        String second = pan.substring(0, 4).toUpperCase();
        String third = dob.replace("/", "");

        int randomNumber = 100 + random.nextInt(900);

        char special = specialChars.charAt(random.nextInt(specialChars.length()));

        return first + special + second + third + randomNumber;
    }
    public static String checkStrength(String password) {

        boolean upper = false;
        boolean digit = false;
        boolean special = false;

        for (char ch : password.toCharArray()) {

            if (Character.isUpperCase(ch)) {
                upper = true;
            }

            else if (Character.isDigit(ch)) {
                digit = true;
            }

            else {
                special = true;
            }
        }

        if (password.length() >= 12 && upper && digit && special) {
            return "Strong";
        }

        else if (password.length() >= 8) {
            return "Medium";
        }

        else {
            return "Weak";
        }
    }
}

public class SmartPasswordGenerator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n===== SMART PASSWORD GENERATOR =====");
            System.out.println("1. Generate Password");
            System.out.println("2. Exit");
            System.out.print("Enter Choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter PAN Number: ");
                    String pan = sc.nextLine();

                    System.out.print("Enter DOB (DD/MM/YYYY): ");
                    String dob = sc.nextLine();

                    String password = PasswordUtility.generatePassword(name, pan, dob);

                    System.out.println("\nGenerated Password: " + password);

                    String strength = PasswordUtility.checkStrength(password);

                    System.out.println("Password Strength: " + strength);

                    break;

                case 2:
                    System.out.println("Exiting Application...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 2);

        sc.close();
    }
}