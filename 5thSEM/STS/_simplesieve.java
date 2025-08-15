class sieve {

    /**
     * Sieve of Eratosthenes implementation to find all primes ≤ num
     * 
     * Algorithm:
     * 1. Create a boolean array 'vis' of size num+1, initially all false.
     *    vis[i] == false → i is currently assumed to be prime.
     * 2. Loop i from 2 to sqrt(num):
     *    - If vis[i] is false (i is prime):
     *        - Mark all multiples of i starting from i*i as composite (vis[j] = true).
     *        - Start from i*i because smaller multiples were already marked by smaller primes.
     * 3. After marking, print all indices i ≥ 2 where vis[i] is false (these are primes).
     * 
     * Time Complexity:
     *  - Outer loop runs ~√num times.
     *  - Inner loop marks multiples: total number of operations ≈ num * (1/2 + 1/3 + 1/5 + ... + 1/p)
     *    which is O(num log log num) for marking.
     *  Overall: **O(n log log n)** for finding all primes ≤ num.
     * 
     * Space Complexity:
     *  - Boolean array of size (num+1) → **O(n)** space.
     * 
     * Example:
     *  num = 10 → Output: 2 3 5 7
     */
    static void sieveSimple(int num) {
        // Boolean array to track composite numbers
        // false → prime candidate, true → composite
        boolean vis[] = new boolean[num + 1];

        // Process numbers from 2 to sqrt(num)
        for (int i = 2; i * i <= num; i++) {
            // If i is still marked as prime
            if (!vis[i]) {
                // Mark all multiples of i starting from i*i
                for (int j = i * i; j <= num; j += i) {
                    vis[j] = true; // mark composite
                }
            }
        }

        // Print all numbers which are still marked as prime
        for (int i = 2; i < vis.length; i++) {
            if (!vis[i]) {
                System.out.print(i + " ");
            }
        }
    }

    public static void main(String[] args) {
        sieveSimple(100); // Find and print primes ≤ 100
    }
}
