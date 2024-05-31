package algorithm.package01;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Code03_KM {

    //一个数组中有一种数出现K次,其他数都出现了M次,M>1,K<M,找到出现K次的数。
    public static int findKOccurrenceNumber(int[] arr, int k, int m) {
        //int类型有32位,新建一个32位的新数组,将原数组的数都以二进制的形式存入新数组。
        int[] t = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                //表示该数num在数组t中的i位置为1
                if ((num >> i & 1) == 1) {
                    t[i]++;
                }
            }
        }
        //将数组t中的每一位数对m进行求余,余数的二进制值数组就是K值。
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (t[i] % m != 0) {  //数组t在i的位置上为1
                ans = (1 << i) | ans;
            }
        }
        return ans;
    }

    public static int test(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == k) {
                return entry.getKey();
            }
        }
        return -1;
    }


    public static int[] arrayRandom(int maxKinds, int range, int k, int m) {
        //要返回的出现k次的那个数
        int kTimeNum = randomNumber(range);
        //创建数组中数的种类数>=2
        int numKinds = (int) (Math.random() * (maxKinds - 2)) + 2;
        int[] arr = new int[k + (numKinds - 1) * m];
        int index = 0;
        //将出现了k次的哪个数填充到arr数组中
        for (; index < k; index++) {
            arr[index] = kTimeNum;
        }
        numKinds--; //后面用来控制填充m数的边界条件
        //将出现了m次的那些数填充进去
        HashSet hashSet = new HashSet();
        //让随机生成m次的数不能重复,并且也不能和前面k次出现的数重复
        hashSet.add(kTimeNum);
        while (numKinds != 0) {
            int mTimeNum = 0;
            do {
                //随机生成出现m次的那个数
                mTimeNum = randomNumber(range);
            } while (hashSet.contains(mTimeNum));
            //如果数没有重复
            hashSet.add(mTimeNum);
            numKinds--;
            for (int i = 0; i < m; i++) {
                arr[index++] = mTimeNum;
            }
        }
        // arr已经填充好了
        // 将arr打散随机排列
        for (int i = 0; i < arr.length; i++) {
            int j = (int) (Math.random() * arr.length);   //[0,N-1]
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }


    // [-(range-1),range-1]
    //[0,range)+1 ===> [1,range]
    public static int randomNumber(int range) {
        return ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
    }

    public static void main(String[] args) {
        int kindMax = 9;
        int range = 201;
        int testTime = 100000;
        int max = 99;
        System.out.println("程序开始~~~~~~");
        for (int i = 0; i < testTime; i++) {
            int a = (int) (Math.random() * max) + 1; //1~9
            int b = (int) (Math.random() * max) + 1; //1~9
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            if (k == m) {
                m++;
            }
            int[] arr = arrayRandom(kindMax, range, k, m);
            int ans1 = findKOccurrenceNumber(arr, k, m);
            int ans2 = test(arr, k, m);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("程序出错了！！！！");
            }
        }
        System.out.println("程序结束~~~~~~");
    }

}
