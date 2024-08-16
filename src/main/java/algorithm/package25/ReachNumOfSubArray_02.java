package algorithm.package25;

import java.util.LinkedList;

public class ReachNumOfSubArray_02 {
    // for test
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = reachNumOfSubArray(arr, sum);
            int ans2 = reachNumOfSubArray1(arr, sum);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(sum);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }


    public static int reachNumOfSubArray(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        for (int L = 0; L < N; L++) {
            for (int R = L; R < N; R++) {
                int max = arr[L];
                int min = arr[L];
                for (int i = L + 1; i <= R; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if (max - min <= sum) {
                    count++;
                }
            }
        }
        return count;
    }


    /****
     结论一:假设某一个子数组从L到R,如果已知到他是达标的，那么它的内部子数组一定是达标的。
     结论二:   如果L到R已经不达标了,
     这个L往左扩，出来的新的子数组一定不达标,
     这个R往右扩,出来的新的子数组也一定不达标。*/
    //[0.....R] R为首次不达标的元素下标，以0为开头的子数组有R-0个
    //[1.....R] 0位置元素过期,R为首次不达标的元素下标，以1为开头的子数组有R-1个
    //[N-1...R] R-2位置元素过期,R为首次不达标的元素下标，以R-1为开头的子数组有1个
    public static int reachNumOfSubArray1(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        LinkedList<Integer> qMax = new LinkedList<>();
        LinkedList<Integer> qMin = new LinkedList<>();
        int count = 0;
        int R = 0;//R不用回退
        for (int L = 0; L < N; L++) {
            //R一直向右进行扩展，即是窗口尾巴上不断地加元素过来，
            // R为首次不达标的元素下标,停下来
            while (R < N) {
                // L固定，R不断向右扩时任意一点时的最大值更新结构qMax，和最小值更新结构qMin
                while (!qMax.isEmpty() && arr[R] >= arr[qMax.peekLast()]) {
                    qMax.pollLast();
                }
                qMax.addLast(R);

                while (!qMin.isEmpty() && arr[R] <= arr[qMin.peekLast()]) {
                    qMin.pollLast();
                }
                qMin.addLast(R);

                if ((arr[qMax.peekFirst()] - arr[qMin.peekFirst()]) <= sum) {
                    //从L开始，R只要达标，R就继续向右进扩展
                    R++;
                } else {
                    //R首次不达标的值
                    break;
                }
            }
            count = count + (R - L);
            //L位置马上要过期了，L位置在qMax在最大值的更新结构中，直接弹出
            if (L == qMax.peekFirst()) {
                qMax.pollFirst();
            }
            //L位置马上要过期了，L位置在qMin在最小值的更新结构中，直接弹出
            if (L == qMin.peekFirst()) {
                qMin.pollFirst();
            }
        }
        return count;
    }


}
