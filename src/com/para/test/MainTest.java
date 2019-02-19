package com.para.test;

import com.para.bal.FileSizeMan;
import com.para.bal.MD5HashMan;
import com.para.bal.SHA256HashMan;
import com.para.bal.ScanMan;

import com.para.dal.DataAccess;
import com.para.model.Project;
import org.junit.jupiter.api.Test;



public class MainTest {
    String filePath = "C:/Users/gene/Downloads/Paradigm";
    String projectName = "Para";
    @Test
    public void ScanManTest()
    {
        ScanMan sm = new ScanMan();
        sm.newProject(projectName, filePath);

    }

    @Test
    public void ThreadTest()
    {
        try
        {
            DataAccess da = new DataAccess();
            Project p = da.getProject(projectName);
            MD5HashMan mhm = new MD5HashMan("md5", p);
            mhm.start();
            SHA256HashMan shm = new SHA256HashMan("SHA256", p);
            shm.start();
            FileSizeMan fsm = new FileSizeMan("file size", p);
            fsm.start();

        }
        catch (Exception e)
        {
            // Throwing an exception
            System.out.println ("Exception is caught");
        }

    }


}
