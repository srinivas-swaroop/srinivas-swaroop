Perfect 👏 You’ve chosen the **FIFO Page Replacement Algorithm**.
Here’s the **complete formatted lab record content** (aim → code → expected output), neat and ready for your **Lab FAT exam** 👇

---

### **Experiment: FIFO Page Replacement Algorithm**

#### **Aim:**

To implement the **First-In First-Out (FIFO)** page replacement algorithm and calculate the number of page faults.

---

#### **Objective:**

* To understand how pages are replaced in memory using the FIFO method.
* To simulate page replacement using a fixed number of frames.
* To count the total number of page faults during the process.

---

#### **Algorithm:**

1. Start the program.
2. Initialize the number of frames and the reference string (pages).
3. For each page in the reference string:

   * If the page is **not already in memory (frame)**:

     * If all frames are full, remove the **oldest page** (the one that came first).
     * Add the new page to the frame.
     * Increment the **page fault count**.
   * Else (page already present): continue.
4. Display the frame content after each replacement.
5. Print the total number of page faults.
6. Stop.

---

#### **Problem Statement:**

Given a reference string of page numbers and a fixed number of memory frames, use the FIFO page replacement policy to find how many page faults occur.

---

#### **Code:**

```java
import java.util.*;

public class FIFO {
    public static void main(String[] args) {
        int frames = 3; // Number of frames
        int[] pages = {1, 3, 0, 3, 5, 6, 3}; // Reference string

        Queue<Integer> q = new LinkedList<>();
        int faults = 0;

        for (int page : pages) {
            if (!q.contains(page)) { // Page not in memory
                if (q.size() == frames) 
                    q.poll(); // Remove oldest page
                q.add(page); // Add new page
                faults++; // Increase fault count
            }
            System.out.println(q);
        }

        System.out.println("Page Faults = " + faults);
    }
}
```

---

#### **Expected Output:**

```
[1]
[1, 3]
[1, 3, 0]
[1, 3, 0]
[3, 0, 5]
[0, 5, 6]
[5, 6, 3]
Page Faults = 6
```

---

#### **Observation:**

* The FIFO algorithm replaces the oldest loaded page first.
* Total number of **page faults = 6**.

---

#### **Conclusion:**

FIFO is a simple page replacement algorithm that replaces the page that has been in memory the longest. However, it may not always give the optimal result and can suffer from **Belady’s Anomaly**.

---

Would you like me to give the **same formatted content for Optimal Page Replacement** next (to prepare both FIFO & Optimal together)?
