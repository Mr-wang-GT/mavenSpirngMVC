package cn.wgt.bean;


public class Mood {

  private String account;
  private String moodTitle;
  private String moodFlag;
  private String moodMsg;
  private String moodPicUrl;

  public Mood(String account, String moodTitle, String moodFlag, String moodMsg, String moodPicUrl) {
    this.account = account;
    this.moodTitle = moodTitle;
    this.moodFlag = moodFlag;
    this.moodMsg = moodMsg;
    this.moodPicUrl = moodPicUrl;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }


  public String getMoodTitle() {
    return moodTitle;
  }

  public void setMoodTitle(String moodTitle) {
    this.moodTitle = moodTitle;
  }


  public String getMoodFlag() {
    return moodFlag;
  }

  public void setMoodFlag(String moodFlag) {
    this.moodFlag = moodFlag;
  }


  public String getMoodMsg() {
    return moodMsg;
  }

  public void setMoodMsg(String moodMsg) {
    this.moodMsg = moodMsg;
  }


  public String getMoodPicUrl() {
    return moodPicUrl;
  }

  public void setMoodPicUrl(String moodPicUrl) {
    this.moodPicUrl = moodPicUrl;
  }

}
