public class _6binarypalindrome {

    /**
     * Function to check if the binary representation of a number
     * is a palindrome (reads the same forward and backward).
     *
     * Example:
     *  9  -> binary "1001" -> Palindrome ✅
     *  6  -> binary "110"  -> Not palindrome ❌
     */
    static boolean binaryPalindrome(int num) {
        int original = num;   // Keep original number (for final comparison)
        int reversed = 0;     // This will hold the reversed binary number

        // Process each bit of num until all bits are consumed
        while (num > 0) {
            // Step 1: Shift 'reversed' left by 1 to make room for the new bit
            reversed = reversed << 1;

            // Step 2: Copy the last bit of 'num' into 'reversed'
            // (num & 1) extracts the least significant bit of num
            reversed = reversed | (num & 1);

            // Step 3: Remove the last bit from num (right shift by 1)
            num = num >> 1;
        }

        // If the reversed binary equals the original number,
        // then its binary form is a palindrome
        return original == reversed;
    }

    public static void main(String[] args) {
        // 🔹 Use cases / test cases
        // Check if numbers have palindromic binary representations

        System.out.println(binaryPalindrome(9));   // true → 9 = 1001
        System.out.println(binaryPalindrome(5));   // true → 5 = 101
        System.out.println(binaryPalindrome(6));   // false → 6 = 110
        System.out.println(binaryPalindrome(1));   // true → 1 = 1
        System.out.println(binaryPalindrome(0));   // true → 0 = 0
        System.out.println(binaryPalindrome(21));  // true → 21 = 10101
    }
}
