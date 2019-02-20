package com.para.bal;

import com.para.dal.DataAccess;
import com.para.model.Parafile;
import com.para.model.Project;
import com.para.util.MD5Hasher;

import java.util.List;

public class MD5HashMan implements Runnable {
    private Thread t;
    private String threadName;
    private Project p;
    private int id;

    public MD5HashMan(String threadName, Project p, int scanId)
    {
        this.threadName = threadName;
        this.p = p;
        this.id = scanId;

    }


    @Override
    public void run() {

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
                da.setMD5Hash(fid, MD5Hasher.getMD5Checksum(pf.getPath()));

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
