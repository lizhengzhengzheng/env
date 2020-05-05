package com.env.web.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;

/**
 * 动态类生成工具类
 * @author lz
 *
 */
public class DynamicFactory {
	
	public static final Object dynamicClass(Object object,List<String> name,List<String> value) throws Exception{
		HashMap<String, Object> returnMap = new HashMap<>();
	    HashMap<String, Class<?>> typeMap = new HashMap<>();
	    Class<? extends Object> type = object.getClass();
	    BeanInfo beanInfo = Introspector.getBeanInfo(type);
	    PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
	    for(int i = 0; i < propertyDescriptors.length; i++) {
	        PropertyDescriptor descriptor = propertyDescriptors[i];
	        String propertyName = descriptor.getName();
	        if(!propertyName.equals("class")) {
	            Method readMethod = descriptor.getReadMethod();
	            Object result = readMethod.invoke(object, new Object[0]);
	            if(result != null) {
	                returnMap.put(propertyName, result);
	            } else {
	                returnMap.put(propertyName, "");
	            }
	            typeMap.put(propertyName, descriptor.getPropertyType());
	        }
	    }
	    
	    //添加属性
        for(int i = 0; i < name.size(); i++) {
        	returnMap.put(name.get(i),value.get(i));
        	typeMap.put(name.get(i), Class.forName("java.lang.String"));
	    }
	    //map转换成实体对象
	    DynamicBean bean = new DynamicBean(typeMap);
	    //赋值
	    Set<String> keys = typeMap.keySet();
	    for(Iterator<String> it = keys.iterator(); it.hasNext(); ) {
	        String key = (String) it.next();
	        bean.setValue(key, returnMap.get(key));
	    }
	    Object obj = bean.getObject();
//	    return obj;
	    TypeUtils.compatibleWithJavaBean = true;
	    String gsonString = JSON.toJSONString(obj).replace("$cglib_prop_", "");
        return JSONObject.parseObject(gsonString);
	}
}
