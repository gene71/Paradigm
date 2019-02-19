package com.para.model;

public class Parafile {

    String name;
    String path;
    String md5hash;
    String sha256hash;
    int size;
    int projectId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMd5hash() {
        return md5hash;
    }

    public void setMd5hash(String md5hash) {
        this.md5hash = md5hash;
    }

    public String getSha256hash() {
        return sha256hash;
    }

    public void setSha256hash(String sha256hash) {
        this.sha256hash = sha256hash;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
