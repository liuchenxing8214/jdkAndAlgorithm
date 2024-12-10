package excel01;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class ExcelExportExample {
    public static void main(String[] args) {
        List<LevelOne> levelOnes = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            LevelOne levelOne = new LevelOne();
            levelOne.setName("一级节点 " + i);
            List<LevelTwo> levelTwos = new ArrayList<>();

            for (int j = 1; j <= 3; j++) {
                LevelTwo levelTwo = new LevelTwo();
                levelTwo.setName("二级节点 " + i + "." + j);
                List<LevelThree> levelThrees = new ArrayList<>();

                for (int k = 1; k <= 2; k++) {
                    LevelThree levelThree = new LevelThree();
                    levelThree.setName("三级节点 " + i + "." + j + "." + k);
                    levelThrees.add(levelThree);
                }
                levelTwo.setLevelThrees(levelThrees);
                levelTwos.add(levelTwo);
            }
            levelOne.setLevelTwos(levelTwos);
            levelOnes.add(levelOne);
        }

        // 调用导出方法
        exportToExcel(levelOnes);
    }

    private static void exportToExcel(List<LevelOne> data) {
        String fileName = "D:\\excel\\tree_structure.xlsx";
        EasyExcel.write(fileName, LevelOne.class)
                .registerConverter(new ListConverter()) // 注册自定义转换器
                .sheet("树形结构")
                .doWrite(data);
    }
}