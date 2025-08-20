import java.util.*;

public class StrobogrammaticNumber {

    /**
     * Function to check whether a given number is Strobogrammatic.
     * 
     * A number is Strobogrammatic if it looks the same when rotated 180 degrees.
     * Valid digit mappings:
     *  0 ↔ 0
     *  1 ↔ 1
     *  6 ↔ 9
     *  9 ↔ 6
     *  8 ↔ 8
     * 
     * Example:
     *  "69" → true (rotates to "96")
     *  "88" → true (rotates to "88")
     *  "962" → false (rotates to "296", not the same)
     * 
     * Time Complexity: O(n) → we check each digit once (where n = length of number string).
     * Space Complexity: O(1) → HashMap has fixed size (5 entries) and no extra space grows with input.
     */
    public static boolean isStrobogrammatic(String num) {
        // Step 1: Define valid rotations using a HashMap
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('9', '6');
        map.put('8', '8');

        // Step 2: Use two pointers (start from both ends of the string)
        int left = 0;
        int right = num.length() - 1;

        // Step 3: Check characters until pointers meet
        while (left <= right) {
            char l = num.charAt(left);
            char r = num.charAt(right);

            // If left digit is not valid, return false immediately
            if (!map.containsKey(l)) {
                return false;
            }

            // If rotated left digit does not equal the right digit → not strobogrammatic
            if (map.get(l) != r) {
                return false;
            }

            // Move pointers towards the center
            left++;
            right--;
        }

        // Step 4: If loop finishes, number is strobogrammatic
        return true;
    }

    // Driver code
    public static void main(String[] args) {
        // Test cases
        System.out.println(isStrobogrammatic("69"));   // true
        System.out.println(isStrobogrammatic("88"));   // true
        System.out.println(isStrobogrammatic("962"));  // false
        System.out.println(isStrobogrammatic("818"));  // true
        System.out.println(isStrobogrammatic("2"));    // false
    }
}
