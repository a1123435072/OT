package cn.zzu.takeout.utils;

/**
 * Created by Teacher on 2016/9/2.
 */
public interface Constant {

    //http://localhost:8080/TakeoutService/login?username="itheima"&password="bj"
    int DEBUGLEVEL = LogUtils.LEVEL_ALL;
    //String BASEURL = "http://172.24.28.2:8080/";
    String BASEURL = "http://10.0.2.2:8080/";

    String HOME = "TakeoutService/home";
    String GOODS ="TakeoutService/goods";
    String LOGIN ="TakeoutService/login";

    String ORDER ="TakeoutService/order";
}
