package algorithm.package06;

public class PartitionAndQuickSort_02 {

    public static void quickSort2(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        process2(nums, 0, nums.length - 1);
    }

    public static void process2(int[] nums, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] eqRange = flagOfNetherlands(nums, L, R);
        process2(nums, L, eqRange[0] - 1);
        process2(nums, eqRange[1] + 1, R);
    }


    public static void quickSort3(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        process3(nums, 0, nums.length-1);
    }

    public static void process3(int[] nums, int L, int R) {
        if (L >= R) {
            return;
        }
        //在nums的L到R上随机取一个位置和R作交换
        int random = L + (int) Math.random() * (R - L + 1);
        swap(nums, random, R);
        int[] eqRange = flagOfNetherlands(nums, L, R);
        process3(nums, L, eqRange[0] - 1);
        process3(nums, eqRange[1] + 1, R);
    }

    /**
     * 荷兰国旗问题 2.0 版本
     * 以最右边的一个数作为目标数,向固定不动
     * <区域 放左边
     * =区域 放中间
     * >区域 放右边
     */
    public static int[] flagOfNetherlands(int[] nums, int L, int R) {
        //无效的数组
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, L};
        }
        int lessRight = L - 1; //小于区域右边界
        int moreLeft = R;    //大于区域左边界
        int index = L;       //当前数
        //当前数一直向右移动，当前数的和大于区域左边界碰见时就停下来
        while (index < moreLeft) {
            //(a)当前数小于目标数时
            if (nums[index] < nums[R]) {
                //(I)小于区域的下一个数和当前数交换
                lessRight++;
                swap(nums, lessRight, index);
                //(II)小于区域的右边界向右扩一个位置
                //(III)当前数指向下一个属
                index++;
            } else if (nums[index] == nums[R]) {
                //(b)当前数等于目标数时，直接当过当前数
                index++;
            } else {
                //(c)当前数大于目标数时
                //(I)大于区域的上一个数和当前数交换
                //(II)大于区域左边界向左扩一个位置
                //(III)当前数的位置保持不变
                moreLeft--;
                swap(nums, moreLeft, index);
            }
        }
        //大于区域最左的数据和R位置的数据作交换
        //变成了   小于区域 [L,lessRight]
        //        等于区域 [lessRight+1,moreLeft]
        //        大于区域 [moreLeft+1,R]
        swap(nums, moreLeft, R);
        return new int[]{lessRight + 1, moreLeft};
    }

    public static void swap(int[] arr, int L, int R) {
        int temp = arr[L];
        arr[L] = arr[R];
        arr[R] = temp;
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
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            // quickSort1(arr1);
            quickSort2(arr2);
            quickSort3(arr3);
            if (!isEqual(arr2, arr3)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");

    }


}
