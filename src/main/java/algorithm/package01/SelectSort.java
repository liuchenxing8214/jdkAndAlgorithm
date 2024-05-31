package algorithm.package01;

public class SelectSort {

    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //0~N-1 最小值放在0位
        //1~N-1 最小值放在1位
        //2~N-1 最小值放在2位
        //i~N-1 最小值放在i位
        //外层循环确定当前元素的位置
        for (int i = 0; i < arr.length - 1; i++) {
            //内层循环找到最小元素的位置,用当前元素和最小元素进行替换
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j :minIndex;
            }
            swap(arr,i,minIndex);
        }
    }

    // 数组中交换i和j位置的数
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
