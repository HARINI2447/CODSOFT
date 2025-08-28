import java.io.*;
import java.util.*;

// Student class
class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;
    private int age;

    public Student(String name, int rollNumber, String grade, int age) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.age = age;
    }

    // Getters and Setters
    public String getName() { return name; }
    public int getRollNumber() { return rollNumber; }
    public String getGrade() { return grade; }
    public int getAge() { return age; }

    public void setName(String name) { this.name = name; }
    public void setGrade(String grade) { this.grade = grade; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "Roll No: " + rollNumber + ", Name: " + name + ", Grade: " + grade + ", Age: " + age;
    }
}

// Student Management System
class StudentManagementSystem {
    private List<Student> students;
    private final String FILE_NAME = "students.dat";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadFromFile();
    }

    // Add student
    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
    }

    // Remove student
    public boolean removeStudent(int rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) {
                students.remove(s);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    // Search student
    public Student searchStudent(int rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) {
                return s;
            }
        }
        return null;
    }

    // Display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    // Save students to file
    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load students from file
    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (List<Student>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}

// Main class with console UI
public class StudentMAnagementApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();
        int choice;

        do {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Remove Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    // Input validation
                    System.out.print("Enter Roll Number: ");
                    int roll = sc.nextInt();
                    sc.nextLine();
                    if (sms.searchStudent(roll) != null) {
                        System.out.println("Error: Student with this Roll Number already exists!");
                        break;
                    }

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    if (name.trim().isEmpty()) {
                        System.out.println("Error: Name cannot be empty.");
                        break;
                    }

                    System.out.print("Enter Grade: ");
                    String grade = sc.nextLine();
                    if (grade.trim().isEmpty()) {
                        System.out.println("Error: Grade cannot be empty.");
                        break;
                    }

                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();

                    sms.addStudent(new Student(name, roll, grade, age));
                    System.out.println("Student added successfully.");
                    break;

                case 2:
                    System.out.print("Enter Roll Number to edit: ");
                    int editRoll = sc.nextInt();
                    sc.nextLine();
                    Student studentToEdit = sms.searchStudent(editRoll);
                    if (studentToEdit != null) {
                        System.out.print("Enter New Name (leave empty to keep current): ");
                        String newName = sc.nextLine();
                        if (!newName.trim().isEmpty()) studentToEdit.setName(newName);

                        System.out.print("Enter New Grade (leave empty to keep current): ");
                        String newGrade = sc.nextLine();
                        if (!newGrade.trim().isEmpty()) studentToEdit.setGrade(newGrade);

                        System.out.print("Enter New Age (0 to keep current): ");
                        int newAge = sc.nextInt();
                        if (newAge > 0) studentToEdit.setAge(newAge);

                        System.out.println("Student updated successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Roll Number to search: ");
                    int searchRoll = sc.nextInt();
                    Student found = sms.searchStudent(searchRoll);
                    if (found != null) {
                        System.out.println("Student Found: " + found);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    sms.displayAllStudents();
                    break;

                case 5:
                    System.out.print("Enter Roll Number to remove: ");
                    int removeRoll = sc.nextInt();
                    if (sms.removeStudent(removeRoll)) {
                        System.out.println("Student removed successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);

        sc.close();
    }
}
