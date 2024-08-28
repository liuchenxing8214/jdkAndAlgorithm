package algorithm.package06;

import java.util.Stack;

/**
 * 用非递归的方法实现快速排序
 */
public class QuickSortRecursive_03 {


    public static void notRecursion(int[] nums) {
        int N = nums.length;
        if (nums == null || N < 2) {
            return;
        }
        int random1 = (int) Math.random() * N;
        swap(nums, random1, N - 1);
        int[] eqRange = flagOfNetherlands(nums, 0, N - 1);
        Stack<Task> tasks = new Stack<>();
        Task taskLeft = new Task();
        taskLeft.l = 0;
        taskLeft.r = eqRange[0] - 1;

        Task taskRight = new Task();
        taskRight.l = eqRange[1] + 1;
        taskRight.r = N - 1;

        tasks.push(taskLeft);
        tasks.push(taskRight);
        //只要任务还有，就不停的取出任务
        while (!tasks.isEmpty()) {
            Task task = tasks.pop();
            //如果任务还可以拆分，就继续拆分任务，直到将任务拆到不能拆分为止【l>=r时就不能拆分了】
            if (task.l < task.r) {
                int random2 = (int) Math.random() * (task.r - task.l + 1) + task.l;
                swap(nums, random2, task.r);
                int[] eqRange2 = flagOfNetherlands(nums, task.l, task.r);

                Task taskLeft1 = new Task();
                taskLeft1.l = task.l;
                taskLeft1.r = eqRange2[0] - 1;

                Task taskRight1 = new Task();
                taskRight1.l = eqRange2[1] + 1;
                taskRight1.r = task.r;

                tasks.push(taskLeft1);
                tasks.push(taskRight1);
            }
        }
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


    public static class Task {
        private int l;  //任务的左边界
        private int r;  //任务的右边界
    }


    // 生成随机数组（用于测试）
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // 拷贝数组（用于测试）
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

    // 对比两个数组（用于测试）
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

    // 打印数组（用于测试）
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

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

    // 跑大样本随机测试（对数器）
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            notRecursion(arr1);
            quickSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println("test end");
        System.out.println("测试" + testTime + "组是否全部通过：" + (succeed ? "是" : "否"));
    }


}
