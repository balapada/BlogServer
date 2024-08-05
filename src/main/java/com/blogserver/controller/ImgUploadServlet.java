package com.blogserver.controller;

import com.blogserver.service.ImgUploadService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/upload")
@MultipartConfig
public class ImgUploadServlet extends HttpServlet {
    private static final Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss").create();
    private final ImgUploadService imgUploadService = new ImgUploadService();
    private static final String SAVE_DIRECTORY = "photo";
    private String fileName;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 获取文件上传的目录在服务器中的绝对路径
        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + SAVE_DIRECTORY;

        // 创建存储目录，如果不存在的话
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        String action = request.getParameter("action");

        if(action.equals("profile")){

            String userAccount = request.getParameter("tag");

            // 处理文件上传
            for (Part part : request.getParts()) {
                fileName = extractFileName(part);
                part.write(savePath + File.separator + fileName);

                //更新数据库url
                imgUploadService.updateProfile(SAVE_DIRECTORY + "/" + fileName, userAccount);
            }

            response.setContentType("text/plain");
            try (PrintWriter out = response.getWriter()) {
                out.print(gson.toJson("上传成功!"));
                out.flush();
            }
        }

        if(action.equals("blogPhoto")){
            // 处理文件上传
            for (Part part : request.getParts()) {
                fileName = extractFileName(part);
                part.write(savePath + File.separator + fileName);
            }

            response.setContentType("text/plain");
            try (PrintWriter out = response.getWriter()) {
                out.print(gson.toJson("上传成功!"));
                out.flush();
            }
        }

    }

    // 从 Part 中提取文件名
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");

        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return "";
    }
}
