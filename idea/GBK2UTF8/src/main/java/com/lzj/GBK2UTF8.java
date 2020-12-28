package com.lzj;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

public class GBK2UTF8 {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入源路径：");
        //GBK编码格式源码路径
        String srcDirPath = scanner.nextLine();
        System.out.println("请输入存储目录：");
        //转为UTF-8编码格式源码路径
        String utf8DirPath = scanner.nextLine();
        //String srcDirPath = "C:\\Users\\Keen\\Desktop\\filter-listener-day01-all";
        //
        //String utf8DirPath = "D:\\UTF8\\src";

        //获取所有文件      extensions:new String[]{"java"} //仅获取java文件
        Collection<File> javaGbkFileCol =  FileUtils.listFiles(new File(srcDirPath), null, true);

        for (File javaGbkFile : javaGbkFileCol) {
            //UTF8格式文件路径
            String utf8FilePath = utf8DirPath+javaGbkFile.getAbsolutePath().substring(srcDirPath.length());
            System.out.println(utf8FilePath);
            //使用GBK读取数据，然后用UTF-8写入数据
            FileUtils.writeLines(new File(utf8FilePath), "UTF-8", FileUtils.readLines(javaGbkFile, "GBK"));
        }
    }
}
