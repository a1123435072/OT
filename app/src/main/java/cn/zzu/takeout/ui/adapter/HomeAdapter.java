package cn.zzu.takeout.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.zzu.takeout.R;
import cn.zzu.takeout.model.dao.Bean.HomeBean;
import cn.zzu.takeout.model.dao.Bean.HomeInfo;
import cn.zzu.takeout.utils.UIUtils;

/**
 * Created by yangg on 2017/7/21.
 */

public class HomeAdapter extends RecyclerView.Adapter {


    private HomeBean date;

    public void setDate(HomeBean date) {
        this.date = date;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        TextView textView = new TextView(UIUtils.getContext());
        textView.setText("zhangsa ");

        return new ViewHolder(textView);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;



    }

    @Override
    public int getItemCount() {
        return 50;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);

        }
    }


}
