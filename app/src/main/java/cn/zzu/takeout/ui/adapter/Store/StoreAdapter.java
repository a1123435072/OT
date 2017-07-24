package cn.zzu.takeout.ui.adapter.Store;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.zzu.takeout.R;
import cn.zzu.takeout.model.dao.StoreBean.Data;
import cn.zzu.takeout.model.dao.StoreBean.StoreBean;
import cn.zzu.takeout.utils.UIUtils;


/**
 * Created by yangg on 2017/7/23.
 */

public class StoreAdapter extends RecyclerView.Adapter {
    private StoreBean date;

    public void setDate(StoreBean date) {
        this.date = date;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.store_list, parent, false);

        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        StoreViewHolder storeViewHolder = (StoreViewHolder) holder;
        List<Data> data = date.data;
        String name = data.get(position).getName();
        storeViewHolder.tvStore.setText(name);
    }

    @Override
    public int getItemCount() {
        if (date != null) {

            return date.data.size();
        }
        return 0;
    }

    public class StoreViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvStore;

        public StoreViewHolder(View itemView) {
            super(itemView);
            tvStore = itemView.findViewById(R.id.tv_sendprice);
        }
    }


}
