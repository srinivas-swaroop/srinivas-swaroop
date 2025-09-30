import java.util.*;

class crt{
    static int crtfinder(int div[], int rem[], int size){
        int count = 0;

        while(true){
            for(int i=0; i<size; i++){
                if(count%div[i]!=rem[i]) break;
                if(i == size - 1) return count;
            }

            count++;
        }
    }

    static boolean binary(int x){
        int num = x;
        int reverse = 0;

        while(num > 0){
            reverse = reverse << 1;
            reverse = reverse + (num & 1);
            num = num >> 1;
        }

        return x==reverse;
    }

    public static void main(String[] args) {
        int div[] = {3, 4, 5};  
        int rem[] = {2, 3, 1};   
        int size = 3;

        //System.out.println(crtfinder(div, rem, size));

        System.out.println(binary(17));
    }
}