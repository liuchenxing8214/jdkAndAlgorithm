package mukewang.suanfa.sort;

public class SelectionSort {


    public static void selectSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = (i + 1); j < n; j++) {
                minIndex = arr[minIndex] < arr[j] ? minIndex : j;
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }



    // 打印数组的方法
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // 测试选择排序算法的方法
    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        System.out.println("未排序的数组:");
        printArray(arr);

        selectSort(arr);

        System.out.println("已排序的数组:");
        printArray(arr);
    }
}
