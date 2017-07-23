package cn.zzu.takeout.model.dao.Bean;

/**
 * Created by yangg on 2017/7/22.
 */

public class Promotion {
    public  Integer id;
    public  String info;
    public  String pic;

    public String getInfo() {
        return info;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
