public class _5booth {
    public static void main(String args[]){
        int a = 20;
        int b = 4;
        int prod = 0;

        int len = Integer.toBinaryString(a).length();

        for(int i=0; i<len; i++){
            int currentBit = a & 1;

            if(currentBit == 1) {
                prod = prod + (b << i);
            }

            a = a >> 1;
        }

        System.out.println(prod);  
    }
}
