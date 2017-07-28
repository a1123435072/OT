package cn.zzu.takeout.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cn.zzu.takeout.R;
import cn.zzu.takeout.model.dao.StoreBean.Eat;
import cn.zzu.takeout.ui.ShoppingCartManager;
import cn.zzu.takeout.utils.UIUtils;

/**
 * Created by yangg on 2017/7/28.
 */

public class rvCartAdapter extends RecyclerView.Adapter {
    private ShoppingCartManager shoppingCartManager;
    private Eat eat;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CartViewHolder cartViewHolder = (CartViewHolder) holder;

        eat = ShoppingCartManager.getInstance().goodsInfos.get(position);
        cartViewHolder.tvName.setText(eat.name);
        cartViewHolder.tvNumber.setText(eat.count+"");
        cartViewHolder.tvPrice.setText(eat.newPrice+"");

        String url = eat.icon;
        Picasso.with(UIUtils.getContext()).load(url).into(cartViewHolder.ivIcon);

    }

    @Override
    public int getItemCount() {
        return ShoppingCartManager.getInstance().goodsInfos.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{

        private final TextView tvNumber;
        private final TextView tvPrice;
        private final TextView tvName;
        private final ImageView ivIcon;
        
        public CartViewHolder(View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.item_iv);
            tvName = itemView.findViewById(R.id.item_tv_name);
            tvPrice = itemView.findViewById(R.id.item_tv_price);
            tvNumber = itemView.findViewById(R.id.item_tv_num);
        }
    }
}
