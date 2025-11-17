Excellent 💪 — you want the **Readers–Writers problem** using **mutex-style logic** (showing synchronization, as if threads used locks).
We’ll still keep it **simple and non-threaded**, but conceptually treat it as if it’s a **threaded concurrent system**.

Below is the **complete formatted lab FAT content** — with *Aim, Objective, Algorithm, Code, Expected Output,* and proper **mutex terminology**.

---

## **Experiment: Readers–Writers Problem (Using Mutex Concept)**

---

### **Aim:**

To simulate the **Readers–Writers problem** using **mutex (mutual exclusion)** logic for synchronizing access to shared data.

---

### **Objective:**

* To ensure **multiple readers** can read data simultaneously.
* To allow **only one writer** to modify shared data at a time.
* To use a **mutex mechanism** to control concurrent access and avoid race conditions.
* To demonstrate how mutex helps in synchronization and prevents conflicts between readers and writers.

---

### **Algorithm:**

1. Start the program.
2. Initialize shared variable `data` and synchronization variables:

   * `mutex` → for controlling mutual exclusion.
   * `rw_mutex` → for controlling reader/writer access.
   * `readCount` → to count how many readers are currently reading.
3. When a **reader** wants to read:

   * Wait (lock) `mutex` before modifying `readCount`.
   * If it’s the **first reader**, lock `rw_mutex` (to block writers).
   * Release `mutex` and read data.
   * After reading, again lock `mutex` and decrement `readCount`.
   * If it’s the **last reader**, release `rw_mutex` (allow writers).
   * Release `mutex`.
4. When a **writer** wants to write:

   * Wait (lock) `rw_mutex` before writing (exclusive access).
   * Write to shared data.
   * Release `rw_mutex` after writing.
5. Stop.

---

### **Problem Statement:**

To simulate the **Readers–Writers problem** using mutex-style synchronization, ensuring no data inconsistency occurs when readers and writers access shared data.

---

### **Code:**

```java
// Readers–Writers Problem using Mutex Logic (Non-threaded Simulation)
class ReadersWritersMutex {
    public static void main(String[] args) {
        int data = 10;           // Shared resource
        int readCount = 0;       // Number of readers currently reading
        boolean mutex = true;    // Mutex for readCount
        boolean rw_mutex = true; // Mutex for reader-writer access

        System.out.println("Initial Data = " + data);

        // Reader 1 tries to read
        if (mutex && rw_mutex) {
            mutex = false; // Lock mutex
            readCount++;
            if (readCount == 1) rw_mutex = false; // First reader locks rw_mutex
            mutex = true; // Unlock mutex
            System.out.println("Reader 1 is reading data: " + data);
        }

        // Reader 2 tries to read
        if (mutex && rw_mutex == false) {
            mutex = false;
            readCount++;
            System.out.println("Reader 2 is reading data: " + data);
            mutex = true;
        }

        // Writer tries to write
        if (rw_mutex) {
            System.out.println("Writer waiting... readers active.");
        } else {
            // All readers finished
            rw_mutex = true; // Writers get chance
        }

        // Readers finished reading
        mutex = false;
        readCount -= 2; // Both readers done
        if (readCount == 0) rw_mutex = true; // Release writer lock
        mutex = true;

        // Writer writes now
        if (rw_mutex) {
            rw_mutex = false; // Writer locks
            System.out.println("Writer is writing new data...");
            data = 20;
            rw_mutex = true; // Unlock
        }

        // Readers again
        if (rw_mutex) {
            System.out.println("Reader 1 reads new data: " + data);
            System.out.println("Reader 2 reads new data: " + data);
        }
    }
}
```

---

### **Expected Output:**

```
Initial Data = 10
Reader 1 is reading data: 10
Reader 2 is reading data: 10
Writer is writing new data...
Reader 1 reads new data: 20
Reader 2 reads new data: 20
```

---

### **Observation:**

* Mutex ensures that only **one writer** can modify data at a time.
* Multiple readers can read simultaneously when no writer holds `rw_mutex`.
* The system prevents **race conditions** and **ensures data consistency**.
* The concept of **mutex** and **reader–writer locks** is simulated using boolean flags.

---

### **Conclusion:**

The **Readers–Writers problem using mutex** illustrates how synchronization can prevent conflicts between readers and writers in concurrent systems.

* **Mutex** ensures exclusive access for writers.
* **Multiple readers** can read together safely.
* **No race conditions** occur, maintaining system consistency.

---

Would you like me to now show the **actual threaded version using `synchronized` and `Thread` class** (real concurrent behavior but still simple)?
It’s often asked as an extension or viva question after this mutex-style logic.
