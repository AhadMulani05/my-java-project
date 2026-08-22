class Solve<T> {
    T value1;
    T value2;

    Solve(T value1, T value2) {
        this.value1 = value1;
        this.value2 = value2;

        System.out.println(value1);
        System.out.println(value2);
    }
}

public class practice {
    public static void main(String[] args) {
        Solve<Integer> s1 = new Solve<>(10, 82);
    }
}