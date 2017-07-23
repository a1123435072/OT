package cn.zzu.takeout.model.dao.Bean;

import java.util.List;

/**
 * Created by yangg on 2017/7/22.
 */

public class Head {
    public List<Categorie> categorieList;
    public  List<Promotion> promotionList;

    public List<Categorie> getCategorieList() {
        return categorieList;
    }

    public void setCategorieList(List<Categorie> categorieList) {
        this.categorieList = categorieList;
    }

    public Head(List<Categorie> categorieList, List<Promotion> promotionList) {
        this.categorieList = categorieList;
        this.promotionList = promotionList;
    }
}
