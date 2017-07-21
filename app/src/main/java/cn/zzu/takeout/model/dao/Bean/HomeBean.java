package cn.zzu.takeout.model.dao.Bean;

import java.util.List;



/**
 * Created by yangg on 2017/7/21.
 */

public class HomeBean {
    public List<Body> body;

    public Head head;


    public class Body{

        public Seller seller;
        public List<String> recommendInfos;

    }


    public class Seller {
     public List<String> activityList;
     public String deliveryFee;
     public String distance;
     public String ensure;
     public int id;
     public String invoice;
     public String name;
     public String pic;
     public String recentVisit;
     public String sale;
     public String score;
     public int sendPrice;
     public String time;
    }

    public class Head {
        public  List<Categorie> categorieList;
        public  List<Promotion> promotionList;
    }

    public class Categorie {
        public  int id;
        public  String name;
        public  String pic;
    }

    public class Promotion {
        public  int id;
        public  String name;
        public  String pic;
    }



}
