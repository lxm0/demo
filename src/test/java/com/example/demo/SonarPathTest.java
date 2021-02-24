package com.example.demo;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SonarPathTest {
    public static void main(String[] args) {
        String str = "D:\\IdeaProject\\twmw-framework\\twmw-framework";
        List<String> list = folderMethod1(str);
        filterAndDealPath(list);
    }

    public static void filterAndDealPath(List<String> list) {
        List newlist =
                list.stream().filter(s -> s.endsWith("src\\main")).collect(Collectors.toList());
        StringBuffer stringBuffer = new StringBuffer();
        newlist.forEach(s -> {
            stringBuffer.append(s).append(",");
        });
        System.out.println(
                stringBuffer.toString().replaceAll("D:\\\\IdeaProject\\\\twmw-framework\\\\twmw-framework\\\\", "./").replaceAll(
                        "\\\\","/"));
    }

    public static List<String> folderMethod1(String path) {
        int fileNum = 0, folderNum = 0;
        File file = new File(path);
        LinkedList<File> list = new LinkedList<>();
        LinkedList<String> pathList = new LinkedList<>();

        if (file.exists()) {
            if (null == file.listFiles()) {
                return pathList;
            }
            list.addAll(Arrays.asList(file.listFiles()));
            while (!list.isEmpty()) {
                File[] files = list.removeFirst().listFiles();
                if (null == files) {
                    continue;
                }
                for (File f : files) {
                    if (f.isDirectory()) {
                        // System.out.println("文件夹:" + f.getAbsolutePath());
                        pathList.add(f.getAbsolutePath());
                        list.add(f);
                        folderNum++;
                    } else {
                        // System.out.println("文件:" + f.getAbsolutePath());
                        fileNum++;
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("文件夹数量:" + folderNum + ",文件数量:" + fileNum);
        return pathList;
    }
}
