import java.util.TreeSet;

public class TreeSet_learn {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();

        set.add(50);
        set.add(10);
        set.add(30);
        set.add(20);
        set.add(40);

        System.out.println(set);

        System.out.println(set.first());

        System.out.println(set.last());

        System.out.println(set.higher(20));
        System.out.println(set.lower(20));

        System.out.println(set.ceiling(19));
        System.out.println(set.floor(31));

        int x = set.pollFirst();
        System.out.println(x);
        System.out.println(set);

        int y = set.pollLast();
        System.out.println(y);
        System.out.println(set);

        set.add(10);
        set.add(50);
        System.out.println(set);

// higher(x)
// ↓
// strictly greater


// ceiling(x)
// ↓
// greater than OR equal


// lower(x)
// ↓
// strictly smaller


// floor(x)
// ↓
// smaller than OR equal
    }
}
