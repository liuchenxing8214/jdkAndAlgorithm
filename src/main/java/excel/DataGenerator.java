package excel;

import com.alibaba.excel.EasyExcel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    private static final String[] FIRST_LEVELS = {"A", "B", "C"};
    private static final String[] SECOND_LEVELS = {"1", "2", "3"};
    private static final String[] THIRD_LEVELS = {"X", "Y", "Z"};

    public List<TreeNode> generateFakeData(int count) {
        List<TreeNode> nodes = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            TreeNode node = new TreeNode();
            node.setFirstLevel(FIRST_LEVELS[random.nextInt(FIRST_LEVELS.length)]);
            node.setSecondLevel(SECOND_LEVELS[random.nextInt(SECOND_LEVELS.length)]);
            node.setThirdLevel(THIRD_LEVELS[random.nextInt(THIRD_LEVELS.length)]);
            nodes.add(node);
        }
        return nodes;
    }


    public static void exportToExcel(List<TreeNode> data, String filePath) throws IOException {
        EasyExcel.write(filePath, TreeNode.class)
                .sheet("树形数据")
                .doWrite(data);
    }

}