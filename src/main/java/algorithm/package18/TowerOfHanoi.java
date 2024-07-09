package algorithm.package18;

public class TowerOfHanoi {

    public static void main(String[] args) {
        //hanoi1(3);
        hanoi2(3);
    }

    public static void hanoi1(int n) {
        leftToRight(n);
    }


    //把1~N层圆盘移动从左移动到位置
    public static void leftToRight(int n) {
        if (n == 1) {
            //如果只有一个盘子直接移动, Left-> Right
            System.out.println("Move 盘子" + n + " from Left to Right");
            return;
        }
        //如果盘子大于1个,第一大步骤：将上面的N-1个盘子从左边移动到中间。
        leftToMid(n - 1);
        //第二步骤：将左边上面的第N个盘子从左边移动到右边。
        System.out.println("Move 盘子" + n + " from Left to Right");
        //第三步骤：将中间的N-1个盘子从中间移动到右边。
        midToRight(n - 1);
    }

    //把1~N层圆盘移动从左边位置移动到中间位置
    public static void leftToMid(int n) {
        if (n == 1) {
            //如果只有一个盘子直接移动, Left-> Mid
            System.out.println("Move 盘子" + n + " from Left to Mid");
            return;
        }
        //第一大步骤：1~N-1,从left->right
        leftToRight(n - 1);
        //第二步骤：N 从left->Mid
        System.out.println("Move 盘子" + n + " from Left to Mid");
        //第三步骤： 1~N-1,从 right->Mid
        rightToMid(n - 1);
    }

    //把1~N层圆盘移动从中间位置移动到右边位置
    public static void midToRight(int n) {
        if (n == 1) {
            //如果只有一个盘子直接移动, Mid-> Right
            System.out.println("Move 盘子" + n + " from Mid to Right");
            return;
        }
        //第一大步骤：1~N-1,从 Mid-> Left
        midToLeft(n - 1);
        //第二步骤：N 从  Mid -> Right
        System.out.println("Move 盘子" + n + " from Mid to Right");
        //第三大步骤：1~N-1,从 Left -> Right
        leftToRight(n - 1);
    }

    //把1~N层圆盘移动从中间位置移动到左边位置
    public static void midToLeft(int n) {
        if (n == 1) {
            //如果只有一个盘子直接移动, Mid-> Left
            System.out.println("Move 盘子" + n + " from Mid to Left");
            return;
        }
        //第一大步骤：1~N-1,从 Mid-> Right
        midToRight(n - 1);
        //第二步骤：N 从  Mid -> Left
        System.out.println("Move 盘子" + n + " from Mid to Left");
        //第三大步骤：1~N-1,从 Right -> Left
        rightToLeft(n - 1);
    }

    //把1~N层圆盘移动从右边位置移动到中间位置
    public static void rightToMid(int n) {
        if (n == 1) {
            //如果只有一个盘子直接移动, Right-> Mid
            System.out.println("Move 盘子" + n + " from Right to Mid");
            return;
        }
        //第一大步骤：1~N-1,从 Right-> Left
        rightToLeft(n - 1);
        //第二步骤：N 从  Right -> Mid
        System.out.println("Move 盘子" + n + " from Right to Mid");
        //第三大步骤：1~N-1,从 Left -> Mid
        leftToMid(n - 1);
    }

    //把1~N层圆盘移动从右边位置移动到左边位置
    public static void rightToLeft(int n) {
        if (n == 1) {
            //如果只有一个盘子直接移动, Right-> Left
            System.out.println("Move 盘子" + n + " from Right to Left");
            return;
        }
        //第一大步骤：1~N-1,从 Right-> Mid
        rightToMid(n - 1);
        //第二步骤：N 从  Right -> Left
        System.out.println("Move 盘子" + n + " from Right to Left");
        //第三大步骤：1~N-1,从 Mid -> Left
        midToLeft(n - 1);
    }

    public static void hanoi2(int n) {
        if (n > 0) {
            fun(n, "Left", "Right", "Mid");
        }
    }

    //把上面6个函数合成一个函数,对函数进行了更进一步的抽象【汉诺塔游戏的改进版】
    public static void fun(int n, String from, String to, String other) {
        if (n == 1) {
            //如果只有一个盘子直接移动, from-> to
            System.out.println("Move 盘子" + n + " from " + from + " to " + to);
            return;
        }
        //第一大步骤：1~N-1,从 from -> other【other变成的子函数的to,自己的to变成other】
        fun(n - 1, from, other, to);
        //第二步骤：N 从  from -> to
        System.out.println("Move 盘子" + n + " from " + from + " to " + to);
        //第三大步骤：1~N-1,从 other -> to 【other变成的子函数的from,自己的from变成other】
        fun(n - 1, other, to, from);
    }

}
