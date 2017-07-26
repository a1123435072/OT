package cn.zzu.takeout.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import cn.zzu.takeout.R;
import cn.zzu.takeout.ui.activity.LoginActivity;
import cn.zzu.takeout.utils.UIUtils;

import static cn.zzu.takeout.R.id.login;

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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mefragment, null);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        llUserInfo = view.findViewById(R.id.ll_userinfo);

        llUserInfo.setOnClickListener(UserInfoListener);
    }

    /**
     * 用户信息点击事件
     */
    private View.OnClickListener UserInfoListener =new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(UIUtils.getContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            UIUtils.getContext().startActivity(intent);
        }
    };


}
