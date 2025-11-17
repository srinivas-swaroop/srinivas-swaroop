// // Very Simple Dining Philosophers Example
// public class DiningPhilosophersSimple {
//     public static void main(String[] args) {
//         int n = 5; // Number of philosophers
//         boolean[] fork = new boolean[n]; // All forks are free

//         for (int i = 0; i < n; i++) fork[i] = true;

//         for (int i = 0; i < n; i++) {
//             int left = i;
//             int right = (i + 1) % n;

//             System.out.println("\nPhilosopher " + (i + 1) + " is thinking...");

//             // Check if both forks are free
//             if (fork[left] && fork[right]) {
//                 fork[left] = fork[right] = false; // Take forks
//                 System.out.println("Philosopher " + (i + 1) + " starts eating.");
//                 fork[left] = fork[right] = true;  // Release forks
//                 System.out.println("Philosopher " + (i + 1) + " finished eating.");
//             } else {
//                 System.out.println("Philosopher " + (i + 1) + " cannot eat now (fork not available).");
//             }
//         }
//     }
// }


import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n = 5;
        boolean[] fork = new boolean[n];
        for (int i = 0; i < n; i++) fork[i] = true;

        List<Integer> order = new ArrayList<>();          // 1️⃣ Randomize order
        for (int i = 0; i < n; i++) order.add(i);
        Collections.shuffle(order);

        for (int i : order) {                             // 2️⃣ Random order loop
            int left = i, right = (i + 1) % n;
            System.out.println("\nPhilosopher " + (i + 1) + " is thinking...");

            if (fork[left] && fork[right] && Math.random() > 0.3) { // 3️⃣ Random eating chance
                fork[left] = fork[right] = false;
                System.out.println("Philosopher " + (i + 1) + " starts eating.");
                try { Thread.sleep((int)(Math.random() * 1000)); } catch (Exception e) {}
                fork[left] = fork[right] = true;
                System.out.println("Philosopher " + (i + 1) + " finished eating.");
            } else {
                System.out.println("Philosopher " + (i + 1) + " cannot eat now (fork not available).");
            }
        }
    }
}

