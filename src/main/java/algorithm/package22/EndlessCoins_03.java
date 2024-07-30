package algorithm.package22;

public class EndlessCoins_03 {


    public static int ways(int[] coins, int aim) {
        if (coins == null || coins.length == 0) {
            return 0;
        }
        return process(coins, 0, aim);
    }

    public static int ways2(int[] coins, int aim) {
        if (coins == null || coins.length == 0) {
            return 0;
        }
        return dp(coins, aim);
    }

    public static int ways3(int[] coins, int aim) {
        if (coins == null || coins.length == 0) {
            return 0;
        }
        return dp2(coins, aim);
    }

    /**
     * @param coins 纸币数组
     * @param index 等到index时候,在不超过rest的情况下,可以有多种选择,可以选择0张,1张,2张.....
     * @param rest  等到rest为0时,证明找了一种组成aim的方法
     * @return
     */
    public static int process(int[] coins, int index, int rest) {
        //等到rest为0时,证明找了一种组成aim的方法
        if (rest == 0) {
            return 1;
        }
        //边界条件:纸币用完了,aim还没变成,说明一种方法也没有找到。
        if (index == coins.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        //一个for循环构成一个格子。
        for (int piece = 0; piece * coins[index] <= rest; piece++) {
            ways += process(coins, index + 1, rest - piece * coins[index]);
        }
        return ways;
    }

    public static int dp(int[] coins, int aim) {
        if (coins == null || coins.length == 0) {
            return 0;
        }
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                //一个for循环构成一个格子。
                for (int piece = 0; piece * coins[index] <= rest; piece++) {
                    ways += dp[index + 1][rest - piece * coins[index]];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    /**
     *  用临近位置替换掉枚举行为(for循环时枚举行为)
     */
    public static int dp2(int[] coins, int aim) {
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                //由dp得来,分析过程如图 endlessCoinsOfAnalyse.jpg
                dp[index][rest] = dp[index + 1][rest];
                if ((rest - coins[index]) >= 0) {
                    dp[index][rest] += dp[index][rest - coins[index]];
                }
            }
        }
        return dp[0][aim];
    }


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
        int maxLen = 10;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = ways(arr, aim);
            int ans2 = ways2(arr, aim);
            int ans3 = ways3(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }


}
