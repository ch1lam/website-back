package cn.chilam.websiteback.controller;

import cn.chilam.websiteback.common.entity.ResultMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: website-back
 * @description: 游客权限api
 * @author: chilam
 * @create: 2020-04-01 21:11
 **/
@RestController
@RequestMapping("/guest")
public class GuestController {
    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public ResultMap login() {
        return ResultMap.ok();
    }
}
