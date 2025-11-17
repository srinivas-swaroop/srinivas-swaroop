Perfect 👍 You’ve clearly separated the two important **thread concept experiments** —

1️⃣ `join()`, `yield()`, and `sleep()` functions
2️⃣ `wait()` and `notify()` synchronization

Here’s **complete formatted lab content** (aim → objective → algorithm → code → expected output → observation → conclusion) for both experiments.

---

## 🧵 **Experiment – 1: Thread Functions (join(), yield(), sleep())**

---

### **Aim:**

To demonstrate the working of thread functions — `sleep()`, `yield()`, and `join()` in Java.

---

### **Objective:**

* To create multiple threads using the `Thread` class.
* To understand how:

  * `sleep()` pauses thread execution temporarily.
  * `yield()` allows other threads to run.
  * `join()` ensures one thread completes before continuing main thread execution.

---

### **Algorithm:**

1. Create a class `MyThread` extending `Thread`.
2. Override the `run()` method:

   * Print thread name and count.
   * Use `Thread.sleep()` to pause execution.
   * Use `Thread.yield()` to let other threads execute.
3. In the `main()` method:

   * Create two threads (`t1`, `t2`) of `MyThread`.
   * Set thread names.
   * Start both threads using `start()`.
   * Use `join()` to wait until both threads finish before continuing the main thread.
4. Display message when main thread ends.

---

### **Code:**

```java
// Simple Java Program to demonstrate thread functions
class MyThread extends Thread {
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(Thread.currentThread().getName() + " - Count: " + i);
            try {
                Thread.sleep(500); // sleep pauses the thread for 500ms
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            Thread.yield(); // gives chance for other threads
        }
    }
}

public class ThreadFunctions {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.setName("Thread-1");
        t2.setName("Thread-2");

        t1.start();
        t2.start();

        try {
            // join waits for threads to finish
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("Main thread ends.");
    }
}
```

---

### **Expected Output:**

```
Thread-1 - Count: 1
Thread-2 - Count: 1
Thread-1 - Count: 2
Thread-2 - Count: 2
Thread-1 - Count: 3
Thread-2 - Count: 3
Main thread ends.
```

(Note: Output order may vary slightly due to thread scheduling.)

---

### **Observation:**

* `sleep()` temporarily suspends a thread’s execution.
* `yield()` hints the scheduler to switch to another thread.
* `join()` makes one thread wait until the other completes.

---

### **Conclusion:**

The experiment successfully demonstrates how thread control methods (`sleep()`, `yield()`, `join()`) manage execution order and synchronization in multithreaded programs.

---

## ⚙️ **Experiment – 2: Inter-thread Communication (wait() and notify())**

---

### **Aim:**

To implement inter-thread communication in Java using `wait()` and `notify()` methods.

---

### **Objective:**

* To use `wait()` to pause a thread until another thread sends a signal.
* To use `notify()` to wake up a waiting thread.
* To understand synchronization between two threads on a shared object.

---

### **Algorithm:**

1. Create a class `Message` containing two synchronized methods:

   * `showMessage()` → calls `wait()` to pause thread.
   * `sendNotification()` → calls `notify()` to resume waiting thread.
2. Create two threads:

   * Thread-1 calls `showMessage()`.
   * Thread-2 calls `sendNotification()` after a short delay.
3. Start both threads.
4. Observe that Thread-1 waits until Thread-2 sends notification.

---

### **Code:**

```java
// Simple Java Program to demonstrate wait() and notify()
class Message {
    synchronized void showMessage() {
        System.out.println("Thread-1: Waiting for notification...");
        try {
            wait(); // Thread waits until notified
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("Thread-1: Resumed after notification!");
    }

    synchronized void sendNotification() {
        System.out.println("Thread-2: Sending notification...");
        notify(); // Wakes up waiting thread
    }
}

public class WaitNotifyExample {
    public static void main(String[] args) {
        Message obj = new Message();

        Thread t1 = new Thread(() -> obj.showMessage());
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000); // delay to ensure t1 waits first
            } catch (InterruptedException e) {}
            obj.sendNotification();
        });

        t1.start();
        t2.start();
    }
}
```

---

### **Expected Output:**

```
Thread-1: Waiting for notification...
Thread-2: Sending notification...
Thread-1: Resumed after notification!
```

---

### **Observation:**

* `wait()` pauses the thread inside a synchronized block.
* `notify()` wakes one waiting thread on the same object.
* Proper synchronization ensures controlled communication between threads.

---

### **Conclusion:**

The program successfully demonstrates **inter-thread communication** using `wait()` and `notify()` methods.
It shows how threads coordinate safely over shared resources.

---

Would you like me to combine both experiments into **a single neatly formatted lab record PDF** (with headings, spacing, and ready to print)? I can generate that next.
