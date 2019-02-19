package com.para.bal;

import com.para.dal.DataAccess;
import com.para.model.Project;

public class ScanMan {
    Project p;

    public void newProject(String projectName, String dirPath)
    {
        p = new Project(projectName, dirPath);

        try
        {
            DataAccess da = new DataAccess();
            da.addProject(p);//add the project to db
            da.addFiles(p);//add the files to the db for this project


        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());

        }

    }

}
