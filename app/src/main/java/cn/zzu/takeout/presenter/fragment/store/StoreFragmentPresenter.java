package cn.zzu.takeout.presenter.fragment.store;


import com.google.gson.Gson;

import cn.zzu.takeout.model.ResponseInfo;
import cn.zzu.takeout.model.dao.Bean.HomeBean;
import cn.zzu.takeout.model.dao.StoreBean.StoreBean;
import cn.zzu.takeout.presenter.BasePresenter;
import cn.zzu.takeout.ui.fragment.StoreFragment.StoreFragment;
import cn.zzu.takeout.utils.ErrorInfo;
import cn.zzu.takeout.utils.LogUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yangg on 2017/7/23.
 */

public class StoreFragmentPresenter extends BasePresenter {

    private StoreFragment fragment;

    public StoreFragmentPresenter(StoreFragment fragment) {
        this.fragment = fragment;
    }
    /**
     * 获取首页数据的步骤
     * 1,需要在联网的api接口中增加一个回去首页数据的方法
     * (访问方式和请求参数)
     * 2,异步回去首页数据
     * 3,数据处理
     * 4,展示数据到界面上
     */

    /**
     * 获取服务器短首页数据
     */

    public void getStoreData(int id){
        Call<ResponseInfo> call = responseInfoAPI.goods(id);

        call.enqueue(new Callback<ResponseInfo>() {
            @Override
            public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {

                //处理恢复
                System.out.println("-->" + response);


                if (response != null && response.isSuccessful()) {

                    ResponseInfo info = response.body();
                    if ("0".equals(info.getCode())) {
                        //服务器处理成功,并且返回目标数据
                        LogUtils.s("-->" + "访问数据成功-------------");
                        LogUtils.s("--->" + info.getData());
                        //解析数据
                        parserData(info.getData());

                    } else {
                        //服务器处理成功,返回错误提示,妨碍信息需要展示给用户
                        //一句code值获取到失败的数据
                        String msg = ErrorInfo.INFO.get(info.getCode());
                        //提示用于  ,可以用吐司
                        filed(msg);

                    }
                } else {
                    //联网中出现异常
                }
            }

            @Override
            public void onFailure(Call<ResponseInfo> call, Throwable t) {

                //联网过程中异常
            }
        });
    }
    //出错了
    private void filed(String msg) {
        fragment.filed(msg);

    }

    //成功
    private void parserData(String data) {
        Gson gson = new Gson();
        StoreBean storeBean = gson.fromJson(data, StoreBean.class);

        // HomeBean.Seller.
        //fragment.success();
        fragment.getStoreAdapter().setDate(storeBean);
        LogUtils.s("data-->" + data.toString());
        // 发送Event，传递信息Event---001

        //String event1 = "Event---001";
        // 发布事件
        // EventBus.getDefault().postSticky(event1);

    }
}
