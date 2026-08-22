import java.util.HashMap;

public class HashMap_learn {
    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int[] arr = {1,2,2,3,1,1};

        for(int x : arr) {
            if(map.containsKey(x)) {
                map.put(x, map.get(x)+1);
            }
            else map.put(x, 1);
        }

        System.out.println(map);

        // map.put(101, "Ahad");
        // map.put(102, "Rahul");
        // map.put(103, "Omkar");

        // System.out.println(map);

        // System.out.println(map.get(101));

        // if(map.containsKey( 101)) {
        //     System.out.println("found");
        // }

        // map.remove(103);
        // System.out.println(map);

        // System.out.println(map.size());

        // if(map.isEmpty()) {
        //     System.out.println("Map is empty");
        // }

        // for(Integer key : map.keySet()) {
        //     System.out.println(key);
        // }

        // for(String value : map.values()) {
        //     System.out.println(value);
        // }

        // for(Map.Entry<Integer, String> entry : map.entrySet()) {
        //     System.out.println(entry.getKey() + " " + entry.getValue());
        // }
    }
}
