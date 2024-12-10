package excel;

import java.io.IOException;
import java.util.List;

public class ExcelTest {
    public static void main(String[] args) {
        DataGenerator dataGenerator = new DataGenerator();
        List<TreeNode> fakeData = dataGenerator.generateFakeData(100); // 生成100条虚假数据

        DataGenerator excelExporter = new DataGenerator();
        String filePath = "D:\\excel\\fake_data.xlsx";
        try {
            excelExporter.exportToExcel(fakeData, filePath);
            System.out.println("Excel 文件已成功导出到: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("导出失败: " + e.getMessage());
        }
    }
}

