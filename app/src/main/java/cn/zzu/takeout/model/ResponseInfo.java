package cn.zzu.takeout.model;

/**
 * Created by Teacher on 2016/9/2.
 */
public class ResponseInfo {


/**
 * {
 "code": "0",
 "data": "{……}"
 }

 */

    private String code;
    private String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
