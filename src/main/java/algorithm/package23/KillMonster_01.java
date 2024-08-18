package algorithm.package23;

public class KillMonster_01 {

    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = dp(K, N, M);
            double ans2 = KillMonsterRate(K, N, M);
            double ans3 = dp2(K, N, M);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                break;
            }

        }
        System.out.println("测试结束");
    }


    public static double KillMonsterRate(int K, int N, int M) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        //获取总的情况数
        long all = (long) Math.pow((M + 1), K);
        long killNum = getKillNum(K, N, M);
        return (double) ((double) killNum / (double) all);
    }


    /**
     * @param times 砍怪兽的剩余次数
     * @param hp    怪兽的血量
     * @param M     等概率消耗怪兽的血量[0...M]
     * @return 收集怪兽被砍死的情况数
     */
    public static long getKillNum(int times, int hp, int M) {
        //K==0
        //当砍怪兽的次数都用完时【全部都展开时候,跟血量判断怪兽有没有被砍死,
        //                    如果血量<=0,就收集到了一中情况】
        if (times == 0) {
            return hp <= 0 ? 1 : 0;
        }
        long ways = 0;
        for (int i = 0; i <= M; i++) {
            //血量>0时,还有times次需要砍时候
            if (hp - i > 0) {
                ways += getKillNum(times - 1, hp - i, M);
            } else {
                ////血量<=0时,还有times次需要砍,怪兽被砍死的情况数为(M+1)的times次方。
                ways += (long) Math.pow((M + 1), (times - 1));
            }
        }
        return ways;
    }


    /**
     * 第一版本的动态规划【没有替换枚举行为的动态规划】
     */
    public static double dp(int K, int N, int M) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        //获取总的情况数
        long all = (long) Math.pow((M + 1), K);
        long[][] dp = new long[K + 1][N + 1];  //hp<=0的部分用公式直接计算出来
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[times][0] = (long) Math.pow((M + 1), times);
            for (int hp = 1; hp <= N; hp++) {
                long ways = 0;
                for (int i = 0; i <= M; i++) {
                    if (hp - i > 0) {
                        ways += dp[times - 1][hp - i];
                    } else {
                        ////血量<=0时,还有times次需要砍,怪兽被砍死的情况数为(M+1)的times次方。
                        ways += (long) Math.pow((M + 1), (times - 1));
                    }
                }
                dp[times][hp] = ways;
            }
        }
        long killNum = dp[K][N];
        return (double) ((double) killNum / (double) all);
    }


    /**
     * 第二版本的动态规划【1.用临近的位置替换枚举行为的动态规划，2.超过dp表之外的部分只用用公式计算出来】
     */
    public static double dp2(int K, int N, int M) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        //获取总的情况数
        long all = (long) Math.pow((M + 1), K);
        long[][] dp = new long[K + 1][N + 1];  //hp<=0的部分用公式直接计算出来
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[times][0] = (long) Math.pow((M + 1), times);
            for (int hp = 1; hp <= N; hp++) {
                dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp];
                if (hp - M - 1 >= 0) {
                    dp[times][hp] -= dp[times - 1][hp - M - 1];
                } else {
                    dp[times][hp] -= Math.pow((M + 1), (times - 1));
                }
            }
        }
        long killNum = dp[K][N];
        return (double) ((double) killNum / (double) all);
    }


}
