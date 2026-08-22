import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ahad {
    public static void main(String[] args) {
        // try {
        //     FileWriter file = new FileWriter("Ahad.txt");

        //     file.write("My name is Ahad Rihaj Mulani 123456");
        //     file.close();
        //     System.out.println("write succesfully");
        // }
        // catch (IOException e) {
        //     System.out.println("An error occured");
        //     e.printStackTrace();
        // }

        // try {
        //     FileWriter file = new FileWriter("Ahad.txt");
        //     file.write("Welcome to java.");
        //     file.close();
        //     System.out.println("data appended");

        //     // File file = new File("Ahad.txt");
        //     // Scanner sc = new Scanner(file);

        //     // while(sc.hasNextLine()) {
        //     //     System.out.println(sc.nextLine());
        //     // }
             
        //     // sc.close();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

        // File file = new File("Ahad.txt");

        // if(file.exists()) {
        //     System.out.println("File Name: " + file.getName());
        //     System.out.println("Path: " + file.getAbsolutePath());
        //     System.out.println("Readable: " + file.canRead());
        //     System.out.println("Writeable: " + file.canWrite());
        //     System.out.println("Size: " + file.length() + "bytes");
        // }

        File file = new File("Ahad.txt");
        if(file.delete()) {
            System.out.println("file is deleted: " + file.getName());
        }
        else System.out.println("errror occureds");
    }
}