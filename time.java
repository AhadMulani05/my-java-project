abstract class Animal {
    abstract void makeSound();
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("bhau bhau");
    }
}

class Cat extends Animal {
    @Override
    void makeSound() {
        System.out.println("meowwww");
    }
}

abstract class Car {
    String name;
    abstract void owner();

    Car(String name) {
        this.name = name;
    }

    void display() {
        System.out.println("this is best car....");
    }
}

interface Person {
    void display();
}

class Man implements Person {
    public void display() {
        System.out.println("Hello man");
    }
}


interface Solve {
    void display();
}

interface Solve1 {
    void show();
}

class Solve2 implements Solve,Solve1 {
    public void display(){ System.out.println("Hello i am Solve class"); }
    public void show(){ System.out.println("I am Solve1 class"); }
}


class MyString {
    public String name;

    MyString(String name) {
        this.name = name;
    }

    void display() {
        System.out.println(name);
    }

    void len() {
        System.out.println(name.length());
    }

    void lower() {
        System.out.println(name.toLowerCase());
    }

    void upper() {
        System.out.println(name.toUpperCase());
    }
}


class Cal {
    int add(int a, int b) {
        return a+b;
    }

    int add(int a, int b, int c) {
        return a+b+c;
    }
}

class Ani {
    void sound() {
        System.out.println("barking");
    }
}

class Catt extends Ani{
    void sound() {
        System.out.println("meowwww");
    }
}

class Timepass {
    private String name;
    private int age;

    Timepass() {
    }

    Timepass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    void display() {
        System.out.println(getName() + " " + getAge());
    }
}



public class time {
    public static void main(String[] arg) {

        Timepass t1 = new Timepass();
        t1.setName("Ahad");
        t1.setAge(21);
        t1.display();
        t1.getAge();

        // Ani c1 = new Catt();
        // c1.sound();

        // Student s1 = new Student();
        // s1.setName("Ahad Rihaj Mulani");
        // String ans = s1.getName();
        
        // Man m1 = new Man();
        // m1.display();

        // Solve2 s2 = new Solve2();
        // s2.display();
        // s2.show();

        // MyString str = new MyString("Ahad");
        // str.display();
        // str.len();
        // str.lower();
        // str.upper();

        // Cal C = new Cal();
        // System.out.println(C.add(10,20, 49));


    }
}