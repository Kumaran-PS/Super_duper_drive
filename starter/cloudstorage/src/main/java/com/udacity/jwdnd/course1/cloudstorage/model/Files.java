package com.udacity.jwdnd.course1.cloudstorage.model;

import java.util.Arrays;

public class Files {
    private Integer fileId;
    private String fileName;
    private  String contentType;
    private long fileSize;
    private Integer userId;
    private byte[] fileData;

    public Files(Integer fileId, String fileName, String contentType, long fileSize, Integer userId, byte[] fileData ) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.userId = userId;
        this.fileData = fileData;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

//    @Override
//    public String toString() {
//        return "Files{" +
//                "fileId=" + fileId +
//                ", fileName='" + fileName + '\'' +
//                ", contentType='" + contentType + '\'' +
//                ", fileSize=" + fileSize +
//                ", userId=" + userId +
//                ", fileData=" + Arrays.toString(fileData) +
//                '}';
//    }
}
