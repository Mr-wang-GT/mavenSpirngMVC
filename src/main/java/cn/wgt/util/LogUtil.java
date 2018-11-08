package cn.wgt.util;
import cn.wgt.bean.log.Handler;
import cn.wgt.dao.LogerDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("logUtil")
public class LogUtil {
    @Resource(name="logerDao")
    private LogerDao logerDao;
    public  void logRecord(String account,String operateContent){
        Handler handler=new Handler(account,Time.getTime(),operateContent);
        logerDao.insertInfo(handler);
//        System.out.println(handler);

    }
}
