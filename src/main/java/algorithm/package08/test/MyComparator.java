package algorithm.package08.test;

import java.util.Comparator;

public class MyComparator implements Comparator<MyObject> {
    @Override
    public int compare(MyObject o1, MyObject o2) {
        // 根据需要的逻辑进行比较
        // b-a 最大堆
        // a-b 最小堆
        return o2.getValue() - o1.getValue(); // 例如：按值升序
    }
}

