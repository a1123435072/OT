package cn.zzu.takeout.model.dao.StoreBean;

import java.util.List;

/**
 * Created by yangg on 2017/7/24.
 */

public class Data {
    public Integer id;
    public String info;
    public List<Eat> list;
    public String name;


    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", info='" + info + '\'' +
                ", list=" + list +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Eat> getList() {
        return list;
    }

    public void setList(List<Eat> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
