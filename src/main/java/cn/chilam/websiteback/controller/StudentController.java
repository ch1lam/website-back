package cn.chilam.websiteback.controller;

import cn.chilam.websiteback.common.entity.Node;
import cn.chilam.websiteback.common.entity.ResultMap;
import cn.chilam.websiteback.pojo.User;
import cn.chilam.websiteback.service.CourseService;
import cn.chilam.websiteback.service.FileService;
import cn.chilam.websiteback.service.UploadService;
import cn.chilam.websiteback.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: website-back
 * @description: 学生权限api
 * @author: chilam
 * @create: 2020-04-02 14:03
 **/
@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {
    @Autowired
    UserService userService;

    @Autowired
    UploadService uploadService;

    @Autowired
    CourseService courseService;

    @Autowired
    FileService fileService;


    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    @RequiresRoles(value = {"student", "teacher", "admin"}, logical = Logical.OR)
    public ResultMap getMessage() {
        return ResultMap.ok();
    }


    @PostMapping("/getUserInfo")
    @RequiresRoles(value = {"student", "teacher", "admin"}, logical = Logical.OR)
    public ResultMap getUserInfo(@RequestParam("username") String username) {
        Map<String, Object> data = new HashMap<>();
        data.put("userInfo", userService.getUserInfoByName(username));
        return ResultMap.ok().data(data);
    }


    @PostMapping("/getAllCourseInfo")
    @RequiresRoles(value = {"student", "teacher", "admin"}, logical = Logical.OR)
    public ResultMap getAllCourseInfo() {
        Map<String, Object> data = new HashMap<>();
        data.put("CourseInfo", courseService.getAllCourseInfo());
        return ResultMap.ok().data(data);
    }


    // 老师用的
    @PostMapping("/getAllCourseInfo2")
    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    public ResultMap getAllCourseInfo2() {
        Map<String, Object> data = new HashMap<>();
        data.put("CourseInfo", courseService.getAllCourseInfo());
        return ResultMap.ok().data(data);
    }

    @PostMapping("/putPassword")
    @RequiresRoles(value = {"student", "teacher", "admin"}, logical = Logical.OR)
    public ResultMap putPassword(@RequestParam("username") String username, @RequestParam(
            "password") String password, @Param("newPassword") String newPassword) {
        if (userService.updatePassword(username, password, newPassword)) {
            return ResultMap.ok().message("密码修改成功");
        } else {
            return ResultMap.error().message("密码修改失败");
        }
    }


    /**
     * @description: 获取用户头像
     * @author: chilam
     * @param: username
     * @return: byte[]
     * @date: 2020-04-05
     */
    @GetMapping(value = "/getAvatar", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getAvatar(@RequestParam("username") String username) throws IOException {
        String url = userService.getAvatarUrl(username);
        File file = new File(url);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }


    @GetMapping(value = "/getPoster", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPoster(@RequestParam("id") Integer id) throws IOException {
        String url = courseService.getPosterUrl(id);
        File file = new File(url);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }


    @PostMapping("/uploadAvatar")
    @RequiresRoles(value = {"student", "teacher", "admin"}, logical = Logical.OR)
    public ResultMap uploadAvatar(@RequestParam("file") MultipartFile file,
                                  @RequestParam("username") String username) {
        if (uploadService.uploadAvatar(file, username)) {
            return ResultMap.ok().message("上传成功");
        } else {
            return ResultMap.error().message("上传失败");
        }

    }


    @PostMapping("/getAllChapter")
    @RequiresRoles(value = {"student", "teacher", "admin"}, logical = Logical.OR)
    public ResultMap getAllChapter(@RequestParam("id") Integer id) {
        Map<String, Object> data = new HashMap<>();
        List<Node> tmp = courseService.getAllChapterById(id);
        data.put("chapters", tmp);
        return ResultMap.ok().data(data);
    }


    // 修改用户信息
    @PostMapping("/putUserInfo")
    @RequiresRoles(value = {"student", "teacher", "admin"}, logical = Logical.OR)
    public ResultMap putUser(Integer id, String username, String phoneNum,
                             String collegeName, String schoolNum, Integer gender) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPhoneNum(phoneNum);
        user.setCollegeName(collegeName);
        user.setSchoolNum(schoolNum);
        user.setGender(gender);

        if (userService.updateUserById(user)) {
            return ResultMap.ok();
        } else {
            return ResultMap.error();
        }

    }


    @PostMapping("/getAllFileInfo")
    @RequiresRoles(value = {"student", "teacher", "admin"}, logical = Logical.OR)
    public ResultMap getAllFileInfo() {
        Map<String, Object> data = new HashMap<>();
        data.put("fileList", fileService.getAllFileInfo());
        return ResultMap.ok().data(data);
    }


    /**
     * @description: 文件下载
     * @author: chilam
     * @param: id
     * @param: response
     * @date: 2020-05-01
     */
    @GetMapping("/download/{fileId}")
    public void download(@PathVariable("fileId") Integer id,
                         HttpServletResponse response) throws UnsupportedEncodingException {


        File file = new File(fileService.getFileInfo(id).getUrl());
        response.setContentType("application/force-download"); // 强制下载
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(fileService.getFileInfo(id).getName(),
                        "UTF-8"));
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
