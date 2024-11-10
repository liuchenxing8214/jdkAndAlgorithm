package redis.dict;

class Entry<K, V> {
    K key;
    V value;
    Entry<K, V> next; // 链表中的下一个节点

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }
}