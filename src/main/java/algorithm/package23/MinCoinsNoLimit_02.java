package algorithm.package23;

public class MinCoinsNoLimit_02 {

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = getMinCoinsNoLimit(arr, aim);
            int ans2 = dp(aim, arr);
            int ans3 = dp2(aim, arr);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println("ans1===" + ans1);
                System.out.println("ans2===" + ans2);
                System.out.println("ans2===" + ans3);
                break;
            }
        }
        System.out.println("功能测试结束");
    }


    public static int getMinCoinsNoLimit(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) {
            return 0;
        }
        return process(0, aim, arr);
    }

    //从index开始,及以后组成aim的最少货币数
    public static int process(int index, int aim, int[] arr) {
        //已经没有硬币了
        if (index == arr.length) {
            //看是已经搞定aim了,
            // (I)如果搞定了,还需要的货币数量时0张;
            // (II)如果没有搞定aim,说明走这个分支是无效的。【最大值表示无效】
            return aim == 0 ? 0 : Integer.MAX_VALUE;
        }
        //还有硬币,轮到index硬币做决策,可以用0张,1张,2张.....一直用到不超过aim的张数
        int minCoinNum = Integer.MAX_VALUE; // 表示找不到组成aim的货币,表示无效。
        for (int piece = 0; piece * arr[index] <= aim; piece++) {
            int next = process(index + 1, (aim - piece * arr[index]), arr);
            if (next != Integer.MAX_VALUE) {
                minCoinNum = Math.min(minCoinNum, (next + piece));
            }
        }
        return minCoinNum;
    }

    public static int dp(int aim, int[] arr) {
        if (arr == null || arr.length == 0 || aim == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;  //最大值表示无效
        }
        //任意一个位置依赖下和左下,从下往上开始填写
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int minCoinNum = Integer.MAX_VALUE; // 表示找不到组成aim的货币,表示无效。
                for (int piece = 0; piece * arr[index] <= rest; piece++) {
                    int next = dp[index + 1][(rest - piece * arr[index])];
                    if (next != Integer.MAX_VALUE) {
                        minCoinNum = Math.min(minCoinNum, (next + piece));
                    }
                }
                dp[index][rest] = minCoinNum;
            }
        }
        return dp[0][aim];
    }


    public static int dp2(int aim, int[] arr) {
        if (arr == null || arr.length == 0 || aim == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;  //最大值表示无效
        }
        //任意一个位置依赖下和左下,从下往上开始填写
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                //依赖的左边没有越界,而且左边存放的值是个有效值。
                if (rest - arr[index] >= 0 && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
                    dp[index][rest] = Math.min(dp[index][rest - arr[index]] + 1, dp[index][rest]);
                }
            }
        }
        return dp[0][aim];
    }

    public static int coinChange(int[] coins, int amount) {
        int result = dp2(amount, coins);
        return result == Integer.MAX_VALUE ? -1 : result;
    }


}
