package com.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        FileOutputStream outputStream = null;

        try {
            // 1. 建立数据库连接
            String url = "jdbc:mysql://47.113.226.241:3306/learning";
            String username = "root";
            String password = "xLx00544@@";
            connection = DriverManager.getConnection(url, username, password);

            // 2. 执行查询
            String sql = "SELECT file, filename FROM file";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // 3. 获取 BLOB 数据
                InputStream blobInputStream = resultSet.getBinaryStream("file"); // 替换为你的 BLOB 列名
                String fileName = resultSet.getString("filename");
                String path = "/test/" + fileName;
                // 4. 将 BLOB 数据保存为 PDF 文件
                outputStream = new FileOutputStream(path); // 替换为你想要保存的文件路径
                byte[] buffer = new byte[1024*10];
                int bytesRead;
                while ((bytesRead = blobInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                System.out.println("PDF 文件保存成功！");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
