package cn.zzu.takeout.ui.adapter.Store;

import android.database.DataSetObserver;
import android.media.tv.TvView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.zzu.takeout.R;
import cn.zzu.takeout.model.dao.StoreBean.Data;
import cn.zzu.takeout.model.dao.StoreBean.Eat;
import cn.zzu.takeout.presenter.BasePresenter;
import cn.zzu.takeout.ui.ShoppingCartManager;
import cn.zzu.takeout.ui.adapter.HomeAdapter;
import cn.zzu.takeout.utils.LogUtils;
import cn.zzu.takeout.utils.MyApplication;
import cn.zzu.takeout.utils.UIUtils;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by yangg on 2017/7/24.
 */

public class GoodsAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    private List<Data> headDataSet;
    private List<Eat> itemDataSet ;


    public GoodsAdapter(List<Data> mdataList, List<Eat> datas) {
        this.headDataSet = mdataList;
        this.itemDataSet = datas;

    }


    //----头
    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        //向下滚动,投数据记载的是每组的第一条
        //向上滚动,投数据加载的是魅族的最后一条
        TextView head = new TextView(UIUtils.getContext());

        Data headData = headDataSet.get(itemDataSet.get(position).headIndex);
        head.setText(headData.name);
        head.setBackgroundColor(MyApplication.getmContext().getResources().getColor(R.color.colorPrimary));

        return head;
    }


    @Override
    public long getHeaderId(int position) {
        return itemDataSet.get(position).headId;
    }

    /**
     * -------------------------普通条目---------
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Eat data = itemDataSet.get(i);

        NormalViewHolder holder;
        if (view == null) {
            view = View.inflate(UIUtils.getContext(), R.layout.item_goods, null);
            holder = new NormalViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (NormalViewHolder) view.getTag();
        }

        if (data.count == 0){
            holder.ivMinus.setVisibility(View.GONE);
            holder.tvMoney.setVisibility(View.GONE);
        }else {
            holder.ivMinus.setVisibility(View.VISIBLE);
            holder.tvMoney.setVisibility(View.VISIBLE);
            holder.setData(data);
        }
        LogUtils.s("Eat data-->" + data);

        //设置标题内容
        //holder.tvName.setText(data.name);

        holder.setData(data);
        return view;
    }


    @Override
    public int getCount() {
        if (itemDataSet != null) {
            return itemDataSet.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return itemDataSet.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class NormalViewHolder {

        View itemView;
        private ImageView ivIcon;
        private TextView tvName;
        private TextView tvZucheng;
        private TextView tvYueshounum;
        private TextView tvNewPrice;
        private TextView tvOldPrice;
        private ImageView ivMinus;
        private TextView tvMoney;
        private ImageView ivAdd;
        private Eat data;
        private FrameLayout container;

        private TextView count;

        public void setData(Eat data) {
            this.data = data;
            //设置标题,,设置标题
            tvName.setText(data.name);
            //其他控件暂时不加载数据了,先实现主要功能
            // TODO  显示剩下的数据

        }

        public NormalViewHolder(View itemView) {
            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvName = itemView.findViewById(R.id.tv_name);
            tvZucheng = itemView.findViewById(R.id.tv_zucheng);
            tvYueshounum = itemView.findViewById(R.id.tv_yueshaoshounum);
            tvNewPrice = itemView.findViewById(R.id.tv_newprice);
            tvOldPrice = itemView.findViewById(R.id.tv_oldprice);
            ivMinus = itemView.findViewById(R.id.ib_minus);
            tvMoney = itemView.findViewById(R.id.tv_money);
            ivAdd = itemView.findViewById(R.id.ib_add);


            //减少的点击时间
            ivMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //点击减少的操作
                    minusHeadler(view);
                }
            });
            //增加的点击事件
            ivAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //点击增加
                    addHandler(view);
                }
            });



        }

        /**
         * 减少的操作
         * @param view
         */
        private void minusHeadler(View view) {
            Integer num = ShoppingCartManager.getInstance().minusGoods(data);
            if (num==0){
                //显示减号,和数量  隐藏
                ivMinus.setVisibility(View.GONE);
                tvMoney.setVisibility(View.GONE);
            }
            //给中间的数量设置喂
            tvMoney.setText(num.toString());

            if(container==null) {
                container = (FrameLayout) UIUtils.getContainder(view, R.id.seller_detail_container);
            }

            //通过父容器找到,,小红点
            if (count == null){
                count=  container.findViewById(R.id.fragment_goods_tv_count);
            }
            Integer num1 = ShoppingCartManager.getInstance().getTotalNum();
            count.setText(num1.toString());

        }

        /**
         * 增加的操作
         * @param view
         */
        private void addHandler(View view) {
            Integer num = ShoppingCartManager.getInstance().addGoods(data);
            //设置隐藏
            if (num == 1){
                //显示减号,和数量
                ivMinus.setVisibility(View.VISIBLE);
                tvMoney.setVisibility(View.VISIBLE);
            }
            //加好建好中间的数量设置
            tvMoney.setText(num.toString());

            //TODO 飞到购物车中的动画 还没有实现


            if(container==null) {
                container = (FrameLayout) UIUtils.getContainder(view, R.id.seller_detail_container);
            }
            //修改气泡提示
            if (count==null){
                count = container.findViewById(R.id.fragment_goods_tv_count);

            }

            if (num>0){
                count.setVisibility(View.VISIBLE);
            }
            Integer totalNum = ShoppingCartManager.getInstance().getTotalNum();
            count.setText(totalNum.toString());
        }


    }


}
