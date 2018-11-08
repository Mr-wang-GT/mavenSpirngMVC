package cn.wgt.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("commentsDao")
public class CommentsDao {
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Resource(name = "namedJdbcTemplate")
    private NamedParameterJdbcTemplate namedJdbcTemplate;


    /**插入一条评论
     * @param pubId  待评论内容的id
     * @param comId  本条评论的id
     * @param msg     评论内容
     */
    public void insertComment(String pubId,String comId,String account,String msg ){
        String sql = "INSERT INTO comments(pub_id,com_id,account,msg) " +
                "VALUES(?,?,?,?)";
//        CodeTools.printCodeInfo();
        jdbcTemplate.update(sql,pubId,comId,account,msg);
    }


    /**插入一条回复
     * @param id
     * @param pubAccount
     * @param replyAccount
     * @param msg
     */
    public void insertReply(String id, String pubAccount, String replyAccount, String msg){
        String sql="INSERT INTO reply_msg(id,pub_account,reply_account,msg)" +
                "VALUES(?,?,?,?)";
        jdbcTemplate.update(sql,id,pubAccount, replyAccount,msg);
    }

    /**
     * 由于回复未被用户查看而滞留在表中，当twitter面临删除时此回复也应当被删除
     * @param id
     */
    public void deleteUnreadReply(String id){
        String sql="DELETE FROM reply_msg WHERE id=?";
        jdbcTemplate.update(sql,id);
    }

    /**通过id删除一条评论
     * @param id
     * @return
     */
    public int deleteCommentById(String id){
        String sql="DELETE FROM comments WHERE com_id=?";
        return jdbcTemplate.update(sql,id);
    }
    /**查询是否有对该用户的回复，若有获取之后则删除
     * @param account
     * @return
     */
    public Object queryAndDeleteReply(String account){
        String sql1="SELECT id,reply_account,msg FROM reply_msg WHERE pub_account=?";
        String sql2="DELETE  FROM reply_msg WHERE pub_account=?";
        Object obj=jdbcTemplate.queryForList(sql1,account);
        jdbcTemplate.update(sql2,account);
        return obj;
    }
    /** 根据id判断评论是否存在
     * @param comId
     * @return
     */
    public boolean checkCommentExist(String comId) {
        String sql = "SELECT count(2) FROM comments WHERE com_id=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, comId) > 0;
    }

    /**根据发布内容的id获取所有的评论
     * @param pubId
     * @return
     */
    public Object queryCommentsById(String pubId){
        String sql="SELECT com_id,account,msg FROM comments WHERE pub_id=?";
        return jdbcTemplate.queryForList(sql, pubId);
    }

    /**获取对某一pubId的所有下一层回复的id
     * @param pubId
     * @return
     */
    public Object queryCommentIdsByPubId(String pubId){
        String sql="SELECT com_id FROM comments WHERE pub_id=?";
        return jdbcTemplate.queryForList(sql,pubId);
    }
}
