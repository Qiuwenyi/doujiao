package cn.itcast.doujiao.common.util;

import cn.itcast.doujiao.common.entity.Entity;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;


/*
    JSON工具类
    处理和JSON相关的所有内容
 */
public class JSONUtil {
    public static void main(String[] args) {
        Entity e=new Entity();
        e.setCreateTime("18");
        String s=entityToJSON(e);
        System.out.println(s);//{"createTime":"18","idDel":"1"}


        System.out.println("______________");
        List<Entity> el1=new ArrayList<>();
        el1.add(e);
        String jl=entityListToJSON(el1);
        System.out.println(jl);//[{"createTime":"18","idDel":"1"}]

        System.out.println("______________");
        String js="{\"createTime\":\"18\",\"idDel\":\"1\"}";
        Entity e2=JSONToEntity(js,Entity.class);
        System.out.println(e2.getCreateTime());

        System.out.println("______________");
        String js2="[{\"createTime\":\"18\",\"idDel\":\"1\"},{\"createTime\":\"19\",\"idDel\":\"1\"}]";
        List<Entity> el2=JSONToEntityList(js2,Entity.class);
        System.out.println(el2.get(0).getCreateTime());
    }
    /**
     * 把对象转化成JSON格式的字符串
     * @param entity 指定对象
     * @return 返回JSON格式的字符串
     */
    public static String entityToJSON(Object entity){
        return JSON.toJSONString(entity);
    }

    /**
     * 将对象列表转换成JSON格式的字符串
     * @param entityList 指定对象列表
     * @return 返回JSON格式的字符串
     */
    public static String entityListToJSON(List<?> entityList){
        return entityToJSON(entityList);
    }

    /**
     * ?泛型的通配符，代表未知的任意类型，或者说是Object
     * 而使用T，代表将JSON字符串转换成指定类型的对象
     * @param json 数据
     * @param clazz 指定的类型Class对象
     * @param <T> 指定类型
     * @return 对象
     */
    public static <T>T JSONToEntity(String json,Class <T> clazz){
        return JSON.parseObject(json,clazz);
    }
    /**
     * 将JSON字符串转换成指定类型的对象列表
     * @param json 数据
     * @param clazz 指定的类型Class对象
     * @param <T> 指定类型
     * @return 对象列表
     */
    public static <T>List<T> JSONToEntityList(String json,Class<T> clazz){
        return JSON.parseArray(json,clazz);
    }
}
