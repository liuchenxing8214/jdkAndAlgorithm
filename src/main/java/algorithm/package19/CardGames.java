package algorithm.package19;

public class CardGames {


    public static void main(String[] args) {
        // int[] arr = new int[]{50, 100, 20, 10};
        int[] arr = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
        int result01 = win1(arr);
        System.out.println("result01=====" + result01);

        int result02 = win2(arr);
        System.out.println("result02===" + result02);

        int result03 = win3(arr);
        System.out.println("result03===" + result03);



    }

    public static int win1(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        int firstResult = f(arr, 0, arr.length - 1);
        int secondResult = g(arr, 0, arr.length - 1);
        return Math.max(firstResult, secondResult);
    }

    //arr从L到R范围中，以先手的姿态全力以赴获取的最好分数。
    public static int f(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int p1 = arr[L] + g(arr, L + 1, R);
        //假如拿左边的数,以后手的姿态从(L+1)到R在全力以赴情况下得到先手给较差的一个结果
        int p2 = arr[R] + g(arr, L, R - 1);
        //假如拿右边的数,以后手的姿态从L到(R-1)在全力以赴情况下得到先手给较差的一个结果
        return Math.max(p1, p2);
    }

    //arr从L到R范围中，以后手的姿态全力以赴获取的最好分数。
    // 【因为先手绝对理性，他一定会把最差的你全力以赴的结果给你】零和博弈
    public static int g(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int p1 = f(arr, L + 1, R);//先手拿了左边的数
        int p2 = f(arr, L, R - 1);//先手拿了右边的数
        return Math.min(p1, p2);
    }

    public static int win2(int[] arr) {
        //L:0~N-1
        //R:0:N-1
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] fCacheTab = new int[N][N];
        int[][] gCacheTab = new int[N][N];
        //缓存初始化
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fCacheTab[i][j] = -1;
                gCacheTab[i][j] = -1;
            }
        }
        int firstResult = f2(arr, 0, arr.length - 1, fCacheTab, gCacheTab);
        int secondResult = g2(arr, 0, arr.length - 1, fCacheTab, gCacheTab);
        return Math.max(firstResult, secondResult);
    }

    //arr从L到R范围中，以先手的姿态全力以赴获取的最好分数。
    public static int f2(int[] arr, int L, int R, int[][] fCacheTab, int[][] gCacheTab) {
        //如果缓存中有,直接从缓存中去确取
        if (fCacheTab[L][R] != -1) {
            return fCacheTab[L][R];
        }
        //缓存中没有，需要自己计算
        int ans = 0;
        if (L == R) {
            ans = arr[L];
        } else {
            int p1 = arr[L] + g2(arr, L + 1, R, fCacheTab, gCacheTab);
            //假如拿左边的数,以后手的姿态从(L+1)到R在全力以赴情况下得到先手给较差的一个结果
            int p2 = arr[R] + g2(arr, L, R - 1, fCacheTab, gCacheTab);
            //假如拿右边的数,以后手的姿态从L到(R-1)在全力以赴情况下得到先手给较差的一个结果
            ans = Math.max(p1, p2);
        }
        //将计算得到的数据放在缓存中
        fCacheTab[L][R] = ans;
        return ans;
    }

    //arr从L到R范围中，以后手的姿态全力以赴获取的最好分数。
    // 【因为先手绝对理性，他一定会把你全力以赴获取的较差的结果给你】零和博弈
    public static int g2(int[] arr, int L, int R, int[][] fCacheTab, int[][] gCacheTab) {
        if (gCacheTab[L][R] != -1) {
            return gCacheTab[L][R];
        }
        int ans = 0;
        if (L != R) {
            int p1 = f2(arr, L + 1, R, fCacheTab, gCacheTab);//先手拿了左边的数
            int p2 = f2(arr, L, R - 1, fCacheTab, gCacheTab);//先手拿了右边的数
            ans = Math.min(p1, p2);
        }
        gCacheTab[L][R] = ans;
        return ans;
    }

    //搞一个严格顺序依赖的动态规划,建立空间感,为了方便以后优化【虽然这个题不优化】
    public static int win3(int[] arr) {
        //L:0~N-1
        //R:0:N-1
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] fdb = new int[N][N];
        int[][] gdb = new int[N][N];
        //缓存初始化
        for (int i = 0; i < N; i++) {
            fdb[i][i] = arr[i];
        }
        for (int startCol = 1; startCol < N; startCol++) {
            int L = 0;
            int R = startCol;
            //列先越界
            while (R < N) {
                fdb[L][R] = Math.max((arr[L] + gdb[L + 1][R]), (arr[R] + gdb[L][R - 1]));
                gdb[L][R] = Math.min(fdb[L + 1][R], fdb[L][R - 1]);
                L++;  //L+1,R+1,按着第一条，第二条对角线往下走。【对角线的移动是从左往右移动】
                R++;
            }
        }
        return Math.max(fdb[0][N - 1], gdb[0][N - 1]);
    }
}
