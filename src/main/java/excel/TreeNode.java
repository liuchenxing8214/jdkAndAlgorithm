package excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class TreeNode {
    @ExcelProperty("一级节点")
    private String firstLevel;

    @ExcelProperty("二级节点")
    private String secondLevel;

    @ExcelProperty("三级节点")
    private String thirdLevel;
}