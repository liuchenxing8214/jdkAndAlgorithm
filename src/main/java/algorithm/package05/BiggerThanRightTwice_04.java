package algorithm.package05;

public class BiggerThanRightTwice_04 {

    public static void main(String[] args) {
        int[] arr = {2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647};
        System.out.println(reversePairs(arr));
    }
    public static int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        long[] longArr =  new long[nums.length];
        for(int i=0;i<nums.length;i++){
            longArr[i] = nums[i];
        }
        return process(longArr, 0, nums.length - 1);
    }

    public static int process(long[] arr, int L, int R) {
        //边界条件，只有一个数时候，是不用排序的
        if (L == R) {
            return 0;
        }
        int Mid = (L + R) >> 1;
        return process(arr, L, Mid)
                + process(arr, Mid + 1, R)
                + merge(arr, L, Mid, R);
    }

    public static int merge(long[] arr, int L, int Mid, int R) {
        //在merge和sort之前将满足数据的数都计算出来
        int res = 0;
        int rightIndex = Mid + 1; //右组数第一个数据的下标
        for (int i = L; i <= Mid; i++) {
            //右组数尽可能往右扩，扩到第一个不满足的条件停下来
            //右组数没有越界,而且左组数 >= 右组数 *2
            while (rightIndex <= R && arr[i] > (arr[rightIndex] << 1)) {
                rightIndex++;
            }
            res += rightIndex - Mid - 1;
        }
        //辅助数组长度
        long[] help = new long[R - L + 1];
        int p1 = L;
        int p2 = Mid + 1;
        // （I）左组数和右组数谁小先拷贝谁到辅助数组中去
        //  (II)左组数和右组数相等，先拷贝左组的数【规定规则之后左组和右组必有一个越界】
        //(1)p1和p2都没有越界时候的情况
        int index = 0;
        while (p1 <= Mid && p2 <= R) {
            help[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        //(2)p1和p2必有一个越界时候的情况
        while (p1 <= Mid) {
            help[index++] = arr[p1++];
        }
        while (p2 <= R) {
            help[index++] = arr[p2++];
        }
        //将辅助数组数据移动到arr的L到R中对应的位置上去
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
                if (arr[i] > (arr[j] << 1)) {
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
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
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
/*    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (biggerTwice(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }*/
}
