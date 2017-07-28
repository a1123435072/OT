package cn.zzu.takeout.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.zzu.takeout.R;
import cn.zzu.takeout.model.dao.login.LoginData;
import cn.zzu.takeout.ui.ShoppingCartManager;
import cn.zzu.takeout.ui.adapter.rvCartAdapter;
import cn.zzu.takeout.ui.views.RecycleViewDivider;
import cn.zzu.takeout.utils.MyApplication;
import cn.zzu.takeout.utils.NumberFormatutils;
import cn.zzu.takeout.utils.UIUtils;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class CartActivity extends AppCompatActivity {

    private RecyclerView rvCart;
    private TextView tvMoney;
    private Button btJiesuan;
    private int id;
    private Toolbar toolbar;
    private String money;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //初始化控件
        initView();
        //初始化recycleView
        initRecycleView();
        //请求网络


        initData();

        //初始化点击事件
        initListener();
    }
    //初始化点击事件
    private void initListener() {
        btJiesuan.setOnClickListener(btJiesuanListener);
    }

    /**
     * 结算监听
     */
    private View.OnClickListener btJiesuanListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO  跳转到结算界面

            Intent intent = new Intent(CartActivity.this, JieSuanActivity.class);

            intent.putExtra("moeny",money);
            //开启新的界面
            startActivity(intent);


        }
    };


    private void initData() {
        money = NumberFormatutils.formatDigits(ShoppingCartManager.getInstance().getMoney() / 100.0);
        tvMoney.setText(money);
    }

    private void initRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CartActivity.this, LinearLayoutManager.VERTICAL, false);
        rvCart.setLayoutManager(linearLayoutManager);
        // 设置分割线
        rvCart.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.HORIZONTAL,1,0XE3E0DC));
        rvCart.setAdapter(new rvCartAdapter());
    }

    //初始化控件
    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rvCart = (RecyclerView) findViewById(R.id.cart_rv);
        tvMoney = (TextView) findViewById(R.id.tv_money);
        btJiesuan = (Button) findViewById(R.id.bt_jiesuan);

        toolbar.setTitle("购物车");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}
