package cn.itcast.doujiao.homework.dao.impl;

import cn.itcast.doujiao.common.dao.impl.BaseDAOImpl;
import cn.itcast.doujiao.homework.dao.HMDAO;
import cn.itcast.doujiao.homework.entity.Homework;

import java.util.List;

public class HMDAOImpl extends BaseDAOImpl implements HMDAO {
    /**
     * 调用IDataAccess获取数据并返回
     * 1.创建IDataAccess子类TXTDataAccess的对象
     *      IDataAccess dataAccess=new TXTDataAccess();
     * 2.调用该对象的方法获取所有用户数据并返回
     *      List<Homework> userList=dataAccess.getList(Homework.class);
     * @return 返回已布置作业列表
     * @throws Exception
     */
    @Override
    public List<Homework> getEntityList() throws Exception {
//        2.调用该对象的方法获取所有用户数据并返回
        return dataAccess.getList(Homework.class);
    }
    //TODO 写文件
    public List<Homework> writeEntityList(String data) throws Exception{

        return dataAccess.writeList(data,Homework.class);
    }
}
