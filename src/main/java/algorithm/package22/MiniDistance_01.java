package algorithm.package22;

public class MiniDistance_01 {

    public static void main(String[] args) {
  /*      int rowSize = 10;
        int colSize = 10;
        int[][] m = generateRandomMatrix(rowSize, colSize);*/
        int[][] m = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
        printMatrix(m);
        System.out.println(getMiniDistance(m));
        System.out.println(getMiniDistance1(m));
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 100);
            }
        }
        return result;
    }

    public static int getMiniDistance(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        return process(matrix);
    }

    /***
     *  最简单的动态规划结构
     * @param matrix 原始数组
     * @return
     */
    public static int process(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        //将每一步计算的结果放在dp表中【跟原始数组同等大小】
        int[][] dp = new int[row][col];
        //第0行0列既没有上也没有左,做特殊情况处理
        dp[0][0] = matrix[0][0];
        //将第0行数值填好
        for (int j = 1; j < col; j++) {
            //任何一个格子依赖上和左【这里只能依赖左】
            dp[0][j] = dp[0][j - 1] + matrix[0][j];
        }
        //将第0列值填好
        for (int i = 1; i < row; i++) {
            //任何一个格子依赖上和左【这里只能依赖上】
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }
        //从第一行,第一列,从左往右,从上往下开始填写
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                //任何一个格子依赖上和左
                //左 dp[i][j-1]
                //上 dp[i-1][j]
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + matrix[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    /**
     * @param matrix 进一步优化,自我更新策略,用一维数组完成 【数组压缩技术】
     * @return
     */
    public static int getMiniDistance1(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[] arr = new int[col];
        //第0行0列既没有上也没有左,做特殊情况处理
        arr[0] = matrix[0][0];
        //将第0行数值填好
        for (int j = 1; j < col; j++) {
            //任何一个格子依赖上和左【这里只能依赖左】
            arr[j] = arr[j - 1] + matrix[0][j];
        }
/*        //从第一行开始填起,从左往右,从上往下。
        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (j == 0) {
                    //第0列只依赖上【arr[j]表示左】
                    arr[j] = arr[j] + matrix[i][j];
                } else {
                    //从1列开始依赖左和上【arr[j-1]表示左,arr[j]表示上】
                    arr[j] = Math.min(arr[j - 1], arr[j]) + matrix[i][j];
                }
            }
        }*/
        for (int i = 1; i < row; i++) {
            //第0列只依赖上【arr[j]表示左】
            arr[0] = arr[0] + matrix[i][0];
            for (int j = 1; j < col; j++) {
                //从1列开始依赖左和上【arr[j-1]表示左,arr[j]表示上】
                arr[j] = Math.min(arr[j - 1], arr[j]) + matrix[i][j];
            }
        }
        return arr[col - 1];
    }
}
