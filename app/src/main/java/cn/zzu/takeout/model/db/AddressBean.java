package cn.zzu.takeout.model.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import static android.R.attr.id;

/**
 * Created by yangg on 2017/7/28.
 */
@DatabaseTable(tableName = "t_address")
public class AddressBean {
    @DatabaseField(id =true)
    private int _id;
    @DatabaseField(canBeNull = false)
    private String goodsAddress;
    @DatabaseField(canBeNull = false)
    private String village;

    //有联系,必须要有哟个user信息
    /**
     * 不能喂空
     * 有一个外键
     */
}
