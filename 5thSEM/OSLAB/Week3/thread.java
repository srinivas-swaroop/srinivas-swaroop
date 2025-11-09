// Simple Java Program to demonstrate thread functions
class MyThread extends Thread {
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(Thread.currentThread().getName() + " - Count: " + i);
            try {
                // sleep() pauses thread for 500ms
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            // yield() allows other threads to execute
            Thread.yield();
        }
    }
}

public class thread {
    public static void main(String[] args) {
        // Create two threads
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        // Set thread names
        t1.setName("Thread-1");
        t2.setName("Thread-2");

        // Start both threads
        t1.start();
        t2.start();

        try {
            // join() waits for t1 and t2 to complete
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("Main thread ends.");
    }
}
