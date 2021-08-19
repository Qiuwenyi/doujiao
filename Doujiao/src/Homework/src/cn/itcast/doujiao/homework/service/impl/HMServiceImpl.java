package cn.itcast.doujiao.homework.service.impl;

import cn.itcast.doujiao.homework.dao.HMDAO;
import cn.itcast.doujiao.homework.dao.impl.HMDAOImpl;
import cn.itcast.doujiao.homework.entity.Homework;
import cn.itcast.doujiao.homework.service.HMService;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

public class HMServiceImpl implements HMService {
    public Homework CheckHM(Homework homework) throws Exception{
        //  1.调用HMDAO获取作业列表数据
        HMDAO hmDAO=new HMDAOImpl();
        List<Homework> homeworkList=hmDAO.getEntityList();

//  2.遍历作业列表，逐个与已有作业对象的学科及作业内容进行匹配
        boolean bool=true;
        if(homeworkList!=null){
            for (Homework hm: homeworkList){
                if(hm.getSubject().equals(homework.getSubject())&&hm.getHomework().equals(homework.getHomework())){
                    System.out.println("该作业已存在");
                    bool=false;
                }
            }
            if(bool==false) {
                return null;//匹配成功则返回null，代表已存在
            }
            else{
                System.out.println("作业可布置");
            }
        }
        return homework;//失败返回homework对象代表可布置
    }
    public Homework SetHomework(Homework homework) throws Exception{
        return null;
    }
    public Homework HandInHomework(Homework homework) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a"); // 12:08 PM
        System.out.println(sdf.format(new Date()));
        return null;
    }
}
