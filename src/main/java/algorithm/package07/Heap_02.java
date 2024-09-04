package algorithm.package07;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Heap_02 {

    public static class MyMaxHeap {
        private int[] heap;
        private int limit;
        private int heapSize;

        public MyMaxHeap(int limit) {
            this.limit = limit;
            this.heap = new int[limit];
            this.heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            if (limit == heapSize) {
                throw new RuntimeException("heap is full");
            }
            heap[heapSize] = value;
            heapInsert(heap, heapSize++);
        }

        public int pop() {
            if (heapSize == 0) {
                throw new RuntimeException("heap is empty");
            }
            //0位置的值作为最大值返回
            int ans = heap[0];
            //(I)0位置的值和最后的值作为交换,
            //(II)最后一个元素和堆结构断开【--heapSize】
            swap(heap, 0, --heapSize);
            //之前最后一个元素的值放在0位置上,【它的值一定不会比现在的孩子较大的值大,所以只会下沉】
            heapIfy(heap, 0, heapSize);
            return ans;
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
        public void heapInsert(int[] arr, int index) {

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
    }


    public static class MyComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }

    }

    public static void main(String[] args) {
        // 小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComparator());
        heap.add(5);
        heap.add(5);
        heap.add(5);
        heap.add(3);
        //  5 , 3
        System.out.println(heap.peek());
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        System.out.println(heap.peek());
        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }

        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyMaxHeap my = new MyMaxHeap(curLimit);
            PriorityQueue<Integer> test = new PriorityQueue<>(new MyComparator());
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.add(curValue);
                } else if (my.isFull()) {
                    if (my.pop() != test.poll()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.add(curValue);
                    } else {
                        if (my.pop() != test.poll()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");

    }


}
