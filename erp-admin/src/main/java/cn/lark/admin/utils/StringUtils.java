package cn.lark.admin.utils;

/**
 * @program: erp-manage
 * @description: 判断工具类
 * @author: lark
 * @create: 2021-12-02 20:34
 **/
public class StringUtils {

    /**
     * 判断为空
     * @param s
     * @return
     */
    public static boolean isEmpty(String s){
        if(s == null||"".equals(s.trim())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 判断不为空
     * @param s
     * @return
     */
    public static boolean isNotEmpty(String s){
        if(s == null||"".equals(s.trim())){
            return false;
        }else{
            return true;
        }
    }
}
