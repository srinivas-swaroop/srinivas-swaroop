class _3phi {
    public static int phi(int n) {
        int result = n;  // Initialize result as n
        int temp = n;    // Use a temporary variable to check prime factors without modifying n
        
        /*
         * Euler's Totient formula:
         * If n = p1^a1 * p2^a2 * ... * pk^ak (prime factorization),
         * then
         * phi(n) = n * (1 - 1/p1) * (1 - 1/p2) * ... * (1 - 1/pk)
         *
         * We implement this by iterating over all possible prime factors p,
         * and for each prime factor found, we multiply result by (1 - 1/p),
         * which is equivalent to result -= result/p.
         */
        
        for (int p = 2; p * p <= temp; p++) {
            if (temp % p == 0) {  // p is a prime factor of n
                
                // Remove all factors of p from temp (to avoid counting p multiple times)
                while (temp % p == 0) {
                    temp /= p;  // Equivalent to temp = temp / p;
                }
                
                // Apply the formula: result = result * (1 - 1/p)
                // Equivalent to: result -= result / p;
                result -= result / p;
            }
        }
        
        // If temp > 1, it means temp itself is a prime number greater than sqrt(n)
        // Apply formula one last time for this prime factor
        if (temp > 1) {
            result -= result / temp;
        }
        
        return result;
    }

    public static void main(String[] args) {
        int n = 36;
        System.out.println("phi(" + n + ") = " + phi(n));
    }
}

/*
Dry Run for n = 36:

- Initially: result = 36, temp = 36
- Loop p from 2 to sqrt(temp):
  
  p = 2:
    temp % 2 == 0? Yes
    Remove factors of 2:
      temp = 36 / 2 = 18
      temp = 18 / 2 = 9  (9 % 2 != 0, stop)
    Update result:
      result = result - result/2 = 36 - 18 = 18

  p = 3:
    temp = 9
    temp % 3 == 0? Yes
    Remove factors of 3:
      temp = 9 / 3 = 3
      temp = 3 / 3 = 1  (1 % 3 != 0, stop)
    Update result:
      result = result - result/3 = 18 - 6 = 12

- End loop (since p * p > temp)
- temp = 1, no further prime factor

Return result = 12

Explanation:
- 36 = 2^2 * 3^2
- phi(36) = 36 * (1 - 1/2) * (1 - 1/3) = 36 * 1/2 * 2/3 = 12

---

Time Complexity:
- The outer loop runs up to sqrt(n).
- Inside the loop, we divide out prime factors completely, each division reducing temp.
- Total complexity is approximately O(sqrt(n)), efficient for typical inputs.
*/
