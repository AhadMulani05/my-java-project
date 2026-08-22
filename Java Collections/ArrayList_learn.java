//Ahad Rihaj Mulani

// ArrayList<Integer> arr = new ArrayList<>();

// arr.add(10);
// arr.add(0, 20);

// arr.get(0);
// arr.set(0, 50);

// arr.remove(0);
// arr.remove(Integer.valueOf(50));

// arr.size();
// arr.contains(20);
// arr.indexOf(20);

// Collections.sort(arr);
// Collections.reverse(arr);
// Collections.min(arr);
// Collections.max(arr);


import java.util.ArrayList;

public class ArrayList_learn {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();

        // Add
        arr.add(10);
        arr.add(20);
        arr.add(30);
        arr.add(40);

        System.out.println(arr);

        // Get
        System.out.println(arr.get(2));

        // Update
        arr.set(1, 100);

        // Remove
        arr.remove(0);

        // Size
        System.out.println("Size: " + arr.size());

        // Search
        System.out.println(arr.contains(30));

        // Traverse
        for(int x : arr) {
            System.out.println(x);
        }
    }
}