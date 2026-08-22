import java.util.Queue;
import java.util.LinkedList;

public class Queue_learn {
    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();

        q.offer(10);
        q.offer(20);
        q.offer(30);

        System.out.println(q);

        q.poll();
        System.out.println(q);

        q.offer(40);
        System.out.println(q);

        System.out.println(q.peek());
    }
}
