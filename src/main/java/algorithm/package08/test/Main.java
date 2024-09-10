package algorithm.package08.test;

public class Main {
    public static void main(String[] args) {
        MyComparator comparator = new MyComparator();
        MyHeap heap = new MyHeap(10, comparator);

        // 插入元素
        heap.insert(new MyObject(5));
        heap.insert(new MyObject(3));
        heap.insert(new MyObject(8));
        heap.insert(new MyObject(11));
        heap.insert(new MyObject(19));
        heap.insert(new MyObject(1));
        heap.insert(new MyObject(2));
        heap.insert(new MyObject(15));

        while (!heap.isEmpty()){
            // 提取堆顶元素
            MyObject top = heap.extract();
            System.out.println("Heap top: " + top.getValue());
        }

    }
}
