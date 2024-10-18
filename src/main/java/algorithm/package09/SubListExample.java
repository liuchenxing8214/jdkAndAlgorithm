package algorithm.package09;

import java.util.ArrayList;
import java.util.List;

public class SubListExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        // 获取子列表
        List<String> subList = list.subList(1, 3); // 包含 B 和 C

        System.out.println("子列表: " + subList);

        // 从子列表中移除元素
        subList.remove("B");
        list.remove("C"); // 这会导致 ConcurrentModificationException

        System.out.println("更新后的子列表: " + subList);
        System.out.println("原列表: " + list);
    }
}

