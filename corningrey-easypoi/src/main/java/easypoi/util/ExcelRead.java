package easypoi.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import easypoi.entity.Gas;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ExcelRead {

    private static List<Gas> gasList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String year = "2015";
        // ExcelReader reader = ExcelUtil.getReader(FileUtil.file("/Users/heqifeng/Desktop/Energy Report20140609.xlsx"), "报告");
        File file = new File("/Users/heqifeng/Desktop/2013_2015能耗报表/" + year);
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
            String weekends = c3.toString().replaceAll("/", "-");
            // 2-8
            String weekendEnd = weekends.substring(0, weekends.indexOf("-")) + "-" + weekends.substring(weekends.indexOf("~") + 1);
            String[] weekendEndArray = weekendEnd.split("-");
            if (weekendEndArray.length == 3) {
                LocalDate endDay = LocalDate.of(Integer.parseInt(weekendEndArray[0]), Integer.parseInt(weekendEndArray[1]), Integer.parseInt(weekendEndArray[2]));
                weekends = weekends.substring(0, weekends.indexOf("~"));
                String[] date = weekends.split("-");
                if (date.length == 3) {

                    LocalDate start = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
                    int n = (int) Period.between(start, endDay).getDays();
                    for (int i = 0; i < n + 1; i++) {
                        gasList.add(Gas.builder().dateL(dateToLong(start.plusDays(i).toString())).date(start.plusDays(i).toString()).hydGas(c1).natGas(c2).build());
                    }
                }
            }
        }
    }

    static void intoExcel(String year) throws IOException {
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/" + year + ".xlsx");
        // 合并单元格后的标题行，使用默认标题样式
        //writer.merge(0, "一班成绩单");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(gasList, true);
        // 关闭writer，释放内存
        writer.close();
    }

    static void intoExcel2(String year) throws IOException {
        ExportParams params = new ExportParams("课程详情", null, "课程详情");
        Workbook workbook = ExcelExportUtil.exportExcel(params, Gas.class, gasList);
        File targetFile = new File("temp.xls");
        if (targetFile.exists()) {
            boolean newFile = targetFile.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(targetFile);
        workbook.write(fos);
        fos.close();
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
