package com.para.test;

import com.para.dal.DataAccess;
import com.para.model.Parafile;
import com.para.model.Project;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.List;

class DataAccessTest {
    @Test
    public void TestCon()throws Exception{
        DataAccess da = new DataAccess();
        Assertions.assertEquals(da.connect.isClosed(),false);//connection opened
        da.connect.close();//close it


    }

    @Test
    void setFileSize() throws Exception
    {
        DataAccess da = new DataAccess();
        da.setFileSize(5,125);
    }

    @Test
    void setFileMD5() throws Exception
    {
        DataAccess da = new DataAccess();
        da.setMD5Hash(5, "hash");
    }



    @Test
    void addProject() throws Exception{
        DataAccess da = new DataAccess();
        Project p = new Project("test1", "/tst" );

        try
        {
            da.addProject(p);
        }
        catch (Exception e)
        {
            Assertions.fail(e.getMessage());
        }


    }

    @Test
    void getProjectId() throws Exception
    {
        DataAccess da = new DataAccess();
        Project p = new Project("test1", "/tst" );

        try
        {
            System.out.println(da.getProjectId(p));
        }
        catch (Exception e)
        {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getProject() throws Exception
    {
        DataAccess da = new DataAccess();
        Project p = da.getProject("test1");
        System.out.println(p.getName() + " " + p.getDirectoryPath());
    }
    @Test
    void addFiles() throws Exception
    {
        DataAccess da = new DataAccess();
        Project p = new Project("ctest", "C:/Users/gene/Downloads/Paradigm" );
        da.addProject(p);
        da.addFiles(p);
    }

    @Test
    void getFileID() throws Exception
    {
//        DataAccess da = new DataAccess();
//        System.out.println(da.getFileID(1, "TemporaryGeneratedFile_036C0B5B-1481-4323-8D20-8F5ADCB23D92.cs"));
    }


    @Test
    void getFiles() throws Exception
    {
        DataAccess da = new DataAccess();
        Project p = da.getProject("Para");

        List<Parafile> files =  da.getFiles(p);
        Iterator<Parafile> filesIt = files.iterator();
        while(filesIt.hasNext())
        {
            Parafile pf = filesIt.next();
            System.out.println(pf.getProjectId() + " " + da.getFileID(da.getProjectId(p), pf.getName()) +
                    " " + pf.getName() + " " + pf.getPath());
        }
        System.out.println(files.size());
    }

    @Test
    void bigTest() throws Exception
    {
        //fix directory disconnect with how add files works since we add directory with project,
        //so you need the directory returned when query for project id.

    }

    @Test
    void addScan() throws Exception
    {
        DataAccess da = new DataAccess();
        da.addScan(3);
        System.out.println(da.getLastInsertId());
    }

    @Test
    void addScanStat() throws Exception
    {
        DataAccess da = new DataAccess();
        da.addScanStat(1, "FileScan");
        System.out.println(da.getLastInsertId());
    }

    @Test
    void updateScanStat() throws Exception
    {
        DataAccess da = new DataAccess();
        da.updateScanStat(1);

    }

    @Test
    void truncateAll() throws Exception
    {
        DataAccess da = new DataAccess();
        da.truncateAll();
    }

    @Test
    void getScanStatID() throws Exception
    {
        DataAccess da = new DataAccess();
        System.out.println(da.getScanStatId(2, "MD5Scan"));
    }



}