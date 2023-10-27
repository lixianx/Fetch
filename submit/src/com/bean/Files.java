package com.bean;

public class Files {
    private String fileName;
    private String updateTime;

    public Files(String fileName, String updateTime) {
        this.fileName = fileName;
        this.updateTime = updateTime;
    }

    public Files() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
