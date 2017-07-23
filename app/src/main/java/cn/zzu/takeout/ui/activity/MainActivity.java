package cn.zzu.takeout.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zzu.takeout.R;
import cn.zzu.takeout.ui.fragment.HomeFragment;
import cn.zzu.takeout.ui.fragment.MeFragment;
import cn.zzu.takeout.ui.fragment.MoreFragment;
import cn.zzu.takeout.ui.fragment.OrderFragment;

public class MainActivity extends AppCompatActivity {


    //创建fragment集合用来保存tab栏对应的fragment对象
    List<Fragment> fragments = new ArrayList<>();
    @BindView(R.id.main_fragment_container)
    FrameLayout mainFragmentContainer;
    @BindView(R.id.main_bottome_switcher_container)
    LinearLayout mainBottomeSwitcherContainer;

    // private LinearLayout mainBottomeSwitcherContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //mainBottomeSwitcherContainer = (LinearLayout) findViewById(R.id.main_bottome_switcher_container);

        //2,创建fragment 保存到集合中
        fragments.add(new HomeFragment());
        fragments.add(new OrderFragment());
        fragments.add(new MeFragment());
        fragments.add(new MoreFragment());


        //1,遍历tab栏view容器中所有子视图
        int childCount = mainBottomeSwitcherContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            FrameLayout child = (FrameLayout) mainBottomeSwitcherContainer.getChildAt(i);
            //设置点击监听
            child.setOnClickListener(switchListener);
        }

        String event1 = "Event---001";
        // 发布事件
         EventBus.getDefault().post(event1);

    }

    private View.OnClickListener switchListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //2,遍历点击点击时间中更新tab栏选中的tab的UI效果
            int index = mainBottomeSwitcherContainer.indexOfChild(view);

            updateUI(index);

            //处理点击事件,切换对应的fragment
            switchFragment(index);
        }
    };

    /**
     * 3.1使用FragmentManager 开启fragmet事物,切换Fragment
     *
     * @param index
     */
    private void switchFragment(int index) {

        Fragment fragment = fragments.get(index);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, fragment)
                .commit();

    }


    private void updateUI(int index) {
        Toast.makeText(this, "点击了" + index, Toast.LENGTH_SHORT).show();
        //由于我们要处理通用的item,我们可能要处理很多层,我们要去写赌鬼,遍历孩子的孩子
        //进来后.将控件设置喂Enable/false
        int childCount = mainBottomeSwitcherContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            //给点中的Veiw设置enable是false属性,可以让让不能接收点击事件,不会进入onClick方法
            setEnabled(mainBottomeSwitcherContainer.getChildAt(i), index != i);
        }
    }


    private void setEnabled(View child, boolean enabled) {

        child.setEnabled(enabled);
        if (child instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) child).getChildCount(); i++) {
                setEnabled(((ViewGroup) child).getChildAt(i), enabled);
            }
        }
    }

    /**
     * 需求完成一个通用的底部导航的处理
     *
     */

    /**
     * i=index
     *
     * 不可以在点击了,每一个item控件都需要切换状态
     * 如果有三分或者四个怎么办..如果是一份容器,,或者套了很多层,就不好处理了
     * 吧孩子所有的状态改为false:不可用
     *
     * else
     * 可以点击
     *
     */

    /**
     * i=index  将每个item中的所有控件状态一同改变
     * 由于我们要处理通用的item,我们可能要处理很多层,我们要去写递归,遍历孩子的孩子
     * 只要进来了,设置enable喂false
     */


}
