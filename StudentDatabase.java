import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}
class StudentManagementSystem {
    private List<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    // Write student data to a file
    public void writeToFile(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read student data from a file
    @SuppressWarnings("unchecked")
    public void readFromFile(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            students = (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

public class StudentDatabase{
    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save to File");
            System.out.println("6. Load from File");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Roll Number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character

                    System.out.print("Enter Grade: ");
                    String grade = scanner.nextLine();

                    Student student = new Student(name, rollNumber, grade);
                    system.addStudent(student);
                    break;

                case 2:
                    System.out.print("Enter Roll Number of Student to Remove: ");
                    int rollToRemove = scanner.nextInt();
                    system.removeStudent(rollToRemove);
                    break;

                case 3:
                    System.out.print("Enter Roll Number of Student to Search: ");
                    int rollToSearch = scanner.nextInt();
                    Student foundStudent = system.searchStudent(rollToSearch);
                    if (foundStudent != null) {
                        System.out.println("Student Found: " + foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    system.displayAllStudents();
                    break;

                case 5:
                    system.writeToFile("students.dat");
                    System.out.println("Data saved to file.");
                    break;

                case 6:
                    system.readFromFile("students.dat");
                    System.out.println("Data loaded from file.");
                    break;

                case 7:
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
