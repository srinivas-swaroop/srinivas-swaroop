import java.util.Scanner;

public class _7crt {
    static int calculate(int size , int div[], int rem[]) {
        int j , x = 1;   // Start searching from x = 1

        while(true) {  // Keep checking until we find a solution
            for(j = 0; j < size; j++) {
                // If for any divisor, remainder doesn't match, break
                if(x % div[j] != rem[j])
                    break;
            }
            // If j reached end → all congruences satisfied
            if(j == size)
                return x;  

            x++;  // Otherwise, try next number
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Take input for number of equations
        System.out.println("Enter number of equations:");
        int n = sc.nextInt();

        int[] div = new int[n];   // divisors (mod values)
        int[] rem = new int[n];   // remainders

        // Input divisors
        System.out.println("Enter divisors:");
        for(int i = 0; i < n; i++) {
            div[i] = sc.nextInt();
        }

        // Input remainders
        System.out.println("Enter remainders:");
        for(int i = 0; i < n; i++) {
            rem[i] = sc.nextInt();
        }

        System.out.println("Smallest number satisfying all congruences = " + calculate(n, div, rem));

        
}
}


    
