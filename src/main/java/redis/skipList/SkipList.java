package redis.skipList;

import java.util.Random;

public class SkipList {

    final int HEAD_VALUE = -1;
    final Node HEAD = new Node(HEAD_VALUE);

    Node head;
    int level;
    int length; //链表的数量

    public SkipList() {
        this.head = HEAD;
        this.level = 1;
        this.length = 1;
    }

    public class Node {
        private int val;
        private Node next;
        private Node down;

        public Node(int val) {
            this(val, null, null);
        }

        public Node(int val, Node next, Node down) {
            this.val = val;
            this.next = next;
            this.down = down;
        }
    }

    public boolean search(Integer target) {
        return get(target, head) != null;
    }


    private Node get(Integer target, Node from) {
        Node node = head;
        //1.从左往右,从上往下开始遍历,
        while (node != null) {
            //(i)如果当前节点的下个节点值一直比target要小,当前节点指向下个节点
            while (node.next != null && node.next.val < target) {
                node = node.next;
            }
            //(ii)当前节点的下个节点等于target
            Node next = node.next;
            if (next != null && next.val == target) {
                return node;
            }
            //(iii)如果当前节点的下个节点为空或者大于target
            node = node.down;
        }
        return null;
    }

    public void add(Integer num) {
        Node node = head;
        //1.从左往右,从上往下开始遍历,
        //假如level=4,表示索引层为3,不包含原始的链表层
        Integer i = 0;
        Node[] nodes = new Node[level];
        while (node != null) { //node.down==0,表示已经到了原始的链表层(level=1)
            //(i)如果当前节点的下个节点值一直比target要小,当前节点指向下个节点
            while (node.next != null && node.next.val < num) {
                node = node.next;
            }
            //记录在每一层在下沉的过程过程中,记录每一级要新增节点的前一个节点。
            nodes[i++] = node;
            //(iii)如果当前节点的下个节点为空或者大于target
            node = node.down;
        }
        node = nodes[--i]; //这里的i多加了一次
        //如果当前节点的下个节点为空或者大于target,就新建一个节点，插入到当前节点后面
        Node newNode = new Node(num, node.next, null);
        node.next = newNode;
        length++;
        //(iii)抛硬币来创建索引
        createIndexOfNode(newNode, nodes, i);
    }

    /**
     * @param target     给目标节点添加索引，根据抛硬币来决定
     * @param nodes      记录在每一层在下沉的过程过程中,记录每一级要新增节点的前一个节点。
     * @param indexLevel 索引的层级,不包含原始链表 【假如level=4,表示索引层为3,indexLevel=3不包含原始的链表层】
     *                   indexLevel :[2,1,0]
     */
    private void createIndexOfNode(Node target, Node[] nodes, Integer indexLevel) {
        Random random = new Random();
        //记录下面一层的节点【一级索引的下面节点默认是新添加的节点】
        Node downNode = target;
        Integer coins = random.nextInt(2);

        while (coins == 1 && indexLevel > 0) {
            //在跳表的层级范围之内
            //取nodes倒数第二个node,即是一级索引
            Node preNode = nodes[--indexLevel];
            Node newNode = new Node(target.val, preNode.next, downNode);
            preNode.next = newNode;
            //当前新建的节点作为下次新建索引节点下个节点
            downNode = newNode;
            coins = random.nextInt(2);
        }
        if (coins == 1) {
            //在跳表的层级范围之外
            Node newNode = new Node(target.val, null, downNode);
            Node newHead = new Node(HEAD_VALUE, newNode, head);
            head = newHead;
            level++;

        }
    }

    public boolean erase(Integer num) {
        Node node = head;
        //1.从左往右,从上往下开始遍历,
        boolean exit = false;
        while (node != null) {
            //(i)如果当前节点的下个节点值一直比target要小,当前节点指向下个节点
            while (node.next != null && node.next.val < num) {
                node = node.next;
            }
            //(ii)当前节点的下个节点等于target,当前节点的下个节点
            Node next = node.next;
            if (next != null && next.val == num) {
                node.next = next.next;
                next.next = null; //help gc 没有指针指向next,next也不指向任何节点
                exit = true;
            }
            //(iii)如果当前节点的下个节点为空或者大于target
            node = node.down;
        }
        if (exit) {
            length--;
        }
        return exit;
    }

    public static void main(String[] args) {
        SkipList skiplist = new SkipList();
        skiplist.add(1);
        skiplist.add(2);
        skiplist.add(3);
        System.out.println(skiplist.search(0));   // 返回 false
        skiplist.add(4);
        System.out.println(skiplist.search(1));   // 返回 true
        System.out.println(skiplist.erase(0));    // 返回 false，0 不在跳表中
        System.out.println(skiplist.erase(1));    // 返回 true
    /*    Gson gson = new Gson();
        System.out.println(gson.toJson(skiplist));*/
        System.out.println(skiplist.search(1));   // 返回 false，1 已被擦除
    }


}