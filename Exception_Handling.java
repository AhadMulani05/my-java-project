import java.io.*;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class Exception_Handling {
    public static void main(String[] args) {
        System.out.println("hello");
         int i, j;

         try
         {
             BufferedReader br = new BufferedReader (
                 new InputStreamReader(System.in)
             );

             System.out.println("Enter i: ");
             i = Integer.parseInt(br.readLine());

             System.out.println("enter j: ");
             j = Integer.parseInt(br.readLine());

             System.out.println("you entered: " + i + " " + j);
             System.out.println("Result: " + i/j);
         }

         catch (IOException e) {
             System.out.println("Error in input");
         }

         catch (NumberFormatException e) {
             System.out.println("Incorect input");
         }

         catch (ArithmeticException e) {
             System.out.println("Arithmatic exception eror");
         }

         catch (Exception e) {
             System.out.println("Unkown error");
         }


         int num;

             try
             {
                 BufferedReader br = new BufferedReader(
                     new InputStreamReader(System.in)
                 );

                 System.out.print("enter number: ");
                 num = Integer.parseInt(br.readLine());
                 System.out.println("you entered: " + num);
             }

             catch (IOException e) {
                 System.out.println("Error in input");
             }

             catch (NumberFormatException e) {
                 System.out.println("Incorrect Input");
             }

    }

    static void file() throws FileNotFoundException {
        FileReader file = new FileReader("ahad.txt");
        System.out.println("file found brother");
    }
}