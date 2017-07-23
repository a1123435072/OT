package cn.zzu.takeout.dagger.conponent.Fragment.Store;

import cn.zzu.takeout.dagger.module.fragment.StoreFragmetModule;
import cn.zzu.takeout.ui.fragment.StoreFragment.StoreFragment;
import dagger.Component;

/**
 * Created by yangg on 2017/7/24.
 */
@Component(modules = StoreFragmetModule.class)
public interface StoreFragmentCoponent {
    void in(StoreFragment storeFragment);
}
