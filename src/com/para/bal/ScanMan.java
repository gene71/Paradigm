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
            da.addScan(da.getProjectId(p));//record init scan (add project and files)

            int scanId = da.getLastInsertId();//comes from addScan and is the same for all scans

            //multi thread here
            //////////////////////////Scanners///////////////////////////////////////
            //1.
            FileSizeMan fsm = new FileSizeMan("FileSizScan", p, scanId);
            fsm.start();
            //2.
            MD5HashMan mhm = new MD5HashMan("MD5Scan", p, scanId);
            mhm.start();
            //3.
            SHA256HashMan shm = new SHA256HashMan("SHA256Scan", p, scanId);
            shm.start();
            ///////////////////////////////////////////////////////////////////////////

            //check status
            int scanstatus = 3;//complete since there are three scanners above

            while(scanstatus == 0)
            {
                //make call to db for scanId
            }





        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());

        }

    }

}
