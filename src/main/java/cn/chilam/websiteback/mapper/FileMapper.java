package cn.chilam.websiteback.mapper;

import cn.chilam.websiteback.pojo.File;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: website-back
 * @description:
 * @author: chilam
 * @create: 2020-04-28 14:41
 **/
@Repository
public interface FileMapper {
    void insert(File record);

    File getFileInfoByName(String name);

    List<File> getAllFileInfo();

    String getUrlByName(String name);

    String deleteByName(String name);

}
