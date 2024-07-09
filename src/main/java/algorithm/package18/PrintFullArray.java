package algorithm.package18;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全排列
 */
public class PrintFullArray {

    public static void main(String[] args) {
        String str = "acc";
        printFullArray(str);
        System.out.println("优化之后的版本");
        printFullArray2(str);
    }

    private static List<String> printFullArray(String str) {
        List<String> ans = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return ans;
        }
        List<Character> rest = new ArrayList<>();
        char[] chars = str.toCharArray();
        for (char cha : chars) {
            rest.add(cha);
        }
        String path = "";
        process(rest, ans, path);
        //printAllSubsequence(chars, 1, "a", ans);
        for (String s : ans) {
            System.out.println(s);
        }
        return ans;
    }

    /**
     * @param rest 剩余的字符数组。
     * @param ans  所有字符串的全排列都放到ans中去。
     * @param path 0~index-1次所有的决策放到path中
     */
    public static void process(List<Character> rest, List<String> ans, String path) {
        //表示所有的字符已经都选择完了
        if (rest.size() == 0) {
            ans.add(path);
            return;
        } else {
            for (int i = 0; i < rest.size(); i++) {
                char cur = rest.get(i);
                rest.remove(i);
                process(rest, ans, path + cur);
                //该支路走完之后将rest恢复到走该支路之前的初始数据状态。
                rest.add(i, cur);
            }
        }
    }


    private static List<String> printFullArray2(String str) {
        List<String> ans = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return ans;
        }
        char[] chars = str.toCharArray();
        process2(chars, 0, ans);
        for (String s : ans) {
            System.out.println(s);
        }
        return ans;
    }

    /**
     * @param chars 所有全排列组成的字符。
     * @param index 从index开始到N-1位置的的所有字符进行交换。
     * @param ans   将所有全排列的字符串放在ans中去。
     */
    public static void process2(char[] chars, int index, List<String> ans) {
        int N = chars.length;
        if (index == chars.length) {
            ans.add(String.valueOf(chars));
            return;
        } else {
            boolean[] isVisited = new boolean[256];
            for (int i = index; i < N; i++) {
                if(!isVisited[chars[i]]){
                    isVisited[chars[i]] = true;
                    swap(chars, index, i);
                    process2(chars, index + 1, ans);
                    swap(chars, index, i);
                }
            }
        }
    }

    public static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

}
