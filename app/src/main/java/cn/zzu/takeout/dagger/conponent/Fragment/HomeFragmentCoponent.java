package cn.zzu.takeout.dagger.conponent.Fragment;

import cn.zzu.takeout.dagger.module.fragment.HomeFragmetModule;
import cn.zzu.takeout.ui.activity.MainActivity;
import cn.zzu.takeout.ui.fragment.HomeFragment;
import dagger.Component;

/**
 * Created by yangg on 2017/7/21.
 */
@Component(modules = HomeFragmetModule.class)
public interface HomeFragmentCoponent {
    void in(HomeFragment fragment);
}
