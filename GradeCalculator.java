import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Take number of subjects
        System.out.print("Enter the number of subjects: ");
        int numSubjects = sc.nextInt();

        double[] marks = new double[numSubjects];
        double totalMarks = 0;

        // Step 2: Take marks for each subject
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + (i + 1) + " (out of 100): ");
            marks[i] = sc.nextDouble();
            totalMarks += marks[i];
        }

        // Step 3: Calculate average percentage
        double averagePercentage = totalMarks / numSubjects;

        // Step 4: Grade calculation
        String grade;
        if (averagePercentage >= 90) {
            grade = "A+";
        } else if (averagePercentage >= 80) {
            grade = "A";
        } else if (averagePercentage >= 70) {
            grade = "B";
        } else if (averagePercentage >= 60) {
            grade = "C";
        } else if (averagePercentage >= 50) {
            grade = "D";
        } else {
            grade = "F";
        }

        // Step 5: Display results
        System.out.println("\n--- Result ---");
        System.out.println("Total Marks: " + totalMarks);
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);

        sc.close();
    }
}
