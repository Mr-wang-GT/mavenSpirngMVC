package cn.wgt.bean;


import java.sql.Timestamp;

public class Comment {

  private String diaryId;
  private String account;
  private String msg;
  private java.sql.Timestamp time;

  public Comment(String diaryId, String account, String msg, Timestamp time) {
    this.diaryId = diaryId;
    this.account = account;
    this.msg = msg;
    this.time = time;
  }

  public String getDiaryId() {
    return diaryId;
  }

  public void setDiaryId(String diaryId) {
    this.diaryId = diaryId;
  }


  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }


  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }


  public java.sql.Timestamp getTime() {
    return time;
  }

  public void setTime(java.sql.Timestamp time) {
    this.time = time;
  }

}
