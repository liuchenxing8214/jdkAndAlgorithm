package algorithm.package04;

import lombok.Data;

public class DeleteGivenValue_02 {


    public static void main(String[] args) {
        Node node1 = new Node();
        node1.setValue(2);
        Node node2 = new Node();
        node2.setValue(2);
        Node node3 = new Node();
        node3.setValue(2);
        Node node4 = new Node();
        node4.setValue(3);
        Node node5 = new Node();
        node5.setValue(2);
        Node node6 = new Node();
        node6.setValue(4);
        Node node7 = new Node();
        node7.setValue(2);
        Node node8 = new Node();
        node8.setValue(2);
        Node node9 = new Node();
        node9.setValue(1);

        node8.setNext(node9);
        node7.setNext(node8);
        node6.setNext(node7);
        node5.setNext(node6);
        node4.setNext(node5);
        node3.setNext(node4);
        node2.setNext(node3);
        node1.setNext(node2);
        System.out.println(deleteGivenValue(node1,2));
        System.out.println(node1);

    }


    public static Node deleteGivenValue(Node head, int value) {
        //找到第一个不等于value的节点作为头节点
        while (head != null) {
            if (head.value == value) {
                head = head.next;
            } else {
                break;
            }
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == value) {
                //如果相等,直接跳过当前元素
                pre.next = cur.next;
            }else{
                //pre来到当前节点位置
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }


    @Data
    public static class Node {
        private int value;
        private Node next;
    }
}
