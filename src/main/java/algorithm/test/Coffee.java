package algorithm.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.locks.ReentrantLock;

public class Coffee {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        int len = 10;
        int max = 10;
        int testTime = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(len, max);
            int n = (int) (Math.random() * 7) + 1;
            int a = (int) (Math.random() * 7) + 1;
            int b = (int) (Math.random() * 10) + 1;
            int ans1 = right(arr, n, a, b);
            int ans2 = getMiniTime(arr, n, a, b);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println("n : " + n);
                System.out.println("a : " + a);
                System.out.println("b : " + b);
                System.out.println(ans1 + " , " + ans2);
                System.out.println("===============");
                break;
            }
        }
        System.out.println("测试结束");

    }

    // for test
    public static void printArray(int[] arr) {
        System.out.print("arr : ");
        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[j] + ", ");
        }
        System.out.println();
    }

    // 验证的方法
    // 彻底的暴力
    // 很慢但是绝对正确
    public static int right(int[] arr, int n, int a, int b) {
        int[] times = new int[arr.length];
        int[] drink = new int[n];
        return forceMake(arr, times, 0, drink, n, a, b);
    }

    // 每个人暴力尝试用每一个咖啡机给自己做咖啡
    public static int forceMake(int[] arr, int[] times, int kth, int[] drink, int n, int a, int b) {
        if (kth == n) {
            int[] drinkSorted = Arrays.copyOf(drink, kth);
            Arrays.sort(drinkSorted);
            return forceWash(drinkSorted, a, b, 0, 0, 0);
        }
        int time = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int work = arr[i];
            int pre = times[i];
            drink[kth] = pre + work;
            times[i] = pre + work;
            time = Math.min(time, forceMake(arr, times, kth + 1, drink, n, a, b));
            drink[kth] = 0;
            times[i] = pre;
        }
        return time;
    }

    public static int forceWash(int[] drinks, int a, int b, int index, int washLine, int time) {
        if (index == drinks.length) {
            return time;
        }
        // 选择一：当前index号咖啡杯，选择用洗咖啡机刷干净
        int wash = Math.max(drinks[index], washLine) + a;
        int ans1 = forceWash(drinks, a, b, index + 1, wash, Math.max(wash, time));

        // 选择二：当前index号咖啡杯，选择自然挥发
        int dry = drinks[index] + b;
        int ans2 = forceWash(drinks, a, b, index + 1, washLine, Math.max(dry, time));
        return Math.min(ans1, ans2);
    }

    // for test
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }


    public static int getMiniTime(int[] makeCoffeeTimes, int N, int wash, int airTime) {
        //排队的N个人获取到咖啡的时间,拿到咖啡都是立即喝掉【N个人开始喝完咖啡的时间】
        int[] drinks = new int[N];
        //初始化咖啡机的数据
        PriorityQueue<Machine> heap = new PriorityQueue<>(new MachineComparator());
        for (int i = 0; i < makeCoffeeTimes.length; i++) {
            heap.add(new Machine(0, makeCoffeeTimes[i]));
        }
        for (int i = 0; i < N; i++) {
            lock.lock();
            try {
                //这块代码不是线程安全的
                Machine machine = heap.poll();
                //当前咖啡制作完成时间,就是泡咖啡的机器可以泡下一杯咖啡的时间点。
                int curFinishTime = machine.getAvailableTime()+machine.getCostTime();
                drinks[i] = curFinishTime;
                machine.setAvailableTime(curFinishTime);
                heap.add(machine);//忘记把值放回去了
            } finally {
                lock.unlock();
            }
        }
        return process(drinks, wash, airTime, 0, 0);
    }

    /**
     * @param drinks   N个人开始喝完咖啡的时间,也就是N个人开始开始洗咖啡杯的时间点。
     * @param wash     洗咖啡杯的机器洗一个咖啡杯的时间【串行】。
     * @param airTime  咖啡杯挥发干净的时间【并行】。
     * @param index    第index个人开始决策用洗咖啡杯子的机器洗还是让咖啡杯子自己挥发干净。
     * @param freeTime 洗咖啡杯的机器空闲时间。
     * @return 0到index-1做的决策不用管,返回从index到后续决策中所有咖啡杯子变干净的最短时间。
     */
    public static int process(int[] drinks, int wash, int airTime, int index, int freeTime) {
        //边界条件,当所有的咖啡杯都洗干净
        if (index == drinks.length) {
            return 0;
        }
        //(I)index号人决定用洗咖啡杯子的机器洗杯子
        //当前杯子洗碗的时间点【表示洗咖啡杯子的机器要洗下一个咖啡杯子的可用时间】
        int washFinishTime = Math.max(drinks[index], freeTime) + wash;
        int subWashTime = process(drinks, wash, airTime, index + 1, washFinishTime);
        int p1 = Math.max(washFinishTime, subWashTime);

        //(II)index号人决定将咖啡杯子自然挥发干净，不用洗咖啡杯子的机器,则洗咖啡杯子的机器的空闲时间是不变的。
        int airCleanTime = drinks[index] + airTime;
        int subAirCleanTime = process(drinks, wash, airTime, index + 1, freeTime);
        int p2 = Math.max(airCleanTime, subAirCleanTime);
        return Math.min(p1, p2);
    }
}






