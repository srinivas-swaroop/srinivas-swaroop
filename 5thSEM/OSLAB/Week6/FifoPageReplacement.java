// Simple Java Program for FIFO Page Replacement Algorithm
import java.util.*;

public class FifoPageReplacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of frames and pages
        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();
        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();

        int pages[] = new int[n];
        System.out.println("Enter page reference string:");
        for (int i = 0; i < n; i++)
            pages[i] = sc.nextInt();

        Queue<Integer> q = new LinkedList<>(); // to store pages in memory
        int pageFaults = 0;

        // Process each page
        for (int i = 0; i < n; i++) {
            int page = pages[i];
            
            // If page is not in memory
            if (!q.contains(page)) {
                if (q.size() == frames) {
                    q.poll(); // remove oldest page (FIFO)
                }
                q.add(page); // add new page
                pageFaults++;
            }

            // Display current frame status
            System.out.println("Frames: " + q);
        }

        System.out.println("\nTotal Page Faults = " + pageFaults);
    }
}
