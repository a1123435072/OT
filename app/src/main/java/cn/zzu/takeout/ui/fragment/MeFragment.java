package cn.zzu.takeout.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.zzu.takeout.EventBus.MessageBus;
import cn.zzu.takeout.R;
import cn.zzu.takeout.ui.activity.LoginActivity;
import cn.zzu.takeout.utils.UIUtils;

import static cn.zzu.takeout.utils.MyApplication.loginData;

/**
 * Created by yangg on 2017/7/21.
 * <p>
 * 1,布局
 * 2,投容器的处理
 * 1,侵入是装贪婪
 * 3,recycleView de 数据加载
 * 1,简单数据的加载
 * 2,复杂数据加载
 */

public class MeFragment extends BaseFragment {


    private LinearLayout llUserInfo;

    private TextView tvName;
//    private LoginData loginData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {


        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mefragment, null);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initVeiw(view);

        initListener();
        if (loginData ==  null) {
            //跳转到登录界面
            //点击登录

        } else {
            //进行界面的展示
            tvName.setText(loginData.name);

        }
    }


    //初始化控件
    private void initVeiw(View view) {
        llUserInfo = view.findViewById(R.id.ll_userinfo);
        tvName = view.findViewById(R.id.username);


    }

    //初始化点击监听事件
    private void initListener() {
        if (loginData ==  null) {
            //进入详情界面
            llUserInfo.setOnClickListener(UserInfoListener);
        } else {
            return;
        }
    }

    /**
     * 用户信息点击事件
     */
    private View.OnClickListener UserInfoListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(UIUtils.getContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            UIUtils.getContext().startActivity(intent);
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onevent(MessageBus messageBus) {
        llUserInfo.setVisibility(View.INVISIBLE);
        tvName.setText("jksdfhjks");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
