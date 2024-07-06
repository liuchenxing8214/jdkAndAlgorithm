package algorithm.package20;

public class test01 {
    public static void main(String[] args) {
        boolean[] visited = new boolean[256];
        String str = "beautiful";
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
           if (!visited[chars[i]]) {
                visited[chars[i]] = true;
                System.out.println(chars[i]);
            } else {
                System.out.println("重复的字符为:" + chars[i]);
            }
        }
    }
}
