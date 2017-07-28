package cn.zzu.takeout.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.zzu.takeout.R;
import cn.zzu.takeout.ui.adapter.rvOrderAdapter;
import cn.zzu.takeout.ui.views.RecycleViewDivider;
import cn.zzu.takeout.utils.UIUtils;

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


    private RecyclerView rvOderList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.orderfragment, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);

    }

    private void initView(View view) {
        rvOderList = view.findViewById(R.id.rv_order_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UIUtils.getContext(), LinearLayoutManager.VERTICAL, false);
        rvOderList.setLayoutManager(linearLayoutManager);
        // 设置分割线
        rvOderList.addItemDecoration(new RecycleViewDivider(UIUtils.getContext(),LinearLayoutManager.HORIZONTAL,1,0XE3E0DC));
        rvOderList.setAdapter(new rvOrderAdapter());
    }


}
