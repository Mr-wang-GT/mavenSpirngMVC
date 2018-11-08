package cn.wgt.util;

public class Config {
    /**
     * 当前年份除100取整
     */
    public static final int CURRENT_CENTURY=2000;
    /**
     * 可查看好友最近几天的twitters
     */
    public static final int RECENT_TWITTERS=3;
    /**
     * 互相关注时的热度增加量
     */
    public static final int REDU_UP_FOR_MUTUAL_CONCERN=20;
    public static final int REDU_UP_FOR_CHAT_YESTERDAY=5;
    public static final int REDU_UP_FOR_INVITING=5;
    public static final int REDU_DOWN_FOR_NO_CHAT_YESTERDAY=-5;
    public static final int REDU_DOWN_FOR_REFUSE_INVITING=-5;
    public static final int REDU_CLEAR_ZEOR_FOR_CANCEL_CONCERN =0;
    public static final long TOKEN_RETENTION_TIME=10800000;
    public static final long NEARBY_RADIUS=20000;
    public static final int COUNT_OF_EACH_PAGE=20;

    public static final String SERVER_IP = "http://47.106.108.224/";
}
