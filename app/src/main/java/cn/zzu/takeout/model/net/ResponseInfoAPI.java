package cn.zzu.takeout.model.net;



import cn.zzu.takeout.model.ResponseInfo;
import cn.zzu.takeout.utils.Constant;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static android.R.attr.id;

/**
 * Created by Teacher on 2016/9/2.
 */
public interface ResponseInfoAPI {

    //login?username=xxx
    @GET(Constant.LOGIN)
    Call<ResponseInfo> login(@Query("username") String username, @Query("password") String password, @Query("type") String type);

    /**
     * 获取首页数据
     * @return
     */
    @GET(Constant.HOME)
    Call<ResponseInfo> home();
//    @GET(Constant.GOODS+"/id="+id)
    @GET(Constant.GOODS)
    Call<ResponseInfo> goods(int id);

}
