package cn.zzu.takeout.model.dao.Bean;

import java.util.List;

/**
 * Created by yangg on 2017/7/22.
 */

public class Seller {
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


    public List<String> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<String> activityList) {
        this.activityList = activityList;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getEnsure() {
        return ensure;
    }

    public void setEnsure(String ensure) {
        this.ensure = ensure;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getRecentVisit() {
        return recentVisit;
    }

    public void setRecentVisit(String recentVisit) {
        this.recentVisit = recentVisit;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Integer getSendPrice() {
        return sendPrice;
    }

    public void setSendPrice(Integer sendPrice) {
        this.sendPrice = sendPrice;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
