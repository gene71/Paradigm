package com.para.test;
import com.para.bal.ScanMan;
import com.para.dal.DataAccess;
import org.junit.jupiter.api.Test;



public class MainTest {
    String filePath = "C:/Users/gene/Downloads/Paradigm";
    String projectName = "Para";

    @Test
    public void ScanManTest() throws Exception {

        //clear all db data
        DataAccess da = new DataAccess();
        da.truncateAll();

        //start main test
        ScanMan sm = new ScanMan();
        sm.newProject(projectName, filePath);

    }




}
