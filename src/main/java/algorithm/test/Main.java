package algorithm.test;

import java.util.PriorityQueue;

class Main {
    public static void main(String[] args) {

        //创建优先级队列
        PriorityQueue<Integer> numbers = new PriorityQueue<>(new CustomComparator());
        numbers.add(4);
        numbers.add(2);
        numbers.add(1);
        numbers.add(3);
        for (int i = 0; i < 4; i++) {
            Integer number = numbers.poll();
            System.out.println(number);
        }
    }
}
