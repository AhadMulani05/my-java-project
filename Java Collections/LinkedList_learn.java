import java.util.LinkedList;

public class LinkedList_learn {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(10);
        list.add(20);
        list.add(30);

        System.out.println(list);

        list.remove(0);
        System.out.println(list);

        System.out.println(list.get(1));

        list.add(0, 100);
        System.out.println(list);

        for(int num : list) {
            System.out.print(num + " ");
        }
    }
}
