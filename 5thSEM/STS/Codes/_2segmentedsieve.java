public class _2segmentedsieve {

    // This function prints all primes in the range [low, high]
    static void segmentedSieve(int low, int high) {
        // vis[i] = true means "i is marked as not prime"
        boolean vis[] = new boolean[high + 1];

        // 0 and 1 are not prime
        vis[0] = true;
        vis[1] = true;

        // We iterate over all numbers from 2 to high
        for (int i = 2; i*i <= high; i++) {

            /*
             * Calculate the starting point to mark multiples of i as non-prime.
             * 
             * Explanation:
             * - i * i: The smallest multiple of i that we need to start marking from,
             *          since smaller multiples would have been marked by smaller primes already.
             *
             * - ((low + i - 1) / i) * i:
             *   This expression finds the smallest multiple of i that is >= low.
             *   Let's break it down:
             *     (low + i - 1) / i: integer division that rounds up low/i
             *     Multiplying by i gives the actual multiple of i.
             *
             * We take the max of these two to ensure:
             * - We start from i*i at minimum (because numbers smaller than i*i are already handled),
             * - but if low is greater than i*i, we start from the smallest multiple of i >= low.
             * 
             *  Suppose i = 5, low = 12
            *   
            *   Step 1: i * i = 25
            *   Step 2: (low + i - 1) / i = (12 + 5 - 1) / 5 = 16 / 5 = 3 (integer division)
            *   Step 3: Multiply by i: 3 * 5 = 15
            *
            *   So, the smallest multiple of 5 greater than or equal to 12 is 15.
            *   
            *   Then we take the max of these two values:
            *   Math.max(25, 15) = 25
            *   
            *   Meaning we start marking multiples of 5 from 25 onwards because
            *   multiples less than 25 would have already been marked by smaller primes.
            *
            * - We take the max of these two to ensure:
            *   - We start from i*i at minimum (because numbers smaller than i*i are already handled),
            *   - but if low is greater than i*i, we start from the smallest multiple of i >= low.
                        */
            int highestEnd = Math.max(i * i, ((low + i - 1) / i) * i);

            // Mark all multiples of i in [highestEnd, high] as not prime
            for (int j = highestEnd; j <= high; j += i) {
                vis[j] = true; // Mark as not prime
            }
        }

        // After marking, all indices which are false are primes in [low, high]
        for (int i = low; i <= high; i++) {
            if (!vis[i]) {
                System.out.print(i + " ");
            }
        }
    }

    public static void main(String[] args) {
        segmentedSieve(1, 20);
    }
}
