package cn.zzu.takeout.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.zzu.takeout.R;
import cn.zzu.takeout.presenter.fragment.HomeFragmentPresenter;
import cn.zzu.takeout.ui.adapter.homeAdapter;
import cn.zzu.takeout.utils.UIUtils;

import static android.R.attr.fragment;

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

public class OrderFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.orderfragment, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


}
