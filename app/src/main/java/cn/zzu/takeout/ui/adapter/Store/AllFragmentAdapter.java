package cn.zzu.takeout.ui.adapter.Store;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangg on 2017/7/23.
 */

public class AllFragmentAdapter extends FragmentPagerAdapter{

    private List<Fragment > fragments  =new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    public AllFragmentAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {

        return fragments.size();

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    /**
     * 动态设置Fragment
     */
    public void addAll(List<Fragment> fragments){
        this.fragments.addAll(fragments);
    }
    /**
     * 动态设置标图
     */
    public void addPagerTitle(List<String> title){
        titles.addAll(title);
    }
}
