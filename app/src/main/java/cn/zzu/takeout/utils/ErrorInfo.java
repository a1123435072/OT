package cn.zzu.takeout.utils;

import java.util.HashMap;

/**
 * Created by yangg on 2017/7/21.
 */

public class ErrorInfo {

    public static HashMap<String ,String> INFO =new HashMap<>();

    static {
        INFO.put("0","成功");
        INFO.put("1","用户名或密码有误");
    }
}
