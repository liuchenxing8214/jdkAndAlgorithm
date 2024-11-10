package redis.ziplist;

import java.util.Random;

public class LongMaxMinValue {
    public static void main(String[] args) {
        System.out.println("The maximum value of long is: " + Long.MAX_VALUE);
        System.out.println("The minimum value of long is: " + Long.MIN_VALUE);
        Long max = Long.MAX_VALUE;
        Random random = new Random();
        System.out.println(random.nextInt(2));
        if(max instanceof Long){
            System.out.println(Long.SIZE);

        }
    }
}