package cn.chilam.websiteback.common.entity;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: website-back
 * @description: 统一返回对象格式
 * @author: chilam
 * @create: 2020-04-01 21:28
 **/
@Component
public class ResultMap {

    // 是否成功
    private Boolean success;
    // 返回码
    private Integer code;
    // 返回消息
    private String message;
    // 返回数据
    private Map<String, Object> data = new HashMap<String, Object>();

    private ResultMap() {
    }

    public static ResultMap ok() {
        ResultMap resultMap = new ResultMap();
        resultMap.setSuccess(ResultCode.SUCCESS.getSuccess());
        resultMap.setCode(ResultCode.SUCCESS.getCode());
        resultMap.setMessage(ResultCode.SUCCESS.getMessage());
        return resultMap;
    }

    public static ResultMap error() {
        ResultMap resultMap = new ResultMap();
        resultMap.setSuccess(ResultCode.UNKNOWN_REASON.getSuccess());
        resultMap.setCode(ResultCode.UNKNOWN_REASON.getCode());
        resultMap.setMessage(ResultCode.UNKNOWN_REASON.getMessage());
        return resultMap;
    }

    /**
     * @description: 使用预设返回码
     * @author: chilam
     * @param: resultCode 预设返回码常量
     * @return: cn.chilam.websiteback.common.entity.ResultMap
     * @date: 2020-04-02
     */
    public static ResultMap setResult(ResultCode resultCode) {
        ResultMap resultMap = new ResultMap();
        resultMap.setSuccess(resultCode.getSuccess());
        resultMap.setCode(resultCode.getCode());
        resultMap.setMessage(resultCode.getMessage());
        return resultMap;
    }

    public ResultMap success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public ResultMap message(String message) {
        this.setMessage(message);
        return this;
    }

    public ResultMap code(Integer code) {
        this.setCode(code);
        return this;
    }

    public ResultMap data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public ResultMap data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
