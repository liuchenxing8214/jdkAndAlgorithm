package algorithm.package22;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 每种相等的
 */
public class LimitedCoins_04 {


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


    // 为了测试
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 20;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = ways(arr, aim);
            int ans2 = ways1(arr, aim);
            int ans3 = ways2(arr, aim);
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


    /**
     * @param arr 数组中每种相等的面值都认为时一种面值,没有什么不同。
     * @param aim 组成aim。
     * @return
     */
    public static int ways(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0 || aim == 0) {
            return 0;
        }
        Info info = getInfo(arr);
        return process(info.getCoins(), info.getPieces(), 0, aim);
    }

    private static Info getInfo(int[] arr) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        //统计每一种纸币面值的数量
        for (int num : arr) {
            if (!hashMap.containsKey(num)) {
                hashMap.put(num, 1);
            } else {
                hashMap.put(num, (hashMap.get(num) + 1));
            }
        }
        int N = hashMap.size();
        int[] coins = new int[N];      //纸币的面值数组
        int[] pieces = new int[N];     //纸币面值对应的数量
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            coins[index] = entry.getKey();
            pieces[index] = entry.getValue();
            index++;
        }
        Info info = new Info(coins, pieces);
        return info;
    }

    /**
     * @param coins  纸币的面值数组【数组中每种相等的面值都认为时一种面值,没有什么不同】
     * @param pieces 各种纸币面值的数量
     * @param index  轮到index纸币做决策了,从index开始,到后面的所有纸币做决策,返回组成aim的方法数
     * @param rest   组成rest
     * @return 返回组成rest的方法数
     */
    public static int process(int[] coins, int[] pieces, int index, int rest) {
        //rest < 0这中情况被过滤了。【所以不存在】
        //rest等于0,之前决定找到了一种组成aim的方法
        if (rest == 0) {
            return 1;
        }
        //边界条件:没有纸币了
        int N = coins.length;
        if (index == N) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int piece = 0; piece <= pieces[index] && piece * coins[index] <= rest; piece++) {
            ways += process(coins, pieces, index + 1, rest - piece * coins[index]);
        }
        return ways;
    }


    /**
     * @param arr 数组中每种相等的面值都认为时一种面值,没有什么不同。
     * @param aim 组成aim。
     * @return
     */
    public static int ways1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0 || aim == 0) {
            return 0;
        }
        Info info = getInfo(arr);
        return dp1(info.getCoins(), info.getPieces(), aim);
    }


    /**
     * @param coins  纸币的面值数组
     * @param pieces 纸币面值对应的数量
     * @param aim    目标组成aim
     * @return 动态优化【返回组成aim的方法数】
     */
    public static int dp1(int[] coins, int[] pieces, int aim) {
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int piece = 0; piece <= pieces[index] && piece * coins[index] <= rest; piece++) {
                    ways += dp[index + 1][rest - piece * coins[index]];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }


    /**
     * @param arr 数组中每种相等的面值都认为时一种面值,没有什么不同。
     * @param aim 组成aim。
     * @return
     */
    public static int ways2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0 || aim == 0) {
            return 0;
        }
        Info info = getInfo(arr);
        return dp2(info.getCoins(), info.getPieces(), aim);
    }

    /**
     * @param coins  纸币的面值数组
     * @param pieces 纸币面值对应的数量
     * @param aim    目标组成aim
     * @return 最终优化的版本【返回组成aim的方法数】
     */
    public static int dp2(int[] coins, int[] pieces, int aim) {
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                //dp[index][rest]依赖下和左下的数量为pieces[index]
                dp[index][rest] = dp[index + 1][rest];
                //如果左存在,即左没有越界
                if (rest - coins[index] >= 0) {
                    dp[index][rest] += dp[index][rest - coins[index]];
                }
                //如果甲存在,即甲没有发生越界
                if (rest - (pieces[index] + 1) * coins[index] >= 0) {
                    dp[index][rest] = dp[index][rest] - dp[index + 1][rest - (pieces[index] + 1) * coins[index]];
                }
            }
        }
        return dp[0][aim];
    }


    @Data
    public static class Info {
        private int[] coins; //纸币的面值数组
        private int[] pieces;//纸币面值对应的数量

        public Info(int[] coins, int[] pieces) {
            this.coins = coins;
            this.pieces = pieces;
        }
    }


}
