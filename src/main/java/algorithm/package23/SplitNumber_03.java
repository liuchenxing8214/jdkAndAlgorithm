package algorithm.package23;


/**
 * 拆分整数
 * 给定一个正整数 n，将其拆分为 1 个或多个正整数的和，
 * 要求拆开的数后面的不能比前面的数小。返回最多有多少种拆分方式。
 */
public class SplitNumber_03 {
    public static void main(String[] args) {
        int test = 39;
        System.out.println(splitWays(test));
        System.out.println(dp(test));
        System.out.println(dp2(test));
    }

    public static int splitWays(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return process(1, n);

    }

    public static int process(int pre, int rest) {
        //当所拆的数为0时候,证明找到了一种拆数的方式。
        if (rest == 0) {
            return 1;
        }
        //如果拆开后面的数小于前面的数,表示无效的拆开方式，返回0
        if (rest < pre) {
            return 0;
        }
        //后面的数>=前面的数时候
        int ways = 0;
        for (int cur = pre; cur <= rest; cur++) {
            //现在的数变成下次拆数时的上一个数，剩余的数变成了下次要拆的数
            ways += process(cur, (rest - cur));
        }
        return ways;
    }


    public static int dp(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                int ways = 0;
                for (int cur = pre; cur <= rest; cur++) {
                    //现在的数变成下次拆数时的上一个数，剩余的数变成了下次要拆的数
                    ways += dp[cur][rest - cur];
                }
                dp[pre][rest] = ways;
            }
        }
        return dp[1][n];
    }


    public static int dp2(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[pre][rest] = dp[pre+1][rest]+ dp[pre][rest-pre];
            }
        }
        return dp[1][n];
    }


}
