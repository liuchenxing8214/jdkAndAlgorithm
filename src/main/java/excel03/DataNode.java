package excel03;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;


@Data
public class DataNode {

    @ExcelProperty("一级节点")
    private String level1;

    @ExcelProperty("二级节点")
    private String level2;

    @ExcelProperty("三级节点")
    private String level3;

    @ExcelProperty("属性值")
    private int attributeValue;

    // 构造器，getter 和 setter
    public DataNode(String level1, String level2, String level3, int attributeValue) {
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
        this.attributeValue = attributeValue;
    }
    public DataNode(){}

}