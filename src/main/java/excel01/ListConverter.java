package excel01;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.util.ArrayList;
import java.util.List;

public class ListConverter implements Converter<List<LevelTwo>> {
    @Override
    public Class<List<LevelTwo>> supportJavaTypeKey() {
        return (Class<List<LevelTwo>>) (Class<?>) List.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public List<LevelTwo> convertToJavaData(CellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        // 实现从 CellData 转换为 List<LevelTwo> 的逻辑
        return new ArrayList<>(); // 根据实际需求实现解析逻辑
    }

    @Override
    public CellData convertToExcelData(List<LevelTwo> value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        // 实现从 List<LevelTwo> 转换为 CellData 的逻辑
        return new CellData(); // 根据实际需求实现转换逻辑
    }
}