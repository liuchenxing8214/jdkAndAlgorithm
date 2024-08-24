package algorithm.package05;

public class ReversePair_03 {

    public static int reversePair(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        //边界条件，只有一个数时候，是不用排序的
        if (L == R) {
            return 0;
        }
        int Mid = (L + R) >> 1;
        return process(arr, L, Mid)
                + process(arr, Mid + 1, R)
                + merge(arr, L, Mid, R);
    }

    public static int merge(int[] arr, int L, int Mid, int R) {
        //辅助数组长度
        int[] help = new int[R - L + 1];
        int p1 = Mid;
        int p2 = R;
        // （I）左组数和右组数谁大先拷贝谁到辅助数组中去【拷贝左组数可以计算逆序对】
        //  (II)左组数和右组数相等，先拷贝右组的数
        //  (III)都是从右往左来merge
        //  【要是拷贝左组数 num，就不知道右边有多少个数比左组数num小】
        //(1)p1和p2都没有越界时候的情况
        int index = help.length - 1;
        int res = 0;
        while (p1 >= L && p2 >= (Mid + 1)) {
            res += arr[p1] > arr[p2] ? (p2 - Mid) : 0;
            help[index--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        //(2)p1和p2必有一个越界时候的情况
        while (p1 >= L) {
            help[index--] = arr[p1--];
        }
        while (p2 >= (Mid + 1)) {
            help[index--] = arr[p2--];
        }
        //将辅助数组中的数移动数组arr中L到R对应的位置中去
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return res;
    }

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (reversePair(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
