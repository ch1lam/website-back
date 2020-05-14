package cn.chilam.websiteback.util;

import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * @program: website-back
 * @description: 创建目录文件夹
 * @author: chilam
 * @create: 2020-05-2 10:48
 **/
public class FolderUtil {

    // 创建多级目录文件夹
    public static void createFolder(String url) {
        File directory = new File(url);
        // 如果文件不存在，才创建
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    // 删除目录及其内容
    public static void deleteFolder(String url) {
        File directory = new File(url);
        // 如果文件存在，则删除
        if (directory.exists()) {
            FileUtils.deleteQuietly(directory);
        }

    }

}
