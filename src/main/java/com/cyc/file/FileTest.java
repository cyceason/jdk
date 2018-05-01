package com.cyc.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by cyc_e on 2017/10/15.
 */
public class FileTest {

    public static void main(String[] args) {

    }

    public static void copyDir(String oldPath, String newPath) {
        File file = new File(oldPath);
        String[] filePath = file.list();
        File newPathFile = new File(newPath);
        if (!newPathFile.exists()) {
            newPathFile.mkdir();
        }

        for (int i = 0; i < filePath.length; i++) {
            String oldFile = oldPath + File.separator + filePath[i];
            String newFile = newPath + File.separator + filePath[i];
            if ((new File(oldFile)).isDirectory()) {
                copyDir(oldFile, newFile);
            }
            if (new File(oldFile).isFile()) {
                copyFile(oldFile, newFile);
            }
        }
    }

    public static void copyFile(String oldPath, String newPath) {
        File oldFile = new File(oldPath);
        File file = new File(newPath);

        try (FileChannel fci = new FileInputStream(oldFile).getChannel(); FileChannel fco = new FileOutputStream(file).getChannel()) {
            //连接两个通道，并且从in通道读取，然后写入out通道
            fci.transferTo(0, fci.size(), fco);
        } catch (IOException e) {
            e.printStackTrace();
        }
        oldFile.deleteOnExit();
    }
}
