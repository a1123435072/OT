package cn.zzu.takeout.ui.adapter.Store;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import cn.zzu.takeout.R;
import cn.zzu.takeout.model.dao.StoreBean.Data;
import cn.zzu.takeout.utils.MyApplication;
import cn.zzu.takeout.utils.UIUtils;

import static android.media.CamcorderProfile.get;


/**
 * Created by yangg on 2017/7/23.
 */

public class StoreAdapter extends BaseAdapter {

    private List<Data> headDataSet;

    private int selectedHeadIndex;

    public StoreAdapter(List<Data> headDataSet) {
        this.headDataSet = headDataSet;
    }

    public void setDatas(List<Data> headData) {
        this.headDataSet = headData;

    }


    @Override
    public int getCount() {
        return headDataSet.size();
    }

    @Override
    public Object getItem(int i) {
        return headDataSet.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup parent) {
        Data data = headDataSet.get(i);
        HeadViewHolder holder;
        if (view == null) {
            view = new TextView(parent.getContext());
            holder = new HeadViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (HeadViewHolder) view.getTag();
        }

        holder.setData(data);

        if (i == selectedHeadIndex) {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
        return view;
    }


    private class HeadViewHolder {

        private View itemView;
        private Data data;

        public HeadViewHolder(View itemView) {
            this.itemView = itemView;
        }

        public void setData(Data dataa) {
            this.data = dataa;
            ((TextView) itemView).setText(data.name);
            ((TextView) itemView).setBackgroundColor(UIUtils.getContext().getResources().getColor(R.color.colorItemBg));
            ((TextView) itemView).setTextSize(12);

            int h = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, itemView.getResources().getDisplayMetrics()) + 0.5f);

            ((TextView) itemView).setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, h));
            ((TextView) itemView).setGravity(Gravity.CENTER);
        }
    }


    public void setSelectedPositon(int index) {
        selectedHeadIndex = index;
        notifyDataSetChanged();
    }
}
