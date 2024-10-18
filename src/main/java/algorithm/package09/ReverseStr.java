package algorithm.package09;

public class ReverseStr {

    public static void main(String[] args) {
        String str = "beautiful";
        System.out.println(reverse(str));

    }


    public static String reverse(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        return reverse(str.substring(1)) + str.charAt(0);
    }
}
