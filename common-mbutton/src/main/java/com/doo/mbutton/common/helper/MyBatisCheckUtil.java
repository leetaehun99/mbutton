package com.doo.mbutton.common.helper;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

public class MyBatisCheckUtil {
	public static boolean isEmpty(Object obj){
        if( obj instanceof String ) return obj==null || "".equals(obj.toString().trim());
        else if( obj instanceof List ) return obj==null || ((List)obj).isEmpty();
        else if( obj instanceof Map ) return obj==null || ((Map)obj).isEmpty();
        else if( obj instanceof Object[] ) return obj==null || Array.getLength(obj)==0;
        else return obj==null;
    }
	
	//@com.doo.mbutton.common.helper.MyBatisCheckUtil@isEmpty(keyword)
    public static boolean isNotEmpty(Object obj){
        return !isEmpty(obj);
    }
}

