package algorithm.package18;

import java.util.ArrayList;
import java.util.List;

public class PrintAllSubsequence {

    public static void main(String[] args) {
        String str = "abc";
        printAllSubsequence(str);
    }

    /**
     * @param str 打印某个字符串全部子序列
     */
    private static void printAllSubsequence(String str) {
        List<String> ans = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return;
        }
        char[] chars = str.toCharArray();
        List<Character> restList = new ArrayList<>();
        for (Character cha : chars) {
            restList.add(cha);
        }
        String path = "";
        proecss1(chars, 0, path, ans);
        //printAllSubsequence(chars, 1, "a", ans);
        for (String s : ans) {
            System.out.println(s);
        }
    }

    /**
     * @param chars 子序列又其中的某几个字符组成
     * @param index 到了第index次做决策的时候
     * @param path  0到index-1做决策的结果放在path中
     * @param ans   从index到N次做决策的所有结果都放在ans中
     */
    public static void proecss1(char[] chars, int index,
                                String path, List<String> ans) {
        if (index == chars.length) {
            ans.add(path);
            return;
        }
        //在第index次决策是,选择不要当前的字符的决定
        proecss1(chars, index + 1, path, ans);
        //在第index次决策是,选择要当前的字符的决定
        proecss1(chars, index + 1, path + chars[index], ans);
    }
}
