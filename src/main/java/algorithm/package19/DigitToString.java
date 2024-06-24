package algorithm.package19;

public class DigitToString {
    public static void main(String[] args) {
        String numberStr = "12345";
        for (char c : numberStr.toCharArray()) {
            int a = (c - '0');
            System.out.println(c == '1');
            System.out.println(a);
        }

        String english = "abbcccdddddeeeeez";
        char[] str1 = english.toCharArray();
        int[] count = new int[26];
        for (char str : str1) {
            count[str - 'a']++;
        }
        System.out.println(count);
        char b = (char) ('a' + 0);
        System.out.println(b);


    }
}