package algorithm.package20;

public class NumberStrCovertAlphabet {
    public static void main(String[] args) {
        String numStr = "7210231231232031203123";
        char[] chars = numStr.toCharArray();
        System.out.println(ways(chars));
        System.out.println(dp(chars));
    }

    public static int ways(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }
        return process(chars, 0);
    }

    //i-1及以前的字符不管，i位置字符及以后字符有多少中转换方式
    public static int process(char[] chars, int i) {
        int N = chars.length;
        //字符转换刚刚越界,表示有一种转换方式
        if (i == N) {
            return 1;
        }
        //如果当前i位置字符为'0'元素,表示之前的选择方案都是无效的，才会让我独自面对'0'字符【因为'0'字符没法单独进行转换成英文字母】
        if (chars[i] == '0') {
            return 0;
        }
        //如果当前i位置字符不是'0'元素,可以直接将i位置字符的1~9数字转换成字母
        int way = process(chars, i + 1);
        //直接将i位置和i+1位置合在一起转成一个字母【条件1：i+1没有越界；条件2：i位置和i+1位置合在一起组合在一构成的数字要小于27】
        if (i + 1 < N && (chars[i] - '0') * 10 + (chars[i + 1] - '0') < 27) {
            way = way + process(chars, i + 2);
        }
        return way;
    }

    public static int dp(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }
        int N = chars.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            if (chars[i] != '0') {
                //如果当前i位置字符不是'0'元素,可以直接将i位置字符的1~9数字转换成字母
                int way = dp[i + 1];
                //直接将i位置和i+1位置合在一起转成一个字母【条件1：i+1没有越界；条件2：i位置和i+1位置合在一起组合在一构成的数字要小于27】
                if (i + 1 < N && (chars[i] - '0') * 10 + (chars[i + 1] - '0') < 27) {
                    way = way + dp[i + 2];
                }
                dp[i] = way;
            }
        }
        return dp[0];
    }

}
