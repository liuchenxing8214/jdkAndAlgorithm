package algorithm.package08.test;


import java.util.Comparator;

public class MyHeap {
    private MyObject[] heap;
    private int size;
    private Comparator<MyObject> comparator;

    public MyHeap(int capacity, Comparator<MyObject> comparator) {
        this.heap = new MyObject[capacity];
        this.size = 0;
        this.comparator = comparator;
    }

    // 插入元素
    public void insert(MyObject obj) {
        if (size >= heap.length) {
            throw new IllegalStateException("Heap is full");
        }
        heap[size] = obj;
        size++;
        heapifyUp(size - 1);
    }

    public boolean isEmpty(){
        return size==0;
    }


    // 上浮操作
    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (comparator.compare(heap[index], heap[parentIndex]) < 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    // 交换元素
    private void swap(int i, int j) {
        MyObject temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // 取出堆顶元素
    public MyObject extract() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        MyObject root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return root;
    }

    // 下沉操作
    private void heapifyDown(int index) {
        while (index < size) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int largest = index;

            if (leftChild < size && comparator.compare(heap[leftChild], heap[largest]) < 0) {
                largest = leftChild;
            }
            if (rightChild < size && comparator.compare(heap[rightChild], heap[largest]) < 0) {
                largest = rightChild;
            }
            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }
}

