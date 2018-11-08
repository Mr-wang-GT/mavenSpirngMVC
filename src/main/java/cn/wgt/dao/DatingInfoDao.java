package cn.wgt.dao;

//import cn.mofeng.test.CodeTools;
import cn.wgt.bean.DatingInfo;
import cn.wgt.util.Config;
import cn.wgt.util.UtilTools;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.File;

@Repository("datingInfoDao")
public class DatingInfoDao {
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Resource(name = "namedJdbcTemplate")
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    /**
     * @param info
     */
    public void insertDatingInfo(DatingInfo info) {
        String sql = "INSERT INTO personal_info(account,name,location,longitude,altitude,sex,height,birthday,constellatory,hobby,declaration,photo) " +
                "VALUES(:account,:name,:location,:longitude,:altitude,:sex,:height,:birthday,:constellatory,:hobby,:declaration,:photo)";
        namedJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(info));
    }


    /**
     * @param info
     */
    public void modifyInfo(DatingInfo info) {
        String sql="UPDATE personal_info SET name=?,hometown=?,location=?,longitude=?,altitude=?,sex=?,height=?,birthday=?,constellatory=?,"
                +"hobby=?,declaration=?,photo=?" +
                "WHERE account=?";
        jdbcTemplate.update(sql,info.getName(),info.getHometown(),info.getLocation(),info.getLongitude(),info.getAltitude(),info.getSex(),info.getHeight(),info.getBirthday(),info.getConstellatory(),
                info.getHobby(),info.getDeclaration(),info.getPhoto(),info.getAccount());

    }
    public void modifyInfoExceptPhotoUrl(DatingInfo info) {
        String sql="UPDATE personal_info SET name=?,hometown=?,location=?,longitude=?,altitude=?,sex=?,height=?,birthday=?,constellatory=?,"
                +"hobby=?,declaration=? " +
                "WHERE account=?";
        jdbcTemplate.update(sql,info.getName(),info.getHometown(),info.getLocation(),info.getLongitude(),info.getAltitude(),info.getSex(),info.getHeight(),info.getBirthday(),info.getConstellatory(),
                info.getHobby(),info.getDeclaration(),info.getAccount());

    }
    public void updateLonLatLocation(String account, String location,String lon, String lat){
        String sql="UPDATE personal_info SET location=?,longitude=?,altitude=? " +
                "WHERE account=?";
        jdbcTemplate.update(sql,location,lon,lat,account);
    }
    public String queryPhotoUrls(String account){
        String sql="SELECT photo FROM personal_info WHERE account=?";
        return jdbcTemplate.queryForObject(sql,String.class,account);
    }
    public void deletePhoto(String account,String photoUrls){
        String sql="UPDATE personal_info SET photo=?"+
                "WHERE account=?";
        String photos=queryPhotoUrls(account);
        String urls[]=photoUrls.split(",");
        for(int i=0;i<urls.length;i++){
            int index=photos.indexOf(urls[i]);
            photos=UtilTools.getString(urls[i], photos, index);
            //删除本地图片
            File  file=new File("C:\\Program Files\\Tomcat\\webapps\\ROOT\\imgs",urls[i].replace(Config.SERVER_IP+"imgs/",""));
            if(file.delete()){
//                CodeTools.printCodeInfo();
                System.out.println("删除文件成功");
            }
        }
        jdbcTemplate.update(sql,photos,account);



    }

    /**
     * 根据账户查询交友信息   目测这里有毒
     * @param account
     * @return
     */
    public DatingInfo queryDatingInfoByAccount(String account) {                //modified by cn.wgt
//        CodeTools.printCodeInfo();
        String sql = "SELECT * FROM personal_info  WHERE account=?";
        return jdbcTemplate.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(DatingInfo.class),
                account);
    }

    /**
     * 查询用户信息是否存在
     * @param account
     * @return
     */
    public boolean checkUserExist(String account) {
        String sql = "SELECT count(1) FROM personal_info WHERE account=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, account) > 0;
    }

    /**
     * @param account
     * @return  获取交友信息表中的某些项
     */
    public Object queryItemsByAccount(String account){
        String sql="SELECT name,sex,birthday,height,constellatory,photo FROM personal_info " +
                "WHERE account=?";
//        return jdbcTemplate.queryForList(sql,account);
        return jdbcTemplate.queryForMap(sql,account);
    }

    /**
     * @param account
     * @return 用户的位置信息
     */
    public String queryLocationByAccount(String account){
        String sql="SELECT location FROM personal_info WHERE account=?";
        return jdbcTemplate.queryForObject(sql,String.class,account);
    }
    public String queryLongitudeByAccount(String account){
        String sql="SELECT longitude FROM personal_info WHERE account=?";
        return jdbcTemplate.queryForObject(sql,String.class,account);
    }
    public String queryAltitudeByAccount(String account){
        String sql="SELECT altitude FROM personal_info WHERE account=?";
        return jdbcTemplate.queryForObject(sql,String.class,account);
    }
    public Object queryLonLat(String account){
        String sql="SELECT longitude,altitude FROM personal_info WHERE account=?";
        return jdbcTemplate.queryForMap(sql,account);
    }

    public int queryCount(){
        String sql="SELECT NUM_ROWS FROM INNODB_SYS_TABLESTATS where name='compuslove/personal_info'";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }
    public int querySameLocationUserCount(String location){
        String sql="SELECT COUNT(1) FROM personal_info WHERE location=?";
        return jdbcTemplate.queryForObject(sql,Integer.class,location);
    }
    /**
     * @param location
     * @return 随机从数据库中返回10个附近的用户
     */
    public Object queryNearbyUsersByLocation(String location,int m,int n){      //先随机获取五十个同一地区用户，然后在其中进行筛选多少公里以内的
        String sql="SELECT * FROM personal_info WHERE location=?  LIMIT "+m+","+n;
        return jdbcTemplate.queryForList(sql,location);
    }
    public Object queryUserByLocation(String location,int start){
        String sql="SELECT * FROM personal_info WHERE location=?  LIMIT "+start+","+1;
        return jdbcTemplate.queryForMap(sql,location);
    }
}
