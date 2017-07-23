package cn.zzu.takeout.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import cn.zzu.takeout.R;
import cn.zzu.takeout.ui.adapter.Store.AllFragmentAdapter;
import cn.zzu.takeout.ui.fragment.StoreFragment.StoreFragment;

public class StoreActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tl;

    private List<Fragment> fragments = new ArrayList<>();

    private List<String> titles = new ArrayList<>();
    private AllFragmentAdapter allFragmentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        tl = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.vp);

        allFragmentAdapter = new AllFragmentAdapter(getSupportFragmentManager());

        initFragment();

        initTitle();

        bindtablayoutView();
    }

    //绑定 tablayout 和 title
    private void bindtablayoutView() {

        viewPager.setAdapter(allFragmentAdapter);
        tl.setupWithViewPager(viewPager);
    }

    /**
     * 初始化标题
     */
    private void initTitle() {
        titles.add("商品");
        titles.add("评价");
        titles.add("商家");
        allFragmentAdapter.addPagerTitle(titles);
    }

    /**
     * 初始化Fraqment
     */
    private void initFragment() {
        fragments.add(new StoreFragment());
        fragments.add(new StoreFragment());
        fragments.add(new StoreFragment());
        allFragmentAdapter.addAll(fragments);
    }
}
