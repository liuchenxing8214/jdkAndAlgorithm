package javaBase.redis;

import java.util.HashMap;
import java.util.Map;

public class LruCache<T> {

    //限定缓存阈值,填加的元素数量超过了这个阈值,就开始删除链表尾巴上的元素【最不常用的元素删除掉】
    private int limit;
    private Node head;
    private Node tail;
    private Map<String, Node<T>> map; //记录缓存中每个key对应的Node

    public LruCache(int limit) {
        this.limit = Math.max(limit, 2);
        this.head = new Node("head", null);
        this.tail = new Node("tail", null);
        head.next = tail;
        tail.prev = head;
        this.map = new HashMap<>();
    }

    /**
     * 删除链表中的某一个节点
     */
    public void remove(Node node) {
        this.map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * 新添加的元素添加到头节点下面
     * 四个指针发生了变化
     */
    public void toHead(Node node) {
        node.next = this.head.next;
        node.prev = this.head;
        this.head.next.prev = node;
        this.head.next = node;
        this.map.put(node.key,node);
    }

    public T searchByKey(String key) {
        Node<T> node = this.map.get(key);
        if (node == null) {
            return null;
        }
        //(I)先将该查询的节点和原来的链表断开
        remove(node);
        //(II)将查询的节点链入到头节点之下
        toHead(node);
        return node.value;
    }

    public void put(String key, T value) {
        Node node = this.map.get(key);
        if (node == null) {
            //如果该key在缓存中不存在,
            node = new Node(key, value);
        } else {
            //(I)修改后的元素需要需要先和原来的链表断开
            node.value = value;
            //需要断开node
            remove(node);
        }
        //(II)新加的元素或者修改的元素链入到头节点之下
        toHead(node);
        //(III)如果新加的元素超过了这个阈值,就开始删除链表尾巴上的元素【最不常用的元素删除掉】
        if (this.map.size() > limit) {
            Node last = this.tail.prev;
            this.map.remove(last.key);
            remove(last);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(head);
        Node current = head.next; // 从头节点的下一个开始
        builder.append(current);
        while ((current = current.next) != null) { // 遍历到尾节点
            builder.append(current);
        }
        return builder.toString();
    }


    public static class Node<T> {
        private Node prev;
        private Node next;
        private String key;
        private T value;

        public Node(String key, T value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("(");
            builder.append(this.prev == null ? null : this.prev.key);
            builder.append("<-");
            builder.append(this.key);
            builder.append("->");
            builder.append(this.next == null ? null : this.next.key);
            builder.append(")");
            return builder.toString();
        }
    }


    public static void main(String[] args) {
        LruCache cache = new LruCache(5);
        System.out.println(cache);

        cache.put("1", 1);
        System.out.println(cache);
        cache.put("2", 2);
        System.out.println(cache);
        cache.put("3", 3);
        System.out.println(cache);
        cache.put("4", 4);
        System.out.println(cache);
        cache.put("5", 5);
        System.out.println(cache);
        cache.put("6", 6);
        System.out.println(cache);
        cache.searchByKey("2");
        System.out.println(cache);
        cache.put("7", 7);
        System.out.println(cache);
        cache.put("4", 10);
        System.out.println(cache);
    }


}
