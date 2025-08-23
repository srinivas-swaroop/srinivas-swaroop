import java.util.*;
import java.util.stream.Stream;
class subratogmaticnumber {
    static boolean subtoNum(String num){
        HashMap<Character, Character> map = new HashMap<>();
        map.put('6','9');
        map.put('6','9');
        map.put('8','8');
        map.put('1','1');
        map.put('0','0');

        int left = 0;
        int right = num.length()-1;

        while(right>=left){
            if(map.containsKey(num.charAt(left))==false) return false;
            if(map.get(num.charAt(left)) != num.charAt(right)) return false;

            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(subtoNum("102"));
    }
}
