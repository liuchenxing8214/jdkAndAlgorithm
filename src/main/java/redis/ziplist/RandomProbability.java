package redis.ziplist;

import java.util.Random;

public class RandomProbability {
    public static void main(String[] args) {
        Random random = new Random();
        int count0 = 0;
        int count1 = 0;
        int trials = 100000; // 进行 100,000 次实验

        for (int i = 0; i < trials; i++) {
            int number = random.nextInt(2);
            if (number == 0) {
                count0++;
            } else {
                count1++;
            }
        }

        System.out.println("0 的出现次数: " + count0);
        System.out.println("1 的出现次数: " + count1);
    }
}
