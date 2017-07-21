package cn.zzu.takeout.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.zzu.takeout.R;
import cn.zzu.takeout.model.ResponseInfo;
import cn.zzu.takeout.presenter.fragment.HomeFragmentPresenter;
import cn.zzu.takeout.ui.adapter.homeAdapter;
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

public class HomeFragment extends BaseFragment {

    private RecyclerView recyclerView;

    HomeFragmentPresenter presenter ;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.homefragment, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_home);

        presenter = new HomeFragmentPresenter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(
                UIUtils.getContext(),
                LinearLayoutManager.VERTICAL,false));

        recyclerView.setAdapter(new homeAdapter());

        recyclerView.addOnScrollListener(rvListener);

    }

    private RecyclerView.OnScrollListener rvListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

        }
    };
    @Override
    public void onResume() {
        super.onResume();
        presenter.getData();

    }

    public void filed(String msg) {

    }

    public void success() {

    }




    /*public class ViewHolder extends RecyclerView.ViewHolder{



         public ViewHolder(View itemView) {
             super(itemView);
             recyclerView = itemView.findViewById(R.id.rv_home);
         }
     }*/

}
