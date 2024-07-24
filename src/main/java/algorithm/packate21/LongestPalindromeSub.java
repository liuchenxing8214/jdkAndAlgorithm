package algorithm.packate21;

public class LongestPalindromeSub {

    public static void main(String[] args) {
        String str = "bbbab";
        System.out.println(longestPalindromeSubseq(str));

    }

    public static int longestPalindromeSubseq(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        // return process(chars, 0, chars.length - 1);
        return dp(chars);
    }

    /**
     * @param chars
     * @param L
     * @param R
     * @return chars[L到R]返回最长回文子序列
     */
    public static int process(char[] chars, int L, int R) {
        if (L == R - 1) {
            return chars[L] == chars[R] ? 2 : 1;
        }
        if (L == R) {
            return 1;
        }
        //(1) L X  R X
        //(2) L X  R ✓
        //(3) L ✓  R X
        //(4) L ✓  R ✓ (条件是L和R位置上的字符相等)
        int p1 = process(chars, L + 1, R - 1);
        int p2 = process(chars, L + 1, R);
        int p3 = process(chars, L, R - 1);
        int p4 = chars[L] == chars[R] ? (2 + process(chars, L + 1, R - 1)) : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    //L:0~N-1, R:0~N-1
    public static int dp(char[] chars) {
        int N = chars.length;
        int[][] dp = new int[N][N];
        dp[N - 1][N - 1] = 1;
        for (int row = 0; row < N - 1; row++) {
            dp[row][row] = 1;
            dp[row][row + 1] = chars[row] == chars[row + 1] ? 2 : 1;
        }
        //依赖关系：依赖左,左下,下
        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                int p1 = dp[L + 1][R - 1];
                int p2 = dp[L + 1][R];
                int p3 = dp[L][R - 1];
                int p4 = chars[L] == chars[R] ? (2 + dp[L + 1][R - 1]) : 0;
                dp[L][R] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            }
        }
        return dp[0][N - 1];
    }
}
