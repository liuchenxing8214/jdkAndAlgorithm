package algorithm.package25;

import lombok.Data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MinNumOfCoin_04 {


    // 为了测试
/*        public static void main(String[] args) {

            System.out.println("功能测试开始");

            int[] arr = {1, 1, 1, 2, 2, 5};
            int aim = 11; // 目标金额
            int ans1 = getMinNumOfCoin(arr, aim);
            int ans2 = dp1(arr, aim);
            if (ans1 != ans2 ) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
            }

        System.out.println("功能测试结束");
    }*/
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
            int ans1 = getMinNumOfCoin(arr, aim);
            int ans2 = dp(arr, aim);
            int ans3 = dpAndWindow(arr, aim);
            int ans4 = dp3(arr, aim);
            if (ans1 != ans2 || ans3 != ans4 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println(ans4);
                break;
            }
        }
        System.out.println("功能测试结束");

        int aim = 0;
        int[] arr = null;
        long start;
        long end;
        int ans2;
        int ans3;

        System.out.println("性能测试开始");
        maxLen = 30000;
        maxValue = 20;
        aim = 60000;
        arr = randomArray(maxLen, maxValue);

/*
        start = System.currentTimeMillis();
        ans2 = getMinNumOfCoin(arr, aim);
        end = System.currentTimeMillis();
        System.out.println("getMinNumOfCoin答案 : " + ans2 + ", getMinNumOfCoin运行时间 : " + (end - start) + " ms");
*/

        start = System.currentTimeMillis();
        ans3 = dp(arr, aim);
        end = System.currentTimeMillis();
        System.out.println(" dp运行时间 : " + (end - start) + " ms");
        System.out.println("性能测试结束");

        System.out.println("===========");

        System.out.println("货币大量重复出现情况下，");
        System.out.println("大数据量测试dp3开始");
        maxLen = 20000000;
        aim = 10000;
        maxValue = 10000;
        arr = randomArray(maxLen, maxValue);
        start = System.currentTimeMillis();
        ans3 = dpAndWindow(arr, aim);
        end = System.currentTimeMillis();
        System.out.println("dpAndWindow运行时间 : " + (end - start) + " ms");
        System.out.println("大数据量测试dpAndWindow结束");

        System.out.println("===========");

        System.out.println("当货币很少出现重复，dp2比dp3有常数时间优势");
        System.out.println("当货币大量出现重复，dp3时间复杂度明显优于dp2");
        System.out.println("dp3的优化用到了窗口内最小值的更新结构");
    }


    // 为了测试
    public static int[] randomArray(int N, int maxValue) {
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

    public static int getMinNumOfCoin(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        return process(info.coins, info.pieces, 0, aim);
    }


    /**
     * @param coins  纸币面值数组
     * @param pieces 纸币面值对应的张数
     * @param index  到了第index纸币做决策的时候
     * @param rest   组成rest的方法数
     * @return 从index及后面做决策的时候,
     */
    private static int process(int[] coins, int[] pieces, int index, int rest) {
        // 基本情况：如果aim为0，返回0
        if (rest == 0) {
            return 0;
        }
        // 如果aim小于0，返回Integer.MAX_VALUE（表示不可达）
        if (rest < 0 || index >= coins.length) {
            return Integer.MAX_VALUE;
        }
        //轮到该货币选择多少张纸币
        int ways = Integer.MAX_VALUE;
        for (int piece = 0; piece <= pieces[index]; piece++) {
            int p2 = process(coins, pieces, index + 1, rest - piece * coins[index]);
            if (p2 != Integer.MAX_VALUE) {
                p2 = p2 + piece;
            }
            ways = Math.min(ways, p2);
        }
        return ways;
    }

    /**
     * 用动态规划来改写【枚举行为没有被替换掉】
     *
     * @param arr 纸币数组
     * @param aim 组成目标数
     * @return
     */

    public static int dp(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] pieces = info.pieces;
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                //选择当前的纸币的张数为0时
                dp[index][rest] = dp[index + 1][rest];
                for (int piece = 1; piece <= pieces[index] && (rest - piece * coins[index] >= 0); piece++) {
                    if (dp[index + 1][rest - piece * coins[index]] != Integer.MAX_VALUE) {
                        dp[index][rest] = Math.min(dp[index][rest], (piece + dp[index + 1][rest - piece * coins[index]]));
                    }
                }
            }
        }
        return dp[0][aim];
    }

    /**
     * 用动态规划来改写【用窗口函数来替换掉枚举行为】
     *
     * @param arr 纸币数组
     * @param aim 组成目标数
     * @return
     */
    public static int dpAndWindow(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] pieces = info.pieces;
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            //假如当前面值为X元【提高计算相关性】
            //mod mod+X  mod+2*X  mod+3*X .....
            for (int mod = 0; mod < Math.min(aim + 1, coins[index]); mod++) {
                //最小值更新结构,里面存的是面值,根据之前存入的面值,可以找到与之对应的最小张数
                LinkedList<Integer> qMin = new LinkedList<>();
                qMin.add(mod);
                dp[index][mod] = dp[index + 1][mod];
                for (int r = mod + coins[index]; r <= aim; r = r + coins[index]) {
                    while (!qMin.isEmpty() && (dp[index + 1][qMin.peekLast()] == Integer.MAX_VALUE ||
                            dp[index + 1][r] <= (dp[index + 1][qMin.peekLast()]
                                    + transOffsetPiece(qMin.peekLast(), r, coins[index])))) {
                        qMin.pollLast();
                    }
                    qMin.addLast(r);
                    int overdue = r - (pieces[index] + 1) * coins[index];
                    if (qMin.peekFirst() == overdue) {
                        qMin.pollFirst();
                    }
                    dp[index][r] = dp[index + 1][qMin.peekFirst()] + transOffsetPiece(qMin.peekFirst(), r, coins[index]);
                }
            }
        }
        return dp[0][aim];
    }

    // dp3时间复杂度为：O(arr长度) + O(货币种数 * aim)
    // 优化需要用到窗口内最小值的更新结构
    public static int dp3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        // 得到info时间复杂度O(arr长度)
        Info info = getInfo(arr);
        int[] c = info.coins;
        int[] z = info.pieces;
        int N = c.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        // 虽然是嵌套了很多循环，但是时间复杂度为O(货币种数 * aim)
        // 因为用了窗口内最小值的更新结构
        for (int i = N - 1; i >= 0; i--) {
            for (int mod = 0; mod < Math.min(aim + 1, c[i]); mod++) {
                // 当前面值 X
                // mod  mod + x   mod + 2*x   mod + 3 * x
                LinkedList<Integer> w = new LinkedList<>();
                w.add(mod);
                dp[i][mod] = dp[i + 1][mod];
                for (int r = mod + c[i]; r <= aim; r += c[i]) {
                    while (!w.isEmpty() && (dp[i + 1][w.peekLast()] == Integer.MAX_VALUE
                            || dp[i + 1][w.peekLast()] + compensate(w.peekLast(), r, c[i]) >= dp[i + 1][r])) {
                        w.pollLast();
                    }
                    w.addLast(r);
                    int overdue = r - c[i] * (z[i] + 1);
                    if (w.peekFirst() == overdue) {
                        w.pollFirst();
                    }
                    dp[i][r] = dp[i + 1][w.peekFirst()] + compensate(w.peekFirst(), r, c[i]);
                }
            }
        }
        return dp[0][aim];
    }

    public static int compensate(int pre, int cur, int coin) {
        return (cur - pre) / coin;
    }


    public static int transOffsetPiece(int preCoin, int curCoin, int coin) {
        return (curCoin - preCoin) / coin;
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
