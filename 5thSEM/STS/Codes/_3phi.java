class _3phi {
    public int gcd(int a, int b){
      if(b<=0) return a;

      return gcd(b, a%b);
    }
    public static int phi(int n) {
        int count = 0;

        for(int i=0; i<=n; i++){
          _3phi ph = new _3phi();
          if(ph.gcd(n, i)==1) count++;
        }

        return count;
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
