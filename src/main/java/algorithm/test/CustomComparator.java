package algorithm.test;

import java.util.Comparator;

class CustomComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer number1, Integer number2) {
        int value =  number1.compareTo(number2);
        //元素以相反的顺序排序
        if (value > 0) {
            return 1;
        }
        else if (value < 0) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
