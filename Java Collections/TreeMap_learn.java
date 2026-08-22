import java.util.TreeMap;

public class TreeMap_learn {
    public static void main(String[] args) {
        TreeMap<Integer, String> map = new TreeMap<>();

        map.put(30, "C");
        map.put(10, "A");
        map.put(20, "B");

        System.out.println(map);

        System.out.println(map.firstKey());
        System.out.println(map.lastKey());

        System.out.println(map.higherKey(20));
        System.out.println(map.lowerKey(30));

        System.out.println(map.ceilingKey(30));

        System.out.println(map.floorKey(30));
    }
}