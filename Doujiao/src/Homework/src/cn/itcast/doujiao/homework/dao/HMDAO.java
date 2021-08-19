package cn.itcast.doujiao.homework.dao;

import cn.itcast.doujiao.common.dao.BaseDAO;
import cn.itcast.doujiao.homework.entity.Homework;

import java.util.List;

public interface HMDAO extends BaseDAO {
    List<Homework> getEntityList() throws Exception;
    List<Homework> writeEntityList(String data) throws Exception;
}
