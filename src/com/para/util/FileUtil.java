package com.para.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    public static double getFileSize(String filePath) throws Exception {
        try
        {
            File file =new File(filePath);
            if(file.exists()) {

                return file.length();
            }
            return 0;
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());//make custom exception for para
        }

    }



    public static int countLines(String str){
        String[] lines = str.split("\r\n|\r|\n");
        return  lines.length;//add 1 to account for the zeroth element?
    }

    public static String readAllBytesJava7(String filePath) throws Exception
    {
        String content = "";
        try
        {
            content = new String (Files.readAllBytes(Paths.get(filePath)));
            return content;
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());//make custom exception for para
        }

    }


}
