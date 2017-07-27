package cn.zzu.takeout.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mob.MobSDK;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.zzu.takeout.EventBus.MessageBus;
import cn.zzu.takeout.R;
import cn.zzu.takeout.model.dao.login.LoginData;
import cn.zzu.takeout.presenter.activity.LoginPresenter;
import cn.zzu.takeout.ui.fragment.MeFragment;
import cn.zzu.takeout.utils.SPUtil;
import cn.zzu.takeout.utils.ShapeUtil;
import cn.zzu.takeout.utils.UIUtils;

import static android.R.attr.id;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static cn.zzu.takeout.R.id.login;
import static cn.zzu.takeout.R.id.text;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EventHandler eventHandler;
    private boolean boolShowInDialog = true;
    private TextView btGetCode;
    private EditText etPhoneNum;
    private EditText etCode;
    private TextView btlogin;

    //sms
    private static final int CODE_ING = 1;//已发送,倒计时
    private static final int CODE_PEPEAT = 2;//重新发送
    private static final int SMSDDK_HANDLER = 3;//短信回调
    private int TIME = 60;//倒计时60秒中
    private String phoneNumber;
    private LoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // 如果希望在读取通信录的时候提示用户，可以添加下面的代码，并且必须在其他代码调用之前，否则不起作用；如果没这个需求，可以不加这行代码


        //初始化界面
        initView();
        //厨师话sdk
        initSMSSDK();


    }

    private void initSMSSDK() {
        MobSDK.init(LoginActivity.this, "1fc17dfef737b", "87b312a4df51d1c9051ff331fe777d45");
        // 创建EventHandler对象
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {

                if (data instanceof Throwable) {
                    Throwable throwable = (Throwable) data;
                    throwable.printStackTrace();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(throwable.getMessage());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String des = object.optString("detail");//错误描述
                    int status = object.optInt("status");//错误代码
                    if (status > 0 && !TextUtils.isEmpty(des)) {
                       // Toast.makeText(getApplicationContext(), "错误代码:" + des, Toast.LENGTH_SHORT).show();
                        return;
                    }


                } else {
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        // 处理你自己的逻辑
                        //Toast.makeText(getApplicationContext(), "验证码发送成功", Toast.LENGTH_SHORT).show();

                    } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //携带着手机好进行登录  验证成功
                        //if (!TextUtils.isEmpty(phoneNumber)) {
                        loginLocation(phoneNumber);
                        System.out.println("EVENT_SUBMIT_VERIFICATION_CODE-->" + "执行了");
                        //}

                    }
                }
            }
        };
        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        btGetCode = (TextView) findViewById(R.id.tv_user_code);
        etPhoneNum = (EditText) findViewById(R.id.et_user_phone);
        etCode = (EditText) findViewById(R.id.et_user_code);
        btlogin = (TextView) findViewById(login);
        btGetCode.setOnClickListener(this);
        btlogin.setOnClickListener(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_user_code://获取验证码
                phoneNumber = etPhoneNum.getText().toString().trim();

                if (!TextUtils.isEmpty(phoneNumber)) {
                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("发送短信")
                            .setMessage("我们将阿布短信验证码发送到一下好吗:\n"
                                    + "+86:" + phoneNumber)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    SMSSDK.getVerificationCode("86", phoneNumber);

                                    btGetCode.setClickable(false);//设置位不可点击
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {

                                            for (int j = 60; j > 0; j--) {

                                                handler.sendEmptyMessage(CODE_ING);//已发送
                                                if (j <= 0) {
                                                    break;
                                                }
                                                SystemClock.sleep(100);
                                            }
                                            handler.sendEmptyMessage(CODE_PEPEAT);//重新发送
                                        }
                                    }).start();
                                }
                            })
                            .create()
                            .show();
                }
                break;
            case login:

                if (phoneNumber == null) {
                    Toast.makeText(this, "请先获取验证码", Toast.LENGTH_SHORT).show();
                } else {
                    //取出来输入的验证码,然后进行验证
                    String code = etCode.getText().toString().trim();
                    if (!TextUtils.isEmpty(code)) {
                        SMSSDK.submitVerificationCode("86", phoneNumber, code);
                        Toast.makeText(this, "点击了登录", Toast.LENGTH_SHORT).show();
                        //对验证吗发送到服务器进行验证
                    }
                }
                break;
        }
    }

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CODE_ING:
                    btGetCode.setText("重新发送(" + TIME + "s)");
                    break;
                case CODE_PEPEAT://重新发送
                    btGetCode.setText("获取验证码");
                    btGetCode.setClickable(true);
                    break;
            }
        }
    };

    /**
     * 进行请求进行本地登录
     */
    private String type = "2";

    private void loginLocation(String phoneNumber) {
        loginPresenter = new LoginPresenter(LoginActivity.this);
        loginPresenter.getLoginData(phoneNumber, type);
    }


    /**
     * 登录成功,后 跳转到,,我的界面将数据传递过去,进行界面的展示
     *
     * @param loginBean
     */
    public void success(LoginData loginBean, String data) {

        //先保存用户的的id
        // int id = loginBean.id;
        ShapeUtil.put("user", data);
        //SPUtil.newInstance(UIUtils.getContext()).putString("uid",id+"");

        finish();
        MessageBus messageBus = new MessageBus();
        EventBus.getDefault().post(messageBus);
        /*Intent intent = new Intent(UIUtils.getContext(), MeFragment.class);

        startActivity(intent);*/


    }
}
