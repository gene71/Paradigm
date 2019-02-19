package com.para.test;


import com.para.dal.DataAccess;
import com.para.model.Project;
import com.para.util.FileUtil;
import com.para.util.MD5Hasher;
import com.para.util.SHA256Hasher;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilTest {
    @Test
    public void TestGetFiles()
    {
        File fp = new File("C:/Users/gene/Downloads/Paradigm");
        String[] fileFilter = {"cs", "xml"};


        Iterator<File> fileIt = FileUtils.iterateFiles(fp, fileFilter, true);

        while (fileIt.hasNext())
        {
            File afile = fileIt.next();
            System.out.println(afile.getName());
        }



    }

    @Test
    public void TestHashers()throws Exception
    {
        String filename = "C:/Users/gene/Downloads/Paradigm/ParadigmScan/Program.cs";
        System.out.println(MD5Hasher.getMD5Checksum(filename));
        System.out.println(SHA256Hasher.getMD5Checksum(filename));
    }

    @Test
    public void TestgetFileSize() throws Exception
    {
        String filename = "C:/Users/gene/Downloads/Paradigm/ParadigmScan/Program.cs";
        System.out.println(Math.round(FileUtil.getFileSize(filename))
        );
    }

    @Test
    public void TestReadAllBytes() throws Exception
    {
        String filename = "C:/Users/gene/Downloads/Paradigm/ParadigmScan/Program.cs";

        System.out.println(FileUtil.readAllBytesJava7(filename));
    }

    @Test
    public void TestGetLinecount() throws Exception
    {
        String filename = "C:/Users/gene/Downloads/Paradigm/ParadigmScan/Program.cs";

        System.out.println(FileUtil.countLines(FileUtil.readAllBytesJava7(filename)));
    }





}