package algorithm.package07;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SortArrayDistanceLessK_04 {

    public static void sortedArrDistanceLessK(int[] arr, int k) {
        if (k == 0) {
            return;
        }
        // int right = Math.min()
        int N = arr.length;
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        int index = 0;
        //将前面 0到K-1位置的数放在堆中
        for (; index <= Math.min(k - 1, N - 1); index++) {
            minHeap.add(arr[index]);
        }
        //(I)假设K>=N-1时,已经将0到N-1位置数放在堆中了,此时index=N就不满足下面for条件了
        //(II)假设K<N-1时,已经放置了0到k-1位置的数到堆中,还可以放置k位置的数,一直到 N-K-1到N-1位置
        int j = 0;
        for (; index < N; index++) {
            //堆中加一个数,再弹出一个最小值,arr的index-K位置的数就确定了
            minHeap.add(arr[index]);
            arr[j++] = minHeap.poll();
        }
        //)当K>=N-1时,堆中还有元素
        while (!minHeap.isEmpty()) {
            arr[j++] = minHeap.poll();
        }
    }

    // for test
    public static void comparator(int[] arr, int k) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] randomArrayNoMoveMoreK(int maxSize, int maxValue, int K) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        // 先排个序
        Arrays.sort(arr);
        // 然后开始随意交换，但是保证每个数距离不超过K
        // swap[i] == true, 表示i位置已经参与过交换
        // swap[i] == false, 表示i位置没有参与过交换
        boolean[] isSwap = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int j = Math.min(i + (int) (Math.random() * (K + 1)), arr.length - 1);
            if (!isSwap[i] && !isSwap[j]) {
                isSwap[i] = true;
                isSwap[j] = true;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
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
        System.out.println("test begin");
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * maxSize) + 1;
            int[] arr = randomArrayNoMoveMoreK(maxSize, maxValue, k);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            sortedArrDistanceLessK(arr1, k);
            comparator(arr2, k);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                System.out.println("K : " + k);
                printArray(arr);
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}
