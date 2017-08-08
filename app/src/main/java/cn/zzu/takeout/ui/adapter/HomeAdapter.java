package cn.zzu.takeout.ui.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import cn.zzu.takeout.R;
import cn.zzu.takeout.model.dao.Bean.Body;
import cn.zzu.takeout.model.dao.Bean.Categorie;
import cn.zzu.takeout.model.dao.Bean.HomeBean;
import cn.zzu.takeout.model.dao.Bean.Promotion;
import cn.zzu.takeout.ui.activity.StoreActivity;
import cn.zzu.takeout.utils.LogUtils;
import cn.zzu.takeout.utils.UIUtils;

import static android.R.attr.type;

/**
 * Created by yangg on 2017/7/21.
 */

public class HomeAdapter extends RecyclerView.Adapter implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {


    private static final String TAG = HomeAdapter.class.getSimpleName();


    private HomeBean data;

    private static final int TYPE_TITLE = 1;
    private static final int TYPE_SELLER = 0;
    private static final int TYPE_HEAD = 2;
    private Integer id;


    public void setDate(HomeBean date) {
        this.data = date;
        notifyDataSetChanged();
        //LogUtils.s("data-->" + data.toString());
    }

    public HomeAdapter() {
    }

    public HomeBean getData() {
        return data;
    }

    @Override
    public int getItemViewType(int position) {



        if (position == 0) {
            return TYPE_HEAD;
        } else {
            int type = data.getBody().get(position-1).getType();
//            type = data.getBody().get(position-1).getType();
//            LogUtils.s("---->position"+position+"--type-"+type);
            if (type == 0) {
                //LogUtils.s(type + "-->type");
                return TYPE_SELLER;
            } else if (type == 1) {

                //type = type  == 1 ? TYPE_TITLE : TYPE_DIVISION;
                // LogUtils.s("--->type" + type);
                return TYPE_TITLE;
            }
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_SELLER) {
            View sellerview = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_seller, parent, false);
            return new SellerViewHolder(sellerview);
        } else if (viewType == TYPE_TITLE) {
            View titleView = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_division, parent, false);
            return new TitleViewHolder(titleView);
        } else if (viewType == TYPE_HEAD) {
            View HeadView = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_home_head, parent, false);
            return new HeadViewHolder(HeadView);
        }

        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Body bodyData = data.getBody().get(position);
        int itemViewType = getItemViewType(position);

        if (itemViewType == TYPE_SELLER) {
            SellerViewHolder sellerViewHolder = (SellerViewHolder) holder;
            sellerViewHolder.tvName.setText(bodyData.getSeller().getName());
            id = bodyData.getSeller().getId();
            String url = "http://10.0.2.2:8080/TakeoutService/imgs/category/1.png";
            Picasso.with(UIUtils.getContext()).load(url).error(R.drawable.ic_launcher).into(sellerViewHolder.iv);
            sellerViewHolder.llSale.setOnClickListener(saleLintener);
        } else if (itemViewType == TYPE_TITLE) {
            TitleViewHolder titleViewHolder = (TitleViewHolder) holder;
            List<String> recommendInfos = data.body.get(position).recommendInfos;
            titleViewHolder.tv1.setText(recommendInfos.get(0));
            titleViewHolder.tv2.setText(recommendInfos.get(1));
            titleViewHolder.tv3.setText(recommendInfos.get(2));
            titleViewHolder.tv4.setText(recommendInfos.get(3));
            titleViewHolder.tv5.setText(recommendInfos.get(4));
            titleViewHolder.tv6.setText(recommendInfos.get(5));
        } else if (itemViewType == TYPE_HEAD) {


            HeadViewHolder headViewHolder = (HeadViewHolder) holder;

            HashMap<String, String> url_map = new HashMap<>();
            List<Promotion> promotionList = data.head.promotionList;
            for (int i = 0; i < promotionList.size(); i++) {

                String names = promotionList.get(i).getInfo() + i + "";
                String pics = promotionList.get(i).getPic();

                LogUtils.s("pics--->" + pics);
                url_map.put(names, pics);
            }

            for (String s : url_map.keySet()) {
                TextSliderView textSliderView = new TextSliderView(UIUtils.getContext());
                textSliderView
                        .description(s)
                        .image(url_map.get(s))
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(this);
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle()
                        .putString("extra", s);
                //添加之前,要先remoview掉
                headViewHolder.sliderLayout.removeAllSliders();
                headViewHolder.sliderLayout.addSlider(textSliderView);
            }

            headViewHolder.sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
            headViewHolder.sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            headViewHolder.sliderLayout.setCustomAnimation(new DescriptionAnimation());
            headViewHolder.sliderLayout.setDuration(4000);
            headViewHolder.sliderLayout.addOnPageChangeListener(this);


            //动态添加分类布局

            headViewHolder.llFenlei.setOrientation(LinearLayout.HORIZONTAL);//设置Linearlayout为垂直方向布局

            List<Categorie> categorieList = data.head.getCategorieList();
            for (int i = 0; i < categorieList.size(); i += 2) {
                View view = View.inflate(UIUtils.getContext(), R.layout.item_home_head_category, null);


                ImageView ivTop = view.findViewById(R.id.top_iv);
                TextView tvTop = view.findViewById(R.id.top_tv);
                ImageView ivBottom = view.findViewById(R.id.bottom_iv);
                TextView tvBottom = view.findViewById(R.id.bottom_tv);

                ivTop.setPadding(10, 10, 10, 10);
                tvTop.setPadding(10, 10, 10, 10);
                ivBottom.setPadding(10, 10, 10, 10);
                tvBottom.setPadding(10, 10, 10, 10);
                Categorie categorie = categorieList.get(i);
                String picUrl = categorie.getPic();
                Picasso.with(UIUtils.getContext()).load(picUrl).into(ivTop);
                tvTop.setText(categorie.getName());

                Categorie categorie2 = categorieList.get(i + 1);
                String picurl2 = categorie2.getPic();
                Picasso.with(UIUtils.getContext()).load(picurl2).into(ivBottom);
                tvBottom.setText(categorie2.getName());
                headViewHolder.llFenlei.addView(view);
            }

        }

    }

    //创建布局
    private View createView(String zhangsan) {

        return null;
    }

    @Override
    public int getItemCount() {
        return data != null && data.body != null ? data.body.size() : 0;
    }

    //轮播图的点击实践
    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //商家点击事件
    private View.OnClickListener saleLintener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Toast.makeText(UIUtils.getContext(),"dianji le " ,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UIUtils.getContext(), StoreActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("id", id);
            UIUtils.getContext().startActivity(intent);
        }
    };


    public class SellerViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private ImageView iv;
        private final LinearLayout llSale;

        public SellerViewHolder(View itemView) {
            super(itemView);

            llSale = itemView.findViewById(R.id.ll_sale);
            tvName = itemView.findViewById(R.id.tv_name);
            iv = itemView.findViewById(R.id.iv_pic);

        }
    }

    public class TitleViewHolder extends RecyclerView.ViewHolder {


        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private TextView tv4;
        private TextView tv5;
        private TextView tv6;

        public TitleViewHolder(View itemView) {
            super(itemView);

            tv1 = itemView.findViewById(R.id.tv_1);
            tv2 = itemView.findViewById(R.id.tv_2);
            tv3 = itemView.findViewById(R.id.tv_3);
            tv4 = itemView.findViewById(R.id.tv_4);
            tv5 = itemView.findViewById(R.id.tv_5);
            tv6 = itemView.findViewById(R.id.tv_6);
        }
    }


    public class HeadViewHolder extends RecyclerView.ViewHolder {

        public SliderLayout sliderLayout;
        private final LinearLayout llFenlei;


        public HeadViewHolder(View itemView) {
            super(itemView);

            sliderLayout = itemView.findViewById(R.id.slider);
            llFenlei = itemView.findViewById(R.id.ll_fenlei);


        }
    }


}
