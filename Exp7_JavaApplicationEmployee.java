import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Exp7_JavaApplicationEmployee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            // Switch case to choose different options
            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayAllEmployees();
                    break;
                case 3:
                    System.out.println("Exiting the application.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void addEmployee() {
        try (FileWriter writer = new FileWriter("employees.txt", true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter employee name: ");
            String name = scanner.nextLine();
            System.out.print("Enter employee id: ");
            String id = scanner.nextLine();
            System.out.print("Enter employee designation: ");
            String designation = scanner.nextLine();

            double salary;
            while (true) {
                try {
                    System.out.print("Enter employee salary: ");
                    salary = scanner.nextDouble();
                    break; // Break out of the loop if input is valid
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid salary (numeric value).");
                    scanner.nextLine(); // Consume the invalid input
                }
            }

            printWriter.println(name + "," + id + "," + designation + "," + salary);
            System.out.println("Employee added successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    private static void displayAllEmployees() {
        try (FileReader reader = new FileReader("employees.txt");
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            System.out.println("Employee Details:");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] employeeDetails = line.split(",");
                System.out.println("Name: " + employeeDetails[0]);
                System.out.println("ID: " + employeeDetails[1]);
                System.out.println("Designation: " + employeeDetails[2]);
                System.out.println("Salary: " + employeeDetails[3]);
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}
