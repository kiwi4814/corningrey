package easypoi.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.sun.deploy.net.MessageHeader;
import easypoi.entity.Gas;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ExcelRead {

    private static List<Gas> gasList = new ArrayList<>();

    public static void main(String[] args) {
        String year = "2013";
        // ExcelReader reader = ExcelUtil.getReader(FileUtil.file("/Users/heqifeng/Desktop/Energy Report20140609.xlsx"), "报告");
        File file = new File("E:\\kiwi\\Desktop\\2013~2015能耗报表\\" + year);
        listAllFiles(file);
        gasList = gasList.stream().sorted(Comparator.comparingLong(Gas::getDateL)).collect(Collectors.toList());
        intoExcel(year);
    }


    private static Object formatExcelDate(double numericCellValue) {
        LocalDate date = LocalDate.of(1900, Month.JANUARY, 1);
        LocalDate localDate = date.plusDays((int) numericCellValue - 2);
        return localDate.toString();
    }

    private static long dateToLong(String date) {
        date = date.replaceAll("-", "").replaceAll("/", "").replaceAll(" ", "");
        return Long.parseLong(date);
    }


    static void intoList(String path) {
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(path), "报告");
        Cell cell1 = reader.getCell(13, 7);
        Object c1 = cell1.getNumericCellValue();
        Cell cell2 = reader.getCell(13, 8);
        Object c2 = cell2.getNumericCellValue();
        Cell cell3 = reader.getCell(5, 3);
        Object c3 = new Object();
        try {
            String dateStr = DateFormatUtils.format(cell3.getDateCellValue(), "yyyy-MM-dd");
            gasList.add(Gas.builder().dateL(dateToLong(dateStr)).date(dateStr).hydGas(c1).natGas(c2).build());
        } catch (IllegalStateException e) {
            c3 = cell3.getStringCellValue();
            String weekends = c3.toString();
            weekends = weekends.substring(0, weekends.indexOf("~"));
            if (weekends.length() >= 8) {
                String[] date = weekends.split("-");
                if (date.length != 3) {
                    date = weekends.split("/");
                }
                if (date.length == 3) {
                    LocalDate start = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
                    gasList.add(Gas.builder().dateL(dateToLong(start.toString())).date(start.toString()).hydGas(c1).natGas(c2).build());
                    gasList.add(Gas.builder().dateL(dateToLong(start.plusDays(1).toString())).date(start.plusDays(1).toString()).hydGas(c1).natGas(c2).build());
                    gasList.add(Gas.builder().dateL(dateToLong(start.plusDays(2).toString())).date(start.plusDays(2).toString()).hydGas(c1).natGas(c2).build());
                }
            }

        }

    }

    static void intoExcel(String year) {
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/" + year + ".xlsx");

// 合并单元格后的标题行，使用默认标题样式
        // writer.merge(4, "一班成绩单");
// 一次性写出内容，使用默认样式，强制输出标题
        writer.write(gasList, true);
// 关闭writer，释放内存
        writer.close();
    }


    public static void listAllFiles(File dir) {
        if (dir == null || !dir.exists()) {
            return;
        }
        if (dir.isFile()) {
            intoList(dir.getAbsolutePath());
        } else {
            for (File f : Objects.requireNonNull(dir.listFiles())) {
                listAllFiles(f);
            }
        }

    }
}
