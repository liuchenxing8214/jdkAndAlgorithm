package algorithm.package08;

import lombok.Data;

import java.util.*;

/**
 * 加强堆的结构(第二题)
 */
@Data
public class HeapGreater<T> {
    //堆的存储空间
    private List<T> heap;
    //反向索引表，记录存放的每一个具体实例在堆结构中的位置【下标】
    private Map<T, Integer> map;
    //堆的大小
    private int heapSize;
    //按照某一个特定的方式进行排序，可以组成大根堆或者小根堆
    private Comparator<? super T> comp;

    public HeapGreater(Comparator<? super T> comparator) {
        this.heap = new ArrayList<>();
        this.map = new HashMap<>();
        this.heapSize = 0;
        this.comp = comparator;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T obj) {
        return map.containsKey(obj);
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T obj) {
        heap.add(obj);
        map.put(obj, heapSize);
        //在最后的位置插入到堆中【新加元素的下标是heapSize】
        heapInsert(heapSize++);
    }


    public T pop() {
        T ans = heap.get(0);
        //(I)将最后位置的对象和0位置的对象作交换
        swap(0, heapSize - 1);
        //(II)原来0位置的对象放置到最后的位置了，把它删除掉,heapSize也需要-1
        map.remove(ans);
        heap.remove(--heapSize);
        //(III)原来的最后位置的对象现在把它给放在0位置了,
        // 需要在0位置heapIfy一下，重新调成堆结构。
        heapIfy(0);
        return ans;
    }

    public List<T> getAllElements() {
        List<T> ans = new ArrayList<>();
        for (T t : heap) {
            ans.add(t);
        }
       return ans;
    }


    /**
     * 删除堆中任意一个对象
     */
    public void remove(T obj) {
        int index = map.get(obj);
        T last = heap.get(heapSize - 1);
        map.remove(obj);
        heap.remove(--heapSize);
        //如果删除不是最后一个元素
        if (obj != last) {
            map.put(last, index);
            heap.set(index, last);
            //再index位置重新调整堆结构
            realign(last);
        }

    }

    public void realign(T obj) {
            heapIfy(map.get(obj));
            heapInsert(map.get(obj));
    }


    /**
     * 将新加的节点不断向下移动，直到不能移动为止。
     */
    public void heapIfy(int index) {
        int leftChild = index * 2 + 1;
        while (leftChild < heapSize) {
            //假如右子节点存在(找到如论是降序还是升序过程中那个最想要的下标)
            //如果是小根堆,找的最小值的那个下标,
            //如果是大根堆,找的是最大值的那个下标,
            //由此根据排序比较器可以找到对应最小值或者最大值的那个下标.
            //(I)假如是小根堆【升序】,a-b<0,a在前,b在后,找出下面最小孩子的下标
            //(II)假设是大根堆【降序】,b-a<0,较小的b在前,a在后,由于是大根堆，a和b的位置需要互换。
            int best = (leftChild + 1) < heapSize
                    && comp.compare(heap.get(leftChild + 1), heap.get(leftChild)) < 0
                    ? (leftChild + 1) : leftChild;
            //在孩子和当前节点中找到最好的
            best = comp.compare(heap.get(best), heap.get(index)) < 0 ? best : index;
            //如果最想要的那个下标竟是自己,就不用替换【即是不用再往下沉了】
            if (best == index) {
                break;
            }
            swap(index, best);
            //当前节点指向下沉的那个位置【当前节点指向那个最想要去的那个位置】
            index = best;
            leftChild = index * 2 + 1;
        }
    }


    /**
     * 将新加的节点不断向上移动，直到不能移动为止。
     */
    public void heapInsert(int index) {
        //a假设是父节值，b表示子节点的值
        //如果是小根堆,a-b<0,a在前,b在后面，后面所有进来的数比a小都要和互换。
        //假如是大根堆,b-a<0,b在前,a在后面,由于现在是小的b在前，由于要转成大根堆，b和a要互换。
        while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, index - 1 / 2);
            //当前节点和父节点交换位置之后，当前节点指向父节点。
            index = index - 1 / 2;
        }
    }

    public void swap(int i, int j) {
        T obj1 = heap.get(i);
        T obj2 = heap.get(j);
        heap.set(i, obj2);
        heap.set(j, obj1);
        map.put(obj2, i);
        map.put(obj1, j);
    }


}
