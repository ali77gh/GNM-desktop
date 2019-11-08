package com.gnm.desktop.core;


import java.io.*;


//  Makes & Reads  File Byts
//
//
public class FileIO {

    private static byte[] fileByts=null;


    private FileIO() {
        //........
    }


    public static byte[] readFileByts(String fileName){

        try {

            String pathToFile=get(fileName);
            FileInputStream fis=new FileInputStream(pathToFile);
            fileByts=fis.readAllBytes();
            fis.close();

        } catch (Exception e) {
            Log.d("FileIO.readFileByts exeption", e.getMessage() + " -> " + e.getCause());
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
            Log.d("FileIO.writeBytsToFile exeption", e.getMessage() + " -> " + e.getCause());
        }

    }


    //Returns AbsolutePath
    private static String get(String File){

        File file = new File(File);

        return "//" + file.getAbsolutePath().replace("\\", "/");
    }


    public static void CopyFileUsingStream(File from, File to) {
        InputStream is;
        OutputStream os;
        try {
            is = new FileInputStream(from);
            os = new FileOutputStream(to);
            byte[] buffer = new byte[2048];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            is.close();
            os.close();
        } catch (IOException e) {
            Log.d("self", e.getMessage());
        }
    }

    public static void CopySignFileHere(File file) {
        CopyFileUsingStream(file, new File(System.getProperty("user.dir") + "/sign.sig"));
    }
}