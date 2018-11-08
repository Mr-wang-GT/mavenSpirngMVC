package cn.wgt.util;

import cn.wgt.bean.Message;
import cn.wgt.bean.RestResult;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Repository("tokenUtil")
public class TokenUtil {
    @Resource(name = "logUtil")
    private LogUtil logUtil;
//    private TokenUtil(){
//
//    }
//    private static final TokenUtil instance=new TokenUtil();
//    public static TokenUtil getInstance(){
//        return instance;
//    }
    public String getToken(String account){
        String token=System.currentTimeMillis()+account;
        return token;
    }
    public  Boolean tokenCheck(String token){

            Long timeMillis= Long.valueOf(token.substring(0,13));
            if(System.currentTimeMillis()-timeMillis<=Config.TOKEN_RETENTION_TIME){
                return true;
            }else{
                return false;
            }
    }
    public RestResult isTokenIllegle(String token, String account, HttpServletRequest request){
        try{
            Long.valueOf(token.substring(0,13));  //检测token格式是否合法
//            CodeTools.printCodeInfo();
//            System.out.println(token);
//            System.out.println(token.substring(13));
            if(!token.substring(13).equals(account)){
                throw new  Exception(Message.FAKE_TOKEN);
            }
        }catch (Exception e){
            System.out.println("捕捉到异常token即将进行记录");
            String content="user:"+request.getRemoteUser()+"addr:"+request.getRemoteAddr()+"host:"+request.getRemoteHost();
//            System.out.println(content);
            try{
                logUtil.logRecord(account,content);

            }catch (Exception e1){
                e1.printStackTrace();
            }
            System.out.println("成功记录");
            return new RestResult(false, Message.ILLEGAL_VISIT_REPLY);

        }
//        System.out.println("异常处理结束");
        return null;
    }
}
