package cn.zzu.takeout.model.dao.Bean;

import java.util.List;



/**
 * Created by yangg on 2017/7/21.
 */

public class HomeBean {
    public List<Body> body;

    public Head head;


    /*public class Body{

        public Seller seller;
        public Integer type;
        public List<String> recommendInfos;


    }*/


   /* public class Seller {
     public List<String> activityList;
     public String deliveryFee;
     public String distance;
     public String ensure;
     public Integer id;
     public String invoice;
     public String name;
     public String pic;
     public String recentVisit;
     public String sale;
     public String score;
     public Integer sendPrice;
     public String time;
    }*/

  /*  public class Head {
        public  List<Categorie> categorieList;
        public  List<Promotion> promotionList;
    }*/

   /* public class Categorie {
        public  Integer id;
        public  String name;
        public  String pic;
    }*/

   /* public class Promotion {
        public  Integer id;
        public  String name;
        public  String pic;
    }*/

    public List<Body> getBody() {
        return body;
    }

    public void setBody(List<Body> body) {
        this.body = body;
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    @Override
    public String toString() {
        return "HomeBean{" +
                "body=" + body.size() +
                ", head=" + head +
                '}';
    }
}
