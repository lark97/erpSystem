package cn.lark.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: erp-manage
 * @description: 登录返回结果
 * @author: lark
 * @create: 2021-12-02 20:23
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {

    @ApiModelProperty(value = "返回状态码")
    private long code;
    @ApiModelProperty(value = "返回状态信息")
    private String message;
    @ApiModelProperty(value = "返回状态对象")
    private Object obj;

    public static RespBean success(String message){
        return new RespBean(200,message,null);
    }
    public static RespBean success(String message, Object obj){
        return new RespBean(200,message,obj);
    }

    public static RespBean error(String message){
        return new RespBean(500,message,null);
    }
    public static RespBean error(String message,Object obj){
        return new RespBean(500,message,obj);
    }

}
