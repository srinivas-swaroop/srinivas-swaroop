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

    public static void main(String[] args) {
        int div[] = {3, 4, 5};  
        int rem[] = {2, 3, 1};   
        int size = 3;

        System.out.println(crtfinder(div, rem, size));
    }
}