package algorithm.package08;

import lombok.Data;

import java.util.*;

public class CoverMax_01 {


    //多条线段中重合最多的区域，有多少条线段。【最简单的暴力方法，没有分】
    public static int maxCover1(int[][] lines) {
        if (lines == null || lines.length == 0) {
            return 0;
        }
        int minLeft = lines[0][0];
        int maxRight = lines[0][1];
        //遍历每一条线段，将线段中最大值和最小值都找出来
        for (int i = 1; i < lines.length; i++) {
            int L = lines[i][0];
            int R = lines[i][1];
            //求所有线段中最小的左边界
            minLeft = Math.min(L, minLeft);
            //求所有线段中最大的右边界
            maxRight = Math.max(R, maxRight);
        }
        //在最小和最大区域这个范围内，考察每一个点5,求出有多少前端包含了这个点5
        int maxCover = 0;
        for (double i = minLeft + 0.5; i < maxRight; i++) {
            int cover = 0;
            for (int j = 0; j < lines.length; j++) {
                if (lines[j][0] < i && i < lines[j][1]) {
                    cover++;
                }
            }
            maxCover = Math.max(maxCover, cover);
        }
        return maxCover;
    }

    //借助堆来求解
    public static int maxCover2(int[][] lines) {
        if (lines == null || lines.length == 0) {
            return 0;
        }
        //遍历每一条线段，将线段中最大值和最小值都找出来
        List<Line> lineList = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            int L = lines[i][0];
            int R = lines[i][1];
            Line line = new Line(L, R);
            lineList.add(line);
        }
        lineList.sort(new LineComparator());
        //默认是小根堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int maxCover = 0;
        for (Line line : lineList) {
            //计算每条线段,以该线段的左边界为开始位置，
            // 求出有多少线段穿这个开始位置【所以要将之前线段结束位置 小于等于 当前线段开始位置的线段全部弹出，不做统计。
            // 之前线段的开始位置一定小于当前线段的开始位置，因为是按线段开始位置由小到大排序的】
            while (!minHeap.isEmpty() && minHeap.peek() <= line.l) {
                minHeap.poll();
            }
            minHeap.add(line.r);
            int cover = minHeap.size();
            maxCover = Math.max(cover, maxCover);
        }
        return maxCover;
    }

    @Data
    public static class Line {
        private int l;
        private int r;

        public Line(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public static class LineComparator implements Comparator<Line> {

        //按照开始位置进行升序【由小到达进行排序】
        @Override
        public int compare(Line o1, Line o2) {
            return o1.l - o2.l;
        }
    }


    // for test
    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static void main(String[] args) {


        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = maxCover1(lines);
            int ans2 = maxCover2(lines);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }


}
