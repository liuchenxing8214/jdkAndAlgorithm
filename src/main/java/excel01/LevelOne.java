package excel01;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.List;

@Data
public class LevelOne {
    @ExcelProperty("一级节点")
    private String name;

    @ExcelProperty("二级节点")
    private List<LevelTwo> levelTwos;
}