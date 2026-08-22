import java.util.HashSet;

public class HashSet_learn {
    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();

        set.add(10);
        set.add(20);
        set.add(30);

        System.out.println(set);

        if(set.contains(20)) {
            System.out.println("found");
        }

        set.remove(20);
        System.out.println(set);

        System.out.println(set.size());   
        
        set.add(20);
        System.out.println(set);

        // HashSet<Integer> set = new HashSet<>();

        // int[] arr = {1,2,3,4,2,5};

        // for(int num : arr) {
        //     if(set.contains(num)) {
        //         System.out.println("Duplicate" + num);
        //     }
        //     set.add(num);
        // }

        // System.out.println(set);
        // System.out.println(set.size());
        
    }
}
