package algorithm.package25;

import java.util.LinkedList;

public class MaxNumOfWin_01 {

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
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

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
            int[] ans1 = getMaxNum(arr, w);
            int[] ans2 = getMaxNumOfWin(arr, w);
            if (!isEqual(ans1, ans2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

    /**
     * 常规方法求解
     *
     * @param arr
     * @param w
     * @return
     */
    public static int[] getMaxNum(int[] arr, int w) {
        if (arr == null || w < 1 || w > arr.length) {
            return null;
        }
        int N = arr.length;
        int[] res = new int[N - w + 1];
        int index = 0;
        int L = 0;
        int R = w - 1;
        while (R < N) {
            int max = arr[L];
            for (int i = L + 1; i <= R; i++) {
                max = Math.max(max, arr[i]);
            }
            res[index++] = max;
            L++;
            R++;
        }
        return res;
    }

    /**
     * 用窗口函数实现窗口内的最大值数组更新结构
     *
     * @param arr
     * @param W
     * @return
     */
    public static int[] getMaxNumOfWin(int[] arr, int W) {
        if (arr == null || W < 1 || W > arr.length) {
            return null;
        }
        int N = arr.length;
        int[] res = new int[N - W + 1];
        //窗口最大值更新结构，从头到尾依次大小依次减小
        LinkedList<Integer> qMax = new LinkedList();
        int index = 0;
        int R = 0;
        while (R < N) {
            while (!qMax.isEmpty() && arr[R] >= arr[qMax.peekLast()]) {
                //如果新加的值大于等于窗口的尾巴值,窗口的尾巴值从右边弹出。
                qMax.pollLast();
            }
            qMax.addLast(R);
            //如果之前的添加的值都是从大到小，加的值到一定程度，就会超过窗口的边界
            int exceedBound = R - W;
            if (qMax.peekFirst() == exceedBound) {
                //头部的最大值过期，将头部的的最大值弹出。
                qMax.pollFirst();
            }
            //窗口开始形成时保存最大值
            if (R >= W - 1) {
                res[index++] = arr[qMax.peekFirst()];
            }
            R++;
        }
        return res;
    }

}
