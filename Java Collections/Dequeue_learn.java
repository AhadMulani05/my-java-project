import java.util.Deque;
import java.util.LinkedList;

public class Dequeue_learn {
    public static void main(String[] args) {
        Deque<Integer> dq = new LinkedList<>();

        dq.addFirst(10);
        dq.addLast(20);
        System.out.println(dq);

        dq.removeLast();
        System.out.println(dq);

        //in dq
        //dq.addFirst()
        //dq.addLast()
        //dq.removeFirst()
        //dq.removeLast()
    }
}