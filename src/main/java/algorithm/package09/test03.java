package algorithm.package09;

import java.util.Arrays;
import java.util.List;

public class test03 {
    public static void main(String[] args) {
        List<Integer> parentList = Arrays.asList(1,3,4,5);
        List subList =  parentList.subList(1,3);
        subList.remove(0);
      //  parentList.add(2);
        System.out.println(subList);
    }
}
