package cn.zzu.takeout.dagger.conponent.Fragment.Store;

import cn.zzu.takeout.dagger.module.fragment.HomeFragmetModule;
import cn.zzu.takeout.dagger.module.fragment.OrderFragmetModule;
import cn.zzu.takeout.ui.fragment.HomeFragment;
import cn.zzu.takeout.ui.fragment.OrderFragment;
import dagger.Component;

/**
 * Created by yangg on 2017/7/21.
 */
@Component(modules = OrderFragmetModule.class)
public interface OrderFragmentCoponent {
    void in(OrderFragment fragment);
}
