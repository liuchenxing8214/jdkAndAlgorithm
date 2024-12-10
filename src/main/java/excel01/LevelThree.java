package excel01;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class LevelThree {
    @ExcelProperty("三级节点")
    private String name;

}