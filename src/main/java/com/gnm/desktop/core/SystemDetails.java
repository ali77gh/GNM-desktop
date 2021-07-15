package com.gnm.desktop.core;


import java.io.*;
import java.util.Scanner;

public class SystemDetails{

    public static final int OS_LINUX = 0;
    public static final int OS_WINDOWS = 1;
    public static final int OS_MAC_OS_X = 2;
    public static final int OS_HAVE_NO_IDEA = 3;

    public static int getOs() {

        String osName = System.getProperty("os.name");
        String osNameMatch = osName.toLowerCase();
        if (osNameMatch.contains("linux")) {
            return OS_LINUX;
        } else if (osNameMatch.contains("windows")) {
            return OS_WINDOWS;
        } else if (osNameMatch.contains("mac os") || osNameMatch.contains("macos") || osNameMatch.contains("darwin")) {
            return OS_MAC_OS_X;
        } else {
            return OS_HAVE_NO_IDEA;
        }
    }

    public static String getHardwareId() {

        switch (getOs()) {
            case OS_LINUX:
                return getLinuxSerialNumber();

            case OS_WINDOWS:
                return getWinSerialNumber();

            case OS_MAC_OS_X:
                return getMacOsSerialNumber();

            default:
                throw new RuntimeException("");
        }
    }


    private static String sn = null;

    // --------------------------windows--------------------------
    private static String getWinSerialNumber() {

        String sn = null;

        OutputStream os = null;
        InputStream is = null;

        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try {
            process = runtime.exec(new String[]{"wmic", "bios", "get", "serialnumber"});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        os = process.getOutputStream();
        is = process.getInputStream();

        try {
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scanner sc = new Scanner(is);
        try {
            while (sc.hasNext()) {
                String next = sc.next();
                if ("SerialNumber".equals(next)) {
                    sn = sc.next().trim();
                    break;
                }
            }
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (sn == null) {
            throw new RuntimeException("Cannot find computer SN");
        }

        return sn;
    }


    // --------------------------linux----------------------------

    private static String getLinuxSerialNumber() {

        sn = "PK28YX7";

        if (sn == null) {
            readDmidecode();
        }
        if (sn == null) {
            readLshal();
        }
        if (sn == null) {
            throw new RuntimeException("Cannot find computer SN");
        }

        return sn;
    }

    private static BufferedReader read(String command) {

        OutputStream os = null;
        InputStream is = null;

        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try {
            process = runtime.exec(command.split(" "));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        os = process.getOutputStream();
        is = process.getInputStream();

        try {
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new BufferedReader(new InputStreamReader(is));
    }

    private static void readDmidecode() {

        String line = null;
        String marker = "Serial Number:";
        BufferedReader br = null;

        try {
            br = read("dmidecode -t system");
            while ((line = br.readLine()) != null) {
                if (line.indexOf(marker) != -1) {
                    sn = line.split(marker)[1].trim();
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static void readLshal() {

        String line = null;
        String marker = "system.hardware.serial =";
        BufferedReader br = null;

        try {
            br = read("lshal");
            while ((line = br.readLine()) != null) {
                if (line.indexOf(marker) != -1) {
                    sn = line.split(marker)[1].replaceAll("\\(string\\)|(\\')", "").trim();
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    // --------------------------mac os---------------------------
    public static final String getMacOsSerialNumber() {

        if (sn != null) {
            return sn;
        }

        OutputStream os = null;
        InputStream is = null;

        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try {
            process = runtime.exec(new String[]{"/usr/sbin/system_profiler", "SPHardwareDataType"});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        os = process.getOutputStream();
        is = process.getInputStream();

        try {
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        String marker = "Serial Number";
        try {
            while ((line = br.readLine()) != null) {
                if (line.contains(marker)) {
                    sn = line.split(":")[1].trim();
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (sn == null) {
            throw new RuntimeException("Cannot find computer SN");
        }

        return sn;
    }

}