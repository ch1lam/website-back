package cn.chilam.websiteback.controller;

import cn.chilam.websiteback.common.entity.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: website-back
 * @description: 学生权限api
 * @author: chilam
 * @create: 2020-04-02 14:03
 **/
@RestController
@RequestMapping("/student")
public class StudentController {

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public ResultMap getMessage(){
        return ResultMap.ok();
    }



}
