package cn.wgt.dao;//package cn.cn.wgt.dao;
//
//import cn.cn.wgt.util.UtilTools;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import javax.annotation.Resource;
//
//@Repository("twittersDao")
//public class TwittersDao {
//    @Resource(name = "jdbcTemplate")
//    private JdbcTemplate jdbcTemplate;
//
//    @Resource(name = "namedJdbcTemplate")
//    private NamedParameterJdbcTemplate namedJdbcTemplate;
//
//    public void insertTwitter(String id,String account,String message,String photo,String location,
//                              String visiters,String reminders){
//        String sql = "INSERT INTO twitters(id,account,message,photo,location,visiters,reminders,thumbs_up) " +
//                "VALUES(?,?,?,?,?,?,?,?)";
////        CodeTools.printCodeInfo();
//        jdbcTemplate.update(sql,id,account,message,photo,location,visiters,reminders,"0,");
//    }
//    public void deleteTwitterById(String id){
//        String sql="DELETE FROM twitters WHERE id=?";
//        jdbcTemplate.update(sql,id);
//    }
//
//    /**点赞后count+1，再将点赞用户添加进去数据库中thumbs-up项中的数据为 count，account1,account2
//     * @param id
//     * @param account
//     */
//    public void addThumbsUp(String id,String account){
//        String sql1="SELECT thumbs_up FROM twitters WHERE id=?";
//        String str=jdbcTemplate.queryForObject(sql1,String.class,id);
//        String[] thumbsUp= str.split(",",2);
//        int count=Integer.valueOf(thumbsUp[0]);
//        String sql2="UPDATE twitters SET thumbs_up=? WHERE id=?";
//        if(thumbsUp[1].equals("")){
//            jdbcTemplate.update(sql2,(count+1)+","+account,id);
//        }else{
//            jdbcTemplate.update(sql2,(count+1)+","+thumbsUp[1]+","+account,id);
//        }
//    }
//    public void cancelThumbsUp(String id,String account){
//        String sql1="SELECT thumbs_up FROM twitters WHERE id=?";
//        String str=jdbcTemplate.queryForObject(sql1,String.class,id);
//        String[] thumbsUp= str.split(",",2);
//        int count=Integer.valueOf(thumbsUp[0]);
//        String sql2="UPDATE twitters SET thumbs_up=? WHERE id=?";
//         int index=thumbsUp[1].indexOf(account);
//        System.out.println(thumbsUp[1]);
//        System.out.println(account);
//        System.out.println(index);
//
//        jdbcTemplate.update(sql2,(count-1)+","+UtilTools.getString(account,thumbsUp[1],index),id);
//    }
//
//    /**通过账户获取说说内容但不包含评论
//     * @param account
//     * @return
//     */
//    public Object queryTwitterByAccount(String account){
//        String sql="SELECT * FROM twitters " +
//                "WHERE account=?";
//        return jdbcTemplate.queryForList(sql,account);
//    }
//
//    public Object queryTwitterById(String id){
//        String sql="SELECT * FROM twitters WHERE id=?";
//        return jdbcTemplate.queryForMap(sql,id);
//    }
//
//
//    public boolean checkTwitterExist(String id) {
//        String sql = "SELECT count(1) FROM twitters WHERE id=?";
//        return jdbcTemplate.queryForObject(sql, Integer.class, id) > 0;
//    }
//
//    /**
//     * @param account
//     * @return 返回twitter的id号
//     */
//    public Object queryIdByAccount(String account){
//        String sql="SELECT id FROM twitters WHERE account=?";
//        return jdbcTemplate.queryForList(sql,account);
//    }
//    //***********************细节功能增加**************************
//
//    /**通过id查询谁可以看列表
//     * @param id
//     * @return
//     */
//    public String queryVisitersById(String id){
//        String sql="SELECT visiters FROM twitters WHERE id=?";
//        return jdbcTemplate.queryForObject(sql,String.class,id);
//    }
//
//    /**更新谁可以看
//     * @param id
//     * @param visiters
//     */
//    public void UpdateVisitersById(String id,String visiters){
//        System.out.println(id+":"+visiters);
//        String sql="UPDATE twitters SET visiters=? WHERE id=?";
//        jdbcTemplate.update(sql,visiters,id);
//    }
//
//    /**查询提醒谁看列表
//     * @param id
//     * @return
//     */
//    public String queryRemindersById(String id){
//        String sql="SELECT reminders FROM twitters WHERE id=?";
//        return jdbcTemplate.queryForObject(sql,String.class,id);
//    }
//
//    /**更新提醒谁看列表
//     * @param id
//     * @param reminders
//     */
//    public void UpdateVRemindersById(String id,String reminders){
//        String sql="UPDATE twitters SET reminders=? WHERE id=?";
//        jdbcTemplate.update(sql,reminders,id);
//    }
//}
