package algorithm.package20;

public class LongestCommonSubsequence {

    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println(longestCommonSubsequence(text1, text2));
        System.out.println(longestCommonSubsequence2(text1, text2));
    }


    public static int longestCommonSubsequence(String str1, String str2) {
        if (str1 == null || str1.length() == 0 || str2 == null || str2.length() == 0) {
            return 0;
        }
        char[] strArr1 = str1.toCharArray();
        char[] strArr2 = str2.toCharArray();
        return process(strArr1, strArr2, strArr1.length - 1, strArr2.length - 1);
    }

    /**
     * 返回str1中的0到i位置和str2位置的字符串的0到j位置最长公共子序列是多少
     *
     * @param str1
     * @param str2
     * @param i
     * @param j
     * @return
     */
    public static int process(char[] str1, char[] str2, int i, int j) {
        if (i == 0 && j == 0) {
            return str1[0] == str2[0] ? 1 : 0;
        } else if (i == 0) {
            if (str1[0] == str2[j]) {
                return 1;
            } else {
                return process(str1, str2, i, j - 1);
            }
        } else if (j == 0) {
            if (str1[i] == str2[0]) {
                return 1;
            } else {
                return process(str1, str2, i - 1, j);
            }
        } else {
            //i!=0 &&j!=0
            //(I)可能考虑以i位置字符为结尾,绝对不考虑以j位置字符为结尾的可能性
            int p1 = process(str1, str2, i, j - 1);
            //(II)绝对不考虑以i位置字符为结尾,可能考虑以j位置字符为结尾的可能性
            int p2 = process(str1, str2, i - 1, j);
            //(III)既考虑以i位置字符为结尾,同时考虑以i位置字符为结尾的可能性,
            // 且判断str1[i]是等于str1[],如果不相等,证明这种可能不存在
            int p3 = str1[i] == str2[j] ? (1 + process(str1, str2, i - 1, j - 1)) : 0;
            return Math.max(p1, Math.max(p2, p3));
        }
    }

    public static int longestCommonSubsequence2(String text1, String text2) {
        if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) {
            return 0;
        }
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        //i=0,dp[i][j] 依赖 dp[i-1][j]和dp[i][j-1]和dp[i-1][j-1] 【dp的依赖关系】
        //先填充dp中第0行所有列的值,在填充dp中第0列所有行得值,在开始从左往右依次向下填充值。
        //i == 0
        for (int j = 1; j < M; j++) {
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
        }
        //j==0
        for (int i = 1; i < N; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int p1 = dp[i][j - 1];
                int p2 = dp[i - 1][j];
                int p3 = str1[i] == str2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[N - 1][M - 1];
    }


}
