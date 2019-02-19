package com.para.model;

public class Project {

    String Name;
    String DirectoryPath;

    public Project()
    {}

    public Project(String name, String directoryPath)
    {
        Name = name;
        DirectoryPath = directoryPath;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDirectoryPath(String directoryPath) {
        DirectoryPath = directoryPath;
    }

    public String getName() {
        return Name;
    }

    public String getDirectoryPath() {
        return DirectoryPath;
    }
}
