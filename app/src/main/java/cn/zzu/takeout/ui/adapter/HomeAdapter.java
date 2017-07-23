package cn.zzu.takeout.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.zzu.takeout.model.dao.Bean.Body;
import cn.zzu.takeout.model.dao.Bean.HomeBean;
import cn.zzu.takeout.utils.LogUtils;
import cn.zzu.takeout.utils.UIUtils;

import static android.R.attr.type;

/**
 * Created by yangg on 2017/7/21.
 */

public class HomeAdapter extends RecyclerView.Adapter {


    private HomeBean data;

    private static final int TYPE_TITLE = 0;
    private  static  final  int TYPE_DIVISION = 1;
    private static final  int TYPE_SELLER= 2;

    public void setDate(HomeBean date) {
        this.data = date;

        LogUtils.s("data-->" + data.toString());
    }

    public HomeAdapter() {

    }

    public HomeBean getData() {
        return data;
    }

    @Override
    public int getItemViewType(int position) {

        int type = 1;
        if (position  == 0){
            type = TYPE_TITLE;
            type = TYPE_DIVISION;
        }else if ((position - ()))


        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        //Integer type = body.getType();


        // HomeBean.Body body = data.body.get(9);

        System.out.println("-->"+type);

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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);

        }
    }


}
