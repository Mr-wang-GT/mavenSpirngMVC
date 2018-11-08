package cn.wgt.util;


import java.sql.Timestamp;
import java.util.Date;

public class Time {
    public static String getTime(){
       Date date=new Date();
       return (new Timestamp(date.getTime())).toString().substring(0,19);
    }
}
