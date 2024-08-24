package algorithm.package05;

public class MergeSort_01 {

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    /**
     * 【第一种方法用递归】
     * arr中的L到R中都是排好序的
     */
    public static void process(int[] arr, int L, int R) {
        //边界条件，只有一个数时候，是不用排序的
        if (L == R) {
            return;
        }
        int Mid = (L + R) >> 1;
        process(arr, L, Mid);
        process(arr, Mid + 1, R);
        merge(arr, L, Mid, R);
    }

    public static void merge(int[] arr, int L, int Mid, int R) {
        //辅助数组长度
        int[] help = new int[R - L + 1];
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
    }

    /**
     * 第一种方法用非递归方法实现
     */
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        int step = 1;
        //通过控制步长的次数就能知道数组是否已经被排好序了，
        //步长 >= N时， 表示数组已经排好序
        while (step < N) {
            //步长不变时，L尽可能不断的向右进行扩展，L也要小于N,
            //L1 = 0，   Mid = L1+step-1,  R1 = Mid+step
            //L2 = R1+1  Mid = L2+step-1,  R2 = Mid+step
            int L = 0;
            while (L < N) {
                int Mid = L + step - 1;
                //左组数据没有排满时
                if (Mid >= N) {
                    break;
                }
                //左组数据能排满时
                //(I)Mid+step在L不断向右扩展时,最后一次可能Mid+step会大于数组最大下标N-1
                //(II)Mid+step在不超过边界的条件下,R = Mid+step
                int R = Math.min(Mid + step, N - 1);
                merge(arr, L, Mid, R);
                //下一次的L值为
                L = R + 1;
            }
            //防止溢出
            if (step > (N >> 1)) {
                break;
            }
            step = step << 1;
        }
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
            mergeSort(arr1);
            mergeSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }


}
