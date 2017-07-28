package cn.zzu.takeout.model.dao.order;


import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yangg on 2017/7/28.
 */

public class Order {

    public Detail detail;
    public Distribution distribution;

    public List<GoodsInfoss> goodsInfos;

    public String id;
    public Rider rider;

    public Seller seller;

    public String type;
    /**
     * detail : {"address":"修正校区","pay":"在线支付","phone":"135000000000","time":"2020-10-10 10:10:10","username":"老黑"}
     * distribution : {"des":"黑马配送","type":"1"}
     * goodsInfos : [{"bargainPrice":false,"form":"","icon":"","id":0,"monthSaleNum":0,"name":"红烧肉","new":false,"newPrice":"25","oldPrice":0},{"bargainPrice":false,"form":"","icon":"","id":0,"monthSaleNum":0,"name":"米饭","new":false,"newPrice":"2","oldPrice":0},{"bargainPrice":false,"form":"","icon":"","id":0,"monthSaleNum":0,"name":"雪碧","new":false,"newPrice":"4","oldPrice":0}]
     * id : 1010 8027 3652 5689 39
     * rider : {"id":100,"location":{"latitude":"106.23","longitude":"43.123"},"name":"张三","phone":"13200000000"}
     * seller : {"activityList":[],"deliveryFee":"","distance":"","ensure":"","id":1,"invoice":"","name":"黑马程序员外卖项目","pic":"http://10.0.2.2:8080/TakeoutService/imgs/category/1.png","recentVisit":"","sale":"","score":"5","sendPrice":0,"time":""}
     * type : 10
     */

   /* public DetailBean detail;
    public DistributionBean distribution;
    public String id;
    public RiderBean rider;
    public SellerBean seller;
    public String type;
    public List<GoodsInfosBean> goodsInfos;

    public static class DetailBean {
        *//**
     * address : 修正校区
     * pay : 在线支付
     * phone : 135000000000
     * time : 2020-10-10 10:10:10
     * username : 老黑
     *//*

        public String address;
        public String pay;
        public String phone;
        public String time;
        public String username;
    }

    public static class DistributionBean {
        *//**
     * des : 黑马配送
     * type : 1
     *//*

        public String des;
        public String type;
    }

    public static class RiderBean {
        *//**
     * id : 100
     * location : {"latitude":"106.23","longitude":"43.123"}
     * name : 张三
     * phone : 13200000000
     *//*

        public int id;
        public LocationBean location;
        public String name;
        public String phone;

        public static class LocationBean {
            *//**
     * latitude : 106.23
     * longitude : 43.123
     *//*

            public String latitude;
            public String longitude;
        }
    }

    public static class SellerBean {
        *//**
     * activityList : []
     * deliveryFee :
     * distance :
     * ensure :
     * id : 1
     * invoice :
     * name : 黑马程序员外卖项目
     * pic : http://10.0.2.2:8080/TakeoutService/imgs/category/1.png
     * recentVisit :
     * sale :
     * score : 5
     * sendPrice : 0
     * time :
     *//*

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
        public List<?> activityList;
    }

    public static class GoodsInfosBean {
        *//**
     * bargainPrice : false
     * form :
     * icon :
     * id : 0
     * monthSaleNum : 0
     * name : 红烧肉
     * new : false
     * newPrice : 25
     * oldPrice : 0
     *//*

        public boolean bargainPrice;
        public String form;
        public String icon;
        public int id;
        public int monthSaleNum;
        public String name;
        @SerializedName("new")
        public boolean newX;
        public String newPrice;
        public int oldPrice;
    }*/


}
