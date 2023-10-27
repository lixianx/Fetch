package com.servlet;
import com.alibaba.fastjson.JSON;
import com.bean.Files;
import com.utils.DBUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/find")
public class Find extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String keywords = request.getParameter("keywords");
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        List<Files> list = new ArrayList<>();
//        try {
//            conn = DBUtils.getConnection();
//            String sql = "select filename, date from file where filename like ?";
//            ps = conn.prepareStatement(sql);
//            ps.setString(1, '%' + keywords + "%");
//            rs = ps.executeQuery();
//            while(rs.next()){
//                String fileName = rs.getString("filename");
//                String date = rs.getString("date");
//                Files files = new Files(fileName, date);
//                list.add(files);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            DBUtils.close(conn, ps, rs);
//        }
//        String json = JSON.toJSONString(list);
//        response.getWriter().print(json);
        response.setHeader("X-Content-Type-Options", "nosniff");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        String directoryPath = "/test";

        File directory = new File(directoryPath);
        List<Files> list = new ArrayList<>();
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName();

                    long createTime = file.lastModified();
                    Date createDate = new Date(createTime);

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = dateFormat.format(createDate);

                    Files files1 = new Files(fileName, formattedDate);
                    list.add(files1);
                }
            }
                String json = JSON.toJSONString(list);
                response.getWriter().print(json);
        }
    }
}
