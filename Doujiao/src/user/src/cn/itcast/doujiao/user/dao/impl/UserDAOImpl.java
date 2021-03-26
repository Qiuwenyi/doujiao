package cn.itcast.doujiao.user.dao.impl;

import cn.itcast.doujiao.common.dao.impl.BaseDAOImpl;
import cn.itcast.doujiao.user.dao.UserDAO;
import cn.itcast.doujiao.user.entity.User;

import java.util.List;

public class UserDAOImpl extends BaseDAOImpl implements UserDAO {
    /**
     * 调用IDataAccess获取数据并返回
     * 1.创建IDataAccess子类TXTDataAccess的对象
     *      IDataAccess dataAccess=new TXTDataAccess();
     * 2.调用该对象的方法获取所有用户数据并返回
     *      List<User> userList=dataAccess.getList(User.class);
     * @return 返回用户对象列表
     * @throws Exception
     */
    @Override
    public List<User> getEntityList() throws Exception {
//        2.调用该对象的方法获取所有用户数据并返回
        return dataAccess.getList(User.class);
    }
    //TODO 写文件
    public List<User> writeEntityList() throws Exception{
        //dataAccess.writeList(User.class);
        return null;
    }

}

