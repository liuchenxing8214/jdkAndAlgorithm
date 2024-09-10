package algorithm.package08;

public class Test02 {
    public static void main(String[] args) {
        HeapGreater<EveryStepShowBoss_03.Customer> heap =
                new HeapGreater<>(new EveryStepShowBoss_03.CandidateComparator());
        EveryStepShowBoss_03.Customer cur = new EveryStepShowBoss_03.Customer(1, 2, 4);
        heap.push(cur);
        for (EveryStepShowBoss_03.Customer customer : heap.getAllElements()) {
            System.out.println(heap.contains(cur));
            cur.setBuyNum(100);
            System.out.println(heap.contains(customer));
           // heap.push(customer);
            System.out.println("=====");
        }

    }
}
