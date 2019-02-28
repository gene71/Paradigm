package com.para.dal;

import com.para.model.Parafile;
import com.para.model.Project;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DataAccess {
    public Connection connect;


    public DataAccess() throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://197.167.176.147:3306", "root", "pwd*");


        } catch (Exception e) {
            throw e;
        }


    }

    public void addProject(Project p) throws Exception {
        try {

            PreparedStatement ps = connect
                    .prepareStatement("INSERT INTO `para_schema`.`projects` (`name`, `directory`)" +
                            " VALUES ('" + p.getName() + "', '" + p.getDirectoryPath() + "');");
            ps.executeUpdate();

        } catch (Exception e) {
            throw new Exception(e.getMessage());//make custom exception for para
        }


    }

    public Project getProject(String projectName) throws Exception {
        try {
            Project p = new Project();
            Statement s = connect.createStatement();
            String query = "SELECT * FROM para_schema.projects where name like '"
                    + projectName + "';";
            ResultSet rs = s.executeQuery(query);


            while (rs.next()) {
                p.setName(rs.getString(2));
                p.setDirectoryPath(rs.getString(3));

            }

            return p;
        } catch (Exception e) {
            throw new Exception(e.getMessage());//make custom exception for para
        }
    }

    public int getProjectId(Project p) throws Exception {
        try {
            Statement s = connect.createStatement();
            String query = "SELECT * FROM para_schema.projects";
            ResultSet rs = s.executeQuery(query);

            int id = 0;
            while (rs.next()) {
                if (rs.getString(2).toString().equals(p.getName())) {
                    id = rs.getInt(1);
                }

            }

            return id;


        } catch (Exception e) {
            throw new Exception(e.getMessage());//make custom exception for para
        }

    }

    public void addFiles(Project p) throws Exception {
        try {
            int projId = getProjectId(p);
            File fp = new File(p.getDirectoryPath());
            String[] fileFilter = {"cs", "c", "cpp", "java", "h"};//need to make prop file


            Iterator<File> fileIt = FileUtils.iterateFiles(fp, fileFilter, true);

            while (fileIt.hasNext()) {
                File afile = fileIt.next();
                String filePath =
                        afile.getAbsolutePath().replaceAll("\\\\", "/");//change to java file path format
                PreparedStatement ps = connect
                        .prepareStatement("INSERT INTO `para_schema`.`files` (`idprojects`, `filename`, `filepath`)" +
                                " VALUES ('" + projId + "', '" + afile.getName()
                                + "', '" + filePath + "');");


                ps.executeUpdate();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());//make custom exception for para
        }

    }

    public int getFileID(int projectId, String dbColfilePath) throws Exception {
        try {
            Statement s = connect.createStatement();
            String query = "SELECT * FROM para_schema.files where idprojects like " +
                    projectId + " and filepath like '" + dbColfilePath + "';";

            ResultSet rs = s.executeQuery(query);

            int id = 0;
            while (rs.next()) {
                id = rs.getInt(1);

            }
            return id;
        } catch (Exception e) {
            throw new Exception(e.getMessage());//make custom exception for para
        }
    }

    public List<Parafile> getFiles(Project p) throws Exception {
        try {
            List<Parafile> files = new ArrayList<>();
            Statement s = connect.createStatement();
            String query = "SELECT * FROM para_schema.files where idprojects like " +
                    getProjectId(p) + ";";
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                Parafile pf = new Parafile();
                pf.setProjectId(rs.getInt(2));
                pf.setName(rs.getString(3));
                pf.setPath(rs.getString(4));
                pf.setSize(rs.getInt(5));
                pf.setMd5hash(rs.getString(6));
                pf.setSha256hash(rs.getString(7));
                files.add(pf);

            }
            return files;
        } catch (Exception e) {
            throw new Exception(e.getMessage());//make custom exception for para
        }

    }

    public void setFileSize(int fileID, int size) throws Exception {
        try {
            //UPDATE `para_schema`.`files` SET `filesize`='12' WHERE `idfiles`='5';
            PreparedStatement ps = connect
                    .prepareStatement("UPDATE `para_schema`.`files` SET `filesize`=' " +
                            size + "' WHERE `idfiles`='" + fileID + "';");
            ps.executeUpdate();

        } catch (Exception e) {
            throw new Exception(e.getMessage());//make custom exception for para
        }
    }

    public void setMD5Hash(int fileId, String md5hash) throws Exception {
        try {

            PreparedStatement ps = connect
                    .prepareStatement("UPDATE `para_schema`.`files` SET `filemd5hash`=' " +
                            md5hash + "' WHERE `idfiles`='" + fileId + "';");
            ps.executeUpdate();

        } catch (Exception e) {
            throw new Exception(e.getMessage());//make custom exception for para
        }

    }

    public void setSHA256Hash(int fileId, String sha256hash) throws Exception {
        try {

            PreparedStatement ps = connect
                    .prepareStatement("UPDATE `para_schema`.`files` SET `filesha256hash`=' " +
                            sha256hash + "' WHERE `idfiles`='" + fileId + "';");
            ps.executeUpdate();

        } catch (Exception e) {
            throw new Exception(e.getMessage());//make custom exception for para
        }
    }

    public void addScan(int projectId) throws Exception {
        try {
            PreparedStatement ps = connect
                    .prepareStatement("INSERT INTO `para_schema`.`scans` (`idproject`, `datetime`)"
                            + " VALUES (" + projectId + ", CURRENT_TIMESTAMP);");


            ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception(e.getMessage());//make custom exception for para
        }
    }

    public int getLastInsertId() throws Exception
    {
        try
        {
            Statement s = connect.createStatement();
            String query = "SELECT last_insert_ID();";
            ResultSet rs = s.executeQuery(query);

            int id = 0;
            while (rs.next()) {
                id = rs.getInt(1);

            }

            return id;


        } catch (Exception e) {
            throw new Exception(e.getMessage());//make custom exception for para
        }

    }

    public void addScanStat(int scanId, String scanName) throws Exception
    {
        try
        {
            PreparedStatement ps = connect
                    .prepareStatement("INSERT INTO `para_schema`.`scanstat` (`idscans`, `scannername`)"
                            + " VALUES (" + scanId +", '" + scanName +"');");


            ps.executeUpdate();
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());//make custom exception for para
        }
    }

    public void updateScanStat(int scanId) throws Exception
    {
        try
        {

            PreparedStatement ps = connect
                    .prepareStatement("UPDATE `para_schema`.`scanstat` SET `status`" +
                            " = 1 WHERE `idscanstat` = " + scanId + ";");
            ps.executeUpdate();

        } catch (Exception e)
        {
            throw new Exception(e.getMessage());//make custom exception for para
        }
    }


    public void truncateAll() throws Exception {
        try
        {
            Statement s = connect.createStatement();
            String query = "truncate para_schema.scanstat;";
            s.execute(query);

            query = "truncate para_schema.scans;";
            s.execute(query);

            query = "truncate para_schema.files;";
            s.execute(query);

            query = "truncate para_schema.projects;";
            s.execute(query);
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());//make custom exception for para
        }
    }

    public int getScanStatId(int id, String name) throws Exception
    {
        try
        {
            Statement s = connect.createStatement();
            String query = "SELECT idscanstat FROM para_schema.scanstat" +
                    " where idscans = " + 1 + " and scannername like '" + name + "';";
            ResultSet rs = s.executeQuery(query);

            int scanStatid = 0;
            while (rs.next()) {
                scanStatid = rs.getInt(1);

            }

            return scanStatid;
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());//make custom exception for para
        }

    }

}


















