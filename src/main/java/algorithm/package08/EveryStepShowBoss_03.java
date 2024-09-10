package algorithm.package08;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


/**
 * 手动改写手写堆
 */
public class EveryStepShowBoss_03 {

    /**
     * 【最简单的暴力方法，时间复杂度很高】
     *
     * @param arr 用户id集合
     * @param op  用户表示对应的客户在相应的时间的依次操作时间【购买或者退货行为】
     * @param k   限定每次只有K个用户得奖
     * @return 在每一时刻, 用户在做了相应的操作之后, 与之对应的得奖名单都要返回。
     */
    public static List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
        //记录每一个唯一的用户id对应的customer实例
        HashMap<Integer, Customer> map = new HashMap<>();
        //候选区名单【用户购买商品数量由大变小排序,进入时间是由小到大排序】大根堆
        List<Customer> candidateList = new ArrayList<>();
        //得奖区名单【用户购买商品数量由小变大排序,进入时间是由小到大排序】小根堆
        List<Customer> prizeList = new ArrayList<>();
        //收集每个时刻的获奖名单集合
        List<List<Integer>> ans = new ArrayList<>();
        //遍历每个时刻,i表示用户arr[i]在i时间点进行的事件操作是op[i]
        for (int i = 0; i < arr.length; i++) {
            int id = arr[i];
            boolean eventFlag = op[i];
            //(IV)用户的购买数量为0,用户发生退货事件,
            //规则一:如果现在购买商品数为0，而且发生了退货事件,
            //让该用户的在该时刻的操作无效，即获奖名单和上个事件获奖名单是一样的
            if (!eventFlag && !map.containsKey(id)) {
                ans.add(getPrizeList(prizeList));
                continue;
            }
            //(I)用户的购买数量不为0,用户发生退货事件,
            //(II)用户的购买数量不为0,用户发生购买事件,
            //(III)用户的购买数量为0,用户发生购买事件,【这中情况下是没有用户,所以需要初始化】|【新用户,在候选区和得奖区都不存在这个用户】
            //(a)购买数量为0,第一种情况是用户首次进来购买事件
            //(b)购买数量为0,第二种情况是用户先发生过购买事件,接着发生过退货事件
            Customer customer = map.get(id);
            if (eventFlag && !map.containsKey(id)) {
                customer = new Customer(id, 0, 0);
            }
            if (eventFlag) {
                customer.buyNum++;
            } else {
                customer.buyNum--;
            }
            //当前用户是新用户,判断是进入候奖区还是得奖区
            if (!candidateList.contains(customer) && (!prizeList.contains(customer))) {
                customer.enterTime = i;
                map.put(id, customer);
                //得奖区没有满,该用户直接放在得奖区
                if (prizeList.size() < k) {
                    prizeList.add(customer);
                } else {
                    candidateList.add(customer);
                }
            }
            //该用户之前就在候奖区或者是得奖区,进入时间保持不变

            if (customer.buyNum == 0) {
                //在候选区和得奖区都要将这个用户删除
                map.remove(id);
            }
            //(a)将当前用户最新的购买数更新到候奖区或者得奖区域
            //(b)将候奖区和得奖区中购买数为0的客户删除掉
            remove(candidateList, customer);
            remove(prizeList, customer);
            candidateList.sort(new CandidateComparator());
            prizeList.sort(new PrizeComparator());
            //判断候选区的人能不能进入得奖区域
            move(candidateList, prizeList, i, k);
            ans.add(getPrizeList(prizeList));
        }
        return ans;
    }

    public static void move(List<Customer> candidateList,
                            List<Customer> prizeList,
                            int time,
                            int K) {
        //候选区为空
        if (candidateList.isEmpty()) {
            return;
        } else if (prizeList.size() < K) {
            //候选区不为空,且得奖品区也没有满 【将候选区的max放进得奖区】
            // 【当前操作的用户只可能之前在得奖区,且当前操作是退货事件，
            // 导致当前客户的购买数量为0,导致该用户在得奖区域被删除，
            // 从而导致得奖区域没有满】
            Customer candidate = candidateList.get(0);
            candidate.enterTime = time;
            candidateList.remove(0);
            prizeList.add(candidate);
        } else {
            //候选区不为空,且得奖品区满了
            Customer candidate = candidateList.get(0);
            Customer prize = prizeList.get(0);
            if (candidate.buyNum > prize.buyNum) {
                //候选区购买数最多的max客户大于得奖区购买最少的min客户
                //max客户要和min客户作交换,进入时间要重置。
                candidate.enterTime = time;
                prize.enterTime = time;
                candidateList.remove(0);
                prizeList.remove(0);
                candidateList.add(prize);
                prizeList.add(candidate);
            }
        }
    }

    public static void remove(List<Customer> customerList, Customer curCustomer) {
        List<Customer> nonZeroCustomerList = new ArrayList<>();
        for (Customer customer : customerList) {
  /*          //当前操作得客户的购买数发生了变化,需要修改。
            if (customer.id == curCustomer.id) {
                customer.buyNum = curCustomer.buyNum;
            }*/
            if (customer.buyNum != 0) {
                nonZeroCustomerList.add(customer);
            }
        }
        customerList.clear();
        for (Customer customer : nonZeroCustomerList) {
            customerList.add(customer);
        }
    }


    public static List<Integer> getPrizeList(List<Customer> prizeList) {
        List<Integer> list = new ArrayList<>();
        for (Customer customer : prizeList) {
            list.add(customer.id);
        }
        return list;

    }

    public static class CandidateComparator implements Comparator<Customer> {
        //【用户购买商品数量由大变小排序,进入时间是由小到大排序】大根堆
        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buyNum != o2.buyNum ? o2.buyNum - o1.buyNum : o1.enterTime - o2.enterTime;
        }
    }

    public static class PrizeComparator implements Comparator<Customer> {
        //【用户购买商品数量由小变大排序,进入时间是由小到大排序】小根堆
        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buyNum != o2.buyNum ? o1.buyNum - o2.buyNum : o1.enterTime - o2.enterTime;
        }
    }


    public static class Customer {
        private Integer id;        //用户id
        private Integer buyNum;    //用户购买商品数量
        private Integer enterTime; //用户首次进入候选区和得奖区时间

        public Customer(Integer id, Integer buyNum, Integer enterTime) {
            this.id = id;
            this.buyNum = buyNum;
            this.enterTime = enterTime;
        }
    }


    //对第二种方法,用堆结构进行优化
    public static class AwardStructure {
        //记录每一个唯一的用户id对应的customer实例
        private HashMap<Integer, Customer> map;
        private HeapGreater<Customer> candidateHeap;
        private HeapGreater<Customer> prizeHeap;
        private int limitNum; //限制得奖区域人的个数

        public AwardStructure(CandidateComparator candidateComparator,
                              PrizeComparator prizeComparator,
                              int limitNum) {
            this.map = new HashMap<>();
            this.candidateHeap = new HeapGreater<>(candidateComparator);
            this.prizeHeap = new HeapGreater<>(prizeComparator);
            this.limitNum = limitNum;
        }

        public static List<List<Integer>> bestTopK(int[] arr, boolean[] op, int k)  {
            AwardStructure awardStructure = new AwardStructure(new CandidateComparator(), new PrizeComparator(), k);
            //收集每个时刻的获奖名单集合
            List<List<Integer>> ans = new ArrayList<>();
            //遍历每个时刻,i表示用户arr[i]在i时间点进行的事件操作是op[i]
            for (int i = 0; i < arr.length; i++) {
                awardStructure.operate(arr[i], op[i], i);
                ans.add(awardStructure.byCustomerList());
            }
            return ans;

        }

        /**
         * 某个用户按照时间点的顺序依次进行操作,对堆结构产生实时变化
         */
        public void operate(int id, boolean eventFlag, int time) {
            //(IV)用户的购买数量为0,用户发生退货事件,
            //规则一:如果现在购买商品数为0，而且发生了退货事件,
            //让该用户的在该时刻的操作无效，即获奖名单和上个事件获奖名单是一样的
            //System.out.println("id==="+id+"eventFlag==="+eventFlag+" ,time==="+time+" ,limitNum=="+limitNum);
            if (!eventFlag && !map.containsKey(id)) {
                return;
            }
            //(I)用户的购买数量不为0,用户发生退货事件,
            //(II)用户的购买数量不为0,用户发生购买事件,
            //(III)用户的购买数量为0,用户发生购买事件,【这中情况下是没有用户,所以需要初始化】|【新用户,在候选区和得奖区都不存在这个用户】
            //(a)购买数量为0,第一种情况是用户首次进来购买事件
            //(b)购买数量为0,第二种情况是用户先发生过购买事件,接着发生过退货事件
            Customer customer = map.get(id);
            if (eventFlag && !map.containsKey(id)) {
                customer = new Customer(id, 0, 0);
            }
            int buyNum = 0;
            if (eventFlag) {
                buyNum = customer.buyNum + 1;
            } else {
                buyNum = customer.buyNum - 1;
            }
            //当前用户是新用户,判断是进入候奖区还是得奖区
            if (!candidateHeap.contains(customer) && (!prizeHeap.contains(customer))) {
                customer.buyNum = buyNum;
                customer.enterTime = time;
                map.put(id, customer);
                //得奖区没有满,该用户直接放在得奖区
                if (prizeHeap.size() < limitNum) {
                    prizeHeap.push(customer);
                } else {
                    candidateHeap.push(customer);
                }
            } else if (candidateHeap.contains(customer)) {
                if (buyNum == 0) {
                    map.remove(id);
                    candidateHeap.remove(customer);
                } else {
                    customer.buyNum = buyNum;
                    candidateHeap.realign(customer);
                }
            } else {
                if (buyNum == 0) {
                    map.remove(id);
                    prizeHeap.remove(customer);
                } else {
                    customer.buyNum = buyNum;
                    prizeHeap.realign(customer);
                }
            }
            candidateMoveToPrize(time);

        }

        public void candidateMoveToPrize(int time) {
            if (candidateHeap.isEmpty()) {
                return;
            } else if (prizeHeap.size() < limitNum) {
                //候选区有人,得奖区没满
                Customer candidate = candidateHeap.pop();
                candidate.enterTime = time;
                prizeHeap.push(candidate);
            } else {
                //候选区不为空,且得奖品区满了
                if (candidateHeap.peek().buyNum > prizeHeap.peek().buyNum) {
                    //候选区购买数最多的max客户大于得奖区购买最少的min客户
                    //max客户要和min客户作交换,进入时间要重置。
                    Customer candidate = candidateHeap.pop();
                    Customer prize = prizeHeap.pop();
                    candidate.enterTime = time;
                    prize.enterTime = time;
                    candidateHeap.push(prize);
                    prizeHeap.push(candidate);
                }
            }
        }

        public List<Integer> byCustomerList() {
            List<Integer> idList = new ArrayList<>();
            for (Customer customer : prizeHeap.getAllElements()) {
                idList.add(customer.id);
            }
            return idList;
        }

    }


    // 为了测试
    public static class Data {
        public int[] arr;
        public boolean[] op;

        public Data(int[] a, boolean[] o) {
            arr = a;
            op = o;
        }
    }

    // 为了测试
    public static Data randomData(int maxValue, int maxLen) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] arr = new int[len];
        boolean[] op = new boolean[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
            op[i] = Math.random() < 0.5 ? true : false;
        }
        return new Data(arr, op);
    }

    // 为了测试
    public static boolean sameAnswer(List<List<Integer>> ans1, List<List<Integer>> ans2) {
        if (ans1.size() != ans2.size()) {
            return false;
        }
        for (int i = 0; i < ans1.size(); i++) {
            List<Integer> cur1 = ans1.get(i);
            List<Integer> cur2 = ans2.get(i);
            if (cur1.size() != cur2.size()) {
                return false;
            }
            cur1.sort((a, b) -> a - b);
            cur2.sort((a, b) -> a - b);
            for (int j = 0; j < cur1.size(); j++) {
                if (!cur1.get(j).equals(cur2.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }



    public static void main(String[] args) {
        int maxValue = 10;
        int maxLen = 100;
        int maxK = 6;
        int testTimes = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Data testData = randomData(maxValue, maxLen);
            int k = (int) (Math.random() * maxK) + 1;
            int[] arr = testData.arr;
            boolean[] op = testData.op;
            List<List<Integer>> ans1 = topK(arr, op, k);
            List<List<Integer>> ans2 = AwardStructure.bestTopK(arr, op, k);
            if (!sameAnswer(ans1, ans2)) {
                for (int j = 0; j < arr.length; j++) {
                    System.out.println(arr[j] + " , " + op[j]);
                }
                System.out.println(k);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");
    }



/*public static void main(String[] args) {
    int[] arr = {6,6,9,2,7,5,9,2,4,2};
    boolean[] ops ={true,true,false,true,false,false,false,true,false,true};
    System.out.println(AwardStructure.bestTopK(arr,ops,1));
}*/

/*    public static void main(String[] args) {
        int[] arr = {5,0,0,5,0};
        boolean[] ops ={true,false,false,true,true};
        System.out.println(AwardStructure.bestTopK(arr,ops,6));
    }*/


}
