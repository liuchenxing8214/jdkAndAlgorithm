package algorithm.packate21;

public class HorseJump {

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(ways(step, x, y));
        System.out.println(dp(step, x, y));
    }

    public static int ways(int rest, int m, int n) {
        return process(rest, 0, 0, m, n);
    }

    public static int process(int rest, int X, int Y, int M, int N) {
        //跳出棋盘外面，说明越界了,说明此方法无效。
        if (X < 0 || X > 9 || Y < 0 || Y > 8) {
            return 0;
        }
        //马将所有的步数都跳完了,判断最终跳到的位置是否为终点位置。
        if (rest == 0) {
            return (X == M && Y == N) ? 1 : 0;
        }
        int ways = process(rest - 1, X + 2, Y + 1, M, N);
        ways += process(rest - 1, X + 1, Y + 2, M, N);
        ways += process(rest - 1, X - 1, Y + 2, M, N);
        ways += process(rest - 1, X - 2, Y + 1, M, N);
        ways += process(rest - 1, X - 2, Y - 1, M, N);
        ways += process(rest - 1, X - 1, Y - 2, M, N);
        ways += process(rest - 1, X + 1, Y - 2, M, N);
        ways += process(rest - 1, X + 2, Y - 1, M, N);
        return ways;
    }

    public static int dp(int k, int m, int n) {
        int[][][] dp = new int[10][9][k + 1];
        dp[m][n][0] = 1;
        //依赖关系是,当前层依赖下面一层,
        // 先得到第0层,再得到第一层,再得到第二层,依次往上得到第K层。
        for (int rest = 1; rest <= k; rest++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    int ways = pick(dp, rest - 1, i + 2, j + 1);
                    ways += pick(dp, rest - 1, i + 1, j + 2);
                    ways += pick(dp, rest - 1, i - 1, j + 2);
                    ways += pick(dp, rest - 1, i - 2, j + 1);
                    ways += pick(dp, rest - 1, i - 2, j - 1);
                    ways += pick(dp, rest - 1, i - 1, j - 2);
                    ways += pick(dp, rest - 1, i + 1, j - 2);
                    ways += pick(dp, rest - 1, i + 2, j - 1);
                    dp[i][j][rest] = ways;
                }
            }
        }
        return dp[0][0][k];
    }

    public static int pick(int[][][] dp, int k, int X, int Y) {
        //跳出棋盘外面，说明越界了,说明此方法无效。
        if (X < 0 || X > 9 || Y < 0 || Y > 8) {
            return 0;
        }
        return dp[X][Y][k];
    }


}