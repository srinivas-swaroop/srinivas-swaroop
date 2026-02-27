import java.util.*;

public class Sortwes {

    // Method to sort the queue without extra space
    private static void sortQueue(Queue<Integer> queue) {

        int n = queue.size();

        System.out.println("Initial Queue: " + queue);

        // -------- OUTER LOOP --------
        for (int i = 0; i < n; i++) {

            int minIndex = -1;
            int minValue = Integer.MAX_VALUE;

            System.out.println("\n--- Pass " + (i + 1) + " ---");

            // -------- FIND MINIMUM --------
            for (int j = 0; j < n; j++) {

                int currValue = queue.poll();

                if (currValue < minValue && j < (n - i)) {
                    minValue = currValue;
                    minIndex = j;
                }

                queue.add(currValue);
                System.out.println("Scanned " + currValue + 
                                   " → Queue: " + queue);
            }

            System.out.println("Minimum = " + minValue +
                               ", Index = " + minIndex);

            // -------- REMOVE MINIMUM --------
            for (int j = 0; j < n; j++) {

                int currValue = queue.poll();

                if (j != minIndex) {
                    queue.add(currValue);
                } else {
                    System.out.println("Removed minimum: " + currValue);
                }
            }

            // -------- PLACE MINIMUM AT REAR --------
            queue.add(minValue);

            System.out.println("Queue after placing min at rear: " + queue);
        }

        System.out.println("\nSorted Queue: " + queue);
    }

    // Main method
    public static void main(String[] args) {

        Queue<Integer> q1 = new LinkedList<>();

        // Elements already added to queue
        q1.add(4);
        q1.add(1);
        q1.add(3);
        q1.add(2);

        sortQueue(q1);
    }
}
