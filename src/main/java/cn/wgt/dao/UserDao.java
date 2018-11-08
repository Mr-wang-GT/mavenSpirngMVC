package cn.wgt.dao;

//import cn.mofeng.test.CodeTools;
import cn.wgt.bean.*;
import cn.wgt.util.UtilTools;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author cn.wgt
 */
@Repository("userDao")
public class UserDao {
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Resource(name = "namedJdbcTemplate")
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    /**  2018/11/04
     * @param account
     */
    public boolean checkUserExist(String account) {
        String sql = "SELECT count(1) FROM user WHERE account=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, account) > 0;
    }
    public boolean checkLoverExist(String account) {
        String sql = "SELECT count(1) FROM lover WHERE account=? OR binded_account=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, account,account) > 0;
    }
    public void insertUser(String account,String sex){
        String sql="INSERT INTO user(account,sex) VALUES (?,?)";
        jdbcTemplate.update(sql,account,sex);
    }
    public Map<String, Object> queryUserInfo(String account){
        String sql="SELECT * FROM user WHERE  account=?";
        return jdbcTemplate.queryForMap(sql,account);
    }
    public String queryLover(String account){
        String sql="SELECT binded_account FROM  lover WHERE account=?";
        String lover= jdbcTemplate.queryForObject(sql,String.class,account);
        if(lover.equals("")){
            sql="SELECT account FROM  lover WHERE binded_account=?";
            lover=jdbcTemplate.queryForObject(sql,String.class,account);
        }
        return lover;
    }
    public String queryUserSex(String account){
        String sql="SELECT sex FROM user WHERE account=?";
        return jdbcTemplate.queryForObject(sql,String.class,account);
    }
    public void modifyNickName(String account,String nickName){
        String sql="UPDATE  user  SET nickname=? WHERE account=?";
        jdbcTemplate.update(sql,nickName,account);
    }
    public void updateMood(String account,String mood,String upset,String girly){
        String sql="UPDATE user SET mood=?,upset=?,girly=? WHERE account=?";
        jdbcTemplate.update(sql,mood,upset,girly,account);
    }
    public  void updateBackground(String account,String photoUrl){
        String sql="UPDATE  user  SET background_url=? WHERE account=?";
        jdbcTemplate.update(sql,photoUrl,account);
    }
    public void deleteLover(String account){
        String sql="DELETE * FROM  lover WHERE account=? or binded_account=? ";
         jdbcTemplate.update(sql,account,account);
    }
    /** 更新头像
     * @param account
     * @param photoUrl
     */
    public  void updateAvatar(String account,String photoUrl){
        String sql="UPDATE  user  SET avatar=? WHERE account=?";
        jdbcTemplate.update(sql,photoUrl,account);
    }
    public void bindLover(Lover lover){
        String sql="INSERT INTO lover(account,binded_account)" +
                "VALUES(:account,:bindedAccount)";
        namedJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(lover));
    }
    public void insertSuggestion(Suggestion suggestion){
        String sql="INSERT INTO suggestion(account, msg, sub_time) " +
                "VALUES (:account,:msg:subTime)";
        namedJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(suggestion));
    }
    public void insertDiary(Diary diary){
        String sql="INSERT INTO diary(id, account, title, msg, photoUrl, pub_time, weather) " +
                "VALUES (:id,:account,:title,:msg,:photoUrl,:pubTime,:weather)";
        namedJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(diary));
    }
    public void insertComment(Comment comment){
        String sql="INSERT INTO comment(com_id, pub_id, com_account, pub_account, msg) " +
                "VALUES (:comId,:pubId,:comAccount,:pubAccount,:msg)";
        namedJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(comment));
    }
    public List<Map<String, Object>> queryDiaries(String account, String loverAccount){
        String sql="SELECT * FROM diary WHERE account=? or account=?" +
                "ORDER BY diary.pub_time ASC";
        return jdbcTemplate.queryForList(sql,account,loverAccount);
    }
    public List<Map<String, Object>> queryCommentsById(String id){
        String sql="SELECT c.*,u.avatar FROM comment AS c JOIN user AS u ON diary_id=? AND c.account=u.account ORDER BY c.time ASC";
        return jdbcTemplate.queryForList(sql,id);
    }
    public void deleteDiaryById(String id){
        String sql="DELETE * FROM diary WHERE id=? ";
        jdbcTemplate.update(sql,id);
    }















    public void modifyLoverRedu(String account,int redu){
        String sql="UPDATE  lover  SET redu=? WHERE account=?";
        jdbcTemplate.update(sql,redu,account);
    }
    public int queryRedu(String account){
        String sql="SELECT redu FROM lover WHERE account=? or binded_account=?";
        return jdbcTemplate.queryForObject(sql,Integer.class,account,account);
    }
    public void insertPersonalInfo(PersonalInfo personalInfo){
        String sql="INSERT INTO personal_info(account,name,hoppy,personal_sig)" +
                "VALUES(:account,:name,:hoppy,:personalSig)";
        namedJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(personalInfo));
    }

    public void modifyPersonalInfo(PersonalInfo personalInfo){
        String sql="UPDATE  personal_info  SET name=?,hoppy=?,personal_sig=? WHERE account=?";
        jdbcTemplate.update(sql,personalInfo.getName(),personalInfo.getHoppy(),personalInfo.getPersonalSig(),personalInfo.getAccount());
    }
    public boolean checkPersonalInfoExist(String account) {
        String sql = "SELECT count(1) FROM personal_info WHERE account=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, account) > 0;
    }
    public boolean checkMoodExist(String account) {
        String sql = "SELECT count(1) FROM mood WHERE account=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, account) > 0;
    }
    public void modifyMoodInfo(Mood mood){
        String sql="UPDATE  mood  SET mood_title=?,mood_flag=?,mood_msg=?,mood_pic_url WHERE account=?";
        jdbcTemplate.update(sql,mood.getMoodPicUrl(),mood.getMoodFlag(),mood.getMoodMsg(),mood.getMoodPicUrl(),mood.getAccount());
    }
    public void insertMoodInofo(Mood mood){
        String sql="INSERT INTO mood(account,mood_title,mood_flag,mood_msg,mood_pic_url)" +
                "VALUES(:account,:moodTitle,:moodFlag,:moodMsg,:moodPicUrl)";
        namedJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(mood));
    }
    public Map<String, Object> queryPersonalInfo(String account){
        String sql="SELECT * FROM personal_info WHERE  account=?";
        return jdbcTemplate.queryForMap(sql,account);
    }
    public Map<String,Object> queryMood(String account){
        String sql="SELECT mood_title,mood_flag,mood_msg,mood_pic_url FROM mood WHERE account=?";
        return jdbcTemplate.queryForMap(sql,account);
    }
    public void insertTask(Task task){
        String sql="INSERT INTO task(account, task_title, task_pic_url, redu, flag) " +
                "VALUES(:account,:taskTitle,:taskPicUrl,:redu,:flag)";
        namedJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(task));
    }
    public List<Task> queryTask(String account){     //先写出来用的时候在调整
        String sql="SELECT * FROM task WHERE account=? AND flag=0";
        return jdbcTemplate.queryForList(sql,Task.class,account);
    }
    public int finishTask(int taskId ){
        String sql="UPDATE  task  SET flag=1 WHERE task_id=?";
        String sql2="SELECT redu FROM task WHERE task_id=?";
        jdbcTemplate.update(sql,taskId);
        return jdbcTemplate.queryForObject(sql2,Integer.class,taskId);
    }
//    public boolean checkReduExist(String accountA_B) {
//        String sql = "SELECT count(1) FROM redu WHERE account=?";
//        return jdbcTemplate.queryForObject(sql, Integer.class, accountA_B) > 0;
//    }



//
//    /**
//     * 新建一个User
//     *
//     * @param user 新建的User信息
//     * @return
//     */
//    public void insertUser(User user) {
//        String sql = "INSERT INTO user(account,password,secret_question_answer) " +
//                "VALUES(:account,:password,:secretQA)";
//        namedJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(user));
//    }




    /**
     * 根据username查找user
     *
     * @param account 查找的用户名
     * @return
     */
    public User queryUserByAccount(String account) {                //modified by cn.wgt
        String sql = "SELECT * FROM user  WHERE account=?";
        return jdbcTemplate.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(User.class),
                account);
    }

//****************************************************************************************


    /**
     * 是否对当前用户设置关注列表
     * @param account
     * @return
     */
    public boolean checkConcernUsersExist(String account) {
        String sql = "SELECT count(1) FROM concern_users WHERE account=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, account) > 0;
    }

    /**是否对当前用户设置了被关注列表
     * @param account
     * @return
     */
    public boolean checkConcernedUsersExist(String account) {
        String sql = "SELECT count(1) FROM concerned_users WHERE account=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, account) > 0;
    }

    /**
     * 创建一个用户的关注记录
     * @param account
     * @param concerningUser
     * @return
     */
    public int insertConcerningUser(String account,String concerningUser){
        String sql="INSERT INTO concern_users(account,users)" +
                "VALUES(?,?)";
        return  jdbcTemplate.update(sql,account,concerningUser);

    }
    /**
     * 创建一个用户的关注记录
     * @param account
     * @param concernedUser  关注当前用户的用户
     * @return
     */
    public int insertConcernedUser(String account,String concernedUser){
        String sql="INSERT INTO concerned_users(account,users)" +
                "VALUES(?,?)";
        return  jdbcTemplate.update(sql,account,concernedUser);

    }
    /**
     * 从关注表中查询某一用户的关注用户
     * @param account
     * @return 以字符串格式返回
     */
    public String queryConcerningUser(String account){
        String sql="SELECT users FROM concern_users WHERE account=?";
        return jdbcTemplate.queryForObject(sql,String.class,account);
    }
    /**
     * 从关注表中查询某一用户的关注用户
     * @param account
     * @return 以字符串格式返回
     */
    public String queryConcernedUser(String account){
        String sql="SELECT users FROM concerned_users WHERE account=?";
        return jdbcTemplate.queryForObject(sql,String.class,account);
    }

    /**添加新的关注用户
     * @param account
     * @param concerningUser
     * @return
     */
    public int addConcerningUser(String account,String concerningUser){
        String sql="UPDATE concern_users SET users=?"+
                "WHERE account=?";
        String users=queryConcerningUser(account)+","+concerningUser;
        return jdbcTemplate.update(sql,users,account);
    }
    /**被新的用户关注
     * @param account  当前用户
     * @param concernedUser    被哪个用户关注的
     * @return
     */
    public int addConcernedUser(String account,String concernedUser){
        String sql="UPDATE concerned_users SET users=?"+
                "WHERE account=?";
        String users=queryConcernedUser(account)+","+concernedUser;
        return jdbcTemplate.update(sql,users,account);
    }


    /**
     * 取消关注，关注列表操作
     * @param account
     * @param concerningUser
     * @return
     */
    public int cancelConcerningUser( String account,String concerningUser){
        String sql="UPDATE concern_users SET users=?"+
                "WHERE account=?";
        String users=queryConcerningUser(account);
        int index=users.indexOf(concerningUser);
        String str;
        str = UtilTools.getString(concerningUser, users, index);
        if(str.equals("")){
            String del_sql="DELETE FROM concern_users WHERE account=?";
            jdbcTemplate.update(del_sql,account);
        }
        return jdbcTemplate.update(sql,str,account);
    }


    /**取消关注，被关注列表处理
     * @param account
     * @param concernedUser
     * @return
     */
    public int cancelConcernedUser( String account,String concernedUser){
        String sql="UPDATE concerned_users SET users=?"+
                "WHERE account=?";
        String users=queryConcernedUser(account);
        int index=users.indexOf(concernedUser);
        String str = UtilTools.getString(concernedUser, users, index);
        if(str.equals("")){
            String del_sql="DELETE FROM concerned_users WHERE account=?";
            jdbcTemplate.update(del_sql,account);
        }
        return jdbcTemplate.update(sql,str,account);
    }

    //****************热度*********************
//    public boolean checkReduExist(String doubleAccounts){
////        System.out.println(doubleAccounts);
//        String sql = "SELECT count(1) FROM redu WHERE double_accounts=?";
//        return jdbcTemplate.queryForObject(sql, Integer.class, doubleAccounts) == 1;
//    }
    public void insertRecordIntoRedu(String doubleAccounts,int degree){
        String sql = "INSERT INTO redu(double_accounts,degrees) " +
                "VALUES(?,?)";
        jdbcTemplate.update(sql,doubleAccounts,degree);
    }
    public int queryForDegrees(String doubleAccounts){
        String sql="SELECT degrees FROM redu WHERE double_accounts=?";
        return jdbcTemplate.queryForObject(sql,Integer.class,doubleAccounts);
    }
    public void updateRedu(String doubleAccounts,int degrees){
//        System.out.println(doubleAccounts+"redu:"+degrees);

        String sql="UPDATE  redu SET degrees=? WHERE double_accounts=?";
        if(degrees==0){
            jdbcTemplate.update(sql,degrees,doubleAccounts);  //热度清零
        }else{
            jdbcTemplate.update(sql,queryForDegrees(doubleAccounts)+degrees,doubleAccounts);
        }
    }
    public void deleteRedu(String doubleAccounts){
        String sql="DELETE FROM redu WHERE double_accounts=?";
        jdbcTemplate.update(sql,doubleAccounts);
    }
    public Object queryAllRecordsFromRedu(){
        String sql="SELECT * FROM redu";
        return jdbcTemplate.queryForList(sql);
    }

    //*****************************dating-process**************
    public Boolean checkDatingProcessExist(String account){
        String sql = "SELECT count(1) FROM dating_process WHERE account=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, account) > 0;
    }
    public void upDateDatingProcessInfo(String account,String redu20,String redu40,String redu60,String redu80){
        String sql="UPDATE  dating_process SET redu20=?,redu40=?,redu60=?,redu80=? WHERE account=?";
        jdbcTemplate.update(sql,redu20,redu40,redu60,redu80,account);
    }
    public void insertDatingProcessInfo(String account,String redu20,String redu40,String redu60,String redu80){
        String sql = "INSERT INTO dating_process(account,redu_20,redu_40,redu_60,redu_80) " +
                "VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql,account,redu20,redu40,redu60,redu80);
    }
    public Object queryDatingProcessInfo(String account){
        String sql = "SELECT redu_20,redu_40,redu_60,redu_80 FROM dating_process  WHERE account=?";
        return jdbcTemplate.queryForMap(sql,account);
    }
}
