package cn.itcast.doujiao.user.service.impl;

import cn.itcast.doujiao.user.dao.UserDAO;
import cn.itcast.doujiao.user.dao.impl.UserDAOImpl;
import cn.itcast.doujiao.user.entity.User;
import cn.itcast.doujiao.user.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    /**
     * 用户登录，根据用户名、密码获取用户对象
     * 1.调用UserDAO获取用户列表数据
     *  List<User> getEntityList() throws Exception;
     * 2.遍历用户列表，逐个与给定用户对象的用户名，密码进行匹配
     * 3.匹配成功则返回User对象，失败返回null
     * @param user 封装了用户名，密码的实体对象
     * @return 返回User对象，或者当用户名或密码错误时返回null
     * @throws Exception
     */
    @Override
    public User login(User user) throws Exception {
//  1.调用UserDAO获取用户列表数据
        UserDAO userDAO=new UserDAOImpl();
        List<User>userList=userDAO.getEntityList();

//  2.遍历用户列表，逐个与给定用户对象的工号，密码进行匹配
        if(userList!=null){
            for (User u: userList){
                if(u.getIdNumber().equals(user.getIdNumber())
                        &&u.getPassword().equals(user.getPassword())){
                    return u;//匹配成功则返回User对象
                }
            }
        }
        return null;//失败返回null
    }
    public User check(User user) throws Exception {
//  1.调用UserDAO获取用户列表数据
        UserDAO userDAO=new UserDAOImpl();
        List<User>userList=userDAO.getEntityList();

//  2.遍历用户列表，逐个与已有用户对象的工号及用户名进行匹配
        boolean bool=true;
        if(userList!=null){
            for (User u: userList){
                if(u.getUsername().equals(user.getUsername())){
                    System.out.println("该用户名已存在");
                    bool=false;
                }

                if(u.getIdNumber().equals(user.getIdNumber())){
                    System.out.println("该工号用户已存在");
                    bool=false;
                }

            }
            if(bool==false) {
                return null;//匹配成功则返回null，代表已存在
            }
            else{
                System.out.println("用户名可使用");
            }
        }
        return user;//失败返回user对象代表可注册
    }
    //TODO 将用户注册信息写入数据库
    public User register(User user) throws Exception {
////  1.调用UserDAO获取用户列表数据
//        UserDAO userDAO=new UserDAOImpl();
//        List<User>userList=userDAO.getEntityList();
//
////  2.遍历用户列表，逐个与已有用户对象的用户名进行匹配
//        if(userList!=null){
//            for (User u: userList){
//                if(u.getUsername().equals(user.getUsername())){
                    return null;//匹配成功则返回null代表已存在
//                }
//            }
//        }
//        return user;//失败返回user对象代表可注册
    }
}
