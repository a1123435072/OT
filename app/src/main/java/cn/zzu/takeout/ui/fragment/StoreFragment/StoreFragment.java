package cn.zzu.takeout.ui.fragment.StoreFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import cn.zzu.takeout.R;
import cn.zzu.takeout.model.dao.StoreBean.Data;
import cn.zzu.takeout.model.dao.StoreBean.Eat;
import cn.zzu.takeout.presenter.fragment.store.StoreFragmentPresenter;
import cn.zzu.takeout.ui.ShoppingCartManager;
import cn.zzu.takeout.ui.activity.CartActivity;
import cn.zzu.takeout.ui.adapter.Store.GoodsAdapter;
import cn.zzu.takeout.ui.adapter.Store.StoreAdapter;
import cn.zzu.takeout.utils.LogUtils;
import cn.zzu.takeout.utils.UIUtils;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 *
 */
public class StoreFragment extends Fragment implements AbsListView.OnScrollListener {


    private ListView lv;
    private StoreAdapter storeAdapter;



    private StoreFragmentPresenter presenter;
    private int id;
    private StickyListHeadersListView sll;

    private GoodsAdapter goodsAdapter;
    private TextView tvFragmentGoodsCount;
    private RelativeLayout cart;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        //先用 new 对象的方法代替,,然后在用dagger注解代替
        presenter = new StoreFragmentPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        lv = view.findViewById(R.id.rv_store);
       /* recyclerView.setLayoutManager(new LinearLayoutManager(
                UIUtils.getContext(),
                LinearLayoutManager.VERTICAL, false));*/

        //右侧列表的展示
        sll = view.findViewById(R.id.shl);

       /* if (headAdapter == null) {
            headAdapter = new HeaderAdapter(mDataArrayList, sll);
        }
        sll.setAdapter(headAdapter);*/

        cart = view.findViewById(R.id.cart);

        Intent intent = new Intent();
        id = intent.getIntExtra("id", 1);

        LogUtils.s("id--->" + id);

        //加载数据
        presenter.getStoreData(id);


        tvFragmentGoodsCount = view.findViewById(R.id.fragment_goods_tv_count);
        //判断购物车中是否有信息,如果有,就显示有的气泡和文字
        Integer totalNum = ShoppingCartManager.getInstance().getTotalNum();
        if (totalNum>0){
            tvFragmentGoodsCount.setVisibility(View.VISIBLE);
            tvFragmentGoodsCount.setText(totalNum.toString());
        }

        cart.setOnClickListener(cartListener);

    }

    //购物车点击事件
    private View.OnClickListener cartListener  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(UIUtils.getContext(), CartActivity.class);
            UIUtils.getContext().startActivity(intent);

        }
    };


    //请求网络失败的时候调用的方法
    public void filed(String msg) {

    }


    /**
     * 得到商店左侧列表的adapter
     *
     * @return
     */
    public StoreAdapter getStoreAdapter() {
        return storeAdapter;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getStoreData(id);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventAsync(List<Data> dataArrayList) {

       /* mDataArrayList.clear();
        mDataArrayList.addAll(dataArrayList);
        LogUtils.s(mDataArrayList.toString());
        notifyAll();*/
    }

    private ArrayList<Eat> datas = new ArrayList<>();
    private List<Data> mDataArrayList;

    //执行成功的方法
    public void success(List<Data> dataList) {
        //安装数据结构处理goodsEat容器
        mDataArrayList = dataList;

        for (int hi = 0; hi < mDataArrayList.size(); hi++) {
            //显示标题的分类对象
            Data head = mDataArrayList.get(hi);

            //普通条目
            for (int di = 0; di < head.list.size(); di++) {
                //获取到每个商品信息对象
                Eat eat = head.list.get(di);
                eat.headId = head.id;
                eat.headIndex = hi;
                if (di == 0){
                    head.groupFirstIndex = datas.size();
                }
                datas.add(eat);
            }
        }

        storeAdapter = new StoreAdapter(mDataArrayList);
        lv.setAdapter(storeAdapter);

        goodsAdapter = new GoodsAdapter(mDataArrayList, datas);
        sll.setAdapter(goodsAdapter);
        //notifyDataSetChange();

        //监听点击事件左侧listView的点击时间
        lv.setOnItemClickListener(leftItemListener);


    }
    private AdapterView.OnItemClickListener leftItemListener =new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //在头容器中点击某个条目的额时候没让该组信息在条目容器中置顶,
            //1,高亮,,点击条目
            storeAdapter.setSelectedPositon(i);
            Data head = mDataArrayList.get(i);
            sll.setSelection(head.groupFirstIndex);
            isScroll= false;
        }
    };


    /**
     * 控制是否可以滑动
     */
    private boolean isScroll = false;

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        LogUtils.s("scrollState==>"+i);
        //用户在滚动
        isScroll = true;
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        //用户在滚动
        if (isScroll){
            LogUtils.s("firstVisibleItem-->"+i);
            Eat data = datas.get(i);
            LogUtils.s("data.headIndex"+data.headIndex);
            //当前正在置顶显示的头高亮 处理
            storeAdapter.setSelectedPositon(data.headIndex);
            //判断分类容器对应的条目是否处于可见状态
            //获取到第一个课件,和最后一个课件的额,比第一个小的,
            //或者比最后一个大的均为不可见
            int firstVisiblePosition = lv.getFirstVisiblePosition();
            int lastVisiblePosition = lv.getLastVisiblePosition();
            if (data.headIndex <= firstVisiblePosition|| data.headIndex>= lastVisiblePosition ){
                lv.setSelection(data.headIndex);//课件处理
            }
        }
    }
}
