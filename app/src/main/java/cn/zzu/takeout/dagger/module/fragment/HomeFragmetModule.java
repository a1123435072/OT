package cn.zzu.takeout.dagger.module.fragment;

import dagger.Module;

/**
 * Created by yangg on 2017/7/21.
 */
@Module
public class HomeFragmetModule {
    private HomeFragmetModule homeFragmetModule;

    public HomeFragmetModule(HomeFragmetModule homeFragmetModule) {
        this.homeFragmetModule = homeFragmetModule;
    }

    //return
}
