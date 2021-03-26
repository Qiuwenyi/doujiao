package cn.itcast.doujiao.user.service;

import cn.itcast.doujiao.user.entity.User;

public interface UserService {
    User login(User user) throws Exception;
    User register(User user) throws Exception;
    User check(User user) throws Exception;
}
