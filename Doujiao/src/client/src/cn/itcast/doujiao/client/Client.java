package cn.itcast.doujiao.client;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 客户端顶层父类
 * 处理公共的用户操作
 */
public class Client {
    /** 全局 用户操作 首页 */
    public static final String INDEX = "I";
    /** 全局 用户操作 登录 */
    public static final String LOGIN = "L";
    /** 全局 用户操作 上一次操作记录 */
    public static String HISTORY = "I";
    /** 全局 用户操作 退出 */
    public static final String EXIT = "E";
    /** 全局 用户操作 注册 */
    public static final String REGISTER = "R";
    /** 全局 用户操作 录入个人信息*/
    public static final String PRI_INFORMATION="PI";
    /** 全局 用户操作 作业操作*/
    public static final String HOMEWORK="H";

    /** Scanner扫描器对象，从控制台录入 */
    protected Scanner sc = new Scanner(System.in);
    /** 日期格式化对象 */
    protected SimpleDateFormat sdf = new SimpleDateFormat("h:mm a"); // 12:08 PM

    /**
     * 程序入口
     * @param args
     */
    public static void main(String[] args) {
        Client c = new Client();
        c.start();
    }

    /**
     * Debug调试
     * 1.在可能出现问题的代码行前加上断点
     * 2.以Debug模式运行
     *  F8  --> 单步执行
     *  F7  --> 进入方法
     *  Shift + F8 --> 执行完毕方法
     *  F9  --> 执行到下一个断点
     */
    public void start() {
        UserClient userClient = new UserClient();
        HomeworkClient homeworkClient = new HomeworkClient();

        System.out.println("-------------欢迎使用----------------");
        System.out.println("登录L,注册R？");
        String as=sc.nextLine();
        String result;
        while (true) {
            if (as.equals("L") || as.equals("l")) {
                result = LOGIN;
                break;
            } else if (as.equals("R") || as.equals("r")) {
                result = REGISTER;
                break;
            }
            System.out.println("输入错误，请重新输入");
            as=sc.nextLine();
        }

        //TODO 注册操作后回主页的选项，包括（不小心）进入登录或者注册页面后的退出页面功能
          while(true) {
            if(result.equals(INDEX)) {          // 首页
                HISTORY = INDEX;
//TODO 判断是否已登录，然后分流录入信息和作业
            } else if(result.equals(LOGIN)) {   // 登录页面
                result = userClient.showLogin();
            } else if(result.equals(EXIT)) {    // 退出
                System.exit(0);
            } else if(result.equals(REGISTER)) {     // 注册
                result = userClient.register();
                while (result!=REGISTER) {
                    System.out.println("请问现在绑定手机号等信息吗？Y/N");
                    String as2 = sc.nextLine();
                    if (as2.equals("Y") || as2.equals("y")||as2.equals("N") || as2.equals("n")) {
                        if(as2.equals("Y") || as2.equals("y")) {
                            while (true) {
                                System.out.println("请输入手机号");
                                String phoneNumber = sc.nextLine();
                                if(phoneNumber.length()==11){
                                    break;
                                }
                                //TODO 字符检测
                                System.out.println("号码长度不正确或不全为数字");
                            }
                            while(true) {
                                System.out.println("请输入邮箱");
                                String mailAddress = sc.nextLine();
                                //TODO 后缀检测(半成
                                if(mailAddress.endsWith(".com")){
                                    break;
                                }
                                System.out.println("邮箱格式不正确");
                            }
                        }
                        break;
                    }

                }
            } else if(result.equals(PRI_INFORMATION)) {     // 录入个人信息
                result = userClient.index();
            } else if(result.equals(HOMEWORK)){
              result=homeworkClient.index();
            } else {
                System.out.println("出错了。");
                System.exit(0);
            }
    }
//
    }

    /**
     * 需求：创建公共的用户操作的方法
     * 主要功能：
     *  1.提示用户信息和用户操作
     *      请根据编号进行操作（或 L登录；E退出）：
     *  2.接收用户的录入
     *      sc.nextLine()
     * 方法的分析：
     *  方法名：        userOperate
     *  返回值：        String
     *  参数列表：
     *      String msg：     用户信息
     *      String... oprs： 用户操作
     *          可变参数，本质是一个数组
     */
    public String UserOperate(String msg, String... oprs) {
        // oprs == String[]
        String opr = Arrays.toString(oprs); // [opr1, opr2, opr3,,]
        opr = opr.substring(1, opr.length() - 1); // opr1, opr2, opr3,,
        msg = msg + "（或 "+ opr +" E退出）：";
        System.out.println(msg); // 请根据编号进行操作（或 L登录；E退出）：
        return sc.nextLine().trim().toUpperCase(); // 去掉空格，转成大写
    }

}
