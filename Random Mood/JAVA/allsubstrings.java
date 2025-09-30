public class allsubstrings {
    static void strings(String s) {
        int len = s.length();

        for (int i = 0; i < len; i++) {
           
            for (int j = 0; j < len - i; j++) {
                StringBuilder str = new StringBuilder();
                for (int k = j; k <= j+i ; k++) {
                    str.append(s.charAt(k));
                }
                System.out.println(str);
            }
            
        }
    }

    public static void main(String[] args) {
        strings("babad");
    }
}
