import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentDAO dao = new StudentDAO();

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    sc.nextLine();

                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter marks: ");
                    double marks = sc.nextDouble();

                    dao.insertStudent(name, marks);

                    break;


                case 2:

                    dao.getAllStudents();

                    break;


                case 3:

                    System.out.print("Enter student ID: ");
                    int updateId = sc.nextInt();

                    System.out.print("Enter new marks: ");
                    double newMarks = sc.nextDouble();

                    dao.updateStudent(
                            updateId,
                            newMarks
                    );

                    break;


                case 4:

                    System.out.print("Enter student ID: ");
                    int deleteId = sc.nextInt();

                    dao.deleteStudent(deleteId);

                    break;


                case 5:

                    System.out.println("Application closed.");

                    sc.close();

                    return;


                default:

                    System.out.println(
                            "Invalid choice!"
                    );
            }
        }
    }
}