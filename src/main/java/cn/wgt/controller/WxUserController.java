package cn.wgt.controller;

import cn.wgt.bean.*;
import cn.wgt.dao.UserDao;
import cn.wgt.util.Config;
import cn.wgt.util.UtilTools;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
//import cn.wgt.bean.;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller("userController")
@RequestMapping("wxUser")
public class WxUserController {
    @Resource(name = "userDao")
    private UserDao userDao;


    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public RestResult login(@RequestParam(value="account") String account,
                            @RequestParam(value = "sex",defaultValue ="")String sex){
        if(userDao.checkUserExist(account)){
            if(sex.equals("")){
               sex=userDao.queryUserSex(account);
            }
            if(sex.equals("男")){
                if(userDao.checkLoverExist(account)){
                    account= userDao.queryLover(account);
                }else{
                    return new RestResult(false,Message.NOT_BINDING);
                }
            }
            Map map= userDao.queryUserInfo(account);
            return new RestResult(true,map);
        }else{
            userDao.insertUser(account,sex);
            return new RestResult(true,Message.REGISTER_SUCCESS);
        }
    }
    @RequestMapping(value = "modifyNickName",method = RequestMethod.POST)
    @ResponseBody
    public RestResult modifyNickName(@RequestParam(value = "account")String account,
                                     @RequestParam(value = "nickName")String nickName){
        userDao.modifyNickName(account,nickName);
        return new RestResult(true);
    }
    @RequestMapping(value = "modifyHobby",method = RequestMethod.POST)
    @ResponseBody
    public RestResult modifyHobby(@RequestParam(value = "account")String account,
                                     @RequestParam(value = "hobby")String hobby){
//        String[] temp= hobby.replace('#',' ').split("\\s+");
//        hobby="";
//        for (String str:temp
//        ) {if(str.equals("")){
//            continue;
//        }
//        else{
//            hobby+="#"+str+" ";
//        }
//        }
//        System.out.println(hobby);
        userDao.modifyNickName(account,hobby);
        return new RestResult(true);
    }
    @RequestMapping(value = "setMood",method = RequestMethod.POST)
    @ResponseBody
    public RestResult setMood(@RequestParam(value = "account")String account,
                              @RequestParam(value = "mood")String mood,
                              @RequestParam(value = "upset")String upset,
                              @RequestParam(value = "girly")String girly){
        userDao.updateMood(account,mood,upset,girly);
        return new RestResult(true);
    }
    @RequestMapping(value = "modifyBackground",method = RequestMethod.POST)
    @ResponseBody
    public RestResult modifyBackground(@RequestParam(value = "account")String account,
                                       @RequestParam(value = "photo")MultipartFile photo){
        userDao.updateBackground(account,UtilTools.storagePhoto(photo));
        return new RestResult(true);
    }
    @RequestMapping(value = "modifyAvatar",method = RequestMethod.POST)
    @ResponseBody
    public RestResult modifyAvatar(@RequestParam(value = "account")String account,
                                       @RequestParam(value = "photo")MultipartFile photo){
        userDao.updateAvatar(account,UtilTools.storagePhoto(photo));
        return new RestResult(true);
    }
    @RequestMapping(value = "bindUser",method = RequestMethod.POST)
    @ResponseBody
    public RestResult bindUser(@RequestParam(value = "account")String account,
                                   @RequestParam(value = "bindedAccount")String bindedAccount){
        userDao.bindLover(new Lover(account,bindedAccount));
        return new RestResult(true);
    }
    @RequestMapping(value = "unBindUser",method = RequestMethod.POST)
    @ResponseBody
    public RestResult unBindUser(@RequestParam(value = "account")String account){
        userDao.deleteLover(account);
        return new RestResult(true);
    }
    @RequestMapping(value = "pubSuggestion",method = RequestMethod.POST)
    @ResponseBody
    public RestResult pubSuggestion(@RequestParam(value = "account")String account,
                                    @RequestParam(value = "msg")String msg){
        userDao.insertSuggestion(new Suggestion(account,msg,new Timestamp(System.currentTimeMillis())));
        return new RestResult(true);
    }
    @RequestMapping(value = "pubDiary",method = RequestMethod.POST)
    @ResponseBody
    public Object pubDiary(
                             @RequestParam(value = "account") String account,
                             @RequestParam(value = "title") String title,
                             @RequestParam(value = "message") String message,
                             @RequestParam(value = "photo") MultipartFile photo,
                             @RequestParam(value = "weather") String weather) {
        String diaryId=UtilTools.generateId();
        String photoUrl=UtilTools.storagePhoto(photo);
        Timestamp pubTime=new Timestamp(System.currentTimeMillis());
        userDao.insertDiary(new Diary(diaryId,account,title,message,photoUrl,pubTime,weather));
        return new RestResult(true);
    }
    @RequestMapping(value = "pubComment",method = RequestMethod.POST)
    @ResponseBody
    public RestResult pubComment(
                            @RequestParam(value = "id")String id,
                            @RequestParam(value = "account")String account,
                            @RequestParam(value = "msg")String msg){
        userDao.insertComment(new Comment(id,account,msg,new Timestamp(System.currentTimeMillis())));
        return new RestResult(true);
    }
    @RequestMapping(value = "getDiaries",method = RequestMethod.POST)
    @ResponseBody
    public RestResult getDiaries(
            @RequestParam(value = "account")String account,
            @RequestParam(value = "loverAccount")String loverAccount){
        return new RestResult(true,userDao.queryDiaries(account,loverAccount));
    }
    @RequestMapping(value = "getCommentsById",method = RequestMethod.POST)
    @ResponseBody
    public RestResult getCommentsById(
            @RequestParam(value = "id")String id){
        return new RestResult(true,userDao.queryCommentsById(id));
    }
    @RequestMapping(value = "deleteDiary", method = RequestMethod.POST)
    @ResponseBody
    public RestResult deleteDiary(
            @RequestParam(value = "id")String id){
        userDao.deleteDiaryById( id);
        return new RestResult(true);
    }















//
//
//
//    @RequestMapping(value = "personalInfo",method = RequestMethod.POST)
//    @ResponseBody
//    public RestResult personalInfo(@RequestParam(value = "account")String account,
//                                   @RequestParam(value = "name")String name,
//                                   @RequestParam(value = "hoppy")String hoppy,
//                                   @RequestParam(value = "peronalSignature")String personalSignature){
//        PersonalInfo personalInfo=new PersonalInfo(account,name,hoppy,personalSignature);
//        if(userDao.checkUserExist(account)){
//            userDao.modifyPersonalInfo(personalInfo);
//        }else{
//            userDao.insertPersonalInfo(personalInfo);
//        }
//        return new RestResult(true);
//    }
////    @RequestMapping(value = "bindLover",method = RequestMethod.POST)
////    @ResponseBody
////    public RestResult bindLover(String accountA, String accountB){
//////        if(accountA.compareTo(accountB)>0){   //比较没用了
////            userDao.insertLover(new Lover(accountA,accountB,0));
//////        }else{
//////            userDao.insertLover(new Lover(accountB,accountA,0));
//////        }
////        return new RestResult(true);
////    }
//    @RequestMapping(value = "loverExist",method = RequestMethod.POST)
//    @ResponseBody
//    public RestResult loverExist(@RequestParam(value = "account")String account){
//        if(userDao.checkLoverExist(account)){
//            return new RestResult(true);
//        }else{
//            return new RestResult(false);
//        }
//    }
//    @RequestMapping(value = "pubMood",method = RequestMethod.POST)
//    @ResponseBody
//    public RestResult pubMood(@RequestParam(value = "account")String account,
//                              @RequestParam(value = "title")String title,
//                              @RequestParam(value = "flag")String flag,
//                              @RequestParam(value = "msg")String msg,
//                              @RequestParam(value = "photos")MultipartFile photoFiles[]){
//        String photoUrl="";
//        if(photoFiles!=null&&photoFiles.length>0){
//            for(int i=0;i<photoFiles.length;i++){
//                String fileName=photoFiles[i].getOriginalFilename();
//                File f=new File("C:\\Program Files\\Tomcat\\webapps\\ROOT\\imgs",fileName);
////                        "C:\\Program Files\\Tomcat\\webapps\\ROOT\\imgs"
//                if(!f.exists()){
//                    try{
//                        String suffix=fileName.substring(fileName.lastIndexOf("."));
//                        String imgName= UUID.randomUUID().toString()+suffix;
//                        File localFile=new File("C:\\Program Files\\Tomcat\\webapps\\ROOT\\imgs",imgName);
//                        localFile.createNewFile();
//                        photoUrl+= Config.SERVER_IP+"imgs/"+imgName+",";
//                        FileOutputStream outputStream=new FileOutputStream(localFile);
//                        outputStream.write(photoFiles[i].getBytes());
//                        outputStream.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        Mood mood=new Mood(account,title,flag,msg,photoUrl);
//        if(userDao.checkMoodExist(account)){
//            userDao.modifyMoodInfo(mood);
//        }else{
//            userDao.insertMoodInofo(mood);
//        }
//        return new RestResult(true);
//    }
//    @RequestMapping(value = "getPersonalPageInfo",method = RequestMethod.POST)
//    @ResponseBody
//    public RestResult getPersonalPageInfo(@RequestParam(value = "account")String account){
//        Map map=userDao.queryPersonalInfo(account);
//        map.put("redu",userDao.queryRedu(account));
//
//        map.putAll(userDao.queryMood(account));
//        List<Task> tasks=userDao.queryTask(account);
//        map.put("tasks",tasks);
//        return new RestResult(true,map);
//    }
//    @RequestMapping(value = "pubTask",method = RequestMethod.POST)
//    @ResponseBody
//    public RestResult pubTask(@RequestParam(value = "account")String  account,
//                              @RequestParam(value = "title")String title,
//                              @RequestParam(value = "redu") int redu,
//                              @RequestParam(value = "taskPhoto")MultipartFile taskPhoto){
//        String photoUrl="";
//        if(taskPhoto!=null){
//            String fileName=taskPhoto.getOriginalFilename();
//            File f=new File("C:\\Program Files\\Tomcat\\webapps\\ROOT\\imgs",fileName);
////                        "C:\\Program Files\\Tomcat\\webapps\\ROOT\\imgs"
//            try{
//                String suffix=fileName.substring(fileName.lastIndexOf("."));
//                String imgName= UUID.randomUUID().toString()+suffix;
//                File localFile=new File("C:\\Program Files\\Tomcat\\webapps\\ROOT\\imgs",imgName);
//                localFile.createNewFile();
//                photoUrl+= Config.SERVER_IP+"imgs/"+imgName+",";
//                FileOutputStream outputStream=new FileOutputStream(localFile);
//                outputStream.write(taskPhoto.getBytes());
//                outputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        userDao.insertTask(new Task(account,title,photoUrl,redu,0));
//        return new RestResult(true);
//    }
//    @RequestMapping(value = "finishTask",method = RequestMethod.POST)
//    @ResponseBody
//    public RestResult finishTask(@RequestParam(value = "account")String account,
//                                 @RequestBody List<Integer> taskIds){
//        for(int i=0;i<taskIds.size();i++){
//           int addedRedu= userDao.finishTask(taskIds.get(i))+userDao.queryRedu(account);
//           userDao.modifyLoverRedu(account,addedRedu);
//        }
//        return new RestResult(true);
//    }
}
