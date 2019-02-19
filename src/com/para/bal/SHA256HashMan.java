package com.para.bal;

import com.para.dal.DataAccess;
import com.para.model.Parafile;
import com.para.model.Project;
import com.para.util.MD5Hasher;
import com.para.util.SHA256Hasher;

import java.util.List;

public class SHA256HashMan implements Runnable {

    private Thread t;
    private String threadName;
    private Project p;

    public SHA256HashMan(String threadName, Project p)
    {
        this.threadName = threadName;
        this.p = p;

    }

    @Override
    public void run()
    {
        try
        {
            DataAccess da = new DataAccess();
            List<Parafile> files = da.getFiles(p);

            for(int i = 0; i < files.size(); i++)
            {
                Parafile pf = files.get(i);
                int fid = da.getFileID(da.getProjectId(p), pf.getPath());
                da.setSHA256Hash(fid, SHA256Hasher.getMD5Checksum(pf.getPath()));

            }
            System.out.println(threadName + " complete " + files.size());//need to manage jobs in db

        }
        catch (Exception e)
        {
            e.printStackTrace();//probably need logforj
        }

    }

    public void start()
    {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.run ();
        }
    }
}
