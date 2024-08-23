package algorithm.package04;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 循环数组实现队列
 */
public class RingArray_04 {

    public static class MyQueue {
        private int[] arr;
        private int limit; //arr的长度大小,限制arr最大能装多少数
        private int start; //队列从右边弹出一个数,从start位置取数,start++
        private int end;   //队列从左边加一个数,从end位置放置,end++
        private int size;  //arr中装了多少个数

        public MyQueue(int[] arr, int limit, int start, int end, int size) {
            this.arr = arr;
            this.limit = limit;
            this.start = start;
            this.end = end;
            this.size = size;
        }

        public void push(int value) {
            //判断队列是否满了吗
            if (size == limit) {
                throw new RuntimeException("队列已经装满数据了,不能加数据！！！");
            }
            //队列还没有满
            size++;
            //将新加的数据放到end位置
            arr[end] = value;
            //end的下标要加1
            end = ringAddOne(end);
        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("队列中的数据为空,不能弹出数据");
            }
            //队列中还有数据,从start位置取出要弹出的数据
            size--;
            int value = arr[start];
            start = ringAddOne(start);
            return value;
        }

        public boolean isEmpty() {
            return size == 0;
        }


        public int ringAddOne(int index) {
            if (index < limit - 1) {
                index++;
            } else {
                //如果下标等于数组内最大的下标,将该下标值重新跳到0位置
                index = 0;
            }
            return index;
        }
    }

    public static boolean isEqual(Integer o1, Integer o2) {
        if (o1 == null && o2 != null) {
            return false;
        }
        if (o1 != null && o2 == null) {
            return false;
        }
        if (o1 == null && o2 == null) {
            return true;
        }
        return o1.equals(o2);
    }

    public static void main(String[] args) {
        System.out.println("======开始测试=====");
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = new int[1000000];
            MyQueue myQueue = new MyQueue(arr, arr.length, 0, 0, 0);
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                int numq = (int) (Math.random() * value);
                if (j < 50 || Math.random() < 0.5) {
                    myQueue.push(numq);
                    queue.offer(numq);
                } else {
                    if (!isEqual(myQueue.pop(), queue.poll())) {
                        System.out.println("oops!");
                    }
                }
            }
        }
        System.out.println("======测试完成finish!========");
    }


}
