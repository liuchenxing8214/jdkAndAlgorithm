package algorithm.package01;

public class BSNearLeft {

    public static int nearestIndex(int[] arr, int value) {
        if (arr == null || arr.length == 1) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int index = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) { //向左边去找
                index = mid;
                R = mid - 1;
            } else { //向右边去找
                L = mid + 1;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 2, 3, 3, 3, 3, 4, 5, 5, 6, 7, 8, 9, 9, 9};
        int index = nearestIndex(arr, 3);
        System.out.println("index==" + index);
    }

}
