package algorithm.package01;

public class XorOperate {

    // 第二题：arr中，只有一种数，出现奇数次
    public static int printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int num : arr) {
            eor = eor ^ num;
        }
        System.out.println("出现奇数的那个数为eor==" + eor);
        return eor;
    }
    // 第三题：怎么把一个int类型的数，提出出来最右侧的1来
    //    a   = 01 1011 1001 0000   7056 = 2^12+2^11+2^9+2^8+2^7+2^4
    //得到 ans = 00 0000 0001 0000   16
    //计算过程
    //    a   = 01 1011 1001 0000
    //   ~a   = 10 0100 0110 1111
    //   ~a+1 = 10 0100 0111 0000
    // ans = a&(~a+1) = a&(-a)

    public static int getRightFirstOneNum(int num) {
        //return num & (~num+1);
        return num & (-num);
    }


    //第四题：arr中，有两种数，出现奇数次，其他数都出现了偶数次,分别打印出这两种数。
    //解题思路：分别设出现奇数次的数为a,b且(a!=b)。
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int num : arr) {
            eor ^= num;  //eor = a^b
        }
        // a 和 b是两种数
        // eor != 0
        // eor最右侧的1，提取出来
        // eor :     00110010110111000
        // rightOne :00000000000001000
        int rightOne = eor & (-eor); //假设a的左右边第一个数在某个位上为1，假设b在该位上为0。
        int onlyOne = 0; // eor'
        for (int num : arr) {
            //  arr[1] =  111100011110000
            // rightOne=  000000000010000
            // 表示数组中的某个数在最右侧为1的那个位上一定是1,只有满足这个条件下的数才能异或进eor'
            if ((num & rightOne) != 0) {
                onlyOne = onlyOne ^ num;
            }
        }
        int b = eor ^ onlyOne;
        System.out.println("a === " + onlyOne + ";" + "b====" + b);

    }


    public static void main(String[] args) {
        int[] arr1 = {3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1};
        printOddTimesNum1(arr1);

        int rightFirstNum = getRightFirstOneNum(7056);
        System.out.println("rightFirstNum ===" + rightFirstNum);


        int[] arr2 = {10,6,6,6,4,4,12,12,12,12,3,3};
        printOddTimesNum2(arr2);

        int[] arr3 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
        printOddTimesNum2(arr3);
     }

}
