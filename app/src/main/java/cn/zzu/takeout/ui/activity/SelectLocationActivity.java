package cn.zzu.takeout.ui.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.MyLocationStyle;

import cn.zzu.takeout.R;

public class SelectLocationActivity extends AppCompatActivity implements LocationSource {

    private Toolbar toolbar;
    private MapView mapView;
    private RecyclerView rvPoiList;
    private AMap aMap;

    /**定位步骤
     * 1.布局中放置MapView
     * 2.mapview的管理工具的获取
     * 3.基本设置
     * 4.配置小蓝点
     * 设置监听(定位.定位结果通知)
     * 开始定位
     * 获取位置信息
     * 结束定位
     *
     * 周边搜索的步骤
     * 1,获取定位点:开启周边搜索(指定范围和搜索内容)
     * 2,处理结果(兴趣点列表)
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mapView = (MapView) findViewById(R.id.map);
        rvPoiList = (RecyclerView) findViewById(R.id.rv_poiList);

        //初始化toolbar
        initToolBar();
        //初始化地图
        initMap(savedInstanceState);
    }


    private void initMap(Bundle savedInstanceState) {
        mapView.onCreate(savedInstanceState);//此方法必须重写
        if (aMap == null){
            aMap = mapView.getMap();
            setUpMatp();
        }

    }

    //设置地图的配置信息
    private void setUpMatp() {
        // 自定义系统定位小蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                .fromResource(R.drawable.location_marker));// 设置小蓝点的图标
        myLocationStyle.strokeColor(Color.TRANSPARENT);// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// 设置圆形的填充颜色
        // myLocationStyle.anchor(int,int)//设置小蓝点的锚点
        myLocationStyle.strokeWidth(0.0f);// 设置圆形的边框粗细
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.moveCamera(CameraUpdateFactory.zoomTo(18));

        aMap.setLocationSource(this);// 设置定位数据源监听
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false

    }

    //初始化toolbar
    private void initToolBar() {


    }

    //设置定位数据源监听1
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

    }

    //设置定位数据源监听2
    @Override
    public void deactivate() {

    }
}
