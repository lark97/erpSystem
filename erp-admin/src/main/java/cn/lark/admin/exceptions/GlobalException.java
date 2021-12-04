package cn.lark.admin.exceptions;

import cn.lark.admin.vo.RespBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: erp-manage
 * @description: 全局异常拦截类
 * @author: lark
 * @create: 2021-12-03 16:21
 **/
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ParamsException.class)
    @ResponseBody
    public RespBean paramsException(ParamsException e){
        return RespBean.error(e.getMsg());
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RespBean exception(Exception e){
        return RespBean.error(e.getMessage());
    }
}
