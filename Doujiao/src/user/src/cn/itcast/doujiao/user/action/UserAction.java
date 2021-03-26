package cn.itcast.doujiao.user.action;

import cn.itcast.doujiao.common.action.BaseAction;
import cn.itcast.doujiao.common.entity.Msg;
import cn.itcast.doujiao.common.util.JSONUtil;
import cn.itcast.doujiao.user.entity.User;
import cn.itcast.doujiao.user.service.UserService;
import cn.itcast.doujiao.user.service.impl.UserServiceImpl;
import java.util.Scanner;


/**
 * 用户控制器类
 * 处理所有用户的后台操作，并返回JSON格式的字符串消息
 */
public class UserAction extends BaseAction {
    //    用户名
    private String username;
    //    密码
    private String password;
    //    身份
    private String identity;
    //    学号
    private String IdNumber;

    /** Scanner扫描器对象，从控制台录入 */
    protected Scanner sc = new Scanner(System.in);

    public String register(){
        Msg msg=new Msg();
        try{
            User user = new User();
            user.setUsername(username);
            user.setIdNumber(IdNumber);
            UserService userService = new UserServiceImpl();

            System.out.println("请再次输入密码");
            String password2=sc.nextLine();

            user=userService.check(user);
            if(user==null) {
                context.put(REGISTER_USER, user);//把当前登录对象放到上下文对象中
                msg.setType(Msg.FAIL);
                msg.setMsg("请重新注册！");
            }
            else{
                msg.setType(Msg.SUCCESS);

                    if (password2.equals(password)) {
                        msg.setMsg("用户名及学号符合要求，且密码符合条件，注册成功");

                        //      5.记录日志
                        log.info("工号"+IdNumber+"用户名"+username + "同学/老师注册成功");
                    } else {
                        msg.setMsg("用户名及学号符合要求,但两次密码不同,注册失败");
                        msg.setType(Msg.FAIL);
                        //      5.记录日志
                        log.info("工号"+IdNumber+"用户名"+username + "同学/老师注册失败");
                    }
            }
            return JSONUtil.entityToJSON(msg);
        } catch (Exception e) {
            e.printStackTrace();
            msg.setType(Msg.FAIL);
            msg.setMsg("服务器异常");
            return JSONUtil.entityToJSON(msg);
        }
    }
    /**
     * 用户登录功能
     * @return
     */
    public String login(){
//        System.out.println("username:"+username);
//        System.out.println("password:"+password);
        Msg msg = new Msg();
        try {
//      1.封装数据到user对象
            User user = new User();
            user.setIdNumber(IdNumber);
            user.setPassword(password);

            //      2.调用UserService处理逻辑
//              User login(User user) throws Exception;
            UserService userService = new UserServiceImpl();
            user=userService.login(user);

//      3.异常处理

//      4.根据服务层返回结果生成消息
//            消息实体类Msg
            if(user!=null) {
                context.put(LOGIN_USER,user);//把当前登录对象放到上下文对象中
                msg.setType(Msg.SUCCESS);
                msg.setMsg("登录成功");
//      5.记录日志
                log.info("工号"+IdNumber+"同学/老师已登录");
            }
            else{
                msg.setType(Msg.FAIL);
                msg.setMsg("用户名或密码不正确");
            }
//      6.响应消息到客户端
            return JSONUtil.entityToJSON(msg);
        }catch (Exception e){
            e.printStackTrace();
            msg.setType(Msg.FAIL);
            msg.setMsg("服务器异常");
            return JSONUtil.entityToJSON(msg);
        }
    }

    /**
     * 获取当前登录对象
     * @return 返回用户对象,或null
     */
    public User getLoginUser(){
        Object obj=context.get(LOGIN_USER);
        if(obj !=null){
            return (User) obj;
        }
        return null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String IdNumber) {
        this.IdNumber = IdNumber;
    }
}

