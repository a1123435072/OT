package cn.zzu.takeout.ui.activity;

import android.content.DialogInterface;
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

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.zzu.takeout.R;
import cn.zzu.takeout.utils.UIUtils;

import static android.R.attr.id;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

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
        // 创建EventHandler对象
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (data instanceof Throwable) {
                    Throwable throwable = (Throwable) data;
                    String msg = throwable.getMessage();
                    Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                } else {
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        // 处理你自己的逻辑

                        Message msg = new Message();
                        msg.arg1 = event;
                        msg.arg2 = result;
                        msg.obj = data;
                        msg.what = SMSDDK_HANDLER;
                        handler.sendMessage(msg);
                    }
                }
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                msg.what = SMSDDK_HANDLER;
                handler.sendMessage(msg);
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
        btlogin = (TextView) findViewById(R.id.login);
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
                                                SystemClock.sleep(1000);

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
            case R.id.login:

                if (phoneNumber == null) {
                    Toast.makeText(this,"请先获取验证码",Toast.LENGTH_LONG).show();
                }else {
                   //

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
                case SMSDDK_HANDLER:
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    //回调完成
                    if (result == SMSSDK.RESULT_COMPLETE) {

                        Toast.makeText(UIUtils.getContext(), "验证成功", Toast.LENGTH_SHORT).show();

                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        Toast.makeText(UIUtils.getContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();

                    } else {
                        ((Throwable) data).printStackTrace();
                    }
                    if (result == SMSSDK.RESULT_ERROR) {

                    }
                    break;
            }
        }
    };

}
