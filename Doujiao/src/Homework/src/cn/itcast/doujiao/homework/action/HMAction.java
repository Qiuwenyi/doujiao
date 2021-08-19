package cn.itcast.doujiao.homework.action;

import cn.itcast.doujiao.common.action.BaseAction;
import cn.itcast.doujiao.common.entity.Msg;
import cn.itcast.doujiao.common.util.JSONUtil;
import cn.itcast.doujiao.homework.entity.Homework;
import cn.itcast.doujiao.homework.service.HMService;
import cn.itcast.doujiao.homework.service.impl.HMServiceImpl;

import java.util.Scanner;

public class HMAction extends BaseAction {

    private String deadline;

    private String subject;

    private String homework;

    private String classNum;

    private String finished;

    /** Scanner扫描器对象，从控制台录入 */
    protected Scanner sc = new Scanner(System.in);
    //todo boolean 是否完成
    public String check(){
        return null;
    }
    //todo 下面两个分别相当于注册和登录，需要和homework库里的内容进行比对
    public String setHM(){
        Msg msg=new Msg();
        try{
            Homework homework_2 = new Homework();
            homework_2.setSubject(subject);
            homework_2.setHomework(homework);
            homework_2.setDeadline(deadline);
            homework_2.setClassNum(classNum);
            HMService hmService = new HMServiceImpl();

            homework_2=hmService.CheckHM(homework_2);
            if(homework_2==null) {
                context.put(SET_HOMEWORK, homework_2);
                msg.setType(Msg.FAIL);
                msg.setMsg("请重新布置作业！");
            }
            else{
                homework_2= hmService.SetHomework(homework_2);
                msg.setType(Msg.SUCCESS);
                msg.setMsg("布置成功");
                //      5.记录日志
                //log.info("学科"+subject+"班级"+classNum+"截止日期"+deadline+"作业"+homework+ "布置成功");
            }
            return JSONUtil.entityToJSON(msg);
        } catch (Exception e) {
            e.printStackTrace();
            msg.setType(Msg.FAIL);
            msg.setMsg("服务器异常");
            return JSONUtil.entityToJSON(msg);
        }
    }
    public String HandInHM(){
        Msg msg=new Msg();
        try{
            Homework homework_2 = new Homework();
            homework_2.setSubject(subject);
            homework_2.setClassNum(classNum);
            HMService hmService = new HMServiceImpl();
            //todo 在handinhomework里面对变量赋值并获取文件，同时包含对时间的比较
            homework_2=hmService.HandInHomework(homework_2);
            if(homework_2==null) {
                context.put(HAND_IN_HOMEWORK, homework_2);
                msg.setType(Msg.FAIL);
                msg.setMsg("请重新提交作业！");
            }
            else{
                homework_2= hmService.SetHomework(homework_2);
                msg.setType(Msg.SUCCESS);
                msg.setMsg("提交成功");
                //      5.记录日志
                //log.info("学科"+subject+"作业"+homework+ "提交成功");
            }
            return JSONUtil.entityToJSON(msg);
        } catch (Exception e) {
            e.printStackTrace();
            msg.setType(Msg.FAIL);
            msg.setMsg("服务器异常");
            return JSONUtil.entityToJSON(msg);
        }
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }
}
