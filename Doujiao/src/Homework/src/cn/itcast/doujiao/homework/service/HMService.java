package cn.itcast.doujiao.homework.service;

import cn.itcast.doujiao.homework.entity.Homework;

public interface HMService {
    Homework CheckHM(Homework homework) throws Exception;
    Homework SetHomework(Homework homework) throws Exception;
    Homework HandInHomework(Homework homework) throws Exception;
}
