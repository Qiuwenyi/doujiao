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
            System.out.println("补充个人信息(PI)/退出登录(E)/进入作业系统(HM)");
            String UsAc =sc.nextLine();
            if(UsAc.equals("PI")||UsAc.equals("pi")){
                while (true){
                    //System.out.println("用户名US/密码PW/头像HP/手机号码TP/邮箱MA/退出个人信息修改E");
                    System.out.println("手机号码TP/邮箱MA/退出个人信息修改E");
                    String UsAs = sc.nextLine();
                    if(UsAs.equals("E")||UsAs.equals("e")){
                        break;
//                    }else if(UsAs.equals("US")||UsAs.equals("us")){
//
//                    }else if(UsAs.equals("pw")||UsAs.equals("PW")){
//
//                    }else if(UsAs.equals("HP")||UsAs.equals("hp")){

                    }else if(UsAs.equals("TP")||UsAs.equals("tp")){
                        while (true) {
                            System.out.println("请输入手机号");
                            String phoneNumber = sc.nextLine();
                            boolean LengthMatch=false;
                            if(phoneNumber.length()==11){
                                LengthMatch=true;
                            }
                            Boolean isNotDigit=false;
                            for (int i=0;i<phoneNumber.length();i++){
                                if(!Character.isDigit(phoneNumber.charAt(i))) { //用char包装类中的判断数字的方法判断每一个字符
                                    isNotDigit = true;
                                }
                            }
                            if(!LengthMatch||isNotDigit) System.out.println("号码长度不正确或不全为数字");
                            else break;
                        }
                    }else if(UsAs.equals("MA")||UsAs.equals("ma")){
                        while(true) {
                            System.out.println("请输入邮箱");
                            String mailAddress = sc.nextLine();
                            if(mailAddress.endsWith(".com")){
                                break;
                            }
                            System.out.println("邮箱格式不正确");
                        }
                    }
                }
            }else if(UsAc.equals("E")||UsAc.equals("e")){
                    return RESET;
            }else if(UsAc.equals("HM")||UsAc.equals("hm")){
                return HOMEWORK;
            }
            else System.out.println("输入错误，请重新输入");
        }
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
        while(true) {
            System.out.println("继续请输入L，若您想退出界面选择注册，请输入E");
            String ac = sc.nextLine();
            if(ac.equals("L")||ac.equals("l")) break;
            else if(ac.equals("E")||ac.equals("e")) return REGISTER;
            else System.out.println("输入错误，请重新输入");
        }
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
            System.out.println("请问您是老师/学生？T/S,或您想退出界面选择登录？E");
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
            else if(ide.equals("E")||ide.equals("e")) return LOGIN;
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
    public String CheckIdentity(){
        UserAction userAction=new UserAction();

        System.out.println("请再次输入您的工号");
        String IdNumber=sc.nextLine();

        userAction.setIdNumber(IdNumber);

        String result = userAction.CheckIdentity();
        if(result.equals("T")) return SET_HOMEWORK;
        else if (result.equals("S")) return HAND_HOMEWORK;
        return null;
    }
}
