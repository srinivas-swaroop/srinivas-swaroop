Excellent question 👍 — this one combines **two classic dynamic programming (DP)** problems:

1. **Finding the Longest Common Subsequence (LCS)** between two DNA sequences.
2. **Counting how many times that LCS appears as a subsequence** in the first genome.

Let’s break it step-by-step, covering **concepts, intuition, code explanation, and example dry run**.

---

## 🧠 **1. Concept: Longest Common Subsequence (LCS)**

The **LCS** of two strings is the longest sequence of characters that appears in both strings **in the same order**, but not necessarily contiguously.

Example:

```
genome1 = ABCDGH
genome2 = AEDFHR
LCS = ADH
Length = 3
```

---

## 💡 **2. Intuition Behind the Code**

We’ll use **Dynamic Programming (DP)** to:

1. **Find the LCS length and string**
2. **Count its occurrences in genome1**

---

### **Step 1: Finding the LCS**

We use a 2D table `dp[i][j]` where:

* `dp[i][j]` = length of the LCS of `s1[0..i-1]` and `s2[0..j-1]`

**Recurrence relation:**

```
If s1[i-1] == s2[j-1]:
    dp[i][j] = dp[i-1][j-1] + 1
Else:
    dp[i][j] = max(dp[i-1][j], dp[i][j-1])
```

This ensures we build the LCS length from smaller substrings.

Once the table is filled, `dp[m][n]` gives the **length of the LCS**.

To get the **LCS string**, we **backtrack** from the bottom-right corner:

* If characters match → add to LCS and move diagonally up-left.
* Else move in the direction of the larger value.

---

### **Step 2: Counting LCS Occurrences in `genome1`**

Once we have the LCS string, say `"ADH"`,
we count **how many subsequences in genome1 form “ADH”**.

We again use a 2D DP table `dp[i][j]` where:

* `dp[i][j]` = number of ways first `j` characters of pattern appear as a subsequence in first `i` characters of text.

**Base Case:**

```
dp[i][0] = 1   // empty pattern always appears once
```

**Transition:**

```
If text[i-1] == pattern[j-1]:
    dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
Else:
    dp[i][j] = dp[i-1][j]
```

This ensures:

* We can skip the current character.
* Or include it if it matches.

Final answer: `dp[n][m]`

---

## 💻 **Step-by-Step Code (Explained in Comments)**

```java
import java.util.*;

class Priya {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String genome1 = sc.nextLine();  // first DNA sequence
        String genome2 = sc.nextLine();  // second DNA sequence
        sc.close();

        // Step 1: Find LCS string
        String lcsString = findLCSString(genome1, genome2);
        int lcsLength = lcsString.length();

        // Step 2: Count its occurrences in genome1
        long occurrenceCount = countSubsequenceOccurrences(genome1, lcsString);

        // Output
        System.out.println(lcsLength);
        System.out.println(occurrenceCount);
        System.out.println(lcsString);
    }

    // Function to find the actual LCS string using DP
    public static String findLCSString(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Fill DP table for LCS length
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        // Backtrack to get the LCS string
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;

        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs.append(s1.charAt(i - 1));
                i--; j--;
            } else if (dp[i - 1][j] > dp[i][j - 1])
                i--;
            else
                j--;
        }

        return lcs.reverse().toString();
    }

    // Function to count how many times LCS appears as a subsequence in genome1
    public static long countSubsequenceOccurrences(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        long[][] dp = new long[n + 1][m + 1];

        // Empty pattern appears once
        for (int i = 0; i <= n; i++)
            dp[i][0] = 1;

        // Fill DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];
                if (text.charAt(i - 1) == pattern.charAt(j - 1))
                    dp[i][j] += dp[i - 1][j - 1];
            }
        }

        return dp[n][m];
    }
}
```

---

## 🔍 **Debug Example**

### Input:

```
ABCDGH
AEDFHR
```

### Step 1: LCS Table

```
dp table filled results in dp[6][6] = 3
LCS = "ADH"
```

### Step 2: Counting "ADH" in "ABCDGH"

We check all subsequences of genome1 that match `"ADH"`:

* Only one subsequence matches → count = **1**

✅ Output:

```
3
1
ADH
```

---

### Another Example

**Input:**

```
AAAAAA
AAXAAA
```

**Step 1:**
LCS = "AAAAA"
Length = 5

**Step 2:**
Count of "AAAAA" in "AAAAAA":

* There are **6 ways** to pick 5 A’s out of 6.

✅ Output:

```
5
6
AAAAA
```

---

## ⏱ **Complexity Analysis**

| Step              | Time     | Space    |
| ----------------- | -------- | -------- |
| Find LCS          | O(n × m) | O(n × m) |
| Count occurrences | O(n × m) | O(n × m) |

✅ Efficient for input ≤ 1000

---

Would you like me to also show **a dry run table visualization** (for the first example) — showing how each cell of the LCS DP matrix gets filled and how the string is formed?

---

Perfect 👍 — Let’s break this problem down **conceptually, intuitively, and then line by line** with an **example debug** to understand how the **Longest Increasing Subsequence (LIS)** dynamic programming approach works.

---

## 🧠 **Concept: Longest Increasing Subsequence (LIS)**

Given an array, the **LIS** is the longest subsequence (not necessarily contiguous) in which each element is **strictly greater** than the previous one.

👉 A **subsequence** is a sequence that can be derived from the array by deleting some or no elements without changing the order.

For example:

```
Array: [5, 4, 11, 1, 16, 8]
Possible increasing subsequences:
→ [5, 11, 16]
→ [4, 11, 16]
→ [1, 16]
Longest is [5, 11, 16] or [4, 11, 16] → length = 3
```

---

## 💡 **Intuition Behind Dynamic Programming**

Brute force would check all subsequences (which is exponential `O(2^n)`).

Instead, we use **Dynamic Programming (DP)** to build solutions to subproblems:

* Let `dp[i]` = length of the **LIS ending at index `i`**.

That means `dp[i]` considers `arr[i]` as the last element of the subsequence.

To compute it:

* For every `j < i`,
  if `arr[j] < arr[i]`,
  then we can extend the subsequence ending at `j` by adding `arr[i]`.

So:

```
dp[i] = max(dp[i], dp[j] + 1)   // for all j < i and arr[j] < arr[i]
```

Finally:

```
maxLength = max(dp[i]) for all i
```

---

## 🧩 **Step-by-Step Code Explanation**

```java
import java.util.*;

class SubSequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();             // Input size
        int[] arr = new int[n];           
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();        // Input array elements
        }

        int result = longestIncreasingSubsequence(arr);
        System.out.println("Length of the longest increasing subsequence: " + result);
    }

    public static int longestIncreasingSubsequence(int[] arr) {
        int n = arr.length;
        if (n == 0)
            return 0;

        int[] dp = new int[n];
        int maxLength = 1;

        // Initially, each element alone is an increasing subsequence
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        // Build LIS using previous smaller elements
        for (int i = 1; i < n; i++) {          // For every element
            for (int j = 0; j < i; j++) {      // Check all previous elements
                if (arr[i] > arr[j]) {         // If increasing
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]); // Track global max
        }

        return maxLength;
    }
}
```

---

## 🧮 **Dry Run Example**

### Input:

```
6
5 4 11 1 16 8
```

### Step-by-step DP table:

| i | arr[i] | Compare with previous                                                           | dp[i] | Explanation                                   |
| - | ------ | ------------------------------------------------------------------------------- | ----- | --------------------------------------------- |
| 0 | 5      | -                                                                               | 1     | First element alone forms a subsequence       |
| 1 | 4      | (4 < 5 ❌)                                                                       | 1     | 4 cannot follow 5                             |
| 2 | 11     | (11>5 ✅ → dp[0]+1=2), (11>4 ✅ → dp[1]+1=2)                                      | 2     | Best subsequence ends at 11: [5,11] or [4,11] |
| 3 | 1      | (1<5 ❌), (1<4 ❌), (1<11 ❌)                                                      | 1     | Can’t extend any                              |
| 4 | 16     | (16>5 ✅ dp[0]+1=2), (16>4 ✅ dp[1]+1=2), (16>11 ✅ dp[2]+1=3), (16>1 ✅ dp[3]+1=2) | 3     | Extends [5,11,16]                             |
| 5 | 8      | (8>5 ✅ dp[0]+1=2), (8>4 ✅ dp[1]+1=2), (8<11 ❌), (8>1 ✅ dp[3]+1=2), (8<16 ❌)     | 2     | Extends [5,8] or [4,8]                        |

Final `dp` array → `[1, 1, 2, 1, 3, 2]`

Max = **3**

✅ Output:

```
Length of the longest increasing subsequence: 3
```

---

## 🧩 **Time Complexity**

* **O(N²)** — Nested loops (`i` and `j`).
* Works fine for N ≤ 337 (as per constraints).

## 🧠 **Brute Force vs DP**

| Approach     | Time Complexity | Idea                                              |
| ------------ | --------------- | ------------------------------------------------- |
| Brute Force  | O(2ⁿ)           | Generate all subsequences and check if increasing |
| DP (Current) | O(n²)           | Build LIS lengths from smaller subproblems        |

---

Would you like me to also show the **Brute Force version in Java (recursive)** for conceptual comparison (with explanation)?
