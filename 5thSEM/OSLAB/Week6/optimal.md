Perfect 👏
Here’s your **complete, formatted content** for the **Optimal Page Replacement Algorithm** — in the same neat style for your **Lab FAT record or viva**.

---

### **Experiment: Optimal Page Replacement Algorithm**

#### **Aim:**

To implement the **Optimal Page Replacement Algorithm** and calculate the total number of page faults.

---

#### **Objective:**

* To understand the working of the Optimal page replacement strategy.
* To simulate page replacement using future knowledge of page references.
* To find the minimum number of page faults possible for a given reference string.

---

#### **Algorithm:**

1. Start the program.
2. Initialize number of frames and the reference string (pages).
3. For each page in the reference string:

   * If the page is already in memory, continue.
   * If there is space in memory, load the page directly.
   * If memory is full:

     * For each page currently in memory, find its **next use position** in the future reference string.
     * Replace the page **that will not be used for the longest time** in the future (or never used again).
     * Increment the page fault count.
4. Display frame contents after each replacement.
5. Print total number of page faults.
6. Stop.

---

#### **Problem Statement:**

Given a reference string and a fixed number of memory frames, implement the Optimal Page Replacement Algorithm to minimize page faults.

---

#### **Code:**

```java
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
                        if (nextUse == -1) { // Page not used again
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
```

---

#### **Sample Input:**

```
Enter number of frames: 3
Enter number of pages: 7
Enter page reference string:
1 3 0 3 5 6 3
```

---

#### **Expected Output:**

```
Frames: [1]
Frames: [1, 3]
Frames: [1, 3, 0]
Frames: [1, 3, 0]
Frames: [5, 3, 0]
Frames: [5, 6, 0]
Frames: [5, 6, 3]

Total Page Faults = 6
```

---

#### **Observation:**

* Optimal algorithm replaces the page that will not be used for the longest time in the future.
* It produces the **minimum possible number of page faults** for any given sequence.

---

#### **Conclusion:**

The Optimal Page Replacement algorithm gives the best performance among all page replacement strategies, but it is **not practically implementable** since it requires future knowledge of page references.

---

Would you like me to make the **same format for LRU (Least Recently Used)** next?
It’s the third one commonly asked in lab FAT after FIFO and Optimal.
