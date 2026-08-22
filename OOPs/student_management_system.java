class Student {
    int id;
    String name;
    int age;
    double marks;

    Student(int id, String name, int age, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    void display() {
        System.out.println("Student ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Marks: " + marks);
    }

    boolean isPass() {
        if(marks >= 35) return true;
        else return false;
    }

    char grade() {
        if(marks >= 90) return 'A';
        else if(marks >= 75) return 'B';
        else if(marks >= 60) return 'C';
        else if(marks >= 35) return 'D';
        else return 'F';
    }
}


public class student_management_system {
    public static void main(String[] args) {
        Student s1 = new Student(101, "Ahad", 21, 91.5);
        Student s2 = new Student(102, "Amit", 20, 72);
        Student s3 = new Student(103, "Ravi", 22, 30);

        s1.display();
        System.out.println("Pass: " + s1.isPass());
        System.out.println("Grade: " + s1.grade());
        System.out.println();

        s2.display();
        System.out.println("Pass: " + s2.isPass());
        System.out.println("Grade: " + s2.grade());
        System.out.println();

        s3.display();
        System.out.println("Pass: " + s3.isPass());
        System.out.println("Grade: " + s3.grade());
    }
}