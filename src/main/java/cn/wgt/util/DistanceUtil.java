package cn.wgt.util;


public class DistanceUtil {
    /**
     * 地球半径
     */
    private static double EarthRadius = 6378.137;

    /**
     * 经纬度转化成弧度
     * Add by 成长的小猪（Jason.Song） on 2017/11/01
     * http://blog.csdn.net/jasonsong2008
     *
     * @param d 经度/纬度
     * @return
     */
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 计算两个坐标点之间的距离
     * Add by 成长的小猪（Jason.Song） on 2017/11/01
     * http://blog.csdn.net/jasonsong2008
     *
     * @param startLat   第一个坐标的纬度
     * @param startLon  第一个坐标的经度
     * @param endLat  第二个坐标的纬度
     * @param endLon 第二个坐标的经度
     * @return 返回两点之间的距离，单位：公里/千米
     */
    public static long getDistance( double startLon,double startLat,
                                      double endLon,double endLat) {
        double firstRadLat = rad(startLat);
        double firstRadLng = rad(startLon);
        double secondRadLat = rad(endLat);
        double secondRadLng = rad(endLon);

        double a = firstRadLat - secondRadLat;
        double b = firstRadLng - secondRadLng;
        double cal = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(firstRadLat)
                * Math.cos(secondRadLat) * Math.pow(Math.sin(b / 2), 2))) * EarthRadius;
        double result = Math.round(cal * 10000d) / 10000d;
        return (long)(result*1000);
    }
//    public static long getDistance(String startLonLat, String endLonLat){
//        //返回起始地startAddr与目的地endAddr之间的距离，单位：米
//        Long result = new Long(0);
//        String queryUrl = "http://restapi.amap.com/v3/distance?key=c79946f188c7e87f7c4928b666de66da&origins="+startLonLat+"&destination="+endLonLat;
//        String queryResult = getResponse(queryUrl);
//        JSONObject job = JSONObject.parseObject(queryResult);
//        JSONArray ja = job.getJSONArray("results");
//        JSONObject jobO = JSONObject.parseObject(ja.getString(0));
//        result = Long.parseLong(jobO.get("distance").toString());
//        return result;
//    }
//    private static String getResponse(String serverUrl){
//        //用JAVA发起http请求，并返回json格式的结果
//        StringBuffer result = new StringBuffer();
//        try {
//            URL url = new URL(serverUrl);
//            URLConnection conn = url.openConnection();
//            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String line;
//            while((line = in.readLine()) != null){
//                result.append(line);
//            }
//            in.close();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result.toString();
//    }

}
