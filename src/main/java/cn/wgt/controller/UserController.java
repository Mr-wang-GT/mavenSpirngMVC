//package cn.wgt.controller;
//
//import cn.wgt.bean.*;
//import cn.wgt.dao.DatingInfoDao;
//import cn.wgt.dao.UserDao;
//import cn.wgt.util.*;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author cn.wgt
// */
//@Controller("userController")
//@RequestMapping("user")
//public class UserController {
//
//    @Resource(name = "userDao")
//    private UserDao userDao;
//    @Resource(name = "datingInfoDao")
//    private DatingInfoDao datingInfoDao;
//
////    @Resource(name = "twittersDao")
////    private TwittersDao twittersDao;
////    @Resource(name = "commentsDao")
////    private CommentsDao commentsDao;
//    @Resource(name = "tokenUtil")
//    private TokenUtil tokenUtil;
//
//    /**
//     * @param session
//     * @param request
//     * @param account    账户即为手机号
//     * @param password
//     * @return
//     */
//    @RequestMapping({"login.ws"})
//    @ResponseBody
//    public RestResult login(HttpSession session, HttpServletRequest request,
//                            @RequestParam(value = "account") String account,
//                            @RequestParam(value = "password") String password) {
//        User user;
////        CodeTools.printCodeInfo();
//        if(userDao.checkUserExist(account)){
//            user = userDao.queryUserByAccount(account);
//            String psw = MD5Util.MD5(account+password);
//            if (!user.getPassword().equals(psw)) {
//                return new RestResult(false, Message.PASSWORD_ERROR);
//            } else {
//                HashMap<String,String> map=new HashMap<>();
//                map.put("token",tokenUtil.getToken(account));
//                if(datingInfoDao.checkUserExist(account)){
//                    map.put("flag","1");
//                }else{
//                    map.put("flag","0");
//                }
//                return new RestResult(true,map);
//            }
//        }else{
//            return new RestResult(false,Message.ACCOUNT_NOT_EXIST);
//        }
//    }
//
//    @RequestMapping({"register.ws"})
//    @ResponseBody
//    public RestResult register(HttpSession session, HttpServletRequest request,
//                               @RequestParam(value = "account") String account,
//                               @RequestParam(value = "password") String password,
//                               @RequestParam(value = "secretQA")String secretQA
//                               ) {
//            if(!userDao.checkUserExist(account)){
//                User user=new User(account,MD5Util.MD5(account+password),secretQA);
//                userDao.insertUser(user);
//                return new RestResult(true,Message.REGISTER_SUCCESS);
//            }else{
//                return new RestResult(false, Message.ACCOUNT_REPEAT);
//            }
//    }
//    @RequestMapping({"makeFriendsInfo/storage.ws"})
//    @ResponseBody
//    public RestResult datingInfoStorage(HttpSession session, HttpServletRequest request,
//                                        @RequestParam(value = "token")String token,
//                                        @RequestParam(value = "account") String account,
//                                        @RequestParam(value = "name") String name,
//                                        @RequestParam(value = "hometown")String hometown,
//                                        @RequestParam(value = "location")String location,
//                                        @RequestParam(value = "longitude") String longitude,
//                                        @RequestParam(value = "altitude")String altitude,
//                                        @RequestParam(value = "sex") String sex,
//                                        @RequestParam(value = "height") String height,
//                                        @RequestParam(value = "birthday") String birthday,
//                                        @RequestParam(value = "constellatory") String constellatory,
//                                        @RequestParam(value = "hobby") String hobby,
//                                        @RequestParam(value = "declaration") String declaration,
//                                        @RequestParam(value = "photoFiles" ) MultipartFile photoFiles ){
////        @RequestParam(value = "photoFiles") List<Object> photoFiles) {
//
//        RestResult obj=tokenUtil.isTokenIllegle(token,account,request);
//        System.out.println("-----------datingInfoStorage 开始--------------------");
//        if(obj==null){
//            if(tokenUtil.tokenCheck(token)){
//                String photoUrl="";
////                if(photoFiles!=null&&photoFiles.size()>0){
////                    for(int i=0;i<photoFiles.size();i++){
////                        MultipartFile multipartFile= (MultipartFile) photoFiles.get(i);
////                        String fileName=multipartFile.getOriginalFilename();
////                        File f=new File("C:\\Program Files\\Tomcat\\webapps\\ROOT\\imgs",fileName);
//////                        "C:\\Program Files\\Tomcat\\webapps\\ROOT\\imgs"
////                        if(!f.exists()){
////                            try{
////                                String suffix=fileName.substring(fileName.lastIndexOf(","));
////                                String imgName=UUID.randomUUID().toString()+suffix;
////                                File localFile=new File("C:\\Program Files\\Tomcat\\webapps\\ROOT\\imgs",imgName);
////                                localFile.createNewFile();
////                                photoUrl+=Config.SERVER_IP+"imgs/"+imgName+",";
////                                FileOutputStream outputStream=new FileOutputStream(localFile);
////                                outputStream.write(multipartFile.getBytes());
////                                outputStream.close();
////                            } catch (IOException e) {
////                                e.printStackTrace();
////                            }
////                        }
////                    }
////                }
//                DatingInfo info= new DatingInfo(account, name,hometown,location, longitude,altitude, sex, height, birthday, constellatory, hobby, declaration, photoUrl);
//             try{
//                 if (datingInfoDao.checkUserExist(account)) {
//
//                     if(photoUrl.equals("")){
//                         datingInfoDao.modifyInfoExceptPhotoUrl(info);
//                     }else {
//                         datingInfoDao.modifyInfo(info);
//                     }
//                 } else {
//                     datingInfoDao.insertDatingInfo(info);
//                 }
//             }catch (Exception e){
//                 e.printStackTrace();
//             }
//                return new RestResult(true, "保存成功");
//            }else{
//                return new RestResult(false,Message.TOKEN_EXPIRE);
//            }
//        }else{
//            return obj;
//        }
//    }
//
//
//
//
//
//    @RequestMapping({"deletePhotos.ws"})
//    @ResponseBody
//    public RestResult deletePhotos(HttpServletRequest request,
//                                   @RequestParam(value = "token")String token,
//                                   @RequestParam(value = "account")String account,
//                                   @RequestParam(value = "photoUrls")String photoUrls){
//        RestResult obj=tokenUtil.isTokenIllegle(token,account,request);
//        if(obj==null){
//            if(tokenUtil.tokenCheck(token)){
//                datingInfoDao.deletePhoto(account,photoUrls);
//                return new RestResult(true,Message.FILE_DELTE_SUCCESS);
//            }else{
//                return new RestResult(false,Message.TOKEN_EXPIRE);
//            }
//        }else{
//            return obj;
//        }
//    }
//
//
////    @RequestMapping({"getPhotos.ws"})
////    @ResponseBody
////    public RestResult getPhotos(HttpSession session,HttpServletRequest request,
////                                @RequestParam(value = "token")String token,
////                                @RequestParam(value = "account")String account,
////                                @RequestParam(value = "photoUrls")String[] photoUrls){
////        RestResult obj=tokenUtil.isTokenIllegle(token,account,request);
////        if(obj==null){
////            if(tokenUtil.tokenCheck(token)) {
////                List list=new LinkedList<byte[]>();
////                for(int i=0;i<photoUrls.length;i++){
////                    String te=photoUrls[i].substring(2,photoUrls[i].length()-2);
////                    System.out.println(te);
////                    File file=new File(te);
////                    try {
////                        FileInputStream fis=new FileInputStream(file);
////                        byte[] bytes= FileCopyUtils.copyToByteArray(fis);
////                        list.add(bytes);
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                    }
////                }
////                return new RestResult(true,list);
////            }else{
////                return new RestResult(false,Message.TOKEN_EXPIRE);
////            }
////        } else{
////            return obj ;
////        }
////    }
//
//    /**
//     * @param session
//     * @param request
//     * @param account 通过账户获得location进行查询
//     * @return 返回附近用户json格式列表  待解决问题随机算法某些数据返回频率过高
//     */
//    @RequestMapping({"requestNearbyFriendsInfo.ws"})
//    @ResponseBody
//    public RestResult requestNearbyFriendsInfo(HttpSession session, HttpServletRequest request,
//                                               @RequestParam(value = "token")String token,
//                                               @RequestParam(value = "account") String account,
//                                               @RequestParam(value = "location")String location,
//                                               @RequestParam(value = "longitude")String longitude,
//                                               @RequestParam(value = "altitude")String altitude) {
//        RestResult restResult=tokenUtil.isTokenIllegle(token,account,request);
//        if(restResult==null){
//            if(tokenUtil.tokenCheck(token)){
//                if(datingInfoDao.checkUserExist(account)){
//                    datingInfoDao.updateLonLatLocation(account,location,longitude,altitude);
//                }else{
//                    Map map=new HashMap();
//                    map.put("flag","0");
//                    return new RestResult(false,map);
//                }
//                String concernedUsers;
//                try{
//                    concernedUsers=userDao.queryConcerningUser(account);  //这里是查询不到的
//                }catch (Exception e){
//                    concernedUsers=null;
//                }
//                List<Map<String,String>> nearByList= new ArrayList<>();//保存最终查询结果
//                double startLon=Double.valueOf(longitude);
//                double startLat=Double.valueOf(altitude);
//                int number;
//                try{
//                    number=datingInfoDao.querySameLocationUserCount(location)/Config.COUNT_OF_EACH_PAGE; //number 为每一个分组的数目，查询到的结果共有COUNT_OF_EACH_PAGE 个分组
//                    if(number==0){
//                        return new RestResult(false,Message.NO_NEARBY_USER);
//                    }
//                }catch(Exception e){
//                    return new RestResult(false,Message.NO_NEARBY_USER);  //modified 2018/09/05
//                }
//                List<Integer>  randomIndexes=UtilTools.generateRandomList(number);
//                int start;
//                int index=0;
//                while(nearByList.size()!=Config.COUNT_OF_EACH_PAGE&&index!=number){
//                    start=randomIndexes.get(index);
//                    index+=1;
//                    for(int i=0;i<Config.COUNT_OF_EACH_PAGE;i++){
//                        Map<String,String> queriedUser;
//                        try{
//                            queriedUser= (Map<String, String>) datingInfoDao.queryUserByLocation(location,start);
//                        }catch (Exception e){
//                            continue;
//                        }
//                        String userAccount=queriedUser.get("account");
////                        String endLonLat=datingInfoDao.queryLongitudeByAccount(userAccount)+","+datingInfoDao.queryAltitudeByAccount(userAccount);
//                        double endLon,endLat;
//                        try{
//                             endLon=Double.valueOf(datingInfoDao.queryLongitudeByAccount(userAccount));
//                             endLat=Double.valueOf(datingInfoDao.queryAltitudeByAccount(userAccount));
//                        }catch (Exception e){
//                            e.printStackTrace();
//                            return new RestResult(false,e.getMessage());
//                        }
//
////                        if(DistanceUtil.getDistance(startLonLat,endLonLat)<Config.NEARBY_RADIUS){
//                        if(DistanceUtil.getDistance(startLon,startLat,endLon,endLat)<Config.NEARBY_RADIUS){
//                            if(concernedUsers!=null&&concernedUsers.indexOf(queriedUser.get("account"))!=-1){ //已关注的用户不会出现在发现列表中
//                                continue;
//                            }
//                            nearByList.add(queriedUser);
//                        }
//                        start+=number;
//                    }
//                }
//                return new RestResult(true, nearByList);
//            }else{
//                return new RestResult(false,Message.TOKEN_EXPIRE);
//            }
//        }else{
//            return restResult;
//        }
//    }
//
//    /**
//     * 添加关注
//     *
//     * @param session
//     * @param request
//     * @param account
//     * @param concernedUser
//     * @return
//     */
//    @RequestMapping({"markConcerning.ws"})
//    @ResponseBody
//    public RestResult markConcerning(HttpSession session, HttpServletRequest request,
//                                     @RequestParam(value = "token")String token,
//                                     @RequestParam(value = "account") String account,
//                                     @RequestParam(value = "concernedUser") String concernedUser) {
//
//        RestResult obj=tokenUtil.isTokenIllegle(token,account,request);
//        if(obj==null){
//            if(tokenUtil.tokenCheck(token)){
//                if (userDao.checkConcernUsersExist(account)) {
//                    userDao.addConcerningUser(account, concernedUser);//如果关注列表中已有该用户的记录，就将被关注用户的account添加到users字段末尾并用逗号隔开
//                } else {
//                    userDao.insertConcerningUser(account, concernedUser);
//                }
//                if (userDao.checkConcernedUsersExist(concernedUser)) {
//                    userDao.addConcernedUser(concernedUser, account);  //同理
//                } else {
//                    userDao.insertConcernedUser(concernedUser, account);
//                }
//                //再有了关注行为之后，就会生成一个双方的热度记录。
//                if (userDao.checkReduExist(concernedUser + "," + account)) { //对方是否已关注自己
//                    userDao.updateRedu(concernedUser + "," + account, Config.REDU_UP_FOR_MUTUAL_CONCERN);//互相关注热度上升
//                } else {
//                    userDao.insertRecordIntoRedu(account + "," + concernedUser, 0);//单方面建立关注，则向热度表中插入一条相关信息
//                }
//                return new RestResult(true, Message.CONCERNING_SUCCESS);
//            }else{
//                return new RestResult(false,Message.TOKEN_EXPIRE);
//            }
//        }else{
//            return obj;
//        }
//    }
//
//    /**取消关注
//     * @param session
//     * @param request
//     * @param account
//     * @param concernedUser
//     * @return
//     */
//    @RequestMapping({"cancelConcerning.ws"})
//    @ResponseBody
//    public RestResult cancelConcerning(HttpSession session, HttpServletRequest request,
//                                       @RequestParam(value = "token") String token,
//                                       @RequestParam(value = "account") String account,
//                                       @RequestParam(value = "concernedUser") String concernedUser) {
//
//        RestResult obj=tokenUtil.isTokenIllegle(token,account,request);
//        if(obj==null){
//            if(tokenUtil.tokenCheck(token)){
//                userDao.cancelConcerningUser(account, concernedUser);
//                userDao.cancelConcernedUser(concernedUser, account);
//                Boolean flag;//互相关注标识
//                try{
//                    String str=userDao.queryConcernedUser(account);
//                    flag=str.indexOf(concernedUser) > -1;
//                }catch (Exception e){
//                    flag=false;
//                }
//                if (flag) {
//                    if (userDao.checkReduExist(account + "," + concernedUser)) {    //针对热度进行的操作
//                        userDao.updateRedu(account + "," + concernedUser, Config.REDU_CLEAR_ZEOR_FOR_CANCEL_CONCERN);
//                    } else if (userDao.checkReduExist(concernedUser + "," + account)) {
//                        userDao.updateRedu(concernedUser + "," + account, Config.REDU_CLEAR_ZEOR_FOR_CANCEL_CONCERN);
//                    }
//                } else {
//                    try{
//                        userDao.deleteRedu(account + "," + concernedUser);  //双方都不在关注后删除热度记录
//                    }
//                    catch (Exception e){
//                        e.printStackTrace();
//                        userDao.deleteRedu(concernedUser + "," + account);
//                    }
//                }
//                return new RestResult(true, Message.CANCEL_CONCERNING_SUCCESS);
//            }else{
//                return new RestResult(false,Message.TOKEN_EXPIRE);
//            }
//
//        }else{
//            return obj;
//        }
//    }
//    /**
//     *
//     * @param session
//     * @param request
//     * @param account
//     * @return
//     */
//    @RequestMapping({"requestDatingInfo.ws"})
//    @ResponseBody
//    public RestResult requestDatingInfo(HttpSession session, HttpServletRequest request,
//                                        @RequestParam(value = "token")String token,
//                                        @RequestParam(value = "account") String account) {
//        RestResult obj=tokenUtil.isTokenIllegle(token,account,request);
//        if(obj==null){
//            if(tokenUtil.tokenCheck(token)){
//                DatingInfo datingInfo;
//                try{
//                     datingInfo = datingInfoDao.queryDatingInfoByAccount(account);
//                }catch (Exception e){
//                    return new RestResult(false,Message.PERSONAL_INFO_MISS);
//                }
//                return new RestResult(true, datingInfo);
//            }else{
//                return new RestResult(false,Message.TOKEN_EXPIRE);
//            }
//        }else{
//            return obj;
//        }
//    }
//
//    @RequestMapping({"getFriendList.ws"})
//    @ResponseBody
//    public Object getFriendList(HttpSession session, HttpServletRequest request,
//                                @RequestParam(value = "token")String token,
//                                @RequestParam(value = "account") String account) {
//        RestResult obj=tokenUtil.isTokenIllegle(token,account,request);
//        if(obj==null){
//            if(tokenUtil.tokenCheck(token)){
//                Map<String, String> map = new HashMap<String, String>();
//                try{
//                    map.put("concerningList", userDao.queryConcerningUser(account));
//
//                }catch (Exception e){
//                    map.put("concerningList",null);
//                    e.printStackTrace();
//                }
//                try{
//                    map.put("concernedList", userDao.queryConcernedUser(account));
//                }catch (Exception e){
//                    map.put("concernedList",null);
//                }
//                return new RestResult(true, map);
//            }else{
//                return new RestResult(false,Message.TOKEN_EXPIRE);
//            }
//        }else{
//            return obj;
//        }
//    }
//
//
//    /**
//     * @param session
//     * @param request
//     * @param account
//     * @return 返回：
//     */
//    @RequestMapping({"requestUserData.ws"})
//    @ResponseBody
//    public RestResult requestUserData(HttpSession session, HttpServletRequest request,
//                                      @RequestParam(value = "token")String token,
//                                      @RequestParam(value = "account") String account) {
//        RestResult obj=tokenUtil.isTokenIllegle(token,account,request);
//        if(obj==null){
//            if(tokenUtil.tokenCheck(token)){
//                Map<String, String> map1 = (Map<String, String>) datingInfoDao.queryItemsByAccount(account);
//                Map<String, String> map2= (Map<String, String>) userDao.queryDatingProcessInfo(account);
//                map1.putAll(map2);
////                List<Map<String, String>> idList = (List<Map<String, String>>) twittersDao.queryIdByAccount(account);//不需要朋友圈功能了
////                idList.add(0, map);
////                return new RestResult(true, idList);
//                return new RestResult(true,map1);
//            }else{
//                return new RestResult(false,Message.TOKEN_EXPIRE);
//            }
//        }else{
//            return obj;
//        }
//
//    }
//    @RequestMapping({"storageUserData.ws"})
//    @ResponseBody
//    public RestResult storageUserData(HttpSession session, HttpServletRequest request,
//                                      @RequestParam(value = "token")String token,
//                                      @RequestParam(value = "account")String account,
//                                      @RequestParam(value = "redu20")String redu20,
//                                      @RequestParam(value = "redu40")String redu40,
//                                      @RequestParam(value = "redu60")String redu60,
//                                      @RequestParam(value = "redu80")String redu80){
//        RestResult obj=tokenUtil.isTokenIllegle(token,account,request);
//        if(obj==null){
//            if(tokenUtil.tokenCheck(token)){
//                if(userDao.checkDatingProcessExist(account)){
//                    userDao.upDateDatingProcessInfo(account,redu20,redu40,redu60,redu80);
//                }else{
//                    userDao.insertDatingProcessInfo(account,redu20,redu40,redu60,redu80);
//                }
//                return new RestResult(true,Message.STORAGE_SUCCESS);
//            }else {
//                return new RestResult(false,Message.TOKEN_EXPIRE);
//            }
//        }else{
//            return obj;
//        }
//
//    }
//    @RequestMapping({"reduUpdate.ws"})
//    @ResponseBody
//    public RestResult reduUpdate(HttpSession session, HttpServletRequest request,
//                                 @RequestParam(value = "token")String token,
//                                 @RequestParam(value = "account")String account,
//                                 @RequestParam(value = "concernedAccount")String concernedAccount,
//                                 @RequestParam(value = "redu")String redu){
//        RestResult obj=tokenUtil.isTokenIllegle(token,account,request);
//        if(obj==null){
//            if (userDao.checkReduExist(account + "," + concernedAccount)) {    //针对热度进行的操作
//                userDao.updateRedu(account + "," + concernedAccount,Integer.valueOf(redu) );
//            } else if (userDao.checkReduExist(concernedAccount + "," + account)) {
//                userDao.updateRedu(concernedAccount + "," + account, Integer.valueOf(redu));
//            }
//            return new RestResult(true,Message.REDU_UPDATE_SUCCESS);
//        } else{
//            return obj ;
//        }
//    }
//
//
//
//
//
////    //*************************twitters area********************
////    @RequestMapping({"requestTwitters.ws"})
////    @ResponseBody
////    public Object requestTwitters(HttpSession session, HttpServletRequest request,
////                                  @RequestParam(value = "account") String account) {
////        if (datingInfoDao.checkUserExist(account)) {
////            List list = (ArrayList<Object>) twittersDao.queryTwitterByAccount(account);            //如果查询不到处理未作出
////            System.out.println(list);
////            return new RestResult(true, list);
////        } else {
////            return new RestResult(false, "所查询账户不存在");
////        }
////    }
////
////    @RequestMapping({"pubTwitter.ws"})
////    @ResponseBody
////    public Object pubTwitter(HttpSession session, HttpServletRequest request,
////                             @RequestParam(value = "account") String account,
////                             @RequestParam(value = "message") String message,
////                             @RequestParam(value = "photo") String photo,
////                             @RequestParam(value = "location") String location,
////                             @RequestParam(value = "visiters") String visiters,
////                             @RequestParam(value = "reminders") String reminders) {
////        //省略判空处理
////        if (datingInfoDao.checkUserExist(account)) {
////            twittersDao.insertTwitter(generateId(), account, message, photo, location, visiters, reminders);
////            // id 中除后四位外保存创建时间信息
////            return new RestResult(true, "发布成功");
////        } else {
////            return new RestResult(false, "发布失败，请检查网络");
////        }
////    }
////
////    /**
////     * @param session
////     * @param request
////     * @param account
////     * @return 获取某一用户所发布twitters的ids
////     */
////    @RequestMapping({"getIdByAccount.ws"})
////    @ResponseBody
////    public Object getTwittersIdByAccount(HttpSession session, HttpServletRequest request,
////                                         @RequestParam(value = "account") String account) {
////        List<Map<String, String>> list = (List<Map<String, String>>) twittersDao.queryIdByAccount(account);
////        if (list != null) {
////            return new RestResult(true, list);
////        } else {
////            return new RestResult(false, "当前用户暂时未发布说说");
////        }
////    }
////
////    /**
////     * 由单个id获取一条完整的twitter但不包含评论信息
////     *
////     * @param session
////     * @param request
////     * @param id
////     * @return
////     */
////    @RequestMapping({"getTwitter.ws"})
////    @ResponseBody
////    public Object getTwitterById(HttpSession session, HttpServletRequest request,
////                                 @RequestParam(value = "id") String id) {
////        Map<String, String> twitter = (Map<String, String>) twittersDao.queryTwitterById(id);
////        return new RestResult(true, twitter);
////    }
////
////    @RequestMapping({"modifyVisiters.ws"})
////    @ResponseBody
////    public Object modifyVisiters(HttpSession session, HttpServletRequest request,
////                                 @RequestParam(value = "id") String id,
////                                 @RequestParam(value = "visiters") String visiters) {
////        twittersDao.UpdateVisitersById(id, visiters);
////        return new RestResult(true);
////    }
////
////    /**更新提醒谁看列表
////     * @param session
////     * @param request
////     * @param id
////     * @param reminders
////     * @return
////     */
////    @RequestMapping({"modifyReminders.ws"})
////    @ResponseBody
////    public Object modifyReminders(HttpSession session,HttpServletRequest request,
////                                  @RequestParam(value = "id")String id,
////                                  @RequestParam(value = "reminders")String reminders){
////        twittersDao.UpdateVRemindersById(id,reminders);
////        //此处应当有推送
////
////        return new RestResult(true);
////    }
////
////
////    //*************************************************************************************
////
////    /**根据id获取下一层的所有评论
////     * @param session
////     * @param request
////     * @param id
////     * @return
////     */
////    @RequestMapping({"getComments.ws"})
////    @ResponseBody
////    public Object getCommentsById(HttpSession session, HttpServletRequest request,
////                                  @RequestParam(value = "id") String id) {
////        List<Map<String, String>> maps = (List<Map<String, String>>) commentsDao.queryCommentsById(id);
////        if (!maps.isEmpty()) {
////            return new RestResult(true, maps);
////        } else {
////            return new RestResult(false, "该内容暂无评论");
////        }
////    }
////
////
////    /**
////     * @param session
////     * @param request
////     * @param id    待评论信息的id
////     * @param pubAccount    发布待评论信息的用户
////     * @param comAccount    进行评论的用户
////     * @param msg           评论信息
////     * @return
////     */
////    @RequestMapping({"pubComment.ws"})
////    @ResponseBody
////    public Object pubComent(HttpSession session,HttpServletRequest request,
////                            @RequestParam(value = "id")String id,
////                            @RequestParam(value = "pubAccount")String pubAccount,
////                            @RequestParam(value = "comAccount")String comAccount,
////                            @RequestParam(value = "msg")String msg){
////        //省略判空处理
////
////        if(twittersDao.checkTwitterExist(id)||commentsDao.checkCommentExist(id)){
////            try{
////                commentsDao.insertComment(id,UtilTools.generateId(),comAccount,msg);
////                commentsDao.insertReply(id,pubAccount,comAccount,msg);
////            }catch (Exception e){
////                e.printStackTrace();
////                return new RestResult(false,"网络错误");
////            }
////
////            return new RestResult(true,"评论发布成功");
////        }else{
////            return new RestResult(false,"所评论的对象以删除，评论失败");
////        }
////    }
////
////    @RequestMapping({"deleteComment.ws"})
////    @ResponseBody
////    public Object deleteCommentById(HttpSession session,HttpServletRequest request,
////                                    @RequestParam(value = "id")String id){
////        if(commentsDao.checkCommentExist(id)){
////            commentsDao.deleteCommentById(id);
////            return new RestResult(true,"评论删除成功");
////        }else {
////            return new RestResult(false,"该评论已删除");
////        }
////    }
////    @RequestMapping({"getReply.ws"})
////    @ResponseBody
////    public Object getReplyByAccount(HttpSession session,HttpServletRequest request,
////                           @RequestParam(value = "account") String account){
////        List<Map<String,String>> list= (List<Map<String, String>>) commentsDao.queryAndDeleteReply(account);
////        System.out.println(list);
////        if(!list.isEmpty()){
////            return new RestResult(true,list);
////        }else {
////            return new RestResult(false);
////        }
////    }
////
////    /**获取最近一段时间内的好友说说，该时间可以在Util.Config.java中修改
////     * @param session
////     * @param request
////     * @param account
////     * @return
////     */
////    @RequestMapping({"getFriendsTwitters.ws"})
////    @ResponseBody
////    public Object getFriendsTwitters(HttpSession session,HttpServletRequest request,
////                                     @RequestParam(value = "account")String account){
////               //取消了用户是否存在检查
////           String watchingList=userDao.queryConcerningUser(account);  //关注列表  当查询不到时返回的是null
////           if(watchingList!=null){
////               String[] items=watchingList.split(",");
////               watchingList=null;
////               List<String> users=new ArrayList<>(); //存放近期发布过说说的用户
////               HashMap<String,String> map=new HashMap<String,String>();
////               Calendar calendar=Calendar.getInstance();
////               int dayOfYear=calendar.get(Calendar.DAY_OF_YEAR);
//////               int nowDay=calendar.get(Calendar.DAY_OF_MONTH);
//////               int nowYear=calendar.get(Calendar.YEAR)-Config.CURRENT_CENTURY;
//////               int nowMonth=calendar.get(Calendar.MONTH)+1;
////               for(int i=0;i<items.length;i++){
//////                   System.out.println(items[i]);i
//////                   CodeTools.printCodeInfo();
////                   //说说列表是升序的
////                   List<Map<String,String>> ids= (List<Map<String, String>>) twittersDao.queryIdByAccount(items[i]);
//////                   System.out.println(items[i]+":"+ids);
////                   if(!ids.isEmpty()){
////                       StringBuffer twitterIds=new StringBuffer();  //保存可以被访问的说说id
////                       for(Map<String,String> li :ids){
////                           String str=li.get("id");     //用户twitter的id
//////                       twitterIds[index]=str;
////                           int pubDay=Integer.valueOf(str.substring(4,6));
////                           int pubMonth=Integer.valueOf(str.substring(2,4));
////                           int pubYear=Integer.valueOf(str.substring(0,2));
////                           int pubDayOfYear=UtilTools.getDayOfYear(pubYear,pubMonth,pubDay);
////                           //可修改逻辑因为查询到的twitter id 是升序的
////                           // if(nowYear==pubYear&&nowMonth==pubMonth&&nowDay-pubDay<=Config.RECENT_TWITTERS){
////                            System.out.println("dayOfYear"+dayOfYear+"pubDayOfYear:"+pubDayOfYear);
////                           if(dayOfYear-pubDayOfYear<Config.RECENT_TWITTERS){ //将最近三天的Twitters取出
////                               //增加谁可以看逻辑
////                               String visiters=twittersDao.queryVisitersById(str);
////                               System.out.println(visiters);
////                               CodeTools.printCodeInfo();
////                               if(visiters!=null){
////                                   if(visiters.indexOf(account)>=0)
////                                       twitterIds.append(str+",");
////                               }else{
////                                   twitterIds.append(str+",");
////                               }
////                           }
////                       }
////                       if(!twitterIds.toString().equals("")){//************
////                           users.add(items[i]);
////                           map.put(items[i],twitterIds.toString().substring(0,twitterIds.length()-1));
////                       }
////                   }
////               }
////               if(!map.isEmpty()){
////                   map.put("friendList",users.toString());
////                   return new RestResult(true,map);
////               }else{
////                   return new RestResult(false,"您的好友最近未发布说说");
////               }
////           }else{
////               return new RestResult(false,"您当前未关注任何用户");
////           }
////    }
////
////
////    /**
////     * @param session
////     * @param request
////     * @param id        twitter的id
////     * @param account   点赞用户
////     * @return
////     */
////    @RequestMapping({"giveThumbsUp.ws"})
////    @ResponseBody
////    public Object thumbsUp(HttpSession session,HttpServletRequest request,
////                           @RequestParam(value = "id")String id,
////                           @RequestParam(value = "account")String account){
////        twittersDao.addThumbsUp(id,account);
////        return new RestResult(true,"点赞成功");
////    }
////
////    /**
////     * @param session
////     * @param request
////     * @param id        twitter的id
////     * @param account   取消点赞的用户
////     * @return
////     */
////    @RequestMapping({"cancelThumbsUp.ws"})
////    @ResponseBody
////    public Object cancelThumbsUp(HttpSession session,HttpServletRequest request,
////                           @RequestParam(value = "id")String id,
////                           @RequestParam(value = "account")String account){
////        twittersDao.cancelThumbsUp(id,account);
////        return new RestResult(true,"取消点赞成功");
////    }
////    //****************************Delete methods*************************
////    /**通过id来删除twitter的所有内容
////     * @param session
////     * @param request
////     * @param id
////     * @return
////     */
////    @RequestMapping({"deleteTwitter.ws"})
////    @ResponseBody
////    public Object deleteTwitterById(HttpSession session,HttpServletRequest request,
////                                    @RequestParam(value = "id")String id){
////        if(twittersDao.checkTwitterExist(id)){      //避免重复删除而坐判空
////            twittersDao.deleteTwitterById(id);
////            commentsDao.deleteUnreadReply(id);
////            deleteAllComments(id);
////            return new RestResult(true,"说说删除成功");
////        }else{
////            return new RestResult(false,"该说说已删除");
////        }
////    }
////
////    /**递归的删除id下的所有评论
////     * @param id
////     */
////    private void deleteAllComments(String id){
////        List list= (List) commentsDao.queryCommentIdsByPubId(id);//估计会有问题
////        System.out.println(list);
////        if(!list.isEmpty()){
////            for(Object li:list){
////                Map<String,String> map= (Map<String, String>) li;
////                String com_id=map.get("com_id");
////                commentsDao.deleteCommentById(com_id);
////                commentsDao.deleteUnreadReply(com_id);
////                deleteAllComments(com_id);
////            }
////        }
////    }
//
////    @RequestMapping({"getPhotoURL.ws"})
////    @ResponseBody
////    public String getPhotoURL(String url){
////
////    }
//}
