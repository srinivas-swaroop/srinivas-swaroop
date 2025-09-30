class cide{
    public static void main(String args[]){
        String a = "abcd ";
        String b = "abcd";

        int i = 0;
        int j = 0;


        while(i < a.length() && j < b.length()){
            if(a.charAt(i)!=b.charAt(j)){
                System.out.print(a.charAt(i));
            }else{
                i++;
                j++;
            }
        }
    }
}