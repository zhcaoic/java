package com.tiandog.Util;

import java.lang.reflect.Field;

public class ParameterEmptyUtil {

    public static <T> T parameterEmptyDeal(T t) throws Exception{
        //反射获取对象所有属性
        Field[] fields = t.getClass().getDeclaredFields();
        //遍历属性
        for (int i = 0; i < fields.length; i ++) {
            Field f = fields[i];
            //允许获取私有属性
            f.setAccessible(true);
            //对空字符串用null替换(仅对String类型属性进行处理)
            if (f.get(t) == "") {
                //检查属性类型是否为String
                if (f.getGenericType().toString().equals("class java.lang.String")) {
                    f.set(t, null);
                }
            }
        }

        return t;
    }

}
