package algorithm.package22;

public class CoinsWay_02 {

/*    public static void main(String[] args) {
        int[] coins = {6, 3, 3};
        System.out.println(coinsWay(coins, 6));
        System.out.println(coinsWay1(coins, 6));
    }*/

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinsWay(arr, aim);
            int ans2 = coinsWay1(arr, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
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


    public static int coinsWay(int[] coins, int aim) {
        if (coins == null || coins.length == 0) {
            return 0;
        }
        return process(coins, 0, aim);
    }

    public static int coinsWay1(int[] coins, int aim) {
        if (coins == null || coins.length == 0) {
            return 0;
        }
        return dp(coins, aim);
    }


    /**
     * @param coins 硬币数组
     * @param index 第index个硬币
     * @param rest  组成rest的目标
     * @return 从index开始做决策, 到后面所有的硬币结束, 组成aim的所有方法数
     * 【从左往右尝试模型,类似背包问题】
     */
    public static int process(int[] coins, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        //aim变成了0,则说明之前的所有决策找到了一种组成aim的方法。
        if (rest == 0) {
            return 1;
        }
        //边界条件:硬币用完了
        if (index == coins.length) {
            //所有的硬币都用完时,
            // (I)aim还没有变成0,则说明之前所有的决策都是失效的,组成aim的所有方法数。
            // (II)aim变成了0,则说明之前的所有决策找到了一种组成aim的方法。
            return rest == 0 ? 1 : 0;
        } else {
            //硬币还没有用完
            //(a)不要当前硬币,aim没有减少【依赖左下和下】
            int p1 = process(coins, index + 1, rest);
            //(a)不要当前硬币,aim减少coins[index]
            int p2 = process(coins, index + 1, rest - coins[index]);
            return (p1 + p2);
        }
    }

    public static int dp(int[] coins, int aim) {
        if (aim == 0) {
            return 1;
        }
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;  //dp[index][rest] 依赖左下和下,从下网上,从左往右。
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                //【依赖左下和下】
                dp[index][rest] = dp[index + 1][rest] +
                        ((rest - coins[index]) >= 0 ? dp[index + 1][rest - coins[index]] : 0);
            }
        }
        return dp[0][aim];
    }



}
