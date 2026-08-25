import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UserDAO userDAO = new UserDAO();

        while (true) {

            System.out.println("\n======================");
            System.out.println("     USER SYSTEM");
            System.out.println("======================");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter password: ");
                    String password = sc.nextLine();

                    User newUser =
                            new User(
                                    name,
                                    email,
                                    password
                            );

                    boolean registered =
                            userDAO.signup(newUser);

                    if (registered) {
                        System.out.println(
                                "Signup successful!"
                        );
                    } else {
                        System.out.println(
                                "Signup failed!"
                        );
                    }

                    break;


                case 2:

                    System.out.print("Enter email: ");
                    String loginEmail = sc.nextLine();

                    System.out.print("Enter password: ");
                    String loginPassword = sc.nextLine();

                    User user =
                            userDAO.login(
                                    loginEmail,
                                    loginPassword
                            );

                    if (user != null) {

                        System.out.println(
                                "Login successful!"
                        );

                        System.out.println(
                                "Welcome, " +
                                user.getName() + "!"
                        );

                    } else {

                        System.out.println(
                                "Invalid email or password."
                        );
                    }

                    break;


                case 3:

                    System.out.println(
                            "Application closed."
                    );

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