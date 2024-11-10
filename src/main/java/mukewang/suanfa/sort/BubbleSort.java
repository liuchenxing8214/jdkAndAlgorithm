package mukewang.suanfa.sort;

public class BubbleSort {
    public static void sort(int[] arr){
        int len = arr.length;
        for(int i=len-1;i>=0;i--){
            for(int j=0;(j+1)<=i;j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    // 打印数组的方法
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // 测试冒泡排序的方法
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("未排序的数组:");
        printArray(arr);

        sort(arr);

        System.out.println("已排序的数组:");
        printArray(arr);
    }
}
