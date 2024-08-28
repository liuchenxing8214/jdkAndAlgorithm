package algorithm.package06;

public class CountOfRangeSum_01 {

    public static void main(String[] args) {
        int[] nums = {-2, 5, -1};
        System.out.println(countRangeSum(nums, -2, 2));
    }

    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long[] sums = new long[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }
        //sum(nums[0....i]) = X
        //以i位置为结尾的子数组之和,有多少在[lower,upper]上
        //转换成i位置之前的前缀和有多少在[X-upper,X-lower]上。
        return process(sums, 0, nums.length - 1, lower, upper);
    }

    public static int process(long[] sums, int L, int R, int lower, int upper) {
        //merge中不可能出现的一种子数组情况
        //以i位置为结尾的子数组 连同自己包含i位置之前所有的数据。 nums[0....i]
        //(I)sums数组不能再拆分了，因为只有一个元素
        if (L == R) {
            return sums[L] >= lower && sums[L] <= upper ? 1 : 0;
        }
        //(II)sums数组还可以拆分
        int mid = (L + R) >> 1;
        int leftNum = process(sums, L, mid, lower, upper);
        int rightNum = process(sums, mid + 1, R, lower, upper);
        int mergeNum = merge(sums, L, mid, R, lower, upper);
        return leftNum + rightNum + mergeNum;
    }

    public static int merge(long[] sums, int L, int mid, int R, int lower, int upper) {
        //指向任意一个右组的数据，且和为X，看左组中有多少个数满足[X-upper,X-lower]
        int windowL = L;
        int windowR = L;
        int res = 0;
        //取值范围是[windowL,windowR),左开右闭来取值的。
        for (int i = mid + 1; i <= R; i++) {
            long leftLimit = sums[i] - upper;
            long rightLimit = sums[i] - lower;
            //windowL和windowR都是不回退的【即都是单挑递增的】
            //满足的条件 （1）sums[windowR] <=rightLimit (2)sums[windowL]>=leftLimit
            while (windowR <= mid && sums[windowR] <= rightLimit) {
                windowR++;
            }
            while (windowL <= mid && sums[windowL] < leftLimit) {
                windowL++;
            }
            res += windowR - windowL;
        }

        //(b)正常归并排序的merge
        long[] help = new long[R - L + 1];
        int p1 = L;
        int p2 = mid + 1;
        //p1和p2都没越界
        int index = 0;
        while (p1 <= mid && p2 <= R) {
            //p1和p2谁小，就把数据赋值到辅助数组
            help[index++] = sums[p1] <= sums[p2] ? sums[p1++] : sums[p2++];
        }
        //p1和p2必须右一个越界，因为再相等情况下，规定了复制左组的数
        while (p1 <= mid) {
            help[index++] = sums[p1++];
        }
        while (p2 <= R) {
            help[index++] = sums[p2++];
        }
        //将help中的数据复制到sums中去
        for (int i = 0; i < help.length; i++) {
            sums[L + i] = help[i];
        }
        return res;
    }


}
