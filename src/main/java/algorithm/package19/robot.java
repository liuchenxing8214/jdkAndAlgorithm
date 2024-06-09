package algorithm.package19;

public class robot {

    public static void main(String[] args) {
        int ways = way1(4, 2, 4, 4);
        System.out.println("way1返回方法数结果为:" + ways);
        int ways2 = way2(4, 2, 4, 4);
        System.out.println("way2返回方法数结果为:" + ways2);

        int ways3 = way3(5, 2, 4, 6);
        System.out.println("way3返回方法数结果为:" + ways3);

    }

    public static int way1(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        return process(start, K, aim, N);
    }

    public static int process(int cur, int res, int aim, int N) {
        //如果剩余步数走完
        if (res == 0) {
            //如果当前位置在目标位置上,则证明这个一条正确的执行方法,否则这个方法不正确。
            return cur == aim ? 1 : 0;
        }
        //1->2
        if (cur == 1) {
            return process(2, res - 1, aim, N);
        }
        //N->N-1
        if (cur == N) {
            return process(N - 1, res - 1, aim, N);
        }
        return (process(cur - 1, res - 1, aim, N) + process(cur + 1, res - 1, aim, N));
    }

    //方法二,加傻缓存表,
    // 1)已经计算过的,直接在缓存表中取；
    // 2)没有计算的去计算,计算好了放在缓冲里面
    public static int way2(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        //cur:1~N
        //res:0~k
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(start, K, aim, N, dp);
    }


    public static int process2(int cur, int res, int aim, int N, int[][] dp) {
        if (dp[cur][res] != -1) {
            return dp[cur][res];
        }
        int ans = 0;
        //如果剩余步数走完
        if (res == 0) {
            //如果当前位置在目标位置上,则证明这个一条正确的执行方法,否则这个方法不正确。
            ans = cur == aim ? 1 : 0;
        }
        //1->2
        else if (cur == 1) {
            ans = process2(2, res - 1, aim, N, dp);
        }
        //N->N-1
        else if (cur == N) {
            ans = process2(N - 1, res - 1, aim, N, dp);
        } else {
            ans = process2(cur - 1, res - 1, aim, N, dp) + process2(cur + 1, res - 1, aim, N, dp);
        }
        dp[cur][res] = ans;
        return ans;
    }



    /**
     *  最终动态规划算法
     * @param N
     * @param start
     * @param aim
     * @param K
     * @return
     */
    public static int way3(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        //cur:1~N
        //res:0~k
        int[][] dp = new int[N + 1][K + 1];
        dp[aim][0] = 1;
        //从左往右(从第一列开始),列值固定,从上往下
        for (int col = 1; col <= K; col++) {
            dp[1][col] = dp[2][col - 1];
            for (int row = 2; row < N; row++) {
                dp[row][col] = dp[row - 1][col - 1] + dp[row + 1][col - 1];
            }
            dp[N][col] = dp[N - 1][col - 1];
        }
        return dp[start][K];
    }


}
