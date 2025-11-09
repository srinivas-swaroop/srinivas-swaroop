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

public class waitnotify {
    public static void main(String[] args) {
        Message obj = new Message();

        Thread t1 = new Thread(() -> obj.showMessage());
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000); // Delay to ensure t1 waits first
            } catch (InterruptedException e) {}
            obj.sendNotification();
        });

        t1.start();
        t2.start();
    }
}
