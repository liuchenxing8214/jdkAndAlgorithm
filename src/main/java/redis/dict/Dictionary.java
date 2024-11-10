package redis.dict;

public class Dictionary<K, V> {
    private Entry<K, V>[] table; // 当前哈希表
    private int size; // 当前元素数量
    private int capacity; // 当前哈希表容量
    private int threshold; // 触发扩容的阈值

    @SuppressWarnings("unchecked")
    public Dictionary(int initialCapacity) {
        this.capacity = initialCapacity;
        this.table = new Entry[capacity];
        this.threshold = (int) (capacity * 0.75); // 75% 的负载因子
    }

    // 哈希函数
    private int hash(K key) {
        return key.hashCode() & (capacity - 1); // 使用掩码计算索引
    }

    // 插入元素
    public void put(K key, V value) {
        int index = hash(key);
        Entry<K, V> newEntry = new Entry<>(key, value);

        if (table[index] == null) {
            table[index] = newEntry;
        } else {
            Entry<K, V> current = table[index];
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value; // 更新现有键的值
                    return;
                }
                if (current.next == null) break; // 找到链表末尾
                current = current.next;
            }
            current.next = newEntry; // 添加到链表末尾
        }

        size++;
        if (size >= threshold) {
            resize(); // 扩容
        }
    }

    // 查找元素
    public V get(K key) {
        int index = hash(key);
        Entry<K, V> current = table[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value; // 找到返回值
            }
            current = current.next;
        }

        return null; // 未找到返回 null
    }

    // 扩容过程
    private void resize() {
        int newCapacity = capacity * 2; // 新容量是原来的两倍
        @SuppressWarnings("unchecked")
        Entry<K, V>[] newTable = new Entry[newCapacity];

        for (Entry<K, V> entry : table) {
            while (entry != null) {
                int newIndex = entry.key.hashCode() & (newCapacity - 1); // 新位置计算

                Entry<K, V> nextEntry = entry.next; // 保存下一个节点

                entry.next = newTable[newIndex]; // 插入新哈希表
                newTable[newIndex] = entry;

                entry = nextEntry; // 移动到下一个节点
            }
        }

        table = newTable; // 更新当前哈希表为新哈希表
        capacity = newCapacity; // 更新容量
        threshold *= 2; // 更新阈值
    }

    public void shrink() {
        if (size <= threshold / 4 && capacity > 4) { // 当元素数量过少且容量大于4时进行收缩
            int newCapacity = capacity / 2;
            @SuppressWarnings("unchecked")
            Entry<K, V>[] newTable = new Entry[newCapacity];
            for (Entry<K, V> entry : table) {
                while (entry != null) {
                    int newIndex = entry.key.hashCode() & (newCapacity - 1);
                    Entry<K, V> nextEntry = entry.next;
                    entry.next = newTable[newIndex];
                    newTable[newIndex] = entry;
                    entry = nextEntry;
                }
            }

            table = newTable;
            capacity = newCapacity;
            threshold /= 2;
        }
    }
}
