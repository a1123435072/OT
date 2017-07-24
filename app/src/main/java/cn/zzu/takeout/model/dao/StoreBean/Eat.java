package cn.zzu.takeout.model.dao.StoreBean;

/**
 * Created by yangg on 2017/7/24.
 */

class Eat {
    public boolean bargainPrice;
    public String form;
    public String icon;
    public Integer id;
    public Integer monthSaleNum;
    public String name;
    //public boolean new;
    public float newPrice;
    public Integer oldPrice;

    public boolean isBargainPrice() {
        return bargainPrice;
    }

    public void setBargainPrice(boolean bargainPrice) {
        this.bargainPrice = bargainPrice;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMonthSaleNum() {
        return monthSaleNum;
    }

    public void setMonthSaleNum(Integer monthSaleNum) {
        this.monthSaleNum = monthSaleNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(float newPrice) {
        this.newPrice = newPrice;
    }

    public Integer getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Integer oldPrice) {
        this.oldPrice = oldPrice;
    }
}
