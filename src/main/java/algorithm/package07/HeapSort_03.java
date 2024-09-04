package algorithm.package07;

import java.util.Arrays;
import java.util.PriorityQueue;

public class HeapSort_03 {


    public static void heapSort(int[] arr) {
        int N = arr.length;
        if (arr == null || N < 2) {
            return;
        }
        // O(N*logN)

/*        for (int i = 0; i < N; i++) {
            heapInsert(arr, i);
        }*/
        //O(N)  从下网上依次将第i个节点调成大根堆
        for (int i = N - 1; i >= 0; i--) {
            heapIfy(arr,i,N);
        }
        int heapSize = N;
        //0位置和N-1位置作交换,最大值和堆结构断开
        //N-1位置上是最大值,最大值和堆结构断开
        swap(arr, 0, --heapSize);
        //由于堆结构的最后一个值被放在0位置上了【值一定不会大于所有孩子的最大值】
        //所以需要从0位置开始重新调堆结构
        while (heapSize > 0) {
            heapIfy(arr, 0, heapSize);
            //N-2位置放置最大值
            swap(arr, 0, --heapSize);
        }
    }

    /**
     * 从index位置节点开始,只要有孩子【左孩子或者右孩子】比我大,
     * 我就不断往下沉【我和我较大的孩子作交换】
     * 直到下沉到没有左孩子或者没有右孩子为止
     */
    public static void heapIfy(int[] arr, int index, int heapSize) {
        int leftChild = index * 2 + 1;
        while (leftChild < heapSize) {

            //右孩子存在且右孩子比左孩子大
            int largestChild = (leftChild + 1) < heapSize && arr[leftChild + 1] > arr[leftChild]
                    ? (leftChild + 1) : leftChild;
            //父节点和孩子的值作比较,获得最大的下标值
            largestChild = arr[largestChild] > arr[index] ? largestChild : index;
            //如果父节点的值大于或者等于孩子节点的值,则有largestChild是父节点的下标
            if (largestChild == index) {
                break;
            }
            //当前节点小于较大的孩子的值
            //(I)当前节点和较大的孩子的值作交换
            swap(arr, index, largestChild);
            //(II)当前节点指向较大的那个孩子
            index = largestChild;
            leftChild = index * 2 + 1;
        }
    }


    // 新加进来的数，现在停在了index位置，请依次往上移动，
    // 移动到0位置，或者干不掉自己的父亲了，停！
    public static void heapInsert(int[] arr, int index) {

        //根据当前节点的下标求得父节点的下标位置
        //由两种情况停下来
        //(I)当前节点位根节点 index = 0;
        //(II)当前节点小于等于父节点
        //int parentNodeIndex = (index - 1) / 2;
        //(arr[index] > arr[parentNodeIndex]) && (index != 0) 等价
        //arr[index] > arr[parentNodeIndex] 【index=0是这个不等式子肯定不会满足的】
        while (arr[index] > arr[(index - 1) / 2]) {
            //如果当前节点一直大于父节点,就一直换到根节点为止
            swap(arr, index, (index - 1) / 2);
            //当前节点指向父节点
            index = (index - 1) / 2;
        }
    }

    /**
     * 数组arr中的L和R位置数据互换
     */
    public static void swap(int[] arr, int L, int R) {
        int temp = arr[L];
        arr[L] = arr[R];
        arr[R] = temp;
    }


    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
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

        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(6);
        heap.add(8);
        heap.add(0);
        heap.add(2);
        heap.add(9);
        heap.add(1);

        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }

        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            heapSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        heapSort(arr);
        heapSort(arr);
        printArray(arr);
    }


}
