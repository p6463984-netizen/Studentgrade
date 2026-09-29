import java.util.ArrayList;
import java.util.Scanner;

/**
 * Task 2: Student Grade Management System
 * -----------------------------------------
 * This console application lets the user store student names and marks,
 * then calculates and displays the average, highest, and lowest marks
 * in a clearly formatted summary report.
 *
 * An ArrayList is used instead of a fixed-size array so the number
 * of students does not need to be known in advance.
 */
public class StudentGradeManagementSystem {

    // Simple inner class to keep a student's name and marks bundled together
    static class Student {
        String name;
        double marks;

        Student(String name, double marks) {
            this.name = name;
            this.marks = marks;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // ArrayList to store all student records
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("===== Student Grade Management System =====");

        // Step 1: Ask how many students will be entered
        int numStudents = readPositiveInt(scanner, "Enter the number of students: ");

        // Step 2: Collect each student's name and marks
        for (int i = 1; i <= numStudents; i++) {
            System.out.println("\n-- Student " + i + " --");

            System.out.print("Enter student name: ");
            String name = scanner.nextLine().trim();

            double marks = readValidMarks(scanner, "Enter marks (0-100): ");

            students.add(new Student(name, marks));
        }

        // Step 3: Calculate average, highest, and lowest marks
        double total = 0;
        double highest = students.get(0).marks;
        double lowest = students.get(0).marks;
        String topStudent = students.get(0).name;
        String bottomStudent = students.get(0).name;

        for (Student s : students) {
            total += s.marks;

            if (s.marks > highest) {
                highest = s.marks;
                topStudent = s.name;
            }
            if (s.marks < lowest) {
                lowest = s.marks;
                bottomStudent = s.name;
            }
        }

        double average = total / students.size();

        // Step 4: Display a clear, formatted summary report
        System.out.println("\n===== Summary Report =====");
        System.out.printf("%-15s %-10s%n", "Name", "Marks");
        System.out.println("-----------------------------");

        for (Student s : students) {
            System.out.printf("%-15s %-10.2f%n", s.name, s.marks);
        }

        System.out.println("-----------------------------");
        System.out.printf("Average Marks : %.2f%n", average);
        System.out.printf("Highest Marks : %.2f (%s)%n", highest, topStudent);
        System.out.printf("Lowest Marks  : %.2f (%s)%n", lowest, bottomStudent);

        scanner.close();
    }

    /**
     * Keeps prompting until the user enters a valid positive integer.
     * Prevents crashes from non-numeric or zero/negative input.
     */
    private static int readPositiveInt(Scanner scanner, String prompt) {
        int value = -1;
        while (value <= 0) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine().trim());
                if (value <= 0) {
                    System.out.println("Please enter a number greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
        return value;
    }

    /**
     * Keeps prompting until the user enters marks between 0 and 100.
     */
    private static double readValidMarks(Scanner scanner, String prompt) {
        double marks = -1;
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt);
            try {
                marks = Double.parseDouble(scanner.nextLine().trim());
                if (marks < 0 || marks > 100) {
                    System.out.println("Marks must be between 0 and 100.");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }
        return marks;
    }
}
