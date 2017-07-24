package cn.zzu.takeout.ui.fragment.StoreFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.zzu.takeout.R;

import cn.zzu.takeout.dagger.conponent.Fragment.Store.StoreFragmentCoponent;
import cn.zzu.takeout.presenter.fragment.store.StoreFragmentPresenter;
import cn.zzu.takeout.ui.adapter.HomeAdapter;
import cn.zzu.takeout.ui.adapter.Store.StoreAdapter;
import cn.zzu.takeout.utils.LogUtils;
import cn.zzu.takeout.utils.UIUtils;

/**
 *
 */
public class StoreFragment extends Fragment {


    private RecyclerView recyclerView;
    private StoreAdapter storeAdapter;


    private StoreFragmentPresenter presenter;
    private int id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //先用 new 对象的方法代替,,然后在用dagger注解代替
        presenter = new StoreFragmentPresenter(this);

        recyclerView = view.findViewById(R.id.rv_store);


        recyclerView.setLayoutManager(new LinearLayoutManager(
                UIUtils.getContext(),
                LinearLayoutManager.VERTICAL, false));
        storeAdapter = new StoreAdapter();

        recyclerView.setAdapter(storeAdapter);


        Intent intent = new Intent();
        id = intent.getIntExtra("id", 1);
        LogUtils.s("id--->"+id);

        //加载数据
        presenter.getStoreData(id);
    }

    public void filed(String msg) {

    }



    public StoreAdapter getStoreAdapter() {
        return storeAdapter;
    }

    @Override
    public void onResume() {
        presenter.getStoreData(id);
        super.onResume();
    }
}
