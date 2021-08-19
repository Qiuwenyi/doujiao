package cn.itcast.doujiao.client;


import cn.itcast.doujiao.common.entity.Msg;
import cn.itcast.doujiao.common.util.JSONUtil;
import cn.itcast.doujiao.homework.action.HMAction;

public class HomeworkClient extends Client{
    public String HandIn(){
        HMAction hmAction=new HMAction();

        System.out.println("请输入提交学科科目名");
        String subject_2=sc.nextLine();
        System.out.println("请输入班级");
        String classNum=sc.nextLine();

        hmAction.setSubject(subject_2);
        hmAction.setClassNum(classNum);
        //todo 提交文件或者信息，写代码
        //todo check一下提交时间
        String result=hmAction.HandInHM();

        Msg msg = JSONUtil.JSONToEntity(result,Msg.class);
        System.out.println(msg.getMsg());

        return HISTORY;
    }
    public String CheckCom(){
        HMAction hmAction=new HMAction();

        System.out.println("请输入学科");
        String subject=sc.nextLine();
        System.out.println("请输入作业信息");
        String homework=sc.nextLine();
        System.out.println("请输入班级号");
        String classNum=sc.nextLine();

        hmAction.setHomework(homework);
        hmAction.setSubject(subject);
        hmAction.setClassNum(classNum);

        String result=hmAction.check();

        Msg msg= JSONUtil.JSONToEntity(result, Msg.class);

        System.out.println(msg.getMsg());
        return HISTORY;
    }
    public String SetHm(){
        HMAction hmAction=new HMAction();

        System.out.println("请输入学科");
        String subject=sc.nextLine();
        System.out.println("请输入作业信息");
        String homework=sc.nextLine();
        System.out.println("请输入截止时间");
        String deadline=sc.nextLine();
        System.out.println("请输入班级号");
        String classNum=sc.nextLine();

        hmAction.setHomework(homework);
        hmAction.setSubject(subject);
        hmAction.setDeadline(deadline);
        hmAction.setClassNum(classNum);

        String result=hmAction.setHM();

        Msg msg= JSONUtil.JSONToEntity(result, Msg.class);

        System.out.println(msg.getMsg());
        return HISTORY;
    }
    public String Teacher_opr(){
        while (true){
            System.out.println("检查作业提交情况C/布置作业S/退出作业系统E");
            String ac=sc.nextLine();
            if(ac.equals("C")||ac.equals("c")) CheckCom();
            else if(ac.equals("S")||ac.equals("s")) SetHm();
            else if(ac.equals("e")||ac.equals("E")) break;
        }
        return HISTORY;
    }
}
