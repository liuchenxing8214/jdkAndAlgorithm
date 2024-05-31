package algorithm.package01;

import java.util.Arrays;


public class ArrayComparator {
    // for test  系统自带的绝对正确的数组排序
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test  产生随机数组
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test 拷贝得到一样的数组
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

    // for test 比较两个数组的值是否完全一致
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

    // for test 打印数组
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
        int testTime = 500000;  //设置比较验证次数
        int maxSize = 200;//设置测试随机数组最大长度
        int maxValue = 100;//设置数组内值的最大值
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);//得到随机数组
            int[] arr2 = copyArray(arr1); //得到随机数组拷贝份
            SelectSort.selectSort(arr1); //用自己的排序算法排序
            comparator(arr2);//用绝对正确的方法排序
            if (!isEqual(arr1, arr2)) { //验证自己的算法和绝对正确的算法得到的结果是否相同
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);//排序前打印
        SelectSort.selectSort(arr);//排序
        printArray(arr);//打印排序后结果
    }

}
