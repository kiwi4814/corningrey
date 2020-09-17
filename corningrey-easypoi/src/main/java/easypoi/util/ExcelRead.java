package easypoi.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;

import java.time.LocalDate;
import java.time.Month;

public class ExcelRead {

    public static void main(String[] args) {
        //ExcelReader reader = ExcelUtil.getReader(FileUtil.file("/Users/heqifeng/Desktop/Energy Report20130802~0804.xlsx"), "报告");
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file("/Users/heqifeng/Desktop/Energy Report20140609.xlsx"), "报告");
        Cell cell1 = reader.getCell(13, 7);
        var c1 = cell1.getNumericCellValue();
        Cell cell2 = reader.getCell(13, 8);
        var c2 = cell2.getNumericCellValue();
        Cell cell3 = reader.getCell(5, 3);
        var c3 = new Object();
        try {
            c3 = formatExcelDate(cell3.getNumericCellValue());
        } catch (IllegalStateException e) {
            c3 = cell3.getStringCellValue();
        }
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
    }

    private static Object formatExcelDate(double numericCellValue) {
        LocalDate date = LocalDate.of(1900, Month.JANUARY, 1);
        LocalDate localDate = date.plusDays((int) numericCellValue - 2);
        return localDate.toString();
    }
}
