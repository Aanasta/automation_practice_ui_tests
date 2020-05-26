package com.automationpractice.utils;

import io.qameta.allure.Step;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.*;

import java.io.File;

public class ExcelManager {

    @Step("Read data from Excel document")
    public static String getDataFromCell(String filename, String cellAddress) {
        ExcelManager manager = new ExcelManager();
        Sheet sheet = manager.getFirstSheetOf(filename);
        return getStringFromCell(manager.getCell(sheet, cellAddress));
    }

    private Workbook createWorkbook(String filename) {
        try {
            return WorkbookFactory.create(new File(filename));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Exception has been caught\n");
        }
    }

    private Sheet getFirstSheetOf(String filename) {
        Workbook workbook = createWorkbook(getClass().getClassLoader().getResource(filename).getFile());
        return workbook.getSheetAt(0);
    }

    private static String getStringFromCell(Cell cell) {
        return cell.getRichStringCellValue().getString();
    }

    private Cell getCell(Sheet sheet, String cellAddress) {
        CellReference cellReference = new CellReference(cellAddress);
        Row row = sheet.getRow(cellReference.getRow());
        return row.getCell(cellReference.getCol());
    }
}
