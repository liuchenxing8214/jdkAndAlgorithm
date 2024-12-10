package excel03;

import com.alibaba.excel.EasyExcel;


import java.util.ArrayList;
import java.util.List;


public class ExcelExportService {

    public static void generateAndExportData(String filePath) {
        List<DataNode> data = generateFakeData(100);
        EasyExcel.write(filePath, DataNode.class).sheet("Sheet1").doWrite(data);
    }

    private static List<DataNode> generateFakeData(int count) {
        List<DataNode> data = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String level1 = "一级节点"+i;
            String level2 = "二级节点"+i;
            String level3 = "三级节点"+i;
            int attributeValue = (int)(Math.random() * 100000);
            data.add(new DataNode(level1, level2, level3, attributeValue));
        }
        return data;
    }

    public static void main(String[] args) {
        generateAndExportData("D:\\excel\\tree_structure.xlsx");
    }
}
