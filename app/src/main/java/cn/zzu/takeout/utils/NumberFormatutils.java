package cn.zzu.takeout.utils;

import java.text.NumberFormat;

/**
 * Created by yangg on 2017/7/28.
 */

public class NumberFormatutils {
    public NumberFormatutils() {
    }

    //格式化数据保留量喂小数,并且带上 $
    public static String formatDigits(double data){
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        nf.setMaximumFractionDigits(2);
        return nf.format(data);
    }
}
