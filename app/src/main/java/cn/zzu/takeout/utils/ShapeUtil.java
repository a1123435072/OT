package cn.zzu.takeout.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;


import java.util.HashSet;
import java.util.Set;

/**
 * Created by EshelGuo on 2017/6/10.
 */

public class ShapeUtil {

	private static SharedPreferences sp;
	static {
		sp = UIUtils.getContext().getSharedPreferences("takeout.config",Context.MODE_PRIVATE);
	}
	private ShapeUtil(){}

	public static void put(String name , Object value){
		SharedPreferences.Editor edit = sp.edit();
		if(value instanceof Integer ){
			edit.putInt(name, (Integer) value);
		}else if(value instanceof Boolean){
			edit.putBoolean(name, (Boolean) value);
		}else if(value instanceof String){
			edit.putString(name, (String) value);
		}else if (value instanceof Long)
			edit.putLong(name, (Long) value);
		else if (value instanceof Float)
			edit.putFloat(name, (Float) value);
		else if (value instanceof Set){
			Set<String> set = new HashSet<>();
			for (Object s : (Set) value) {
				set.add(s.toString());
			}
			edit.putStringSet(name,set);
		}else {
			edit.putString(name,value.toString());
		}
		edit.commit();
	}
	public static <T>T get(String name , @NonNull T value){
		Object result = null;
		if(value instanceof Integer ){
			result = sp.getInt(name, (Integer) value);
		}else if(value instanceof Boolean){
			result = sp.getBoolean(name, (Boolean) value);
		}else if(value instanceof String){
			result = sp.getString(name, (String) value);
		}else if (value instanceof Long)
			result = sp.getLong(name, (Long) value);
		else if (value instanceof Float)
			result = sp.getFloat(name, (Float) value);
		else if (value instanceof Set){
			Set<String> set = new HashSet<>();
			for (Object s : (Set) value) {
				set.add(s.toString());
			}
			result = sp.getStringSet(name,set);
		}else {
			result = sp.getString(name,value.toString());
		}
		return (T) result;
	}
}
