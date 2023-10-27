package com.servlet;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.utils.DBUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/upload")
@MultipartConfig
public class Fetch extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        response.setHeader("X-Content-Type-Options", "nosniff");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

        Part filePart = request.getPart("file");
        String fileName = request.getParameter("filename");
        InputStream fileInputStream = filePart.getInputStream();
        response.getWriter().print("{\"status\":\"ok\"}");
        String saveDirectory = "/test";
        File directory = new File(saveDirectory);


        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath = saveDirectory + File.separator + fileName;

        try {
            // 使用Files类将文件保存到指定目录
            Path targetPath = Paths.get(filePath);
            Files.copy(fileInputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            response.getWriter().print("上传失败" + e.getMessage());
        } finally {
            // 关闭输入流
            fileInputStream.close();
        }
    }
}

