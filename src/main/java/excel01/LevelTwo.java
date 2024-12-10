package excel01;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.List;
@Data
public class LevelTwo {
    @ExcelProperty("二级节点")
    private String name;

    @ExcelProperty("三级节点")
    private List<LevelThree> levelThrees;
}