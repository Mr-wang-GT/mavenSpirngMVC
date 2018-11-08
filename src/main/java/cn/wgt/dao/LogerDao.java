package cn.wgt.dao;

import cn.wgt.bean.log.Handler;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("logerDao")
public class LogerDao {
    @Resource(name = "namedJdbcTemplate")
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    /**
     * 插入一条记录到数据库中
     *
     * @param handler 新建的Handler信息
     * @return
     */
    public  void  insertInfo(Handler handler) {
        String sql = "INSERT INTO logs(account,operate_time,operate_content)" +
                "VALUES(:account,:operateTime,:operateContent)";
        namedJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(handler));
    }


}
