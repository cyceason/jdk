package com.cyc.compress;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CompressFile {
    public static final char SEPARATORCHAR = File.separatorChar;

    /**
     * 入口
     *
     * @param inputPath  输入流
     * @param outputPath 输出流
     */
    public static void compress(String inputPath, String outputPath) {
        if (StringUtils.isBlank(inputPath) || StringUtils.isBlank(outputPath)) {
            throw new RuntimeException("输入路径或者输出路径不能为空");
        }
        ZipArchiveOutputStream zos = null;
        File zipFile = null;
        File inputFile = new File(inputPath);
        String rootPath = inputFile.getName();
        zipFile = new File(outputPath + SEPARATORCHAR + rootPath + ".zip");
        try {
            zos = new ZipArchiveOutputStream(zipFile);
            compressFiles(inputFile, zos, rootPath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            IOUtils.closeQuietly(zos);
        }
    }

    /**
     * 循环文件
     *
     * @param files
     * @param zos
     * @param rootPath
     * @throws IOException
     */
    private static void compressFiles(File files, ZipArchiveOutputStream zos, String rootPath) throws IOException {
        if (files.isDirectory()) {
            File[] listFiles = files.listFiles();
            if (listFiles.length > 0) {
                for (File file : listFiles) {
                    if (file.isDirectory()) {
                        compressFiles(file, zos, rootPath + SEPARATORCHAR + file.getName());
                    } else if (file.isFile()) {
                        compressFile(rootPath + SEPARATORCHAR + file.getName(), file, zos);
                    }
                }
            }
        } else if (files.isFile()) {
            compressFile(files.getName(), files, zos);
        }
    }

    /**
     * 压缩文件
     *
     * @param fileName
     * @param file
     * @param zos
     * @throws IOException
     */
    private static void compressFile(String fileName, File file, ZipArchiveOutputStream zos) throws IOException {
        ZipArchiveEntry ze = new ZipArchiveEntry(fileName);
        zos.putArchiveEntry(ze);
        IOUtils.copy(new FileInputStream(file), zos);
        zos.closeArchiveEntry();// 关闭当前文件
    }

    public static void main(String[] args) {
        CompressFile.compress("C:\\Users\\Administrator\\Desktop\\内蒙食品追朔",
                "C:\\Users\\Administrator\\Desktop\\新建文件夹");
    }

}
