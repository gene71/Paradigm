package com.para.bal;

import com.para.dal.DataAccess;
import com.para.model.Parafile;
import com.para.model.Project;
import com.para.util.FileUtil;
import com.para.util.MD5Hasher;

import java.util.List;

public class FileSizeMan implements Runnable {
    private Thread t;
    private String threadName;
    private Project p;
    private int id;

    public FileSizeMan(String threadName, Project p, int scanId)
    {
        this.threadName = threadName;
        this.p = p;
        this.id = scanId;


    }

    @Override
    public void run()
    {
        try
        {
            DataAccess da = new DataAccess();
            List<Parafile> files = da.getFiles(p);

            //report start
            da.addScanStat(id, threadName);
            //get id for stat
            int scanStatId  = da.getScanStatId(id, threadName);


            for(int i = 0; i < files.size(); i++)
            {
                Parafile pf = files.get(i);
                int fid = da.getFileID(da.getProjectId(p), pf.getPath());
                int fileSize = (int)Math.round(FileUtil.getFileSize(pf.getPath()));
                da.setFileSize(fid, fileSize);


            }

            //report stop
            da.updateScanStat(scanStatId);

        }
        catch (Exception e)
        {
            e.printStackTrace();//probably need logforj
        }
    }

    public void start()
    {
        if (t == null) {
            t = new Thread (this, threadName);
            t.run ();
        }
    }
}
