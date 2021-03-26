package cn.itcast.doujiao.common.dao.impl;

import cn.itcast.doujiao.common.dao.BaseDAO;
import cn.itcast.doujiao.common.dao.IDataAccess;

public class BaseDAOImpl implements BaseDAO{
    //    1.创建IDataAccess子类TXTDataAccess的对象
    protected IDataAccess dataAccess=new TXTDataAccess();
}