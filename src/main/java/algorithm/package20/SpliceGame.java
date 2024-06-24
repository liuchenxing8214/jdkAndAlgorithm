package algorithm.package20;


import java.util.HashMap;
import java.util.Map;

/**
 * 拼纸游戏
 * https://leetcode.cn/problems/stickers-to-spell-word/description/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 */
public class SpliceGame {

    public static void main(String[] args) {
        String[] stickers = {"with", "example", "science"};
        String target = "thehat";
        System.out.println(minStickers(stickers, target));
        System.out.println(minStickers2(stickers, target));

    }


    public static int minStickers(String[] stickers, String target) {
        int result = process1(stickers, target);
        return (result == Integer.MAX_VALUE ? -1 : result);

    }


    /**
     * @param stickers 有无穷张贴纸
     * @param target   无穷贴纸中切割单个字母并重新排列成目标贴纸,
     * @return 返回你需要拼出 target 的最小贴纸数量
     */
    public static int process1(String[] stickers, String target) {
        //边界条件,假如目标贴纸target已经拼成,则不需要stickers中的贴纸
        if (target.length() == 0) {
            return 0;
        }
        //因为要尝试无数次要把target贴纸拼接出来,
        // (1)如果选择该贴纸【假设是第一场贴纸】下的所有分支都拼接不出来target,证明已经使用了贴纸无数张
        int afterNum = Integer.MAX_VALUE;
        //第一次选择假设选择第1张贴纸,后续拼成目标贴纸需要贴纸的最小张数返回。
        //第一次选择假设选择第2张贴纸,后续拼成目标贴纸需要贴纸的最小张数返回。
        //第一次选择假设选择第i张贴纸,后续拼成目标贴纸需要贴纸的最小张数返回。
        //第一次选择假设选择第N张贴纸,后续拼成目标贴纸需要贴纸的最小张数返回。
        //所有分支中最小的那个就是我想要的。
        for (String stick : stickers) {
            String restStr = minus(target, stick);
            //(a)如果减去的该贴纸没有让target目标字符串减少字符,证明选择该贴纸的这个决策是错误的【因为无论增加多少张该贴纸,都不会减少目标贴纸的字符】
            //(b)只有当target中的字符减少了,才证明该贴纸是需要的贴纸。
            if (restStr.length() != target.length()) {
                //只要能将target目标字符串减少到0,在选了任意一张贴纸的情况下,都能得到后续拼成目标贴纸需要贴纸的最小张数。
                //选择任意一张的决策中所有分支中的最小的
                afterNum = Math.min(afterNum, process1(stickers, restStr));
            }
        }
        //用了一张的情况下,后续有用了7张,返回8张
        return afterNum + (afterNum == Integer.MAX_VALUE ? 0 : 1);
    }

    public static String minus(String target, String stick) {
        char[] tar = target.toCharArray();
        char[] sti = stick.toCharArray();
        int[] count = new int[26];
        //统计目标字符串每个字符的的数量。
        for (char ch : tar) {
            count[ch - 'a']++;
        }
        //stick中有多少个相关字符,target中减去多少相应的字符。
        for (char ch : sti) {
            count[ch - 'a']--;
        }
        StringBuilder builder = new StringBuilder();
        //将剩下的统计目标字符串每个字符的的数量变成顺序排好的字符串。
        for (int i = 0; i < 26; i++) {
            // count[0]  表示a有多少个
            // count[1]  表示b有多少个
            // count[25] 表示z有多少个
            if (count[i] > 0) {
                for (int j = 0; j < count[i]; j++) {
                    builder.append((char) ('a' + i));
                }
            }
        }
        return builder.toString();
    }

    /**
     * 不能改成动态规划,只能用傻缓存的方式减少重复计算
     */

    public static int minStickers2(String[] stickers, String target) {
        //边界条件,假如目标贴纸target已经拼成,则不需要stickers中的贴纸
        //改成二维数组,行表示有多少个贴纸，列表示每一贴纸的词频统计
        int N = stickers.length;
        int[][] dp = new int[N][26];
        for (int i = 0; i < stickers.length; i++) {
            char[] chaArr = stickers[i].toCharArray();
            for (char ch : chaArr) {
                dp[i][ch - 'a']++;
            }
        }
        Map<String, Integer> map = new HashMap<>();
        int result = process2(dp, target,map);
        return (result == Integer.MAX_VALUE ? -1 : result);


    }


    public static int process2(int[][] stickersCount, String target,Map<String, Integer> map) {
        //边界条件,假如目标贴纸target已经拼成,则不需要stickers中的贴纸
        if(map.containsKey(target)){
            return map.get(target);
        }
        if (target.length() == 0) {
            return 0;
        }
        //对目标字符串的字片统计
        int[] tarCount = new int[26];
        char[] tarCharArr = target.toCharArray();
        for (char tar : tarCharArr) {
            tarCount[tar - 'a']++;
        }
        int afterNum = Integer.MAX_VALUE;
        for (int row = 0; row < stickersCount.length; row++) {
            int[] curCount = stickersCount[row];
            //如果当前所选择的卡片包含target字符串的首个字符,目标字符串词频统计减去该贴纸的词频统计
            if (curCount[tarCharArr[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < 26; i++) {
                    if (tarCount[i] > 0) {
                        int num = tarCount[i] - curCount[i];
                        for (int k = 0; k < num; k++) {
                            builder.append((char) ('a' + i));
                        }
                    }
                }
                String rustStr = builder.toString();
                afterNum = Math.min(afterNum, process2(stickersCount, rustStr,map));
            }
        }
        int ans = afterNum + (afterNum == Integer.MAX_VALUE ? 0 : 1);
        map.put(target,ans);
        return ans;
    }

}