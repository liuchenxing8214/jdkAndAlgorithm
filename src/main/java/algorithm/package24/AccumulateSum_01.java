package algorithm.package24;

public class AccumulateSum_01 {

    public static int getAccumulateSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return process(arr, 0, sum / 2);
    }

    //从index开始及以后,得到子数组中累加和最接近rest的那个数。
    public static int process(int[] arr, int index, int rest) {
        //没有数可以选择了,
        if (index == arr.length) {
            return 0;
        } else {
            //第一种选择,当前数我不要
            int p1 = process(arr, index + 1, rest);
            //第二种选择,当前数我要
            int p2 = 0;
            if (rest >= arr[index]) {
                p2 = arr[index] + process(arr, index + 1, rest - arr[index]);
            }
            return Math.max(p1, p2);
        }
    }


    public static int dp(int[] arr) {
        int N = arr.length;
        if (arr == null || N < 2) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
        }
        sum = sum / 2;
        int[][] dp = new int[N + 1][sum + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= sum; rest++) {
                //第一种选择,当前数我不要
                int p1 = dp[index + 1][rest];
                //第二种选择,当前数我要
                int p2 = 0;
                if (rest >= arr[index]) {
                    p2 = arr[index] + dp[index + 1][rest - arr[index]];
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][sum];
    }


    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = getAccumulateSum(arr);
            int ans2 = dp(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}