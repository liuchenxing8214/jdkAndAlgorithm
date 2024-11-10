package mukewang.suanfa.sort;

public class BinarySearch {

    public static int binarySearch(int[] arr, int aim) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left+right)>>>1;
            if(arr[mid]==aim){
                return mid;
            }else if(aim>arr[mid]){
                left = mid+1;
            }else{
                right=mid-1;
            }
        }
        return -1;
    }

    // 打印数组的方法
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // 测试二分查找算法的方法
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 10, 40, 55, 60};
        System.out.println("数组:");
        printArray(arr);

        int target = 88;
        int result = binarySearch(arr, target);

        if (result != -1) {
            System.out.println("元素 " + target + " 位于数组的索引 " + result);
        } else {
            System.out.println("元素 " + target + " 不在数组中");
        }
    }

}
