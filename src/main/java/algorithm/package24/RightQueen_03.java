package algorithm.package24;

public class RightQueen_03 {
    public static void main(String[] args) {
        System.out.println(totalNQueens(4));
        System.out.println(bestSolveTotalNQueens(4));
    }


    public static int totalNQueens(int n) {
        if (n < 1) {
            return 0;
        }
        //每一行只能放一个皇后,一行一行的放,从上往下
        int[] records = new int[n];  //记录i行以前每个皇后的放置轨迹,将列项存进去。下标表示第i个皇后
        return process(n, 0, records);
    }


    public static int process(int n, int index, int[] records) {
        //前面n个皇后的位置都放置好了,表示找到了一种摆放皇后的方法
        if (n == index) {
            return 1;
        } else {
            //还有皇后可以放置,第index个皇后可以在0到n列任意位置上选择
            int ways = 0;
            for (int j = 0; j < n; j++) {
                //(I)将第index个的皇后放在j列位置上,先判断第index行的皇后是否跟之前所有的皇后位置冲突
                if (isValid(index, j, records)) {
                    records[index] = j;
                    //开发放置
                    ways += process(n, index + 1, records);
                }
            }
            return ways;
        }
    }

    public static boolean isValid(int index, int j, int[] record) {
        //第index皇后只有和之前所有皇后中任意一个相冲突,第index皇后都不能放置在j列上
        for (int k = 0; k < index; k++) {
            if (record[k] == j || Math.abs(index - k) == Math.abs(j - record[k])) {
                return false;
            }
        }
        return true;
    }

    //优化方案:先将N皇后转化成右边是连续的N个1的整型数字,
    //拿着一个整型值的使用状态来替换掉records的更新
    public static int bestSolveTotalNQueens(int n) {
        //32位以上数据太大了
        if (n < 1 || n > 32) {
            return 0;
        }
        //-1的位运算是32个1
        int limit = n == 32 ? -1 : ((1 << n) - 1);
        return process2(limit, 0, 0, 0);

    }


    public static int process2(int limit,
                               int colRestrict,
                               int leftRestrict,
                               int rightRestrict) {
        if (limit == colRestrict) {
            return 1;
        }
        //所有为1的的位置是你可以尝试放皇后的位置
        int pos = (~(colRestrict | leftRestrict | rightRestrict)) & limit;
        int res = 0;
        int mostRightOne = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process2(limit,
                    (colRestrict | mostRightOne),
                    (leftRestrict | mostRightOne) << 1,
                    (rightRestrict | mostRightOne) >>> 1);

        }
        return res;
    }


}
