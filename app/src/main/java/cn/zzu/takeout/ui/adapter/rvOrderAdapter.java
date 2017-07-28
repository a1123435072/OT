package cn.zzu.takeout.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.zzu.takeout.R;
import cn.zzu.takeout.model.dao.order.GoodsInfoss;
import cn.zzu.takeout.model.dao.order.Order;
import cn.zzu.takeout.utils.UIUtils;

/**
 * Created by yangg on 2017/7/28.
 */

public class rvOrderAdapter extends RecyclerView.Adapter {
    private ArrayList<Order> date;

    public void setDate(ArrayList<Order> data) {
        this.date = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_order, parent, false);

        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        OrderViewHolder orderViewHolder = (OrderViewHolder) holder;

        Order order = date.get(position);
        GoodsInfoss goodsInfoss = order.goodsInfos.get(order.goodsInfos.size());

        orderViewHolder.tvName.setText(goodsInfoss.name);

    }

    @Override
    public int getItemCount() {
        if (date!=null) {
            return date.size();
        }
        return 0;
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvFunction;
        private final TextView tvMoney;
        private final TextView tvFoods;
        private final TextView tvTime;
        private final TextView tvName;
        private final ImageView tvIcon;
        private int data;

        public OrderViewHolder(View itemView) {
            super(itemView);
            tvIcon = itemView.findViewById(R.id.iv_order_item_seller_logo);
            tvName = itemView.findViewById(R.id.tv_order_item_seller_name);
            tvTime = itemView.findViewById(R.id.tv_order_item_time);
            tvFoods = itemView.findViewById(R.id.tv_order_item_foods);
            tvMoney = itemView.findViewById(R.id.tv_order_item_money);
            tvFunction = itemView.findViewById(R.id.tv_order_item_multi_function);

        }


    }


}
