package com.gnm.desktop.core;

import java.io.FileOutputStream;
import java.io.File;
import java.io.FileInputStream;



//  Makes & Reads  File Byts
//
//
public class fileIO{

    private static byte[] fileByts=null;


    private fileIO(){
        //........
    }


    public static byte[] readFileByts(String fileName){

        try {

            String pathToFile=get(fileName);
            FileInputStream fis=new FileInputStream(pathToFile);
            fileByts=fis.readAllBytes();
            fis.close();

        } catch (Exception e) {
            Log.d("fileIO.readFileByts exeption", e.getMessage()+ " -> " + e.getCause());
        }


        return fileByts;
    }


    public static void writeBytsToFile(byte[] fileByts, String fileName){

        try {
            String pathToFile = get(fileName);
            FileOutputStream fos=new FileOutputStream(pathToFile);
            fos.write(fileByts);
            fos.close();
            
        } catch (Exception e) {
            Log.d("fileIO.writeBytsToFile exeption", e.getMessage() +" -> "+ e.getCause());
        }

    }


    //Returns AbsolutePath
    private static String get(String File){

        File file = new File(File);

        return "//" + file.getAbsolutePath().replace("\\", "/");
    }
}