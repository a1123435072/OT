package cn.zzu.takeout.dagger.module.fragment;

import cn.zzu.takeout.presenter.fragment.HomeFragmentPresenter;
import cn.zzu.takeout.presenter.fragment.store.StoreFragmentPresenter;
import cn.zzu.takeout.ui.fragment.HomeFragment;
import cn.zzu.takeout.ui.fragment.StoreFragment.StoreFragment;
import dagger.Module;
import dagger.Provides;

/**
 * Created by yangg on 2017/7/21.
 */
@Module
public class StoreFragmetModule {
    private StoreFragment storeFragment;

    public StoreFragmetModule(StoreFragment storeFragment) {
        this.storeFragment = storeFragment;
    }

    @Provides
    public StoreFragmentPresenter providerStoreFragment(){
        return new StoreFragmentPresenter(storeFragment);
    }
}
