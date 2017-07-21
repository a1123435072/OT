package cn.zzu.takeout.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.zzu.takeout.R;

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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mefragment, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


}
