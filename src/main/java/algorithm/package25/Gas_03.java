package algorithm.package25;

import java.util.LinkedList;

public class Gas_03 {
    public static void main(String[] args) {
        int[] gas = {1, 2, 8, 3, 8, 3};
        int[] costs = {3, 5, 4, 5, 2, 4};
        System.out.println(canCompleteCircuit(gas, costs));
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length != cost.length || gas.length < 1) {
            return -1;
        }
        boolean[] results = getCircuitResult(gas, cost);
        for (int i = 0; i < gas.length; i++) {
            if (results[i]) {
                return i;
            }
        }
        return -1;
    }

    public static boolean[] getCircuitResult(int[] gas, int[] cost) {

        int N = gas.length;
        int[] arr = new int[2 * N];
        //加工好两倍长的的累加和数组
        for (int i = 0; i < N; i++) {
            arr[i] = gas[i] - cost[i];
            arr[i + N] = gas[i] - cost[i];
        }
        for (int i = 1; i < 2 * N; i++) {
            arr[i] = arr[i] + arr[i - 1];
        }
        //从0位置出发的,0到N-1位置上的最小值的下标放到最小更新结果中去
        LinkedList<Integer> qMin = new LinkedList<>();
        for (int R = 0; R < N; R++) {
            while (!qMin.isEmpty() && arr[R] <= arr[qMin.peekLast()]) {
                qMin.pollLast();
            }
            qMin.addLast(R);
        }
        int offset = 0;
        int R = 0;
        boolean[] res = new boolean[N + 1];
        //L表示从第L个元素开始做累加和
        for (int L = 0; L <= N; L++) {
            //得到以前一个元素的值
            if (L > 0) {
                offset = arr[L - 1];
            }
            //从L个元素开始出发到R形成N个元素的窗口中的最小值=两倍长数组中最小值 - 偏移量>=0,说明能走完一圈
            if (arr[qMin.peekFirst()] - offset >= 0) {
                res[L++] = true;
            }
            //L++,原来的L即将过期,最小值直接从头部开始弹出
            if (L == qMin.peekFirst()) {
                qMin.pollFirst();
            }
            R = L + N;
            if (R < 2 * N) {
                while (!qMin.isEmpty() && arr[R] <= arr[qMin.peekLast()]) {
                    qMin.pollLast();
                }
                qMin.addLast(R);
            }
        }
        return res;
    }
}
