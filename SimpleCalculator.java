import java.util.Scanner;

/**
 * Task 1: Simple Calculator Program
 * ----------------------------------
 * This program performs basic arithmetic operations (+, -, *, /)
 * based on user input. It uses a Scanner to read input from the
 * console and handles invalid input (like division by zero or
 * non-numeric entries) using exception handling.
 */
public class SimpleCalculator {

    public static void main(String[] args) {
        // Scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== Simple Calculator =====");

        // Loop lets the user perform multiple calculations without restarting
        boolean continueCalculating = true;

        while (continueCalculating) {
            try {
                // Step 1: Get the first number from the user
                System.out.print("\nEnter first number: ");
                double num1 = Double.parseDouble(scanner.nextLine().trim());

                // Step 2: Get the operator from the user
                System.out.print("Enter an operator (+, -, *, /): ");
                String operator = scanner.nextLine().trim();

                // Step 3: Get the second number from the user
                System.out.print("Enter second number: ");
                double num2 = Double.parseDouble(scanner.nextLine().trim());

                // Step 4: Perform the calculation and store the result
                double result;

                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        // Division by zero is not mathematically valid,
                        // so we throw a custom exception to handle it gracefully.
                        if (num2 == 0) {
                            throw new ArithmeticException("Division by zero is not allowed.");
                        }
                        result = num1 / num2;
                        break;
                    default:
                        // If the operator entered isn't one we support
                        throw new IllegalArgumentException("Invalid operator entered: " + operator);
                }

                // Step 5: Display the result clearly to the user
                System.out.printf("Result: %.2f %s %.2f = %.2f%n", num1, operator, num2, result);

            } catch (NumberFormatException e) {
                // Triggered when the user types something that isn't a valid number
                System.out.println("Error: Please enter valid numeric values only.");
            } catch (ArithmeticException e) {
                // Triggered specifically for division by zero
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                // Triggered for unsupported operators
                System.out.println("Error: " + e.getMessage());
            }

            // Ask the user if they want to perform another calculation
            System.out.print("\nDo you want to perform another calculation? (yes/no): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            if (!choice.equals("yes")) {
                continueCalculating = false;
            }
        }

        System.out.println("\nThank you for using the Simple Calculator!");
        scanner.close();
    }
}
