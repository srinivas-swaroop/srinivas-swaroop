// Simple Java Program for Optimal Page Replacement Algorithm
import java.util.*;

public class OptimalPageReplacement {
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

        ArrayList<Integer> memory = new ArrayList<>();
        int pageFaults = 0;

        for (int i = 0; i < n; i++) {
            int page = pages[i];

            // If page not in memory
            if (!memory.contains(page)) {
                if (memory.size() < frames) {
                    memory.add(page);
                } else {
                    // Find page to replace
                    int farthest = -1, indexToReplace = -1;
                    for (int j = 0; j < memory.size(); j++) {
                        int pageInMem = memory.get(j);
                        int nextUse = -1;
                        for (int k = i + 1; k < n; k++) {
                            if (pages[k] == pageInMem) {
                                nextUse = k;
                                break;
                            }
                        }
                        if (nextUse == -1) { // not used again
                            indexToReplace = j;
                            break;
                        }
                        if (nextUse > farthest) {
                            farthest = nextUse;
                            indexToReplace = j;
                        }
                    }
                    memory.set(indexToReplace, page);
                }
                pageFaults++;
            }

            System.out.println("Frames: " + memory);
        }

        System.out.println("\nTotal Page Faults = " + pageFaults);
    }
}
