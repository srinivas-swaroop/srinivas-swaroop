import java.util.*;

public class FIFO {
    public static void main(String[] args) {
        int frames = 3;
        int[] pages = {1, 3, 0, 3, 5, 6, 3};

        Queue<Integer> q = new LinkedList<>();
        int faults = 0;

        for (int page : pages) {
            if (!q.contains(page)) {
                if (q.size() == frames) q.poll();
                q.add(page);
                faults++;
            }
            System.out.println(q);
        }

        System.out.println("Page Faults = " + faults);
    }
}
