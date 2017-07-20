package cn.zzu.takeout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.zzu.takeout.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity {

    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //此处会实例化具体的P层的对象,一旦new出来的,两层之间的偶和也就开始耦合到一起了
        //model 就是个中数据对象
        /**
         * MVP
         * view
         * view的控制器
         * 业务逻辑  持有者,主持者.写业务逻辑,从产品的角度老说,,与餐品设计的业务逻辑
         * Model
         */
        presenter = new MainActivityPresenter(this);
    }
}
