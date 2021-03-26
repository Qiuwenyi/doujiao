package cn.itcast.doujiao.client;

import cn.itcast.doujiao.common.entity.Msg;
import cn.itcast.doujiao.common.util.JSONUtil;
import cn.itcast.doujiao.user.action.UserAction;

/**
 * 用户操作界面
 * 所有和用户操作相关的内容，都放与这个类中
 */
public class UserClient extends Client{

    /**
     *
     * @return
     */
    public String index(){
        while (true){
            System.out.println("补充个人信息(PI)/退出登录(E)");
            String UsAc =sc.nextLine();
            if(UsAc.equals("PI")||UsAc.equals("pi")){
                while (true){
                    System.out.println("用户名US/密码PW/头像HP/手机号码TP/邮箱MA/退出个人信息修改E");
                    String UsAs = sc.nextLine();

                }
            }
            if(UsAc.equals("E")||UsAc.equals("e")){
                break;
            }
        }
        return null;
    }

    /**
     * 1.使用控制台提示用户输入用户名与密码
     * 2.向服务器发送请求，并接收返回消息字符串
     *  使用setter方法把数据传递给Action
     *  调用Action的登录功能
     * 3.解析消息字符串，提示用户信息
     * 4.页面跳转，使用字符串常量作为跳转标记
     *  成功，返回上一次操作的页面
     *  失败，返回登录界面
     * @return
     */
    public String showLogin(){
        System.out.println("-------------欢迎登录----------------");
//  1.使用控制台提示用户输入用户名与密码
        System.out.println("请输入工号");
        String IdNumber=sc.nextLine();
        System.out.println("请输入密码");
        String password=sc.nextLine();
//  2.向服务器发送请求，并接收返回消息字符串
        UserAction userAction=new UserAction();
//     使用setter方法把数据传递给Action
        userAction.setIdNumber(IdNumber);
        userAction.setPassword(password);
//     调用Action的登录功能
        String result=userAction.login();
//  3.解析消息字符串，提示用户信息
        Msg msg= JSONUtil.JSONToEntity(result, Msg.class);
        if(msg.getType().equals(Msg.SUCCESS)){
            System.out.println(msg.getMsg());
            return HISTORY;
        }
        else{
            System.out.println(msg.getMsg());
            return LOGIN;
        }
    }
    //TODO 密码和用户名还有身份能否实现不按顺序更改的要求？
    public String register(){
        System.out.println("-------------欢迎注册----------------");
        UserAction userAction=new UserAction();
        //  1.使用控制台提示用户输入身份

        while (true) {
            System.out.println("请问您是老师/学生？T/S");
            String ide=sc.nextLine();
            if (ide.equals("T") || ide.equals("t") || ide.equals("S") || ide.equals("s")) {
                if(ide.equals("T") || ide.equals("t")) {
                    userAction.setIdentity("Teacher");
                }
                else{
                    userAction.setIdentity("Student");
                }
                break;
            }
            System.out.println("输入错误");
        }
        //  2.使用控制台提示用户输入工号
        while (true) {
            System.out.println("请输入工号");
            String IdNumber = sc.nextLine();
            if(IdNumber.length()==9){
                userAction.setIdNumber(IdNumber);
                break;
            }
            System.out.println("工号长度不符合要求");
        }
        //  3.使用控制台提示用户输入用户名
        while (true) {
            System.out.println("请输入用户名");
            String username = sc.nextLine();
            if(username.length()>=2&&username.length()<=10){
                userAction.setUsername(username);
                break;
            }
            System.out.println("用户名长度不符合要求");
        }
        //  4.使用控制台提示用户输入密码
        //TODO 密码中字符的检测
        while (true) {
            System.out.println("请设置密码");
            String password = sc.nextLine();
            if(password.length()>=6&&password.length()<=18) {
                userAction.setPassword(password);
                break;
            }
            System.out.println("密码长度不符合要求");
        }
        //  调用Action的注册功能
        String result=userAction.register();
        //  5.解析消息字符串，提示用户信息
        Msg msg= JSONUtil.JSONToEntity(result, Msg.class);
        if(msg.getType().equals(Msg.SUCCESS)){
            System.out.println(msg.getMsg());
            return LOGIN;
        }
        else{
            System.out.println(msg.getMsg());
            return REGISTER;
        }
    }
}
