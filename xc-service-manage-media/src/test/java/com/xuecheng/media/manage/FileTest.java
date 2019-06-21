package com.xuecheng.media.manage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

/**
 * @Author: 98050
 * @Time: 2019-06-10 22:17
 * @Feature:
 */
public class FileTest {
    //测试文件分块方法
    @Test
    public void testChunk() throws IOException {
        File file = new File("F:/video/abc.mp4");
        String chunckPath = "F:/video/chunk/";
        File chunckFolder = new File(chunckPath);
        if (!chunckFolder.exists()){
            chunckFolder.mkdirs();
        }
        //分块大小
        long chunkSize = 1024 * 1024;
        //分块数量
        long chunkNum = (long) Math.ceil(file.length() * 1.0 / chunkSize);
        if (chunkNum <= 0){
            chunkNum = 1;
        }
        //缓冲区大小
        byte[] b = new byte[1024];
        //使用RandomAccessFile访问文件
        RandomAccessFile read = new RandomAccessFile(file, "r");
        //分块
        for (int i = 0; i < chunkNum; i++) {
            //创建分块文件
            File chunk = new File(chunckPath + i);
            boolean newFile = chunk.createNewFile();
            if (newFile){
                //向分块中写数据
                RandomAccessFile write = new RandomAccessFile(chunk, "rw");
                int len = -1;
                while ((len = read.read(b)) != -1){
                    write.write(b,0,len);
                    if (chunk.length() >= chunkSize){
                        break;
                    }
                }
                write.close();
            }
        }
        read.close();
    }

    //测试文件合并方法
    @Test
    public void testMerge() throws IOException {
        //块文件目录
        File chunkFolder = new File("F:\\video\\chunk");
        //合并文件
        File mergeFile = new File("F:\\video\\abcd.mp4");
        if (mergeFile.exists()){
            mergeFile.delete();
        }
        //创建合并文件
        mergeFile.createNewFile();
        //用于写文件
        RandomAccessFile write = new RandomAccessFile(mergeFile, "rw");
        //指针指向文件顶端
        write.seek(0);
        //缓冲区
        byte[] bytes = new byte[1024];
        //分块列表
        File[] files = chunkFolder.listFiles();
        //转成集合，便于排序
        List<File> fileList = new ArrayList<>(Arrays.asList(files));
        //从小到大排序
        Collections.sort(fileList, Comparator.comparingInt(f -> Integer.valueOf(f.getName())));
        //合并文件
        for (File chunk : fileList){
            RandomAccessFile read = new RandomAccessFile(chunk, "rw");
            int len = -1;
            while ((len = read.read(bytes)) != -1){
                write.write(bytes,0,len);
            }
            read.close();
        }
        write.close();
    }
}
