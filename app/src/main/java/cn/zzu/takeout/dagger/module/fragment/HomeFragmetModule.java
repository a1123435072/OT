package cn.zzu.takeout.dagger.module.fragment;

import cn.zzu.takeout.presenter.fragment.HomeFragmentPresenter;
import cn.zzu.takeout.ui.fragment.HomeFragment;
import dagger.Module;
import dagger.Provides;

/**
 * Created by yangg on 2017/7/21.
 */
@Module
public class HomeFragmetModule {
    private HomeFragment homeFragment;

    public HomeFragmetModule(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    @Provides
    public HomeFragmentPresenter providerHomeFragment(){
        return new HomeFragmentPresenter(homeFragment);
    }
}
