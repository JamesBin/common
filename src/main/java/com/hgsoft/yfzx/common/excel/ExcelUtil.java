package com.hgsoft.yfzx.common.excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * 功能描述：返回一个特定样式的工作簿以及导出excel文件
 *
 * @Author: guozhiwen
 * @File: ExcelUtil.java
 * @DATE: 2016/1/18
 * @TIME: 15:00
 */
public class ExcelUtil {
    /**
     * 功能描述：返回一个特定样式的工作簿
     *
     * @param filename
     * @param excelHeader
     * @param wb
     * @return
     */
    public static HSSFSheet excelStructure(String filename, String[] excelHeader, HSSFWorkbook wb) {
        HSSFSheet sheet = wb.createSheet(filename);

        HSSFCellStyle headStyle = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 23);
        headStyle.setFont(font);
        headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直

        for (int i = 0; i < excelHeader.length; i++) {
            sheet.setDefaultColumnStyle(i, style);
        }

        HSSFRow row0 = sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, excelHeader.length - 1));
        row0.setHeightInPoints(30);
        HSSFCell cell00 = row0.createCell(0);
        cell00.setCellValue(filename);
        cell00.setCellStyle(headStyle);

        HSSFRow row2 = sheet.createRow(2);
        for (int i = 0; i < excelHeader.length; i++) {
            HSSFCell cell = row2.createCell(i);
            cell.setCellValue(excelHeader[i]);
            sheet.setColumnWidth(i, 5000);
        }

        return sheet;
    }

    /**
     * 功能描述：导出excel文件
     *
     * @param response
     * @param request
     * @param filename
     * @param wb
     * @throws Exception
     */
    public static void exportExcel(HttpServletResponse response, HttpServletRequest request, String filename, HSSFWorkbook wb) throws Exception {

        String agent = request.getHeader("User-Agent").toUpperCase();
        if (agent.indexOf("MSIE") > 0 || agent.indexOf("TRIDENT") > 0) {
            filename = URLEncoder.encode(filename, "UTF-8");
        } else {
            filename = new String(filename.getBytes("utf-8"), "ISO_8859_1");
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment; filename=" + filename + ".xls");

        ServletContext application = request.getSession().getServletContext();
        String path = application.getRealPath("/WEB-INF/tmpExcel/");
        String destFileName = path
                + "\\" + (int) (Math.random() * 1000000000) + ".xls";
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs(); // 生成新的目录
        }
        wb.write(new FileOutputStream(destFileName));
        FileInputStream is = new FileInputStream(destFileName);
        response.addIntHeader("Content-Length", is.available());

        OutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);
        outputStream.flush();
        outputStream.close();

        if (is != null) {
            is.close();
        }
        File file = new File(destFileName);
        file.delete();
    }
}
