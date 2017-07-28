package cn.zzu.takeout.dagger.module.fragment;

import cn.zzu.takeout.presenter.fragment.orderFragmentPresenter;
import cn.zzu.takeout.presenter.fragment.store.StoreFragmentPresenter;
import cn.zzu.takeout.ui.fragment.OrderFragment;
import cn.zzu.takeout.ui.fragment.StoreFragment.StoreFragment;
import dagger.Module;
import dagger.Provides;

/**
 * Created by yangg on 2017/7/21.
 */
@Module
public class OrderFragmetModule {
    private OrderFragment orderFragment;

    public OrderFragmetModule(OrderFragment orderFragment) {
        this.orderFragment = orderFragment;
    }

    @Provides
    public orderFragmentPresenter providerStoreFragment(){
        return new orderFragmentPresenter(orderFragment);
    }
}
