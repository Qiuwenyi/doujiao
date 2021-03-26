package cn.itcast.doujiao.user.dao;

import cn.itcast.doujiao.common.dao.BaseDAO;
import cn.itcast.doujiao.user.entity.User;

import java.util.List;

public interface UserDAO extends BaseDAO {
    List<User> getEntityList() throws Exception;
    List<User> writeEntityList() throws Exception;
}
