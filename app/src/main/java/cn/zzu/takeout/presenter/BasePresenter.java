package cn.zzu.takeout.presenter;

import cn.zzu.takeout.model.dao.DBHelper;
import cn.zzu.takeout.model.net.ResponseInfoAPI;
import cn.zzu.takeout.utils.Constant;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yangg on 2017/7/21.
 * 业务层公共代码封装
 * <p>
 * 抽取业务层联网代码
 */

public class BasePresenter {
    public ResponseInfoAPI responseInfoAPI;

    protected DBHelper helper;

    public BasePresenter() {
        //网络
        if (responseInfoAPI == null) {
            Retrofit retrofit = new Retrofit.Builder().
                    baseUrl(Constant.BASEURL).
                    addConverterFactory(GsonConverterFactory.create())
                    .build();

            responseInfoAPI = retrofit.create(ResponseInfoAPI.class);
        }

        //数据库 的创建
        helper = DBHelper.getInstance();
    }

}
