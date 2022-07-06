package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credentials {

    private String url;
    private String password;
    private Integer userId;
    private Integer credentialsId;
    private String userName;
    private String key;

    public Credentials(Integer credentialsId, String url, String userName, String key, String password, Integer userId) {
        this.credentialsId = credentialsId;
        this.url = url;
        this.userName = userName;
        this.key = key;
        this.password = password;
        this.userId = userId;
    }

    public Integer getCredentialsId() {
        return credentialsId;
    }

    public void setCredentialsId(Integer credentialsId) {
        this.credentialsId = credentialsId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

//    @Override
//    public String toString() {
//        return "Credentials{" +
//                "url='" + url + '\'' +
//                ", password='" + password + '\'' +
//                ", userId=" + userId +
//                ", credentialsId=" + credentialsId +
//                ", userName='" + userName + '\'' +
//                ", key='" + key + '\'' +
//                '}';
//    }
}
