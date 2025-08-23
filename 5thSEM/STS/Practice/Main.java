public class Main
{   public static boolean binaryPalindrome(int num){
    int reversed = 0;
    int original = num;
    
    while(original > 0){
        reversed = reversed + (original & 1);
        original = original >> 1;
        if(original > 0){
        reversed = reversed << 1;
        }
    }
    
    if(num == reversed) return true;
    
    return false;
}
	public static void main(String[] args) {
		System.out.println(binaryPalindrome(5));
	}
}