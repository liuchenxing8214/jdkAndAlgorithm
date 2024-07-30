package algorithm.package22;

public class ChessSurvivalPro_05 {

    public static void main(String[] args) {
        System.out.println(livePossibility(6, 6, 10, 50, 50));
        System.out.println(dp(6, 6, 10, 50, 50));
    }

    public static double livePossibility(int row, int col, int k, int N, int M) {
        return (double) process(row, col, k, N, M) / Math.pow(4, k);
    }

    public static int process(int N, int M, int x, int y, int k) {
        //边界条件:任何一步走到棋盘外盘
        if (x < 0 || x > (N - 1) || y < 0 || y > (M - 1)) {
            return 0;
        }
        //所有的步数都走完了,还在棋盘内,找到了一种方式。
        if (k == 0) {
            return 1;
        }
        //否则在棋盘内,
        int ways = process(N, M, x, y - 1, k - 1);
        ways += process(N, M, x, y + 1, k - 1);
        ways += process(N, M, x - 1, y, k - 1);
        ways += process(N, M, x + 1, y, k - 1);
        return ways;
    }

    public static double dp(int N, int M, int row, int col, int K) {
        int[][][] dp = new int[N][M][K + 1];
        //根据依赖顺序,先把第0层的值填写完成。
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int rest = 1; rest <= K; rest++) {
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < M; y++) {
                    dp[x][y][rest] = pick(dp, N, M, x, y - 1, K - 1)
                            + pick(dp, N, M, x, y + 1, K - 1)
                            + pick(dp, N, M, x - 1, y, K - 1)
                            + pick(dp, N, M, x + 1, y, K - 1);
                }
            }
        }
        return (double) pick(dp, N, M, row, col, K) / Math.pow(4, K);
    }

    public static int pick(int[][][] dp, int N, int M, int x, int y, int k) {
        if (x < 0 || x > (N - 1) || y < 0 || y > (M - 1)) {
            return 0;
        }
        return dp[x][y][k];
    }


}
