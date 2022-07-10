package com.example.easytravelpro;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class hoteliersinformation {
    
    public void export() {
        String jdbcURL = "jdbc:mysql://localhost:3306/easy_travel";
        String username = "root";
        String password = "integral4560sini";
 
        String excelFilePath = "ALLHOTELIERS.xlsx";
 //
        try {
            Connection connectme = DriverManager.getConnection(jdbcURL, username, password);
            String sql = "SELECT * FROM hotel";
            PreparedStatement getinfos = connectme.prepareStatement(sql);
            ResultSet result = getinfos.executeQuery();
 
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("MySheet");
 
            writeHeaderLine(sheet);
 
            writeDataLines(result, workbook, sheet);
 
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
 
            connectme.close();
 
        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
    }
    private void writeHeaderLine(XSSFSheet sheet) {
 
        XSSFRow headerRow = sheet.createRow(0);
 
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("UserName");
 
        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("HotelName");
 
        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Location");
 
        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("PhoneNumber");
 
        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("Email");

        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("Password");

        headerCell = headerRow.createCell(6);
        headerCell.setCellValue("RoomsCount");

        headerCell = headerRow.createCell(7);
        headerCell.setCellValue("Balance");

    }
    private void writeDataLines(ResultSet result, XSSFWorkbook workbook,XSSFSheet sheet) throws SQLException {
        int rowCount = 1;
 
        while (result.next()) {
            // System.out.println("HEY");
            String username = result.getString("UserName");
            String hotel = result.getString("HotelName");
            String location = result.getString("Location");
            String phone = result.getString("PhoneNumber");
            String email = result.getString("Email");
            String password = result.getString("Password");
            String rooms = result.getString("RoomsCount");
            String balance = result.getString("Balance");
            XSSFRow row = sheet.createRow(rowCount++);
 
            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(username);
 
            cell = row.createCell(columnCount++);
            cell.setCellValue(hotel);

            cell = row.createCell(columnCount++);
            cell.setCellValue(location);
 
            cell = row.createCell(columnCount++);
            cell.setCellValue(phone);
 
            cell = row.createCell(columnCount++);
            cell.setCellValue(email);

            cell = row.createCell(columnCount++);
            cell.setCellValue(password);

            cell = row.createCell(columnCount++);
            cell.setCellValue(rooms);

            cell = row.createCell(columnCount++);
            cell.setCellValue(balance);
        }
    }
 
}
