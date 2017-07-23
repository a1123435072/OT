package cn.zzu.takeout.model.dao.Bean;

import java.util.List;

/**
 * Created by yangg on 2017/7/22.
 */

public class Body {


    public Seller seller;
    public List<String> recommendInfos;
    public Integer type;

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public List<String> getRecommendInfos() {
        return recommendInfos;
    }

    public void setRecommendInfos(List<String> recommendInfos) {
        this.recommendInfos = recommendInfos;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
