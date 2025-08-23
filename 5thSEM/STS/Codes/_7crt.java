public class _7crt {
    
    // Function to find the smallest number 'j' such that:
    // j % div[i] == rem[i] for all i (0 <= i < size)
    static int chineseRemainderTheorem(int div[], int rem[], int size){
        int j = 1;   // Start checking from 1 (can also start from 0)

        // Infinite loop until we find a valid solution
        while(true){
            // Check current number 'j' against all congruences
            for(int i = 0; i < size; i++){
                
                // If j does not satisfy current congruence, break out
                if(j % div[i] != rem[i]) 
                    break;

                // If we reached the last congruence and it's satisfied,
                // then j satisfies ALL conditions → return result
                if(i == size - 1) 
                    return j;
            }
            j++;  // Try next number
        }
    }

    public static void main(String[] args) {
        // Example:
        // Solve the system:
        // x ≡ 2 (mod 3)
        // x ≡ 3 (mod 4)
        // x ≡ 1 (mod 5)
        
        int div[] = {3, 4, 5};   // Divisors (moduli)
        int rem[] = {2, 3, 1};   // Remainders
        int size = 3;

        // Call function
        System.out.println(chineseRemainderTheorem(div, rem, size));
    }
}
