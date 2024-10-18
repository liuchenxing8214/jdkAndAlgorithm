package algorithm.package01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearch {

    /**
     * 数组中没有重复的情况,且数组时有序的
     *
     * @param arr
     * @param left
     * @param right
     * @param value
     * @return
     */
    public static int binarySearch(int[] arr, int left, int right, int value) {
        if (left > right) {
            return -1;
        }
        int mid = left + ((right - left) >> 1);
        if (value > arr[mid]) {//向右边去找
            return binarySearch(arr, left + 1, right, value);
        } else if (value < arr[mid]) {//向左边去找
            return binarySearch(arr, left, mid - 1, value);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
/*        int arr[] = {1, 8, 10, 89, 1000, 1000, 1234};
        int resIndex = binarySearch(arr, 0, arr.length - 1, 1000);
        System.out.println("resIndex=" + resIndex);*/
        int arr[] = {1, 8, 10, 89, 90, 1000, 1000, 1000,1000,1000,1000,1000, 1000, 1000, 1234};
        List indexList = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println("indexList=" + indexList);
    }

    /* 【写代码边界条件非常重要】
     * 课后思考题： {1,8, 10, 89, 1000, 1000，1234} 当一个有序数组中，
     * 有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000
     *
     * 思路分析
     * 1. 在找到mid 索引值，不要马上返回
     * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
     * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
     * 4. 将Arraylist返回
     */

    public static List<Integer> binarySearch2(int[] arr, int left, int right, int value) {
        if (left > right) {
            return Collections.emptyList();
        }
        int mid = left + ((right - left) >> 1);
        if (value > arr[mid]) {//向右边去找
            return binarySearch2(arr, left + 1, right, value);
        } else if (value < arr[mid]) {//向左边去找
            return binarySearch2(arr, left, mid - 1, value);
        } else {
            List<Integer> indexList = new ArrayList<>();
            int leftIndex = mid - 1;
            //向mid 索引值的左边扫描
            while (true) {
                if (leftIndex < 0 || arr[leftIndex] != value) {
                    break;
                }
                indexList.add(leftIndex);
                leftIndex--;
            }
            indexList.add(mid);
            int rightIndex = mid + 1;
            //向mid 索引值的右边扫描
            while (true) {
                if (rightIndex > (arr.length - 1) || arr[rightIndex] != value) {
                    break;
                }
                indexList.add(rightIndex);
                rightIndex++;
            }
            return indexList;
        }
    }


}
