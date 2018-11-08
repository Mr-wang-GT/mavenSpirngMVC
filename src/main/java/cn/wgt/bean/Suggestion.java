package cn.wgt.bean;


import java.sql.Timestamp;

public class Suggestion {

  private String account;
  private String msg;
  private java.sql.Timestamp subTime;

  public Suggestion(String account, String msg, Timestamp subTime) {
    this.account = account;
    this.msg = msg;
    this.subTime = subTime;
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


  public java.sql.Timestamp getSubTime() {
    return subTime;
  }

  public void setSubTime(java.sql.Timestamp subTime) {
    this.subTime = subTime;
  }

}
