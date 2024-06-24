package algorithm.package20;

public class bag {

    public static void main(String[] args) {
   /*     int[] weights = {3, 2, 4, 7, 3, 1, 8};
        int[] values = {5, 6, 3, 19, 12, 4, 2};*/
        int[] weights = {40, 20, 30, 20, 10, 1, 2};
        int[] values = {10, 9, 20, 30, 40, 100, 10};
        int bag = 63;
        System.out.println(getMax(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }

    public static int getMax(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length == 0 || w.length != v.length) {
            return 0;
        }
        return process(w, v, 0, bag);
    }

    //w：index号货物的重量，v:index号货物的价值
    //index表示当前是index号货物在做选择
    //rest表示背包的剩余容量
    //这个函数表示从index号货物开始做决策【要或者不要当前货物】，一直到N号货物货物结束，
    //在不超过背包最大容量的情况下返回背包所装货物的最大价值。
    public static int process(int[] w, int[] v, int index, int rest) {
        //背包没有了容量，为了让上游做出无效决策时，返回给上游的标识能识别出来，我们给一特定标识
        if (rest < 0) {
            return -1;  //返回-1，标识上游的决策无效
        }
        //货物已经没有了
        if (index == w.length) {
            return 0;
        }
        //背包还有空余容量，还有货物可以选择
        //选择不要当前货物
        int p1 = process(w, v, index + 1, rest);
        //选择要当前货物
        //rest-w[index]如果小于0,p2的决策是无效的，所有我们先去调一下，看当前决策是否有效
        int p2 = 0;
        int result = process(w, v, index + 1, rest - w[index]);
        if (result != -1) {
            p2 = v[index] + result;
        }
        return Math.max(p1, p2);
    }

    public static int dp(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length == 0 || w.length != v.length) {
            return 0;
        }
        //index 0~N
        //rest  0~bag
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        //依赖关系当前位置依赖下一层
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int result = (rest - w[index]) < 0 ? -1 : dp[index + 1][rest - w[index]];
                int p2 = 0;
                if (result != -1) {
                    p2 = v[index] + result;
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }
}
